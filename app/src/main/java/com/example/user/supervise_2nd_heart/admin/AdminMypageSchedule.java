package com.example.user.supervise_2nd_heart.admin;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.user.supervise_2nd_heart.R;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class AdminMypageSchedule extends Fragment implements OnMapReadyCallback,GoogleMap.OnMapClickListener,GoogleMap.OnMapLongClickListener,GoogleMap.OnInfoWindowClickListener {
    private MapView mapView = null;
    Context context;
    GoogleMap mGoogleMap;
    private PolylineOptions polylineOptions;
    private ArrayList<LatLng> arrayPoints;
    MapFragment mapFragment;
    FragmentManager fragmentManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.admin_mypage_schedule, null);

        context = container.getContext();
        GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context);
        MapsInitializer.initialize(context);

        fragmentManager =getFragmentManager();
        mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.map3);
        mapFragment.getMapAsync(this);



        MapsInitializer.initialize(getActivity());
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mGoogleMap = googleMap;
        mGoogleMap.setOnMapClickListener(this);
        mGoogleMap.setOnMapLongClickListener(this);
        String coordinates[] = { "37.517180", "127.041268" };
        double lat = Double.parseDouble(coordinates[0]);
        double lng = Double.parseDouble(coordinates[1]);

        LatLng position = new LatLng(lat, lng);
        GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());

        // 맵 위치이동.
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15));

        arrayPoints = new ArrayList<LatLng>();

    }

    @Override
    public void onMapClick(LatLng latLng) {
        MarkerOptions marker=new MarkerOptions();
        marker.position(latLng);
        mGoogleMap.addMarker(marker);

        // 맵셋팅
        polylineOptions = new PolylineOptions();
        polylineOptions.color(Color.RED);
        polylineOptions.width(5);
        arrayPoints.add(latLng);
        polylineOptions.addAll(arrayPoints);
        mGoogleMap.addPolyline(polylineOptions);


        for (int i = 0; i < arrayPoints.size(); i++){

            Double latitude = arrayPoints.get(i).latitude;
            Double longitude = arrayPoints.get(i).longitude;
            Log.e("-_-",i + "  " + latitude+ " " +longitude );
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        mGoogleMap.clear();
        arrayPoints.clear();
    }
}
