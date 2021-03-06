package com.example.user.supervise_2nd_heart.admin;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AdminMypageSchedule extends Fragment implements OnMapReadyCallback,GoogleMap.OnMapClickListener,GoogleMap.OnMapLongClickListener,GoogleMap.OnInfoWindowClickListener {
    private MapView mapView = null;
    Context context;
    GoogleMap mGoogleMap;
    private PolylineOptions polylineOptions;
    private ArrayList<LatLng> arrayPoints;
    MapFragment mapFragment;
    FragmentManager fragmentManager;

    String mJsonString;
    private static final String TAG_JSON = "webnautes";
    private static final String TAG_USERCUSTOMER = "userCustomer";
    private static final String TAG_COMSTATE = "comState";
    private static final String TAG_ASDATE = "asDate";
    ArrayList<HashMap<String, String>> mArrayList;
    ListView AsListview;

    String usercustomer ;
    String comstate ;
    String asdate;
    Context gc;
    double lat1, lon1;
    LatLng hi;
    String addressValue;

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


        AsListview = (ListView)view.findViewById(R.id.aslistview);
        mArrayList = new ArrayList<HashMap<String, String>>();
        GetData2 task2 = new GetData2();
        task2.execute("http://211.115.254.166:8282/AdminMypageSchedule.php");

        return view;
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
            Log.e("좌표값 송출",i + "  " + latitude+ " " +longitude );
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

    private class GetData2 extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(getActivity(),
                    "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();

            Log.d("결과 송출", "response  - " + result);

            mJsonString = result;
            showResult();
            address();
        }

        @Override
        protected String doInBackground(String... params) {

            String searchKeyword = params[0];
            String serverURL = params[0];

            String postParameters = "customer=" + searchKeyword;

            try {
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d("결과송출", "response code - " + responseStatusCode);

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                bufferedReader.close();
                return sb.toString().trim();
            } catch (Exception e) {

                Log.d("에러", "InsertData: Error ", e);
                errorString = e.toString();
                return null;
            }
        }
    }


    private void showResult() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                usercustomer = item.getString(TAG_USERCUSTOMER);
                comstate = item.getString(TAG_COMSTATE);
                asdate = item.getString(TAG_ASDATE);

                HashMap<String, String> hashMap = new HashMap<>();

                hashMap.put(TAG_USERCUSTOMER, usercustomer);
                hashMap.put(TAG_COMSTATE, comstate);
                hashMap.put(TAG_ASDATE, asdate);

                mArrayList.add(hashMap);
                ///////////////////////////////////////
                JSONObject jsonObject1 = new JSONObject(hashMap);
                addressValue = jsonObject1.getString("comState");
                Iterator iterator = jsonObject1.keys();
                ArrayList<String> addressKeyList = new ArrayList<>();
                Log.e("주소값 확인",addressValue );
                while (iterator.hasNext()){
                    String b = iterator.next().toString();
                    Log.e("iterator 확인",b );
                    addressKeyList.add(b);
                }
                //////////////////////////////////////

            }
            ListAdapter ListAdapter = new SimpleAdapter(
                    getActivity(), mArrayList, R.layout.item_list,
                    new String[]{TAG_USERCUSTOMER, TAG_COMSTATE, TAG_ASDATE},
                    new int[]{R.id.textView_list_id, R.id.textView_list_password, R.id.textView_list_Customer}
                    );

            AsListview.setAdapter(ListAdapter);
        } catch (JSONException e) {
            Log.d("예외", "showResult : ", e);
        }
    }
    private void address(){

        List<Address> list = null;
        final Geocoder geocoder = new Geocoder(context);

        String str = addressValue;
        if (addressValue == null){
            Log.e("주소값 없음", "  ");
        }else{
            try {
                list = geocoder.getFromLocationName(
                        str, // 지역 이름
                        1); // 읽을 개수
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("test","입출력 오류 - 서버에서 주소변환시 에러발생");
            }
        }
        if (list != null) {
            if (list.size() == 0) {
                Toast.makeText(getContext(), "오류", Toast.LENGTH_SHORT).show();
            } else {
                Log.e("list","주소값 확인");
                lat1 = list.get(0).getLatitude();
                lon1 = list.get(0).getLongitude();
                hi = new LatLng(lat1,lon1);
                Log.e("hi","위경도" + lat1+"  "+lon1 +"  " +hi);
                mapFragment.getMapAsync(this);
            }
        }
        MarkerOptions markerOptions=new MarkerOptions();
        markerOptions.position(hi);
        mGoogleMap.addMarker(markerOptions);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mGoogleMap = googleMap;
        mGoogleMap.setOnMapClickListener(this);
        mGoogleMap.setOnMapLongClickListener(this);
        String coordinates[] = {"37.3616703","126.9351741" };
        double lat = Double.parseDouble(coordinates[0]);
        double lng = Double.parseDouble(coordinates[1]);

        LatLng position = new LatLng(lat, lng);
        GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(position);

        mGoogleMap.addMarker(markerOptions);
        LatLng dangdong = new LatLng(37.354100, 126.942763);
        LatLng anyang = new LatLng(37.394252699999996,126.9568209);
        LatLng gmjung = new LatLng(37.372148, 126.943418);
        markerOptions.position(dangdong);
        googleMap.addMarker(markerOptions);
        markerOptions.position(anyang);
        googleMap.addMarker(markerOptions);
        markerOptions.position(gmjung);
        googleMap.addMarker(markerOptions);

        // 맵 위치이동.
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15));

        arrayPoints = new ArrayList<LatLng>();
    }

}
