package com.example.user.supervise_2nd_heart.user;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.supervise_2nd_heart.MainActivity;
import com.example.user.supervise_2nd_heart.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.tsengvn.typekit.TypekitContextWrapper;

public class UserMainActivity extends AppCompatActivity {
    private LinearLayout back3;
    ImageView imCom2, imMyPage2, imAs2, imMenual2;
    Button btn_logout;
    String userID;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_main);

        final Intent intent = getIntent();
        userID = intent.getStringExtra("userID");

        imCom2 = (ImageView)findViewById(R.id.com2);
        imMyPage2 = (ImageView)findViewById(R.id.myPage2);
        imAs2 = (ImageView)findViewById(R.id.as2);
        imMenual2 = (ImageView)findViewById(R.id.menual2);
        back3 = (LinearLayout)findViewById(R.id.back3);
        btn_logout = (Button)findViewById(R.id.btn_logout);
        TextView idText = (TextView)findViewById(R.id.idText);

        idText.setText(userID + " 님 환영합니다.");
        //로그인한 유저 아이디정보 가져오기
//////////////////////////////////////////////////////// 버튼클릭리스너/////////////////////////
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent1);
            }
        });
        imCom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction  = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.contentContainer,  UserCom.newInstance(userID));
                fragmentTransaction.commit();
                back3.setVisibility(View.INVISIBLE);
            }
        });
        imMyPage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction  = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.contentContainer, UserService.newInstance(userID));
                fragmentTransaction.commit();
                back3.setVisibility(View.INVISIBLE);
            }
        });
        imAs2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction  = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.contentContainer, UserAs.newInstance(userID));
                fragmentTransaction.commit();
                back3.setVisibility(View.INVISIBLE);
            }
        });
        imMenual2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction  = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.contentContainer, UserMenual.newInstance(userID));
                fragmentTransaction.commit();
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
                android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
                if(tabId == R.id.userCom){
                    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction  = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.contentContainer,  UserCom.newInstance(userID));
                    fragmentTransaction.commit();
                    back3.setVisibility(View.INVISIBLE);
                }
                else if(tabId == R.id.userService){
                    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction  = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.contentContainer, UserService.newInstance(userID));
                    fragmentTransaction.commit();
                    back3.setVisibility(View.INVISIBLE);
                }
                else if(tabId == R.id.userHome){
                    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction  = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.contentContainer, UserHome.newInstance(userID));
                    fragmentTransaction.commit();
                    back3.setVisibility(View.INVISIBLE);
                }
                else if(tabId == R.id.userAS){
                    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction  = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.contentContainer, UserAs.newInstance(userID));
                    fragmentTransaction.commit();
                    back3.setVisibility(View.INVISIBLE);
                }
                else if(tabId == R.id.userMenual){
                    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction  = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.contentContainer, UserMenual.newInstance(userID));
                    fragmentTransaction.commit();
                    back3.setVisibility(View.INVISIBLE);
                }
            }
        });
//////////////////////////////////////////////////////// 바텀내비게이션/////////////////////////

    }
}
