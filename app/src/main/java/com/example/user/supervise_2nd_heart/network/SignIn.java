package com.example.user.supervise_2nd_heart.network;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/*
작성자: 김남혁
작성일자: 2018-04-06
해당내용: 로그인시 해당 URL에 파라메터에 ID,Password전송
 */
public class SignIn extends StringRequest{

    final static private String URL = "http://211.115.254.166:8282/Login.php";
    private Map<String, String>paramters;

    public SignIn(String userID, String userPassword, Response.Listener<String> listener) {
        super(Method.POST, URL,listener,null);
        paramters=new HashMap<>();
        paramters.put("userID",userID);
        paramters.put("userPassword", userPassword);
    }

    @Override
    public Map<String,String> getParams() {return paramters;}
}
