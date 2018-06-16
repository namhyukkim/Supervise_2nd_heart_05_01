package com.example.user.supervise_2nd_heart.admin;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.user.supervise_2nd_heart.R;
import com.example.user.supervise_2nd_heart.network.RegisterRequest;
import com.example.user.supervise_2nd_heart.network.SignIn2;

import org.json.JSONObject;

public class AdminRegister extends Fragment {

    private String userID;
    private String userPassword;
    private String userCustomer;
    private String userRepresentative;
    private String usercrn;
    private String userphone;
    private String useremail;
    private String userFax;
    private String userAddress;
    private String userMk;
    private String userAir;
    private String userhour;
    private AlertDialog dialog;
    private boolean validate = false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.admin_register, container, false);
        final EditText idText = (EditText) v.findViewById(R.id.idtext);
        final EditText Password = (EditText) v.findViewById(R.id.userPassword);
        final EditText customer = (EditText) v.findViewById(R.id.userCompany);
        final EditText Representative = (EditText) v.findViewById(R.id.editRepresentation);
        final EditText userCrn = (EditText) v.findViewById(R.id.editCompanyNum);
        final EditText userPhone = (EditText) v.findViewById(R.id.editPhone);
        final EditText userEmail = (EditText) v.findViewById(R.id.editEmail);
        final EditText userfax = (EditText) v.findViewById(R.id.editFax);
        final EditText useradress = (EditText) v.findViewById(R.id.editAddress);
        final EditText usermk = (EditText) v.findViewById(R.id.editmK);
        final EditText userair = (EditText) v.findViewById(R.id.editAir);
        final EditText userHour = (EditText) v.findViewById(R.id.edithour);

        final Button validateButton = (Button) v.findViewById(R.id.validatebutton);
        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userID = idText.getText().toString();
                if (validate) {
                    return;
                }
                if (userID.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    dialog = builder.setMessage("아이디는 빈 칸일 수 없습니다")
                            .setPositiveButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                }
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                dialog = builder.setMessage("사용할 수 있는 아이디입니다")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                                idText.setEnabled(false);
                                validate = true;
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                dialog = builder.setMessage("사용할 수 없는 아이디입니다")
                                        .setNegativeButton("확인", null)
                                        .create();
                                dialog.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                SignIn2 signIn2 = new SignIn2(userID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                queue.add(signIn2);
            }
        });
        Button registerButton = (Button) v.findViewById(R.id.registerbtn);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userID = idText.getText().toString();
                userPassword = Password.getText().toString();
                userCustomer = customer.getText().toString();
                userRepresentative = Representative.getText().toString();
                usercrn = userCrn.getText().toString();
                userphone = userPhone.getText().toString();
                useremail = userEmail.getText().toString();
                userFax = userfax.getText().toString();
                userAddress = useradress.getText().toString();
                userMk = usermk.getText().toString();
                userAir = userair.getText().toString();
                userhour = userHour.getText().toString();

                if (!validate) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    dialog = builder.setMessage("중복체크를 해주세요")
                            .setNegativeButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                }
                if (userID.equals("") || userPassword.equals("")) //아이디와 비밀번호 입력을 안할 시
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    dialog = builder.setMessage("빈칸없이 해주세요")
                            .setNegativeButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                dialog = builder.setMessage("회원등록에 성공했습니다.")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                                Intent intent = new Intent(getContext(), AdminActivity.class);
                                startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                dialog = builder.setMessage("회원등록에 실패 했습니다")
                                        .setNegativeButton("확인", null)
                                        .create();
                                dialog.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(userID, userPassword, userCustomer, userRepresentative, usercrn, userphone, useremail, userFax, userAddress, userMk, userAir, userhour, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                queue.add(registerRequest);

            }
        });

        return v;


    }
}
