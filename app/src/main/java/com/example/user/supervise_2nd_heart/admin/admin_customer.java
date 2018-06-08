package com.example.user.supervise_2nd_heart.admin;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public class admin_customer extends Fragment {
    private static String TAG = "phptest_MainActivity";

    private static final String TAG_JSON="webnautes";
    private static final String TAG_ID = "userID";
    private static final String TAG_PASSWORD = "userPassword";
    private static final String TAG_CUSTOMER ="userCustomer";


    ArrayList<HashMap<String, String>> mArrayList;

    ListView mListViewList;
    EditText mEditTextSearchKeyword;
    String mJsonString;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.admin_customer, container, false);

        mListViewList = (ListView) v.findViewById(R.id.listView_main_list);
        mArrayList = new ArrayList<HashMap<String, String>>();
        mEditTextSearchKeyword = (EditText) v.findViewById(R.id.editText_main_searchKeyword);

        Button button_search = (Button) v.findViewById(R.id.button_main_search);
        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mArrayList.clear();

                GetData task = new GetData();
                task.execute( mEditTextSearchKeyword.getText().toString());
//                task.execute("http://211.115.254.166:8282/loadinfo.php");
            }
        });


        return v;
    }

    private class GetData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(getActivity(),
                    "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);



            mJsonString = result;
            showResult();


        }
        @Override
        protected String doInBackground(String... params) {

            String searchKeyword = params[0];
            String serverURL = "http://211.115.254.166:8282/userdatasearch.php";
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
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
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
    private void showResult(){

        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);

                String id = item.getString(TAG_ID);
                String password = item.getString(TAG_PASSWORD);
                String customer = item.getString(TAG_CUSTOMER);


                HashMap<String,String> hashMap = new HashMap<>();

                hashMap.put(TAG_ID, id);
                hashMap.put(TAG_PASSWORD, password);
                hashMap.put(TAG_CUSTOMER, customer);


                mArrayList.add(hashMap);
            }

            ListAdapter ListAdapter = new SimpleAdapter(
                    getActivity(), mArrayList,R.layout.item_list,
                    new String[]{TAG_ID,TAG_PASSWORD,TAG_CUSTOMER},
                    new int[]{R.id.textView_list_id, R.id.textView_list_password,R.id.textView_list_Customer}
            );
            mListViewList.setAdapter(ListAdapter);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }

}

