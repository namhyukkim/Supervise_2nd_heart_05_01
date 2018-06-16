package com.example.user.supervise_2nd_heart.user;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.user.supervise_2nd_heart.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.tsengvn.typekit.TypekitContextWrapper;

public class UserMainActivity extends AppCompatActivity {
    private LinearLayout back3;
    private UserCom usercom;
    ImageView imCom2, imMyPage2, imAs2, imMenual2;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_main);
        usercom = new UserCom();

        imCom2 = (ImageView)findViewById(R.id.com2);
        imMyPage2 = (ImageView)findViewById(R.id.myPage2);
        imAs2 = (ImageView)findViewById(R.id.as2);
        imMenual2 = (ImageView)findViewById(R.id.menual2);

        back3 = (LinearLayout)findViewById(R.id.back3);
//////////////////////////////////////////////////////// 버튼클릭리스너/////////////////////////
        imCom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.contentContainer,new UserCom()).addToBackStack(null).commit();
                back3.setVisibility(View.INVISIBLE);
            }
        });
        imMyPage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.contentContainer,new UserService()).addToBackStack(null).commit();
                back3.setVisibility(View.INVISIBLE);
            }
        });
        imAs2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.contentContainer,new UserAs()).addToBackStack(null).commit();
                back3.setVisibility(View.INVISIBLE);
            }
        });
        imMenual2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.contentContainer,new UserMenual()).addToBackStack(null).commit();
                back3.setVisibility(View.INVISIBLE);
            }
        });
//////////////////////////////////////////////////////// 버튼클릭리스너/////////////////////////
//////////////////////////////////////////////////////// 바텀내비게이션/////////////////////////
        BottomBar bottomBar=(BottomBar)findViewById(R.id.bottomBar);
        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(int tabId) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                FragmentManager manager = getFragmentManager();
                if(tabId == R.id.userCom){
                    manager.beginTransaction().replace(R.id.contentContainer,new UserCom()).addToBackStack(null).commit();
                    back3.setVisibility(View.INVISIBLE);
                }
                else if(tabId == R.id.userService){
                    manager.beginTransaction().replace(R.id.contentContainer,new UserService()).addToBackStack(null).commit();
                    back3.setVisibility(View.INVISIBLE);
                }
                else if(tabId == R.id.userHome){
                    manager.beginTransaction().replace(R.id.contentContainer,new UserHome()).addToBackStack(null).commit();
                    back3.setVisibility(View.INVISIBLE);
                }
                else if(tabId == R.id.userAS){
                    manager.beginTransaction().replace(R.id.contentContainer,new UserAs()).addToBackStack(null).commit();
                    back3.setVisibility(View.INVISIBLE);
                }
                else if(tabId == R.id.userMenual){
                    manager.beginTransaction().replace(R.id.contentContainer,new UserMenual()).addToBackStack(null).commit();
                    back3.setVisibility(View.INVISIBLE);
                }
            }
        });
//////////////////////////////////////////////////////// 바텀내비게이션/////////////////////////

    }
}
