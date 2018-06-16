package com.example.user.supervise_2nd_heart.admin;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.FragmentManager;
import android.widget.SimpleAdapter;

import com.example.user.supervise_2nd_heart.R;
import com.example.user.supervise_2nd_heart.user.User;
import com.example.user.supervise_2nd_heart.user.UserListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class admin_com extends Fragment {
    View v;
//    private  ListView listView;
//    private UserListAdapter adapter;
//    private List<User> userList;

    Bundle bundle = new Bundle(1);
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.admin_com,container,false);

//        listView =(ListView)v.findViewById(R.id.listView);
//        userList=new ArrayList<User>();
//        Bundle bundle =getArguments();
//        String message = bundle.getString("key");
//
//        adapter = new UserListAdapter(getContext(),userList);
//        listView.setAdapter(adapter);
//        try {
//            JSONObject jsonObject = new JSONObject(message);
//        }catch ()
        return v;
    }

//    private void showResult() {
//        try {
//            JSONObject jsonObject = new JSONObject(mJsonString);
//            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject item = jsonArray.getJSONObject(i);
//                String id = item.getString(TAG_ID);
//                String password = item.getString(TAG_PASSWORD);
//                String customer = item.getString(TAG_CUSTOMER);
//
//                HashMap<String, String> hashMap = new HashMap<>();
//
//                hashMap.put(TAG_ID, id);
//                hashMap.put(TAG_PASSWORD, password);
//                hashMap.put(TAG_CUSTOMER, customer);
//                //System.out.println(id + "\t" + password + "\t" + customer);
//                mArrayList.add(hashMap);
//            }
//            ListAdapter ListAdapter = new SimpleAdapter(
//                    getActivity(), mArrayList, R.layout.item_list,
//                    new String[]{TAG_ID, TAG_PASSWORD, TAG_CUSTOMER},
//                    new int[]{R.id.textView_list_id, R.id.textView_list_password, R.id.textView_list_Customer}
//            );
//            mListViewList.setAdapter(ListAdapter);
//        } catch (JSONException e) {
//            Log.d(TAG, "showResult : ", e);
//        }
//    }
}