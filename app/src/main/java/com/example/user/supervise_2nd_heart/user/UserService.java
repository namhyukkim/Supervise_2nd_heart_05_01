package com.example.user.supervise_2nd_heart.user;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.user.supervise_2nd_heart.R;

public class UserService extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.user_service,container,false);
        EditText editText;
        editText = (EditText)v.findViewById(R.id.texttest1);
        return v;
    }
}
