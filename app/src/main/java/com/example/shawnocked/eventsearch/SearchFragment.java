package com.example.shawnocked.eventsearch;


import android.app.DownloadManager;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements Observer{

    EditText keywordEditText;
    EditText distanceEditText;
    String categoryChoice;
    String measurementChoice;
    Spinner categorySpinner;
    ArrayAdapter<CharSequence> categoryAdapter;
    Spinner measurementSpinner;
    ArrayAdapter<CharSequence> measurementAdapter;
    RadioGroup radioLocationGroup;
    RadioButton radioLocationButton;
    EditText locationInput;
    View searchButton;
    View clearButton;

    private LocationHandler lh;
    private Location location;
    TextView latAndLon;
    TextView returnResult;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        latAndLon = rootView.findViewById(R.id.latandlon);
        returnResult = rootView.findViewById(R.id.response);

        this.lh = new LocationHandler(getActivity());
        this.lh.addObserver(this);
        this.location = lh.getLocation();

        searchButton = rootView.findViewById(R.id.search_button);
        clearButton = rootView.findViewById(R.id.clear_button);

        keywordEditText = (EditText) rootView.findViewById(R.id.enter_keyword);
        distanceEditText = (EditText) rootView.findViewById(R.id.enter_distance);
        radioLocationGroup = (RadioGroup) rootView.findViewById(R.id.location_group);
        locationInput = (EditText) rootView.findViewById(R.id.user_input_location);
        radioLocationButton = (RadioButton)rootView.findViewById(radioLocationGroup.getCheckedRadioButtonId());

        radioLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radioLocationButton.getText().equals("Current location")){
                    locationInput.setEnabled(false);
                }
                else{
                    locationInput.setEnabled(true);
                }
            }
        });



        categorySpinner = (Spinner)rootView.findViewById(R.id.category_spinner);
        categoryAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.search_category, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                categoryChoice = categorySpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        measurementSpinner = (Spinner)rootView.findViewById(R.id.measurement_spinner);
        measurementAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.measurementchoice, android.R.layout.simple_spinner_item);
        measurementAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        measurementSpinner.setAdapter(measurementAdapter);
        measurementSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                measurementChoice = measurementSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // click search button to get event
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // keyword validation
                boolean keywordCheck = isEmpty(keywordEditText);
                if(keywordCheck == true){
                    setError(keywordEditText, "Please enter mandatory field");
                    Toast.makeText(getActivity().getBaseContext(), "Please fix all fields with errors", Toast.LENGTH_LONG).show();
                }

                else{
                    radioLocationButton = (RadioButton)rootView.findViewById(radioLocationGroup.getCheckedRadioButtonId());
                    if(radioLocationButton.getText().equals("Current location")){

                        // send input to event activity
                        sendData();
                        //Toast.makeText(getActivity().getBaseContext(), "keyword is: " + keywordEditText.getText().toString() + " distance is: " + distanceEditText.getText().toString() + " category choice is: " + categoryChoice + " measurement choice is: " + measurementChoice + " radio button: " + latAndLon.getText().toString(), Toast.LENGTH_LONG).show();
                    }

                    else{
                        boolean locationCheck = isEmpty(locationInput);
                        if (locationCheck == true){
                            setError(locationInput, "Please enter mandatory field");
                            Toast.makeText(getActivity().getBaseContext(), "Please fix all fields with errors", Toast.LENGTH_LONG).show();
                        }

                        else{
                            // continue on sending input to event activity
                            sendData2();
                        }
                    }
                }


//                Intent intent = new Intent(getActivity(), EventActivity.class);
//                getActivity().startActivity(intent);


            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (keywordEditText.getText().toString().equals("") == false){
                    keywordEditText.setText("");
                }
                if (distanceEditText.getText().toString().equals("") == false){
                    distanceEditText.setText("");
                }
                //locationInput
                if (locationInput.getText().toString().equals("") == false){
                    locationInput.setText("");
                }
            }
        });

        return rootView;
    }

    @Override
    public void update(Observable observable, Object o) {
        if(observable instanceof LocationHandler) {
            this.location = (Location) o;
            latAndLon.setText(Double.toString(location.getLatitude()) + "," +  Double.toString(location.getLongitude()));
        }
    }

    public static boolean isEmpty(EditText editText) {

        String input = editText.getText().toString().trim();
        return input.length() == 0;

    }

    public static void setError(EditText editText, String errorString) {
        editText.setError(errorString);

    }


    public void sendData(){

        Intent i = new Intent(getActivity().getBaseContext(), EventActivity.class);

        String keywordReformed = keywordEditText.getText().toString().trim().replaceAll(" ", "+");
        i.putExtra("SENDERKEY", "SEARCHFRAGMENT");
        i.putExtra("KEYWORD", keywordReformed);
        i.putExtra("DISTANCE", distanceEditText.getText().toString());
        i.putExtra("CATEGORY", categoryChoice);
        i.putExtra("MEASUREMENT", measurementChoice);
        i.putExtra("LOCATIONCHOICE", latAndLon.getText().toString());

        getActivity().startActivity(i);
    }

    public void sendData2(){

        Intent i = new Intent(getActivity().getBaseContext(), EventActivity.class);

        String keywordReformed = keywordEditText.getText().toString().trim().replaceAll(" ", "+");
        i.putExtra("SENDERKEY", "SEARCHFRAGMENT");
        i.putExtra("KEYWORD", keywordReformed);
        i.putExtra("DISTANCE", distanceEditText.getText().toString());
        i.putExtra("CATEGORY", categoryChoice);
        i.putExtra("MEASUREMENT", measurementChoice);
        String locationReform = locationInput.getText().toString().trim().replaceAll(" ", "+");
        i.putExtra("LOCATIONCHOICE", locationReform);

        getActivity().startActivity(i);
    }
}
