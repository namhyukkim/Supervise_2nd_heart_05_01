package com.example.user.supervise_2nd_heart.admin;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.app.Fragment;
import android.location.Address;
import android.location.Geocoder;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.supervise_2nd_heart.List1;
import com.example.user.supervise_2nd_heart.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import static android.media.CamcorderProfile.get;

public class AdminMypageAS extends Fragment implements OnMapReadyCallback,View.OnClickListener {

    Button btnSearch0;
    EditText editSearch0;
    Context ct;
    LatLng hi;

    private MapView mapView = null;
    double lat, lon;
    MapFragment mf;
    FragmentManager fm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.admin_mypage_as, null);
        btnSearch0 = (Button) view.findViewById(R.id.btnSearch0);
        editSearch0 = (EditText)view.findViewById(R.id.editTextSearch);
        ct = container.getContext();
        fm=getFragmentManager();
        mf = (MapFragment)fm.findFragmentById(R.id.map2);
        mf.getMapAsync(this);
        btnSearch0.setOnClickListener(this);
        ////////얍 얍 얍 얍 얍 얍 얍 얍///////////////////


        ////////얍 얍 얍 얍 얍 얍 얍 얍///////////////////
        return view; // 따라서 이곳으로 return 이 와야 합니다.
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        hi = new LatLng(lat,lon);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(hi);
        googleMap.addMarker(markerOptions);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(hi));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(13));


    }

    @Override
    public void onClick(View v) {
        List<Address> list = null;
        String str = editSearch0.getText().toString();
        final Geocoder geocoder = new Geocoder(ct);
        try {
            list = geocoder.getFromLocationName(
                    str, // 지역 이름
                    1); // 읽을 개수
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("test","입출력 오류 - 서버에서 주소변환시 에러발생");
        }
        if (list != null) {
            if (list.size() == 0) {
                Toast.makeText(getContext(), "NO!!!!!", Toast.LENGTH_SHORT).show();
            } else {
                Log.e("list","들어오긴한다 병신아");

                lat = list.get(0).getLatitude();
                lon = list.get(0).getLongitude();

                hi = new LatLng(lat,lon);
                Log.e("hi","위경도" + lat+"  "+lon +"  " +hi);
                mf.getMapAsync(this);
                //          list.get(0).getCountryName();  // 국가명
                //          list.get(0).getLatitude();        // 위도
                //          list.get(0).getLongitude();    // 경도
            }
        }

    }

}
