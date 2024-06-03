package com.khaphp.gateway_service.util;

import com.khaphp.common.constant.Role;
import com.khaphp.gateway_service.constant.Publich;
import jakarta.ws.rs.HttpMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EndPointHelper {
    public static  Map<String, List<String>> urlPermission(String PATH_SERVICE) {
        Map<String, List<String>> urlAutho = new HashMap<>();
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

        urlAutho.put(HttpMethod.PUT + "-" + PATH_SERVICE + "/seen",
                List.of(Role.ADMIN.name(),
                        Role.EMPLOYEE.name()));

        urlAutho.put(HttpMethod.DELETE + "-" + PATH_SERVICE,
                List.of(Role.ADMIN.name(),
                        Role.EMPLOYEE.name()));

        return urlAutho;
    }
}
