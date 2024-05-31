package com.khaphp.gateway_service.endpoint;

import com.khaphp.common.constant.Role;
import com.khaphp.gateway_service.constant.Publich;
import jakarta.ws.rs.HttpMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodServiceEndpoint {
    public static final String PATH_SERVICE_FOOD = "/api/v1/food";
    public static final String PATH_SERVICE_FOOD_ENCYCLOPEDIA = "/api/v1/food-encyclopedia";
    private Map<String, List<String>> urlAutho; //Map<method-url, roles>

    public Map<String, List<String>> urlPermission() {  //lazy initialization
        if(urlAutho == null){
            urlAutho = new HashMap<>();
            for(String path : List.of(PATH_SERVICE_FOOD, PATH_SERVICE_FOOD_ENCYCLOPEDIA)){
                urlAutho.put(HttpMethod.GET + "-" + path,
                        List.of(Publich.PUBLICH.name()));

                urlAutho.put(HttpMethod.GET + "-" + path + "/detail",
                        List.of(Publich.PUBLICH.name()));

                urlAutho.put(HttpMethod.POST + "-" + path,
                        List.of(Role.ADMIN.name(),
                                Role.EMPLOYEE.name()));

                urlAutho.put(HttpMethod.PUT + "-" + path,
                        List.of(Role.ADMIN.name(),
                                Role.EMPLOYEE.name()));

                urlAutho.put(HttpMethod.PUT + "-" + path + "/img",
                        List.of(Role.ADMIN.name(),
                                Role.EMPLOYEE.name()));

                urlAutho.put(HttpMethod.DELETE + "-" + path,
                        List.of(Role.ADMIN.name(), Role.EMPLOYEE.name()));
            }
        }
        return urlAutho;
    }
}
