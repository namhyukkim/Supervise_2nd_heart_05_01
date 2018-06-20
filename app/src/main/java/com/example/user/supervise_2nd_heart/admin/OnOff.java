package com.example.user.supervise_2nd_heart.admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.supervise_2nd_heart.R;
import com.example.user.supervise_2nd_heart.user.UserListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class OnOff extends AppCompatActivity {
    private static String TAG = "phptest_MainActivity";
    TextView textView1, textView2;
    String data;
    Intent intent;

    ArrayList<HashMap<String, String>> mArrayList;
    String mJsonString;
    Button button1, button2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onoff);
        intent = getIntent();
        data = intent.getStringExtra("info");
        Log.v("AdminMoreInfo", "입력받은데이터:" + data);
        button1 = (Button) findViewById(R.id.tbtnon);
        button2 = (Button) findViewById(R.id.tbtnoff);

        textView2 = (TextView) findViewById(R.id.userMk_com);
        textView2.setText(data);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Toast.makeText(OnOff.this, "연결되어있지않습니다", Toast.LENGTH_SHORT).show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OnOff.this, "연결되어있지않습니다.", Toast.LENGTH_SHORT).show();
            }
        });


        if (textView2.getText().equals("GRH3200A")) {

            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GetData2 task = new GetData2();
                    task.execute("http://211.115.254.166:7815/?2");
                    Toast.makeText(OnOff.this, "전원 ON", Toast.LENGTH_SHORT).show();
                }
            });

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GetData2 task = new GetData2();
                    task.execute("http://211.115.254.166:7815/?1");
                    Toast.makeText(OnOff.this, "전원 OFF", Toast.LENGTH_SHORT).show();
                }
            });

        }


    }

    // 전체회원을 불러오는 내부 클래스
    private class GetData2 extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(OnOff.this,
                    "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();

            Log.d(TAG, "response  - " + result);

            mJsonString = result;

        }

        // php문
        @Override
        protected String doInBackground(String... params) {

//            String searchKeyword = params[0];
            String serverURL = params[0];

//            String postParameters = "customer=" + searchKeyword;

            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

//                OutputStream outputStream = httpURLConnection.getOutputStream();
////////                outputStream.write(postParameters.getBytes("UTF-8"));
//////                outputStream.flush();
//////                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line+"\n");
                }
                bufferedReader.close();
                return sb.toString().trim();
            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);
                errorString = e.toString();
                return null;
            }
        }
    }

}
