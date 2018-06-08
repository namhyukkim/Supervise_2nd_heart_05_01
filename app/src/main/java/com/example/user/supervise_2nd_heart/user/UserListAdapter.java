package com.example.user.supervise_2nd_heart.user;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.supervise_2nd_heart.R;

import java.util.List;

public class UserListAdapter extends BaseAdapter {

    private Context context;
    private List<User> userList;

    public UserListAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int i) {
        return userList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.admin_com, null);
        TextView userID = (TextView) v.findViewById(R.id.textView_list_id);
        TextView userPassword = (TextView) v.findViewById(R.id.textView_list_password);
        TextView userCustomer = (TextView) v.findViewById(R.id.textView_list_Customer);

        userID.setText(userList.get(i).getUserID());
        userPassword.setText(userList.get(i).getUserPassword());
        userCustomer.setText(userList.get(i).getUserCustomer());

        v.setTag(userList.get(i).getUserID());
        return v;
    }
}
