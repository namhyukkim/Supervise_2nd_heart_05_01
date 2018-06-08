//package com.example.user.supervise_2nd_heart;
//
//import android.app.Fragment;
//import android.app.ProgressDialog;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.util.Log;
//import android.widget.ListAdapter;
//import android.widget.SimpleAdapter;
//import android.view.ViewGroup;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class admin_com extends Fragment{
//
//    private static String TAG = "phptest_MainActivity";
//
//    private static final String TAG_JSON="webnautes";
//    private static final String TAG_ID = "userID";
//    private static final String TAG_PASSWORD = "userPassword";
//    private static final String TAG_CUSTOMER ="userCustomer";
//    private static final String TAG_Representative ="userRepresentative";
//    private static final String TAG_CRN ="userCrn";
//    private static final String TAG_PHONE ="userPhone";
//    private static final String TAG_EALI ="userEmail";
//    private static final String TAG_FAX ="userFax";
//    private static final String TAG_ADDRESS ="useraddress";
//
//    private TextView mTextViewResult;
//    ArrayList<HashMap<String, String>> mArrayList;
//    ListView match_parent;
//    String mJsonString;
//
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.admin_com,container,false);
//
//        mTextViewResult =(TextView)v.findViewById(R.id.textView_main_result);
//        match_parent =(ListView)v.findViewById(R.id.listView_main_list);
//        ListAdapter adapter = new SimpleAdapter(
//                getActivity(), mArrayList, R.layout.item_list,
//                new String[]{TAG_ID,TAG_PASSWORD,TAG_CUSTOMER,TAG_Representative,TAG_CRN,TAG_PHONE,TAG_EALI,TAG_FAX, TAG_ADDRESS},
//                new int[]{R.id.textView_list_id, R.id.textView_list_password,R.id.textView_list_Customer,R.id.textView_list_Representative,
//                        R.id.textView_list_Crn,R.id.textView_list_Phone,R.id.textView_list_Email,R.id.textView_list_fax,R.id.textView_list_address}
//        );
//
//        match_parent.setAdapter(adapter);
//        mArrayList = new ArrayList<>();
//
//        GetData task = new GetData();
//        task.execute("http://211.115.254.166:8282/loadinfo.php");
//
//
//        try {
//            JSONObject jsonObject = new JSONObject(mJsonString);
//            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);
//
//            for(int i=0;i<jsonArray.length();i++){
//
//                JSONObject item = jsonArray.getJSONObject(i);
//
//                String id = item.getString(TAG_ID);
//                String password = item.getString(TAG_PASSWORD);
//                String customer = item.getString(TAG_CUSTOMER);
//                String Representative = item.getString(TAG_Representative);
//                String Crn = item.getString(TAG_CRN);
//                String Phone = item.getString(TAG_PHONE);
//                String Email = item.getString(TAG_EALI);
//                String Fax = item.getString(TAG_FAX);
//                String address = item.getString(TAG_ADDRESS);
//
//                HashMap<String,String> hashMap = new HashMap<>();
//
//                hashMap.put(TAG_ID, id);
//                hashMap.put(TAG_PASSWORD, password);
//                hashMap.put(TAG_CUSTOMER, customer);
//                hashMap.put(TAG_Representative, Representative);
//                hashMap.put(TAG_CRN, Crn);
//                hashMap.put(TAG_PHONE, Phone);
//                hashMap.put(TAG_EALI, Email);
//                hashMap.put(TAG_FAX, Fax);
//                hashMap.put(TAG_ADDRESS, address);
//
//                mArrayList.add(hashMap);
//            }
//
//
//
//        } catch (JSONException e) {
//
//            Log.d(TAG, "showResult : ", e);
//        }
//
//
//
//        return v;
//    }
//
//    private class GetData extends AsyncTask<String, Void, String> {
//        ProgressDialog progressDialog;
//        String errorString = null;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            progressDialog = progressDialog.show(getActivity(),"Please Wait",
//                    null,true,true);
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//
//            progressDialog.dismiss();;
//            mTextViewResult.setText(s);
//            Log.d(TAG,"response -"+s);
//
//            if (s==null){
//
//                mTextViewResult.setText(errorString);
//            }
//            else{
//
//                mJsonString = s;
//
//            }
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//
//            String serverURL =params[0];
//
//            try {
//                URL url=new URL(serverURL);
//                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//
//                httpURLConnection.setReadTimeout(5000);
//                httpURLConnection.setConnectTimeout(5000);
//                httpURLConnection.connect();
//
//                int responseStatusCode = httpURLConnection.getResponseCode();
//                Log.d(TAG, "response code - " + responseStatusCode);
//
//                InputStream inputStream;
//                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
//                    inputStream = httpURLConnection.getInputStream();
//                }
//                else{
//                    inputStream = httpURLConnection.getErrorStream();
//                }
//
//                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
//                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//
//                StringBuilder sb = new StringBuilder();
//                String line;
//
//                while((line = bufferedReader.readLine()) != null){
//                    sb.append(line);
//                }
//
//                bufferedReader.close();
//
//                return sb.toString().trim();
//
//
//            } catch (Exception e) {
//                Log.d(TAG, "InsertData: Error ", e);
//                errorString = e.toString();
//
//                return null;
//            }
//        }
//    }
//
//
//
//}
