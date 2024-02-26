package com.sellerapp.util;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class RequestUtil {

    public String getRemoteIP(RequestAttributes requestAttributes) {
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            return request.getRemoteAddr();
        }
        return null;
    }
}

	

