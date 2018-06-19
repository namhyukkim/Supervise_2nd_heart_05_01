package com.example.user.supervise_2nd_heart.user;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Response;
import com.example.user.supervise_2nd_heart.R;
import com.example.user.supervise_2nd_heart.admin.AdminCom;

import java.util.List;

public class UserListAdapter extends BaseAdapter {

    private Context context;
    private List<User> userList;

    public UserListAdapter(Context context,List<User> userList){
        this.context=context;
        this.userList=userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        View v =View.inflate(context,R.layout.com_table,null);
        TextView userCustomer_com = (TextView)v.findViewById(R.id.userCustomer_com);
        TextView userMk_com = (TextView)v.findViewById(R.id.userMk_com);
        TextView com_temp = (TextView)v.findViewById(R.id.com_temp);
        TextView com_ampere = (TextView)v.findViewById(R.id.com_ampere);
        TextView com_voltage = (TextView)v.findViewById(R.id.com_voltage);
        TextView com_watt = (TextView)v.findViewById(R.id.com_watt);
        TextView com_atmospheric = (TextView)v.findViewById(R.id.com_atmospheric);

        userCustomer_com.setText(userList.get(position).getUserCustomer());
        userMk_com.setText(userList.get(position).getUserMk());
        com_temp.setText(userList.get(position).getTemp());
        com_ampere.setText(userList.get(position).getAmpere());
        com_voltage.setText(userList.get(position).getVoltage());
        com_watt.setText(userList.get(position).getWatt());
        com_atmospheric.setText(userList.get(position).getAtmospheric());

        v.setTag(userList.get(position).getUserCustomer());

        return v;
    }
}
