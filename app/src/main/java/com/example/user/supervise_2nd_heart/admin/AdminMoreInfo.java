package com.example.user.supervise_2nd_heart.admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;


import com.example.user.supervise_2nd_heart.R;

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


public class AdminMoreInfo extends AppCompatActivity {

    private static String TAG = "phptest_MainActivity";

    private static final String TAG_JSON = "webnautes";
    private static final String TAG_ID = "userID";
    private static final String TAG_PASSWORD = "userPassword";
    private static final String TAG_CUSTOMER = "userCustomer";
    private static final String TAG_REPRESENTATIV = "userRepresentative";
    private static final String TAG_CRN = "userCrn";
    private static final String TAG_PHONE = "userPhone";
    private static final String TAG_EMAIL = "userEmail";
    private static final String TAG_FAX = "userFax";
    private static final String TAG_ADDRESS = "useraddress";

    ArrayList<HashMap<String, String>> mArrayList;
    ListView mlistView;
    String mJsonString;
    String data, data1;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_customer_row_listview);

        mlistView = (ListView) findViewById(R.id.listView_main_list);
        mArrayList = new ArrayList<>();
        intent = getIntent();
        data = intent.getStringExtra("info");
        data1 =intent.getStringExtra("info1");
        Log.v("AdminMoreInfo", "입력받은데이터:" + data);


        GetData task = new GetData();
        task.execute(data);

    }


    private class GetData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(AdminMoreInfo.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);
            mJsonString = result;
            Log.v("AdminMoreInfo", "입력받은데이터:" + mJsonString);
            showResult();

        }


        @Override
        protected String doInBackground(String... params) {

            String searchKeyword = params[0];
            String serverURL = "http://211.115.254.166:8282/Information.php";
            String postParameters = "customer=" + searchKeyword;
            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

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
                    sb.append(line);
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


    private void showResult() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject item = jsonArray.getJSONObject(i);

                String id = item.getString(TAG_ID);
                String password = item.getString(TAG_PASSWORD);
                String customer = item.getString(TAG_CUSTOMER);
                String representative = item.getString(TAG_REPRESENTATIV);
                String crn = item.getString(TAG_CRN);
                String phone = item.getString(TAG_PHONE);
                String email = item.getString(TAG_EMAIL);
                String Fax = item.getString(TAG_FAX);
                String Address = item.getString(TAG_ADDRESS);

                HashMap<String, String> hashMap = new HashMap<>();

                hashMap.put(TAG_ID, id);
                hashMap.put(TAG_PASSWORD, password);
                hashMap.put(TAG_CUSTOMER, customer);
                hashMap.put(TAG_REPRESENTATIV, representative);
                hashMap.put(TAG_CRN, crn);
                hashMap.put(TAG_PHONE, phone);
                hashMap.put(TAG_EMAIL, email);
                hashMap.put(TAG_FAX, Fax);
                hashMap.put(TAG_ADDRESS, Address);


                mArrayList.add(hashMap);
            }

            ListAdapter adapter = new SimpleAdapter(
                    AdminMoreInfo.this, mArrayList, R.layout.admin_customer_detail,
                    new String[]{TAG_ID, TAG_PASSWORD, TAG_CUSTOMER, TAG_REPRESENTATIV, TAG_CRN, TAG_PHONE, TAG_EMAIL, TAG_FAX, TAG_ADDRESS},
                    new int[]{R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6, R.id.text7, R.id.text8, R.id.text9}
            );

            mlistView.setAdapter(adapter);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }
    public void test(){

    }

}
