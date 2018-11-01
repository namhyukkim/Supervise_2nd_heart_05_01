package com.example.user.supervise_2nd_heart.user;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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

public class UserService extends Fragment implements UserMainActivity.OnBackPressedListener{
    private static String TAG = "phptest_MainActivity";

    private static final String TAG_JSON = "webnautes";
    private static final String TAG_CUSTOMER = "userCustomer";
    private static final String TAG_COMSTATE = "comState";
    private static final String TAG_ASDATE = "asDate";



    ArrayList<HashMap<String, String>> mArrayList;

    ListView mListViewList;
    EditText mEditTextSearchKeyword;
    String mJsonString;
    Button button_search;
    FragmentManager manager;

    private static final String userID = "param1";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER



    // TODO: Rename and change types of parameters
    private String getUserID;

    // private OnFragmentInteractionListener mListener;

    public UserService() {
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
    public static UserService newInstance(String param1) {
        UserService fragment = new UserService();
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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.user_service, container, false);

        mListViewList = (ListView) v.findViewById(R.id.comListView);
        mArrayList = new ArrayList<HashMap<String, String>>();
        TextView idText = (TextView)v.findViewById(R.id.idText);

        idText.setText(getUserID + " 님 환영합니다.");

        GetData task = new GetData();
        task.execute(getUserID); // 에디트 텍스트에서 값변환하여 입력

        return v;
    }

    @Override
    public void onBack() {
        Log.e("Other", "onBack()");
        // 리스너를 설정하기 위해 Activity 를 받아옵니다.
        android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction  = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contentContainer, UserHome.newInstance(getUserID));
        fragmentTransaction.commit();
    }

    // Fragment 호출 시 반드시 호출되는 오버라이드 메소드입니다.
    @Override
    //                     혹시 Context 로 안되시는분은 Activity 로 바꿔보시기 바랍니다.
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("Other", "onAttach()");
        ((UserMainActivity) context).setOnBackPressedListener(this);
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
            serverURL =  "http://211.115.254.166:8282/com_datasearch_as.php";

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
                String comState = item.getString(TAG_COMSTATE);
                String asDate = item.getString(TAG_ASDATE);
                /*String ampere = item.getString(TAG_AMPERE);*/


                HashMap<String, String> hashMap = new HashMap<>();

                hashMap.put(TAG_CUSTOMER, customer);
                hashMap.put(TAG_COMSTATE, comState);
                hashMap.put(TAG_ASDATE, asDate);
                /*hashMap.put(TAG_AMPERE, ampere);*/

                //System.out.println(id + "\t" + password + "\t" + customer);
                mArrayList.add(hashMap);
            }
            ListAdapter ListAdapter = new SimpleAdapter(
                    getActivity(), mArrayList, R.layout.as_table,
                    new String[]{TAG_CUSTOMER,TAG_COMSTATE, TAG_ASDATE},
                    new int[]{R.id.userCustomer_as,R.id.comState_as, R.id.asDate}
            );
            mListViewList.setAdapter(ListAdapter);
        } catch (JSONException e) {
            Log.d(TAG, "showResult : ", e);
        }
    }
}