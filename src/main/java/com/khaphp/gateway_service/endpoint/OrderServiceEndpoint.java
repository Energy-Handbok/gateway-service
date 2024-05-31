package com.khaphp.gateway_service.endpoint;

import com.khaphp.common.constant.Role;
import com.khaphp.gateway_service.constant.Publich;
import jakarta.ws.rs.HttpMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderServiceEndpoint {
    public static final String PATH_SERVICE_ORDER = "/api/v1/order";
    public static final String API_PAYMENT = "api/payment";
    private Map<String, List<String>> urlAutho; //Map<method-url, roles>

    public Map<String, List<String>> urlPermission() {  //lazy initialization
        if(urlAutho == null){
            urlAutho = new HashMap<>();
            //--PATH_SERVICE_ORDER
            urlAutho.put(HttpMethod.GET + "-" + PATH_SERVICE_ORDER,
                    List.of(Role.CUSTOMER.name(),
                            Role.SHIPPER.name(),
                            Role.ADMIN.name(),
                            Role.EMPLOYEE.name()));

            urlAutho.put(HttpMethod.GET + "-" + PATH_SERVICE_ORDER + "/detail",
                    List.of(Role.CUSTOMER.name(),
                            Role.SHIPPER.name(),
                            Role.ADMIN.name(),
                            Role.EMPLOYEE.name()));

            urlAutho.put(HttpMethod.POST + "-" + PATH_SERVICE_ORDER,
                    List.of(Publich.PUBLICH.name()));

            urlAutho.put(HttpMethod.POST + "-" + PATH_SERVICE_ORDER + "/third-party",
                    List.of(Publich.PUBLICH.name()));

            urlAutho.put(HttpMethod.PUT + "-" + PATH_SERVICE_ORDER + "/status",
                    List.of(Role.SHIPPER.name(),
                            Role.ADMIN.name(),
                            Role.EMPLOYEE.name()));

            urlAutho.put(HttpMethod.PUT + "-" + PATH_SERVICE_ORDER + "/shipper-take",
                    List.of(Role.SHIPPER.name(),
                            Role.ADMIN.name(),
                            Role.EMPLOYEE.name()));

            urlAutho.put(HttpMethod.PUT + "-" + PATH_SERVICE_ORDER + "/finish-delivery",
                    List.of(Role.SHIPPER.name(),
                            Role.ADMIN.name(),
                            Role.EMPLOYEE.name()));

            urlAutho.put(HttpMethod.PUT + "-" + PATH_SERVICE_ORDER + "/cancel",
                    List.of(Role.SHIPPER.name(),
                            Role.ADMIN.name(),
                            Role.EMPLOYEE.name()));

            urlAutho.put(HttpMethod.DELETE + "-" + PATH_SERVICE_ORDER,
                    List.of(Role.ADMIN.name(),
                            Role.EMPLOYEE.name()));

            //--API_PAYMENT
            urlAutho.put(HttpMethod.GET + "-" + API_PAYMENT,
                    List.of(Role.CUSTOMER.name()));

            urlAutho.put(HttpMethod.GET + "-" + API_PAYMENT+ "/payment_result",
                    List.of(Publich.PUBLICH.name()));
        }
        return urlAutho;
    }
}
