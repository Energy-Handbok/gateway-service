package com.khaphp.gateway_service.service;

import com.khaphp.gateway_service.Exception.ForbiddenException;
import com.khaphp.gateway_service.Exception.TokenException;
import com.khaphp.gateway_service.Exception.UnAuthorizedException;
import com.khaphp.gateway_service.constant.Publich;
import com.khaphp.gateway_service.constant.UrlAutho;
import com.khaphp.gateway_service.util.JwtHelper;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    @Value("${security.secret_key}")
    private String secretKey;
    private final JwtHelper jwtHelper;
    private final UrlAutho urlAutho;

    public static class Config{
        //nothing here
    }
    public AuthFilter(JwtHelper jwtHelper, UrlAutho urlAutho){
        super(Config.class);
        this.jwtHelper = jwtHelper;
        this.urlAutho = urlAutho;
    }

    public static void main(String[] args) {
        String url = "http://localhost:8080/auth/login?hahah";
        String hostGateway = "localhost:8080";
        System.out.println(url.substring(url.indexOf(hostGateway) + hostGateway.length(), url.contains("?") ? url.indexOf("?"): url.length()));
    }

    @Override
    public GatewayFilter apply(AuthFilter.Config config) {
        return ((exchange, chain) -> {
            //get info url to check
            String url = exchange.getRequest().getURI().toString();
            log.info("url: " + url);  //http://localhost:8080/auth/login

            String hostGateway = exchange.getRequest().getHeaders().get("Host").get(0);
            log.info("host gateway: " + hostGateway);

            String urlPathNoHostAndParams = url.substring(url.indexOf(hostGateway) + hostGateway.length(), url.contains("?") ? url.indexOf("?"): url.length());
            log.info("urlPathNoHostAndParams: " + urlPathNoHostAndParams);

            String method = exchange.getRequest().getMethod().toString();
            log.info("http method: " + method);

            //get url_permission
            Map<String, List<String>> urlPermission = urlAutho.urlPermission();

            //check autho of url base url_permission (decode token to get role to check autho)
                //check resource not found
            if(!urlPermission.containsKey(method + "-" + urlPathNoHostAndParams)){
                throw new NotFoundException("Not found resource");
            }
                //check public resource (api)
            if(urlPermission.get(method + "-" + urlPathNoHostAndParams).contains(Publich.PUBLICH.name())){
                log.info("true, accept request to PUBLICH resource");
                return chain.filter(exchange);
            }else{
                //get token, decode get claims to get roles to autho
                //check header authorization
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new UnAuthorizedException("Unauthorized, Authorization header not found");
                }

                //take token and check it (valid, expired, role)
                String token = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                String[] arr = token.split(" ");
                    //valid
                if(arr.length != 2 || !arr[0].equals("Bearer")){
                    throw new TokenException("Token is not valid");
                }
                    //expired
                if(jwtHelper.isTokenExpired(arr[1])){
                    throw new TokenException("Token is expired");
                }
                    //role
                if(!urlPermission.get(method + "-" + urlPathNoHostAndParams).contains(jwtHelper.getRoles(arr[1]))){
                    throw new ForbiddenException("Forbidden, you are not allow to access this resource");
                }

                log.info("true, accept request to resource " + urlPathNoHostAndParams + " ("+method+") with role " + jwtHelper.getRoles(arr[1]));
            }
            return chain.filter(exchange);
        });
    }
}
