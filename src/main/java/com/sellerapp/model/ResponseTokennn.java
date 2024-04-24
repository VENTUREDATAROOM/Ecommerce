package com.sellerapp.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseTokennn 
{
public static ResponseEntity<Object> generateResponse(JwtWithUserCodeResponse r, HttpStatus status, String customcode) {
    Map<String, Object> map = new HashMap<String, Object>();
        map.put("response", r);
        map.put("status", status.value());
        map.put("customcode", customcode);
        HttpHeaders headers2 = new HttpHeaders();
       	ResponseEntity<Object> resp=  new ResponseEntity<Object>(map, headers2, status.value());
        return resp;
    }

}