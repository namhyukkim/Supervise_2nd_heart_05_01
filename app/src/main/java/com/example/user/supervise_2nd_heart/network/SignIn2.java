package com.example.user.supervise_2nd_heart.network;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SignIn2 extends StringRequest{

    final static private String URL = "http://211.115.254.166:8282/UserValidate.php";
    private Map<String, String> paramters;

    public SignIn2(String userID,Response.Listener<String> listener) {
        super(Method.POST, URL,listener,null);
        paramters=new HashMap<>();
        paramters.put("userID",userID);

    }
    @Override
    public Map<String,String> getParams() {return paramters;}
}
