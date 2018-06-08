package com.example.user.supervise_2nd_heart.user;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.user.supervise_2nd_heart.R;

public class user_home extends Fragment implements View.OnClickListener {

    private ImageView imCom2, imMyPage2, imAs2, imMenual2;
    private LinearLayout back3;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.user_main,container,false);
        imCom2 = (ImageView) v.findViewById(R.id.com2);
        imMyPage2 = (ImageView)v.findViewById(R.id.myPage2);
        imAs2 = (ImageView)v.findViewById(R.id.as2);
        imMenual2 = (ImageView)v.findViewById(R.id.menual2);

        imCom2.setOnClickListener(this);
        imMyPage2.setOnClickListener(this);
        imAs2.setOnClickListener(this);
        imMenual2.setOnClickListener(this);

        back3= (LinearLayout)v.findViewById(R.id.back3);
        return v;
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getFragmentManager();
        switch (v.getId()){
            case R.id.com2:
                manager.beginTransaction().replace(R.id.contentContainer,new user_com()).addToBackStack(null).commit();
                back3.setVisibility(View.INVISIBLE);
                break;
            case R.id.myPage2:
                manager.beginTransaction().replace(R.id.contentContainer,new user_service()).addToBackStack(null).commit();
                back3.setVisibility(View.INVISIBLE);
                break;

            case R.id.as2:
                manager.beginTransaction().replace(R.id.contentContainer,new user_as()).addToBackStack(null).commit();
                back3.setVisibility(View.INVISIBLE);
                break;
            case R.id.menual2:
                manager.beginTransaction().replace(R.id.contentContainer,new user_menual()).addToBackStack(null).commit();
                back3.setVisibility(View.INVISIBLE);
                break;

        }
    }
}
