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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class OnOff extends AppCompatActivity {
    private static String TAG = "phptest_MainActivity";
    TextView textView1,textView2;
    String data;
    Intent intent;

    ArrayList<HashMap<String, String>> mArrayList;
    String mJsonString;
    Button button1,button2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onoff);
        intent = getIntent();
        data = intent.getStringExtra("data");
        Log.v("AdminMoreInfo", "입력받은데이터:" + data);
        button1 = (Button)findViewById(R.id.tbtnon);
        button2 = (Button)findViewById(R.id.tbtnoff);

        textView2 =(TextView)findViewById(R.id.userMk_com);
        textView2.setText(data);


        if (textView2.getText().equals("GRH3200A")){

            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendData("http://211.115.254.166:7815/?2");
                    Toast.makeText(OnOff.this,"전원 ON",Toast.LENGTH_SHORT).show();
                }
            });

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendData("http://211.115.254.166:7815/?1");
                    Toast.makeText(OnOff.this,"전원 OFF",Toast.LENGTH_SHORT).show();
                }
            });

        }



    }


    private static String sendData(String addr){

        StringBuilder html = new StringBuilder();
        try{
            URL url = new URL(addr);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            if(conn != null){
                conn.setConnectTimeout(10000);
                conn.setUseCaches(false);
                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));
                    for(;;){
                        String line = br.readLine();
                        if(line == null)break;
                        html.append(line );
                        html.append('\n');
                    }
                    br.close();
                }
                conn.disconnect();
            }
        }
        catch(Exception ex){;}

        return html.toString();
    }


}
