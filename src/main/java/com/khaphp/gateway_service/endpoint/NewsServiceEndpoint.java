package com.khaphp.gateway_service.endpoint;

import com.khaphp.common.constant.Role;
import com.khaphp.gateway_service.constant.Publich;
import jakarta.ws.rs.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsServiceEndpoint {
    public static final String PATH_SERVICE = "/api/v1/news";
    private Map<String, List<String>> urlAutho; //Map<method-url, roles>

    public Map<String, List<String>> urlPermission() {  //lazy initialization
        if(urlAutho == null){
            urlAutho = new HashMap<>();
            urlAutho.put(HttpMethod.GET + "-" + PATH_SERVICE,
                    List.of(Publich.PUBLICH.name()));

            urlAutho.put(HttpMethod.GET + "-" + PATH_SERVICE + "/detail",
                    List.of(Publich.PUBLICH.name()));

            urlAutho.put(HttpMethod.POST + "-" + PATH_SERVICE,
                    List.of(Role.ADMIN.name(),
                            Role.EMPLOYEE.name()));

            urlAutho.put(HttpMethod.PUT + "-" + PATH_SERVICE,
                    List.of(Role.ADMIN.name(),
                            Role.EMPLOYEE.name()));

            urlAutho.put(HttpMethod.PUT + "-" + PATH_SERVICE + "/img",
                    List.of(Role.ADMIN.name(),
                            Role.EMPLOYEE.name()));

            urlAutho.put(HttpMethod.DELETE + "-" + PATH_SERVICE,
                    List.of(Role.ADMIN.name(),
                            Role.EMPLOYEE.name()));
        }
        return urlAutho;
    }
}
