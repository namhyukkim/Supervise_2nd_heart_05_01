package com.example.user.supervise_2nd_heart.admin;

import android.content.Intent;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.user.supervise_2nd_heart.List1;
import com.example.user.supervise_2nd_heart.R;
import com.example.user.supervise_2nd_heart.util.Constants;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Admin_Mypage_AS extends Fragment implements OnMapReadyCallback {

    static final String[] LIST_MENU = {"★ 예체능관련분과", "동아리List2", "동아리List3", "동아리List4", "동아리List5",
            "★ 문학관련분과", "동아리List7", "동아리List8", "동아리List9", "동아리List10", "★ 봉사분과", "동아리List12", "동아리List13",
            "동아리List14", "동아리List15"};

    private MapView mapView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.admin_mypage_as, null);
        ArrayAdapter Adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, LIST_MENU);

        ListView listview1 = (ListView) view.findViewById(R.id.listView1);
        listview1.setAdapter(Adapter);

        mapView = (MapView) view.findViewById(R.id.map1);
        mapView.getMapAsync(this);


        // return view;  이부분을 끝으로 옮기세요.
        // return 다음에는 어떠한 코드도 실행되지 않습니다. 따라서 오류라 다음 줄이 보고된것입니다.

        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                adapterView.getItemAtPosition(pos);
                if (LIST_MENU == null) {
                    Intent intent = new Intent(getActivity(), List1.class);
                    startActivity(intent);
                }
            }
        });
        return view; // 따라서 이곳으로 return 이 와야 합니다.
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onLowMemory();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//액티비티가 처음 생성될 때 실행되는 함수

        if (mapView != null) {
            mapView.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng SEOUL = new LatLng(37.56, 126.97);

        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position(SEOUL);

        markerOptions.title("서울");

        markerOptions.snippet("수도");

        googleMap.addMarker(markerOptions);

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));

        googleMap.animateCamera(CameraUpdateFactory.zoomTo(13));

    }
}
