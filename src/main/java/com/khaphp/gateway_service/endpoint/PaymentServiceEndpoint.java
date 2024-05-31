package com.khaphp.gateway_service.endpoint;

import com.khaphp.common.constant.Role;
import com.khaphp.gateway_service.constant.Publich;
import jakarta.ws.rs.HttpMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentServiceEndpoint {
    public static final String PATH_SERVICE_WALLET = "/api/v1/wallet";
    public static final String PATH_SERVICE_WALLET_TRANSACTION = "/api/v1/wallet-transaction";
    private Map<String, List<String>> urlAutho; //Map<method-url, roles>

    public Map<String, List<String>> urlPermission() {  //lazy initialization
        if(urlAutho == null){
            urlAutho = new HashMap<>();
            //--PATH_SERVICE_WALLET
            urlAutho.put(HttpMethod.GET + "-" + PATH_SERVICE_WALLET + "/detail",
                    List.of(Role.CUSTOMER.name()));
            //CÒN 3 ENDPOINT NỮA MÀ NÓ CHỈ DÙNG TRONG VC TEST + CÁC SERVICE GỌI NHAU THÔI

            //--PATH_SERVICE_WALLET_TRANSACTION
            urlAutho.put(HttpMethod.GET + "-" + PATH_SERVICE_WALLET_TRANSACTION,
                    List.of(Role.CUSTOMER.name(),
                            Role.EMPLOYEE.name()));

            urlAutho.put(HttpMethod.GET + "-" + PATH_SERVICE_WALLET_TRANSACTION + "/detail",
                    List.of(Role.CUSTOMER.name(),
                            Role.EMPLOYEE.name()));

            urlAutho.put(HttpMethod.POST + "-" + PATH_SERVICE_WALLET_TRANSACTION,
                    List.of(Role.ADMIN.name(),
                            Role.EMPLOYEE.name()));

            urlAutho.put(HttpMethod.DELETE + "-" + PATH_SERVICE_WALLET_TRANSACTION,
                    List.of(Role.ADMIN.name(),
                            Role.EMPLOYEE.name()));

        }
        return urlAutho;
    }
}
