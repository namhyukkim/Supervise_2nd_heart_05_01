package com.example.user.supervise_2nd_heart.user;

import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

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

    private static final String userID = "param1";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER



    // TODO: Rename and change types of parameters
    private String getUserID;

    // private OnFragmentInteractionListener mListener;

    public UserAs() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.

     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserAs newInstance(String param1) {
        UserAs fragment = new UserAs();
        Bundle args = new Bundle();
        args.putString(userID, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            getUserID = getArguments().getString(userID);

        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.user_as,container,false);
        ////////DatePicker 고고고고고고고!!!!!!!!!!!!

        datePicker = (DatePicker)v.findViewById(R.id.datePicker);
        editAsRequest = (EditText)v.findViewById(R.id.editAsRequest);
        btnAsRequest = (Button)v.findViewById(R.id.btnAsRequest);
        TextView idText = (TextView)v.findViewById(R.id.idText);


        datePicker.init(Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH)
                , Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                , new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        asDate =Integer.toString(year)+"년 " + Integer.toString(monthOfYear+1)+"월 " +Integer.toString(dayOfMonth)+"일";
                    }
                });
        idText.setText(getUserID + " 님 환영합니다.");

        btnAsRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comState = editAsRequest.getText().toString();
                asDate =String.format("%d년 %d월 %d일",datePicker.getYear(),datePicker.getMonth()+1,datePicker.getDayOfMonth());





                Log.e("ㅎㅇ", getUserID+" "+comState + " " + asDate + " ");
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
                UserAsRequest userAsRequest = new UserAsRequest(getUserID,comState,asDate,responseListener);
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                queue.add(userAsRequest);
            }
        });

        ////////DatePicker 고고고고고고고!!!!!!!!!!!!

        return v;
    }

}
