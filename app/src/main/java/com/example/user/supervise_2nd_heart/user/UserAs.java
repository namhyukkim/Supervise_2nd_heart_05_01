package com.example.user.supervise_2nd_heart.user;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.user.supervise_2nd_heart.R;
import com.example.user.supervise_2nd_heart.network.UserAsRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class UserAs extends Fragment {
    DatePicker datePicker;
    EditText editAsRequest;
    Button btnAsRequest;
    String userCustomer;
    String comState;
    String asDate;
    AlertDialog dialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.user_as,container,false);
        ////////DatePicker 고고고고고고고!!!!!!!!!!!!

        datePicker = (DatePicker)v.findViewById(R.id.datePicker);
        editAsRequest = (EditText)v.findViewById(R.id.editAsRequest);
        btnAsRequest = (Button)v.findViewById(R.id.btnAsRequest);


        datePicker.init(Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH)
                , Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                , new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        asDate =Integer.toString(year)+"년 " + Integer.toString(monthOfYear+1)+"월 " +Integer.toString(dayOfMonth)+"일";
                    }
                });

        btnAsRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comState = editAsRequest.getText().toString();
                Log.e("ㅎㅇ", comState + " " + asDate + " ");
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success)
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                dialog = builder.setMessage("구우우웃~")
                                        .setPositiveButton("ㅎㅇ", null)
                                        .create();
                                dialog.show();
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                dialog = builder.setMessage("실패염")
                                        .setPositiveButton("ㅜㅜ", null)
                                        .create();
                                dialog.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                UserAsRequest userAsRequest = new UserAsRequest(comState,asDate,responseListener);
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                queue.add(userAsRequest);
            }
        });

        ////////DatePicker 고고고고고고고!!!!!!!!!!!!

        return v;
    }

}
