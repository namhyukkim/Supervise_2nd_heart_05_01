package com.example.user.supervise_2nd_heart.network;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class UserAsRequest extends StringRequest {
    final static private String URL = "http://211.115.254.166:8282/UserAsRequest.php";
    private Map<String,String>paramters;

    public UserAsRequest(String comState,String asDate,Response.Listener<String> listener){
        super(Method.POST, URL,listener,null);
        paramters = new HashMap<>();
        paramters.put("comState", comState);
        paramters.put("asDate", asDate);
    }
    @Override
    public Map<String,String>getParams(){return paramters;}
}