package com.khaphp.gateway_service.endpoint;

import com.khaphp.common.constant.Role;
import com.khaphp.gateway_service.constant.Publich;
import jakarta.ws.rs.HttpMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InteractServiceEndpoint {
    public static final String PATH_SERVICE_COMMENT = "/api/v1/comment";
    public static final String PATH_SERVICE_INTERACT = "/api/v1/interact";
    private Map<String, List<String>> urlAutho; //Map<method-url, roles>

    public Map<String, List<String>> urlPermission() {  //lazy initialization
        if(urlAutho == null){
            urlAutho = new HashMap<>();
            //--PATH_SERVICE_COMMENT
            urlAutho.put(HttpMethod.POST + "-" + PATH_SERVICE_COMMENT,
                    List.of(Role.CUSTOMER.name(),
                            Role.SHIPPER.name(),
                            Role.ADMIN.name(),
                            Role.EMPLOYEE.name()));

            urlAutho.put(HttpMethod.DELETE + "-" + PATH_SERVICE_COMMENT,
                    List.of(Role.CUSTOMER.name(),
                            Role.SHIPPER.name(),
                            Role.ADMIN.name(),
                            Role.EMPLOYEE.name()));

            //--PATH_SERVICE_INTERACT
            urlAutho.put(HttpMethod.GET + "-" + PATH_SERVICE_INTERACT,
                    List.of(Publich.PUBLICH.name()));

            urlAutho.put(HttpMethod.GET + "-" + PATH_SERVICE_INTERACT + "/child-comment",
                    List.of(Publich.PUBLICH.name()));

            urlAutho.put(HttpMethod.POST + "-" + PATH_SERVICE_INTERACT,
                    List.of(Role.CUSTOMER.name(),
                            Role.SHIPPER.name(),
                            Role.ADMIN.name(),
                            Role.EMPLOYEE.name()));

            urlAutho.put(HttpMethod.DELETE + "-" + PATH_SERVICE_INTERACT,
                    List.of(Role.CUSTOMER.name(),
                            Role.SHIPPER.name(),
                            Role.ADMIN.name(),
                            Role.EMPLOYEE.name()));
        }
        return urlAutho;
    }
}
