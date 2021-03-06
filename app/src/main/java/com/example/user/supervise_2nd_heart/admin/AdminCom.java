package com.example.user.supervise_2nd_heart.admin;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.FragmentManager;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.user.supervise_2nd_heart.R;
import com.example.user.supervise_2nd_heart.user.User;
import com.example.user.supervise_2nd_heart.user.UserListAdapter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class AdminCom extends Fragment implements AdminActivity.OnBackPressedListener {
    private static String TAG = "phptest_MainActivity";

    private static final String TAG_JSON = "webnautes";
    private static final String TAG_CUSTOMER = "userCustomer";
    private static final String TAG_MK = "userMk";
    private static final String TAG_TEMP = "temp";
    private static final String TAG_AMPERE = "ampere";
    private static final String TAG_VOLTAGE = "voltage";
    private static final String TAG_WATT = "watt";
    private static final String TAG_ATMOSPHERIC = "atmospheric";


    ArrayList<HashMap<String, String>> mArrayList;
    Button btnSync;
    ListView mListViewList;
    EditText mEditTextSearchKeyword;
    String mJsonString;
    Button button_search;
    FragmentManager manager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.admin_com, container, false);

        mListViewList = (ListView) v.findViewById(R.id.comListView);
        mArrayList = new ArrayList<HashMap<String, String>>();
        mEditTextSearchKeyword = (EditText) v.findViewById(R.id.comEditSearch);

        button_search = (Button) v.findViewById(R.id.comButtonSearch);
        btnSync = (Button) v.findViewById(R.id.btnSync);
        // 전체 회원의 기기정보를 출력함.
        GetData2 task2 = new GetData2();
        task2.execute("http://211.115.254.166:8282/com_loadinfo.php");

        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mArrayList.clear();
                GetData2 task2 = new GetData2();
                task2.execute("http://211.115.254.166:8282/com_loadinfo.php");
            }
        });

        // 검색 후 해당 정보를 출력함.
        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mArrayList.clear();


                GetData task = new GetData();

                task.execute(mEditTextSearchKeyword.getText().toString()); // 에디트 텍스트에서 값변환하여 입력


            }
        });
        mListViewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int s = 0; s < mArrayList.size(); s++) {
                    if (position == s) {
                        // Log.d(TAG, "s값  - " +mArrayList.get(i));

                        HashMap map = new HashMap();
                        map = mArrayList.get(position);

                        Iterator<String> iterator = map.keySet().iterator();
                        while (iterator.hasNext()) {
                            String key = (String) iterator.next();
                            //Log.i("INFO", "key : "+key);
                            //Log.i("INFO", "value : ska "+mArrayList.get(i).get(key));
                            if (key == "userMk") {
                                //Log.i("INFO", "value : ska "+mArrayList.get(i).get(key));
                                Intent intent = new Intent(getActivity(), OnOff.class);
                                intent.putExtra("info", mArrayList.get(position).get(key));
                                startActivity(intent);
                            }
                        }
                    }
                }
            }
        });

        return v;
    }

    @Override
    public void onBack() {
        Log.e("Other", "onBack()");
        // 리스너를 설정하기 위해 Activity 를 받아옵니다.
        Intent intent = new Intent(getContext(), AdminActivity.class);
        startActivity(intent);



//        AdminActivity activity = (AdminActivity) getActivity();
//        // 한번 뒤로가기 버튼을 눌렀다면 Listener 를 null 로 해제해줍니다.
//        activity.setOnBackPressedListener(null);
//        // MainFragment 로 교체
//        getActivity().getFragmentManager().beginTransaction().replace(R.id.drawer_layout,new AdminActivity()).addToBackStack(null).commit();
//        // Activity 에서도 뭔가 처리하고 싶은 내용이 있다면 하단 문장처럼 호출해주면 됩니다.
//        // activity.onBackPressed();
    }

    // Fragment 호출 시 반드시 호출되는 오버라이드 메소드입니다.
    @Override
    //                     혹시 Context 로 안되시는분은 Activity 로 바꿔보시기 바랍니다.
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("Other", "onAttach()");
        ((AdminActivity) context).setOnBackPressedListener(this);
    }

    // 검색한 데이터를 불러오는 내부 클래스
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

        // php문
        @Override
        protected String doInBackground(String... params) {

            String searchKeyword = params[0];
            String serverURL = null;
            serverURL = "http://211.115.254.166:8282/com_datasearch.php";

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

    // 전체회원을 불러오는 내부 클래스
    private class GetData2 extends AsyncTask<String, Void, String> {
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

        // php문
        @Override
        protected String doInBackground(String... params) {

            String searchKeyword = params[0];
            String serverURL = params[0];

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


    // 결과값을 출력하게 하는 메소드
    private void showResult() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                String customer = item.getString(TAG_CUSTOMER);
                String mk = item.getString(TAG_MK);
                String temp = item.getString(TAG_TEMP);
                String ampere = item.getString(TAG_AMPERE);
                String voltage = item.getString(TAG_VOLTAGE);
                String watt = item.getString(TAG_WATT);
                String atmospheric = item.getString(TAG_ATMOSPHERIC);


                HashMap<String, String> hashMap = new HashMap<>();

                hashMap.put(TAG_CUSTOMER, customer);
                hashMap.put(TAG_MK, mk);
                hashMap.put(TAG_TEMP, temp);
                hashMap.put(TAG_AMPERE, ampere);
                hashMap.put(TAG_VOLTAGE, voltage);
                hashMap.put(TAG_WATT, watt);
                hashMap.put(TAG_ATMOSPHERIC, atmospheric);
                //System.out.println(id + "\t" + password + "\t" + customer);
                mArrayList.add(hashMap);
            }
            ListAdapter ListAdapter = new SimpleAdapter(
                    getActivity(), mArrayList, R.layout.com_table,
                    new String[]{TAG_CUSTOMER, TAG_MK, TAG_TEMP, TAG_AMPERE, TAG_VOLTAGE, TAG_WATT, TAG_ATMOSPHERIC},
                    new int[]{R.id.userCustomer_com, R.id.userMk_com, R.id.com_temp, R.id.com_ampere, R.id.com_voltage, R.id.com_watt, R.id.com_atmospheric}
            );
            mListViewList.setAdapter(ListAdapter);
        } catch (JSONException e) {
            Log.d(TAG, "showResult : ", e);
        }
    }


}



