package com.example.shawnocked.eventsearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

public class VenueDetailsFragment extends Fragment implements OnMapReadyCallback {

    TextView venueName;
    TextView venueNameText;
    RelativeLayout venueNameLinearLayout;

    TextView venueAddress;
    TextView venueAddressText;
    RelativeLayout venueAddressLinearLayout;

    TextView venueCity;
    TextView venueCityText;
    RelativeLayout venueCityLinearLayout;

    TextView venuePhone;
    TextView venuePhoneText;
    RelativeLayout venuePhoneLinearLayout;

    TextView venueOpen;
    TextView venueOpenText;
    RelativeLayout venueOpenLinearLayout;

    TextView venueGeneralRule;
    TextView venueGeneralRuleText;
    RelativeLayout venueGeneralLinearLayout;

    TextView venueChildRule;
    TextView venueChildRuleText;
    RelativeLayout venueChildLinearLayout;


    String venue_adress;
    String venue_cityState;
    String venue_phoneNumber;
    String venue_openHour;
    String venue_generalRule;
    String venue_childRule;

    GoogleMap mGoogleMap;
    MapView mapView;
    View rootview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_venuedetails, container, false);

        venueName = (TextView)rootview.findViewById(R.id.venue_name);
        venueNameText = (TextView)rootview.findViewById(R.id.venue_name_text);
        venueNameLinearLayout = (RelativeLayout) rootview.findViewById(R.id.venue_name_layout);

        venueAddress = (TextView)rootview.findViewById(R.id.venue_address);
        venueAddressText = (TextView)rootview.findViewById(R.id.venue_address_text);
        venueAddressLinearLayout = (RelativeLayout) rootview.findViewById(R.id.venue_address_layout);

        venueCity = (TextView)rootview.findViewById(R.id.venue_city);
        venueCityText = (TextView)rootview.findViewById(R.id.venue_city_text);
        venueCityLinearLayout = (RelativeLayout) rootview.findViewById(R.id.venue_city_layout);

        venuePhone = (TextView)rootview.findViewById(R.id.venue_phone);
        venuePhoneText = (TextView)rootview.findViewById(R.id.venue_phone_text);
        venuePhoneLinearLayout = (RelativeLayout) rootview.findViewById(R.id.venue_phone_layout);

        venueOpen = (TextView)rootview.findViewById(R.id.venue_open);
        venueOpenText = (TextView)rootview.findViewById(R.id.venue_open_text);
        venueOpenLinearLayout = (RelativeLayout) rootview.findViewById(R.id.venue_open_layout);

        venueGeneralRule = (TextView)rootview.findViewById(R.id.venue_rule);
        venueGeneralRuleText = (TextView)rootview.findViewById(R.id.venue_rule_text);
        venueGeneralLinearLayout = (RelativeLayout) rootview.findViewById(R.id.venue_general_layout);

        venueChildRule = (TextView)rootview.findViewById(R.id.venue_child_rule);
        venueChildRuleText = (TextView)rootview.findViewById(R.id.venue_child_rule_text);
        venueChildLinearLayout = (RelativeLayout) rootview.findViewById(R.id.venue_child_layout);

        final String sender = getActivity().getIntent().getExtras().getString("VENUE");
        if(sender != null){
            this.receiveVenue();
//            Toast.makeText(getActivity(), venueNameText.getText().toString(), Toast.LENGTH_SHORT).show();

            final RequestQueue venueRequestQueue = Volley.newRequestQueue(getActivity());
            String venueReturn = venueNameText.getText().toString();
            String realVenue = venueReturn.trim().replaceAll(" ", "+");
            String venueUrl = "http://androidbackendshawn666.us-west-1.elasticbeanstalk.com/venuesearch?venue=" + realVenue;
            JsonObjectRequest venueObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, venueUrl, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                venue_adress = response.getString("address");
                                venue_cityState = response.getString("city");
                                venue_phoneNumber = response.getString("number");
                                venue_openHour = response.getString("open");
                                venue_generalRule = response.getString("general");
                                venue_childRule = response.getString("child");

                                if(venue_adress.equals("null")){
                                    venueAddressLinearLayout.setVisibility(View.GONE);
                                }
                                else{
                                    venueAddressText.setText(venue_adress);
                                }

                                if(venue_cityState.equals("null")){
                                    venueCityLinearLayout.setVisibility(View.GONE);
                                }
                                else{
                                    venueCityText.setText(venue_cityState);
                                }

                                if(venue_phoneNumber.equals("null")){
                                    venuePhoneLinearLayout.setVisibility(View.GONE);
                                }
                                else{
                                    venuePhoneText.setText(venue_phoneNumber);
                                }

                                if(venue_openHour.equals("null")){
                                    venueOpenLinearLayout.setVisibility(View.GONE);
                                }
                                else{
                                    venueOpenText.setText(venue_openHour);
                                }

                                if(venue_generalRule.equals("null")){
                                    venueGeneralLinearLayout.setVisibility(View.GONE);
                                }
                                else{
                                    venueGeneralRuleText.setText(venue_generalRule);
                                }

                                if(venue_childRule.equals("null")){
                                    venueChildLinearLayout.setVisibility(View.GONE);
                                }
                                else{
                                    venueChildRuleText.setText(venue_childRule);
                                }

                            }
                            catch (JSONException e){
                                e.printStackTrace();
                                Log.e("error happened",e.toString());
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("ERROR", "Error occurred ", error);
                            venueRequestQueue.stop();
                        }
                    });

            venueRequestQueue.add(venueObjectRequest);
        }

        return rootview;
    }


    private void receiveVenue(){
        Intent i = getActivity().getIntent();
        String venueReceived = i.getStringExtra("VENUE");

        venueNameText.setText(venueReceived);
        Log.i("received", venueNameText.getText().toString());
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = (MapView) rootview.findViewById(R.id.map);
        if(mapView != null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());

        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        String venueCheck = venueNameText.getText().toString();
        if (venueCheck.equals("Park Theater")){
            googleMap.addMarker(new MarkerOptions().position(new LatLng(36.104 , -115.174)).title("Park Theater").snippet("Test"));
            CameraPosition park = CameraPosition.builder().target(new LatLng(36.104 , -115.174)).zoom(15).bearing(0).build();
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(park));
        }

        else {
            googleMap.addMarker(new MarkerOptions().position(new LatLng(34.0431 , -118.2674)).title("Staples Center").snippet("Test"));

            CameraPosition staples = CameraPosition.builder().target(new LatLng(34.0431 , -118.2674)).zoom(15).bearing(0).build();

            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(staples));
        }

    }
}
