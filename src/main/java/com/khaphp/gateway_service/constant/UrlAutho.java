package com.khaphp.gateway_service.constant;

import com.khaphp.common.constant.Role;
import com.khaphp.gateway_service.endpoint.*;
import jakarta.ws.rs.HttpMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UrlAutho {
    private Map<String, List<String>> urlAuthorization; //Map<method-url, roles>

    public Map<String, List<String>> urlPermission() {  //lazy initialization
        if(urlAuthorization == null){
            urlAuthorization = new HashMap<>();

            NewsServiceEndpoint newsServiceEndpoint = new NewsServiceEndpoint();
            urlAuthorization.putAll(newsServiceEndpoint.urlPermission());

            FoodRecipeServiceEndpoint foodRecipeServiceEndpoint = new FoodRecipeServiceEndpoint();
            urlAuthorization.putAll(foodRecipeServiceEndpoint.urlPermission());

            FoodServiceEndpoint foodServiceEndpoint = new FoodServiceEndpoint();
            urlAuthorization.putAll(foodServiceEndpoint.urlPermission());

            InteractServiceEndpoint interactServiceEndpoint = new InteractServiceEndpoint();
            urlAuthorization.putAll(interactServiceEndpoint.urlPermission());

            NotificationServiceEndpoint notificationServiceEndpoint = new NotificationServiceEndpoint();
            urlAuthorization.putAll(notificationServiceEndpoint.urlPermission());

            OrderServiceEndpoint orderServiceEndpoint = new OrderServiceEndpoint();
            urlAuthorization.putAll(orderServiceEndpoint.urlPermission());

            PaymentServiceEndpoint paymentServiceEndpoint = new PaymentServiceEndpoint();
            urlAuthorization.putAll(paymentServiceEndpoint.urlPermission());

            UserServiceEndpoint userServiceEndpoint = new UserServiceEndpoint();
            urlAuthorization.putAll(userServiceEndpoint.urlPermission());
        }
        return urlAuthorization;
    }

    public static void main(String[] args) {
        //get list url_permission (Map< httpMethod-urlPathNoHostAndParams , List<role> >, vd: Map < POST-/api/v1/news , { ADMIN, EMPLOYEE } )
        UrlAutho urlAutho = new UrlAutho();
        Map<String, List<String>> listMap = urlAutho.urlPermission();
        System.out.println(listMap);

        //get info of url to check autho (url, http method, role)
        String url = "http://localhost:8080/api/v1/news?pageSize=10&pageIndex=1";   //get from request
        String gateway_domain = "localhost:8080";   //get from header with key "Host"
        String urlPathNoHostAndParams = url.substring(url.indexOf(gateway_domain) + gateway_domain.length(), url.indexOf("?"));
        System.out.println(urlPathNoHostAndParams);
        String urlRole = Role.CUSTOMER.name();  //decode token to get role
        String httpMethodUrl = HttpMethod.GET;  //get from request

        //check authorization of url base url_permission
        if(listMap.get(httpMethodUrl+"-"+urlPathNoHostAndParams) != null) {
            if(listMap.get(httpMethodUrl+"-"+urlPathNoHostAndParams).contains(urlRole)){
                System.out.println("true, accept request");
            }else{
                System.out.println("Unauthorized, you are not allow to access this resource");
            }
        }else{
            System.out.println("not found resource");
        }
    }
}
