package com.example.user.supervise_2nd_heart.admin;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.user.supervise_2nd_heart.R;

public class AdminMypageMain extends Fragment implements View.OnClickListener,AdminActivity.OnBackPressedListener {

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
