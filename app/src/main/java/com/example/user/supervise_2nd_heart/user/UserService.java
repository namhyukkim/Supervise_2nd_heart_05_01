package com.example.user.supervise_2nd_heart.user;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.supervise_2nd_heart.R;

public class UserService  extends android.support.v4.app.Fragment {
    @Nullable

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


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.user_service,container,false);
        TextView idText = (TextView)v.findViewById(R.id.idText);

        idText.setText(getUserID + " 님 환영합니다.");
        return v;
    }
}