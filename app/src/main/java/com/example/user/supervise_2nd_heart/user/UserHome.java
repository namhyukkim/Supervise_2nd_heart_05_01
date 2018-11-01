package com.example.user.supervise_2nd_heart.user;

import android.content.Context;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.supervise_2nd_heart.MainActivity;
import com.example.user.supervise_2nd_heart.R;
import com.example.user.supervise_2nd_heart.admin.AdminMoreInfo;

public class UserHome extends Fragment implements View.OnClickListener,UserMainActivity.OnBackPressedListener{

    private ImageView imCom2, imMyPage2, imAs2, imMenual2;
    private Button btnlogout;
    private LinearLayout back3;
    private static final String userID = "param1";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER



    // TODO: Rename and change types of parameters
    private String getUserID;

    // private OnFragmentInteractionListener mListener;

    public UserHome() {
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
    public static UserHome newInstance(String param1) {
        UserHome fragment = new UserHome();
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
        View v = inflater.inflate(R.layout.user_main,container,false);
        imCom2 = (ImageView) v.findViewById(R.id.com2);
        imMyPage2 = (ImageView)v.findViewById(R.id.myPage2);
        imAs2 = (ImageView)v.findViewById(R.id.as2);
        imMenual2 = (ImageView)v.findViewById(R.id.menual2);
        btnlogout = (Button)v.findViewById(R.id.btn_logout);
        imCom2.setOnClickListener(this);
        imMyPage2.setOnClickListener(this);
        imAs2.setOnClickListener(this);
        imMenual2.setOnClickListener(this);
        btnlogout.setOnClickListener(this);

        back3= (LinearLayout)v.findViewById(R.id.back3);
        TextView idText = (TextView)v.findViewById(R.id.idText);

        idText.setText(getUserID + " 님 환영합니다.");
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


    @Override
    public void onClick(View v) {
        android.support.v4.app.FragmentManager manager = getFragmentManager();
        FragmentTransaction fragmentTransaction  = manager.beginTransaction();

        switch (v.getId()){
            case R.id.com2:

                fragmentTransaction.replace(R.id.contentContainer,  UserCom.newInstance(getUserID));
                fragmentTransaction.commit();
                back3.setVisibility(View.INVISIBLE);
                break;
            case R.id.myPage2:
                fragmentTransaction.replace(R.id.contentContainer, UserService.newInstance(getUserID));
                fragmentTransaction.commit();
                back3.setVisibility(View.INVISIBLE);
                break;

            case R.id.as2:
                fragmentTransaction.replace(R.id.contentContainer, UserAs.newInstance(getUserID));
                fragmentTransaction.commit();
                back3.setVisibility(View.INVISIBLE);
                break;
            case R.id.menual2:
                fragmentTransaction.replace(R.id.contentContainer, UserMenual.newInstance(getUserID));
                fragmentTransaction.commit();
                back3.setVisibility(View.INVISIBLE);
                break;
            case R.id.btn_logout:
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

        }
    }
}

