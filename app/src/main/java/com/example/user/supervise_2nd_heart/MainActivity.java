package com.example.user.supervise_2nd_heart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.user.supervise_2nd_heart.admin.AdminActivity;
import com.example.user.supervise_2nd_heart.admin.admin_Sigin;
import com.example.user.supervise_2nd_heart.network.SignIn;
import com.example.user.supervise_2nd_heart.user.UserMainActivity;
import com.tsengvn.typekit.TypekitContextWrapper;

import org.json.JSONObject;


//--------------------------------------------------------------------------//



//-----------------------------------------------------------------------------------------//



public class MainActivity extends AppCompatActivity {
    //-->기존 Activtiy보다 AppCompatAc가 상위임 바꾼 이유는 http://hashcode.co.kr/questions/1487/ 참고


    private AlertDialog dialog;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //폰트적용
        //Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/yetnal.ttf");
        //IDEdit.setTypeface(typeface);
        //PWEdit.setTypeface(typeface);
        //AdminLoBtn.setTypeface(typeface);
        //UserLoBtn.setTypeface(typeface);

        final EditText IDEdit = (EditText) findViewById(R.id.ID);
        final EditText PWEdit = (EditText) findViewById(R.id.PW);


        final Button AdminLoBtn = (Button) findViewById(R.id.AdminLoginBtn);
        final Button UserLoBtn = (Button) findViewById(R.id.UserLoginBtn);

        UserLoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //------------------------------------------남혁이 수정부분-------------------------------------------//
                String userID = IDEdit.getText().toString();
                String userPassword = PWEdit.getText().toString();

                Response.Listener<String> resPonseLister = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject Jsonresponse = new JSONObject(response);
                            boolean success = Jsonresponse.getBoolean("success");
                            if (success) {
                                Toast.makeText(getApplicationContext(), "환영합니다.", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), UserMainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                dialog = builder.setMessage("로그인에 실패했습니다.")
                                        .setNegativeButton("다시시도", null)
                                        .create();
                                dialog.show();
                            }
                        } catch (Exception e) {

                        }
                    }
                };
                SignIn signIn = new SignIn(userID, userPassword, resPonseLister);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(signIn);
                //----------------------------------------------------------------------------------------------------//

            }
        });
        AdminLoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //------------------------------------------남혁이 수정부분-------------------------------------------//
                String userID = IDEdit.getText().toString();
                String userPassword = PWEdit.getText().toString();

                Response.Listener<String> resPonseLister = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject Jsonresponse = new JSONObject(response);
                            boolean success = Jsonresponse.getBoolean("success");
                            if (success) {
                                Toast.makeText(getApplicationContext(), "환영합니다.", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                dialog = builder.setMessage("로그인에 실패했습니다.")
                                        .setNegativeButton("다시시도", null)
                                        .create();
                                dialog.show();
                            }
                        } catch (Exception e) {

                        }
                    }
                };
                admin_Sigin admin_sigIn = new admin_Sigin(userID, userPassword, resPonseLister);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(admin_sigIn);
//                Intent intent = new Intent(getApplicationContext(),AdminActivity.class);
//                startActivity(intent);


                //----------------------------------------------------------------------------------------------------//
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
}

