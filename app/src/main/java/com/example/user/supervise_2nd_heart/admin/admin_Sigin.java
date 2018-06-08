package com.example.user.supervise_2nd_heart.admin;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class admin_Sigin extends StringRequest {

    final static private String URL = "http://211.115.254.166:8282/AdminLogin.php";
    private Map<String, String> paramters;

    public admin_Sigin(String userID, String userPassword, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);
        paramters = new HashMap<>();
        paramters.put("userID", userID);
        paramters.put("userPassword", userPassword);
    }

    @Override
    public Map<String, String> getParams() {
        return paramters;
    }
}
