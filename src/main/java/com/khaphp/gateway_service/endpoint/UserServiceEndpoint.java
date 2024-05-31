package com.khaphp.gateway_service.endpoint;

import com.khaphp.common.constant.Role;
import com.khaphp.gateway_service.constant.Publich;
import jakarta.ws.rs.HttpMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceEndpoint {
    public static final String PATH_SERVICE_AUTH = "/auth";
    public static final String PATH_SERVICE_USER = "/api/v1/user-system";
    private Map<String, List<String>> urlAutho; //Map<method-url, roles>

    public Map<String, List<String>> urlPermission() {  //lazy initialization
        if(urlAutho == null){
            urlAutho = new HashMap<>();

            //--PATH_SERVICE_AUTH
            urlAutho.put(HttpMethod.POST + "-" + PATH_SERVICE_AUTH + "/login",
                    List.of(Publich.PUBLICH.name()));

            //--PATH_SERVICE_USER
            urlAutho.put(HttpMethod.GET + "-" + PATH_SERVICE_USER,
                    List.of(Role.ADMIN.name(),
                            Role.EMPLOYEE.name()));

            urlAutho.put(HttpMethod.GET + "-" + PATH_SERVICE_USER + "/detail",
                    List.of(Publich.PUBLICH.name()));

            urlAutho.put(HttpMethod.GET + "-" + PATH_SERVICE_USER + "/detail-by-email",
                    List.of(Publich.PUBLICH.name()));

            urlAutho.put(HttpMethod.POST + "-" + PATH_SERVICE_USER + "/customer",
                    List.of(Publich.PUBLICH.name()));

            urlAutho.put(HttpMethod.POST + "-" + PATH_SERVICE_USER + "/employee",
                    List.of(Role.ADMIN.name(),
                            Role.EMPLOYEE.name()));

            urlAutho.put(HttpMethod.POST + "-" + PATH_SERVICE_USER + "/shipper",
                    List.of(Role.ADMIN.name(),
                            Role.EMPLOYEE.name()));

            urlAutho.put(HttpMethod.PUT + "-" + PATH_SERVICE_USER,
                    List.of(Role.ADMIN.name(),
                            Role.EMPLOYEE.name(),
                            Role.SHIPPER.name(),
                            Role.CUSTOMER.name()));

            urlAutho.put(HttpMethod.PUT + "-" + PATH_SERVICE_USER + "/change-pwd",
                    List.of(Role.ADMIN.name(),
                            Role.EMPLOYEE.name(),
                            Role.SHIPPER.name(),
                            Role.CUSTOMER.name()));

            urlAutho.put(HttpMethod.PUT + "-" + PATH_SERVICE_USER + "/new-pwd",
                    List.of(Role.ADMIN.name(),
                            Role.EMPLOYEE.name(),
                            Role.SHIPPER.name(),
                            Role.CUSTOMER.name()));

            urlAutho.put(HttpMethod.PUT + "-" + PATH_SERVICE_USER + "/status",
                    List.of(Role.ADMIN.name(),
                            Role.EMPLOYEE.name()));

            urlAutho.put(HttpMethod.PUT + "-" + PATH_SERVICE_USER + "/email",
                    List.of(Role.ADMIN.name(),
                            Role.EMPLOYEE.name(),
                            Role.SHIPPER.name(),
                            Role.CUSTOMER.name()));

            urlAutho.put(HttpMethod.PUT + "-" + PATH_SERVICE_USER + "/img",
                    List.of(Role.ADMIN.name(),
                            Role.EMPLOYEE.name(),
                            Role.SHIPPER.name(),
                            Role.CUSTOMER.name()));

            urlAutho.put(HttpMethod.DELETE + "-" + PATH_SERVICE_USER,
                    List.of(Role.ADMIN.name(),
                            Role.EMPLOYEE.name()));
        }
        return urlAutho;
    }
}
