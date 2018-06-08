package com.example.user.supervise_2nd_heart.network;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/*
작성자: 김남혁
작성일자: 2018-04-06
해당내용: 로그인시 해당 URL에 파라메터에 해당값 전송
 */
public class RegisterRequest extends StringRequest{

    final static private String URL = "http://211.115.254.166:8282/Register.php";
    private Map<String, String>paramters;

    public RegisterRequest(String userID, String userPassword, String userCustomer, String userRepresentative,
                           String userCrn, String userPhone, String userEmail, String userFax, String useraddress,
                           Response.Listener<String> listener) {
        super(Method.POST, URL,listener,null);
        paramters=new HashMap<>();
        paramters.put("userID",userID);
        paramters.put("userPassword", userPassword);
        paramters.put("userCustomer",userCustomer);
        paramters.put("userRepresentative", userRepresentative);
        paramters.put("userCrn",userCrn);
        paramters.put("userPhone", userPhone);
        paramters.put("userEmail",userEmail);
        paramters.put("userFax", userFax);
        paramters.put("useraddress",useraddress);

    }

    @Override
    public Map<String,String> getParams() {return paramters;}
}
