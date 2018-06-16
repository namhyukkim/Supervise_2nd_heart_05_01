package com.example.user.supervise_2nd_heart.admin;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.user.supervise_2nd_heart.R;

public class AdminMypageMain extends Fragment implements View.OnClickListener {

    private Button scheBtn, asBtn;
    private LinearLayout notice;
    private RelativeLayout fragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.admin_mypage_main, container, false);

        scheBtn = (Button) v.findViewById(R.id.scheBtn);
        asBtn = (Button) v.findViewById(R.id.asBtn);
        notice = (LinearLayout) v.findViewById(R.id.notice);
        fragment = (RelativeLayout) v.findViewById(R.id.fragment);

        scheBtn.setOnClickListener(this);
        asBtn.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getFragmentManager();
        switch (v.getId()) {
            case R.id.scheBtn:
                notice.setVisibility(View.INVISIBLE);
                manager.beginTransaction().replace(R.id.fragment, new AdminMypageSchedule())
                        .addToBackStack(null).commit();

                break;
            case R.id.asBtn:
                notice.setVisibility(View.INVISIBLE);
                manager.beginTransaction().replace(R.id.fragment, new AdminMypageAS())
                        .addToBackStack(null).commit();
                break;
        }
    }
}
