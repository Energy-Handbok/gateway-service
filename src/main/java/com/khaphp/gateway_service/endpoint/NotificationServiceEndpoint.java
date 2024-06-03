package com.khaphp.gateway_service.endpoint;

import com.khaphp.common.constant.Role;
import com.khaphp.gateway_service.constant.Publich;
import com.khaphp.gateway_service.util.EndPointHelper;
import jakarta.ws.rs.HttpMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationServiceEndpoint {
    public static final String PATH_SERVICE = "/api/v1/notification";
    private Map<String, List<String>> urlAutho; //Map<method-url, roles>

    public Map<String, List<String>> urlPermission() {  //lazy initialization
        if(urlAutho == null){
            urlAutho = EndPointHelper.urlPermission(PATH_SERVICE);
        }
        return urlAutho;
    }
}
