package com.example.shawnocked.eventsearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpcomingFragment extends Fragment {


    RecyclerView recyclerView;
    private ArrayList<String> mUpName = new ArrayList<>();
    private ArrayList<String> mUpUrl = new ArrayList<>();
    private ArrayList<String> mUpArt = new ArrayList<>();
    private ArrayList<String> mUpTime = new ArrayList<>();
    private ArrayList<String> mUpNewTime = new ArrayList<>();
    private ArrayList<String> mUpType = new ArrayList<>();
    private ArrayList<String> mUpNewType = new ArrayList<>();

    Map<String, List<String>> nameMap = new HashMap<>();
    Map<String, List<String>> artMap = new HashMap<>();
    Map<String, List<String>> timeMap = new HashMap<>();
    Map<String, List<String>> typeMap = new HashMap<>();

    JSONArray upNameArray;
    JSONArray upUrlArray;
    JSONArray upArtArray;
    JSONArray upTimeArray;
    JSONArray upTypeArray;

    Spinner orderBaseSpinner;
    ArrayAdapter<CharSequence> orderBaseAdapter;
    Spinner orderChoiceSpinner;
    ArrayAdapter<CharSequence> orderChoiceAdapter;

    String ordeChoice;
    String orderBase;

    TextView venuePlaceholder;
    TextView noResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_upcoming, container, false);

        venuePlaceholder = rootview.findViewById(R.id.venuename_placeholder);
        noResult = rootview.findViewById(R.id.upnoresult);

        orderBaseSpinner = rootview.findViewById(R.id.order_base);
        orderChoiceSpinner = rootview.findViewById(R.id.order_choice);

        orderBaseAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.orderbase, android.R.layout.simple_spinner_item);
        orderBaseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderBaseSpinner.setAdapter(orderBaseAdapter);
        orderBaseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ordeChoice = orderBaseSpinner.getSelectedItem().toString();
                if(ordeChoice.equals("Default") == false){
                    orderChoiceSpinner.setEnabled(true);
                    //get the choice and order events here
                    orderBase = orderChoiceSpinner.getSelectedItem().toString();
                    if(orderBase.equals("Ascending") || orderBase == null){
                        if(ordeChoice.equals("Event Name")){
                            Collections.sort(mUpName);
                            mUpUrl.clear();
                            mUpArt.clear();
                            mUpTime.clear();
                            mUpType.clear();

                            for (int j = 0 ;j < mUpName.size(); j++){
                                String name = mUpName.get(j);
                                List<String> tempList = nameMap.get(name);
                                String url = tempList.get(0);
                                String art = tempList.get(1);
                                String time = tempList.get(2);
                                String type = tempList.get(3);
                                mUpUrl.add(url);
                                mUpArt.add(art);
                                mUpTime.add(time);
                                mUpType.add(type);
                            }

                            RecyclerViewAdapter newadapter = new RecyclerViewAdapter(mUpName, mUpUrl, mUpArt, mUpTime, mUpType,getActivity());
                            recyclerView.setAdapter(newadapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        }

                        else if(ordeChoice.equals("Time")){
                            Collections.sort(mUpNewTime);
                            mUpUrl.clear();
                            mUpName.clear();
                            mUpArt.clear();
                            mUpType.clear();
                            mUpTime.clear();

                            for (int j = 0 ;j < mUpNewTime.size(); j++){
                                String time = mUpNewTime.get(j);
                                List<String> tempList = timeMap.get(time);
                                String url = tempList.get(0);
                                String name = tempList.get(1);
                                String art = tempList.get(2);
                                String type = tempList.get(3);
                                String anothertime = tempList.get(4);
                                mUpUrl.add(url);
                                mUpName.add(name);
                                mUpArt.add(art);
                                mUpType.add(type);
                                mUpTime.add(anothertime);
                            }

                            RecyclerViewAdapter newadapter2 = new RecyclerViewAdapter(mUpName, mUpUrl, mUpArt, mUpTime, mUpType,getActivity());
                            recyclerView.setAdapter(newadapter2);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        }

                        else if(ordeChoice.equals("Artist")){
                            Collections.sort(mUpArt);
                            mUpUrl.clear();
                            mUpName.clear();
                            mUpTime.clear();
                            mUpType.clear();

                            for (int j = 0 ;j < mUpArt.size(); j++){
                                String art = mUpArt.get(j);
                                List<String> tempList = artMap.get(art);
                                String url = tempList.get(0);
                                String name = tempList.get(1);
                                String time = tempList.get(2);
                                String type = tempList.get(3);
                                mUpUrl.add(url);
                                mUpName.add(name);
                                mUpTime.add(time);
                                mUpType.add(type);
                            }

                            RecyclerViewAdapter newadapter3 = new RecyclerViewAdapter(mUpName, mUpUrl, mUpArt, mUpTime, mUpType,getActivity());
                            recyclerView.setAdapter(newadapter3);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        }

                        else {
                            Collections.sort(mUpNewType);
                            mUpUrl.clear();
                            mUpName.clear();
                            mUpArt.clear();
                            mUpTime.clear();
                            mUpType.clear();

                            for (int j = 0 ;j < mUpNewType.size(); j++){
                                String type = mUpNewType.get(j);
                                List<String> tempList = typeMap.get(type);
                                String url = tempList.get(0);
                                String name = tempList.get(1);
                                String art = tempList.get(2);
                                String time = tempList.get(3);
                                String getType = tempList.get(4);
                                mUpUrl.add(url);
                                mUpName.add(name);
                                mUpArt.add(art);
                                mUpTime.add(time);
                                mUpType.add(getType);
                            }

                            RecyclerViewAdapter newadapter4 = new RecyclerViewAdapter(mUpName, mUpUrl, mUpArt, mUpTime, mUpType,getActivity());
                            recyclerView.setAdapter(newadapter4);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        }
                    }

                    else{
                        if(ordeChoice.equals("Event Name")){
                            Collections.sort(mUpName, Collections.reverseOrder());
                            mUpUrl.clear();
                            mUpArt.clear();
                            mUpTime.clear();
                            mUpType.clear();

                            for (int j = 0 ;j < mUpName.size(); j++){
                                String name = mUpName.get(j);
                                List<String> tempList = nameMap.get(name);
                                String url = tempList.get(0);
                                String art = tempList.get(1);
                                String time = tempList.get(2);
                                String type = tempList.get(3);
                                mUpUrl.add(url);
                                mUpArt.add(art);
                                mUpTime.add(time);
                                mUpType.add(type);
                            }

                            RecyclerViewAdapter newadapter = new RecyclerViewAdapter(mUpName, mUpUrl, mUpArt, mUpTime, mUpType,getActivity());
                            recyclerView.setAdapter(newadapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        }

                        else if(ordeChoice.equals("Time")){
                            Collections.sort(mUpNewTime, Collections.reverseOrder());
                            mUpUrl.clear();
                            mUpName.clear();
                            mUpArt.clear();
                            mUpType.clear();
                            mUpTime.clear();

                            for (int j = 0 ;j < mUpNewTime.size(); j++){
                                String time = mUpNewTime.get(j);
                                List<String> tempList = timeMap.get(time);
                                String url = tempList.get(0);
                                String name = tempList.get(1);
                                String art = tempList.get(2);
                                String type = tempList.get(3);
                                String anothertime = tempList.get(4);
                                mUpUrl.add(url);
                                mUpName.add(name);
                                mUpArt.add(art);
                                mUpType.add(type);
                                mUpTime.add(anothertime);
                            }

                            RecyclerViewAdapter newadapter2 = new RecyclerViewAdapter(mUpName, mUpUrl, mUpArt, mUpTime, mUpType,getActivity());
                            recyclerView.setAdapter(newadapter2);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        }

                        else if(ordeChoice.equals("Artist")){
                            Collections.sort(mUpArt, Collections.reverseOrder());
                            mUpUrl.clear();
                            mUpName.clear();
                            mUpTime.clear();
                            mUpType.clear();

                            for (int j = 0 ;j < mUpArt.size(); j++){
                                String art = mUpArt.get(j);
                                List<String> tempList = artMap.get(art);
                                String url = tempList.get(0);
                                String name = tempList.get(1);
                                String time = tempList.get(2);
                                String type = tempList.get(3);
                                mUpUrl.add(url);
                                mUpName.add(name);
                                mUpTime.add(time);
                                mUpType.add(type);
                            }

                            RecyclerViewAdapter newadapter3 = new RecyclerViewAdapter(mUpName, mUpUrl, mUpArt, mUpTime, mUpType,getActivity());
                            recyclerView.setAdapter(newadapter3);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        }

                        else {
                            Collections.sort(mUpNewType, Collections.reverseOrder());
                            mUpUrl.clear();
                            mUpName.clear();
                            mUpArt.clear();
                            mUpTime.clear();
                            mUpType.clear();

                            for (int j = 0 ;j < mUpNewType.size(); j++){
                                String type = mUpNewType.get(j);
                                List<String> tempList = typeMap.get(type);
                                String url = tempList.get(0);
                                String name = tempList.get(1);
                                String art = tempList.get(2);
                                String time = tempList.get(3);
                                String getType = tempList.get(4);
                                mUpUrl.add(url);
                                mUpName.add(name);
                                mUpArt.add(art);
                                mUpTime.add(time);
                                mUpType.add(getType);
                            }
                            RecyclerViewAdapter newadapter4 = new RecyclerViewAdapter(mUpName, mUpUrl, mUpArt, mUpTime, mUpType,getActivity());
                            recyclerView.setAdapter(newadapter4);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        }
                    }

                }


                else{
                    orderChoiceSpinner.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        orderChoiceAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.orderchoice, android.R.layout.simple_spinner_item);
        orderChoiceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderChoiceSpinner.setAdapter(orderChoiceAdapter);
        orderChoiceSpinner.setEnabled(false);
        orderChoiceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                orderBase = orderChoiceSpinner.getSelectedItem().toString();
                if(orderBase.equals("Descending")){
                    if(ordeChoice.equals("Event Name")){
                        Collections.sort(mUpName, Collections.reverseOrder());
                        mUpUrl.clear();
                        mUpArt.clear();
                        mUpTime.clear();
                        mUpType.clear();

                        for (int j = 0 ;j < mUpName.size(); j++){
                            String name = mUpName.get(j);
                            List<String> tempList = nameMap.get(name);
                            String url = tempList.get(0);
                            String art = tempList.get(1);
                            String time = tempList.get(2);
                            String type = tempList.get(3);
                            mUpUrl.add(url);
                            mUpArt.add(art);
                            mUpTime.add(time);
                            mUpType.add(type);
                        }

                        RecyclerViewAdapter newadapter = new RecyclerViewAdapter(mUpName, mUpUrl, mUpArt, mUpTime, mUpType,getActivity());
                        recyclerView.setAdapter(newadapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    }

                    else if(ordeChoice.equals("Time")){
                        Collections.sort(mUpNewTime, Collections.reverseOrder());
                        mUpUrl.clear();
                        mUpName.clear();
                        mUpArt.clear();
                        mUpType.clear();
                        mUpTime.clear();

                        for (int j = 0 ;j < mUpNewTime.size(); j++){
                            String time = mUpNewTime.get(j);
                            List<String> tempList = timeMap.get(time);
                            String url = tempList.get(0);
                            String name = tempList.get(1);
                            String art = tempList.get(2);
                            String type = tempList.get(3);
                            String anothertime = tempList.get(4);
                            mUpUrl.add(url);
                            mUpName.add(name);
                            mUpArt.add(art);
                            mUpType.add(type);
                            mUpTime.add(anothertime);
                        }

                        RecyclerViewAdapter newadapter2 = new RecyclerViewAdapter(mUpName, mUpUrl, mUpArt, mUpTime, mUpType,getActivity());
                        recyclerView.setAdapter(newadapter2);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    }

                    else if(ordeChoice.equals("Artist")){
                        Collections.sort(mUpArt, Collections.reverseOrder());
                        mUpUrl.clear();
                        mUpName.clear();
                        mUpTime.clear();
                        mUpType.clear();

                        for (int j = 0 ;j < mUpArt.size(); j++){
                            String art = mUpArt.get(j);
                            List<String> tempList = artMap.get(art);
                            String url = tempList.get(0);
                            String name = tempList.get(1);
                            String time = tempList.get(2);
                            String type = tempList.get(3);
                            mUpUrl.add(url);
                            mUpName.add(name);
                            mUpTime.add(time);
                            mUpType.add(type);
                        }

                        RecyclerViewAdapter newadapter3 = new RecyclerViewAdapter(mUpName, mUpUrl, mUpArt, mUpTime, mUpType,getActivity());
                        recyclerView.setAdapter(newadapter3);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    }

                    else {
                        Collections.sort(mUpNewType, Collections.reverseOrder());
                        mUpUrl.clear();
                        mUpName.clear();
                        mUpArt.clear();
                        mUpTime.clear();
                        mUpType.clear();

                        for (int j = 0 ;j < mUpNewType.size(); j++){
                            String type = mUpNewType.get(j);
                            List<String> tempList = typeMap.get(type);
                            String url = tempList.get(0);
                            String name = tempList.get(1);
                            String art = tempList.get(2);
                            String time = tempList.get(3);
                            String getType = tempList.get(4);
                            mUpUrl.add(url);
                            mUpName.add(name);
                            mUpArt.add(art);
                            mUpTime.add(time);
                            mUpType.add(getType);
                        }
                        RecyclerViewAdapter newadapter4 = new RecyclerViewAdapter(mUpName, mUpUrl, mUpArt, mUpTime, mUpType,getActivity());
                        recyclerView.setAdapter(newadapter4);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    }
                }

                else{
                    if(ordeChoice.equals("Event Name")){
                        Collections.sort(mUpName);
                        mUpUrl.clear();
                        mUpArt.clear();
                        mUpTime.clear();
                        mUpType.clear();

                        for (int j = 0 ;j < mUpName.size(); j++){
                            String name = mUpName.get(j);
                            List<String> tempList = nameMap.get(name);
                            String url = tempList.get(0);
                            String art = tempList.get(1);
                            String time = tempList.get(2);
                            String type = tempList.get(3);
                            mUpUrl.add(url);
                            mUpArt.add(art);
                            mUpTime.add(time);
                            mUpType.add(type);
                        }

                        RecyclerViewAdapter newadapter = new RecyclerViewAdapter(mUpName, mUpUrl, mUpArt, mUpTime, mUpType,getActivity());
                        recyclerView.setAdapter(newadapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    }

                    else if(ordeChoice.equals("Time")){
                        Collections.sort(mUpNewTime);
                        mUpUrl.clear();
                        mUpName.clear();
                        mUpArt.clear();
                        mUpType.clear();
                        mUpTime.clear();

                        for (int j = 0 ;j < mUpNewTime.size(); j++){
                            String time = mUpNewTime.get(j);
                            List<String> tempList = timeMap.get(time);
                            String url = tempList.get(0);
                            String name = tempList.get(1);
                            String art = tempList.get(2);
                            String type = tempList.get(3);
                            String anothertime = tempList.get(4);
                            mUpUrl.add(url);
                            mUpName.add(name);
                            mUpArt.add(art);
                            mUpType.add(type);
                            mUpTime.add(anothertime);
                        }

                        RecyclerViewAdapter newadapter2 = new RecyclerViewAdapter(mUpName, mUpUrl, mUpArt, mUpTime, mUpType,getActivity());
                        recyclerView.setAdapter(newadapter2);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    }

                    else if(ordeChoice.equals("Artist")){
                        Collections.sort(mUpArt);
                        mUpUrl.clear();
                        mUpName.clear();
                        mUpTime.clear();
                        mUpType.clear();

                        for (int j = 0 ;j < mUpArt.size(); j++){
                            String art = mUpArt.get(j);
                            List<String> tempList = artMap.get(art);
                            String url = tempList.get(0);
                            String name = tempList.get(1);
                            String time = tempList.get(2);
                            String type = tempList.get(3);
                            mUpUrl.add(url);
                            mUpName.add(name);
                            mUpTime.add(time);
                            mUpType.add(type);
                        }

                        RecyclerViewAdapter newadapter3 = new RecyclerViewAdapter(mUpName, mUpUrl, mUpArt, mUpTime, mUpType,getActivity());
                        recyclerView.setAdapter(newadapter3);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    }

                    else if(ordeChoice.equals("Type")){
                        Collections.sort(mUpNewType);
                        mUpUrl.clear();
                        mUpName.clear();
                        mUpArt.clear();
                        mUpTime.clear();
                        mUpType.clear();

                        for (int j = 0 ;j < mUpNewType.size(); j++){
                            String type = mUpNewType.get(j);
                            List<String> tempList = typeMap.get(type);
                            String url = tempList.get(0);
                            String name = tempList.get(1);
                            String art = tempList.get(2);
                            String time = tempList.get(3);
                            String getType = tempList.get(4);
                            mUpUrl.add(url);
                            mUpName.add(name);
                            mUpArt.add(art);
                            mUpTime.add(time);
                            mUpType.add(getType);
                        }

                        RecyclerViewAdapter newadapter4 = new RecyclerViewAdapter(mUpName, mUpUrl, mUpArt, mUpTime, mUpType,getActivity());
                        recyclerView.setAdapter(newadapter4);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        final String sender = getActivity().getIntent().getExtras().getString("VENUE");
        if(sender != null){
            this.receiveVenue();
            Toast.makeText(getActivity(), venuePlaceholder.getText().toString(), Toast.LENGTH_SHORT).show();

            final RequestQueue upcomingRequestQueue = Volley.newRequestQueue(getActivity());
            String upVenue = venuePlaceholder.getText().toString();
            String realVenue = upVenue.trim().replaceAll(" ", "+");
            String upVenueUrl = "http://androidbackendshawn666.us-west-1.elasticbeanstalk.com/upcomingevent?venue=" + realVenue;
            JsonObjectRequest upcomingObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, upVenueUrl, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String checkFailed = response.getString("failed");
                                if(checkFailed.equals("false")){
                                    noResult.setVisibility(View.GONE);
                                    upNameArray = response.getJSONArray("name");
                                    upUrlArray = response.getJSONArray("nameurl");
                                    upArtArray = response.getJSONArray("arts");
                                    upTimeArray = response.getJSONArray("date");
                                    upTypeArray = response.getJSONArray("type");

                                    Log.i("upname array", upNameArray.toString());
                                    Log.i("upurl array", upUrlArray.toString());
                                    Log.i("upart array", upArtArray.toString());
                                    Log.i("uptime array", upTimeArray.toString());
                                    Log.i("uptype array", upTypeArray.toString());

                                    for (int i = 0; i < upNameArray.length(); i++){
                                        String upname = upNameArray.getString(i);
                                        mUpName.add(upname);

                                        String upurl = upUrlArray.getString(i);
                                        mUpUrl.add(upurl);

                                        String upart = upArtArray.getString(i);
                                        mUpArt.add(upart);

                                        String uptime = upTimeArray.getString(i);

                                        String firsthalf = uptime.substring(0,uptime.indexOf(" "));
                                        String rest = uptime.substring(uptime.indexOf(" ") + 1, uptime.length());
                                        String secondhalf = rest.substring(0, uptime.indexOf(" ")+1);
                                        String newuptime = firsthalf +" " + secondhalf;
                                        newuptime = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("MMM dd,yyyy").parse(newuptime));

                                        //MMMMM d,yyyy MM
                                        mUpTime.add(uptime);
                                        mUpNewTime.add(newuptime);

                                        String uptype = upTypeArray.getString(i);
                                        String newtype = uptype + Integer.toString(i);
                                        mUpType.add(uptype);
                                        mUpNewType.add(newtype);

                                        List<String> tempStorageNoName = new ArrayList<>();
                                        tempStorageNoName.add(upurl);
                                        tempStorageNoName.add(upart);
                                        tempStorageNoName.add(uptime);
                                        tempStorageNoName.add(uptype);

                                        List<String> tempStorageNoArt = new ArrayList<>();
                                        tempStorageNoArt.add(upurl);
                                        tempStorageNoArt.add(upname);
                                        tempStorageNoArt.add(uptime);
                                        tempStorageNoArt.add(uptype);

                                        List<String> tempStorageNoTime = new ArrayList<>();
                                        tempStorageNoTime.add(upurl);
                                        tempStorageNoTime.add(upname);
                                        tempStorageNoTime.add(upart);
                                        tempStorageNoTime.add(uptype);
                                        tempStorageNoTime.add(uptime);

                                        List<String> tempStorageNoType = new ArrayList<>();
                                        tempStorageNoType.add(upurl);
                                        tempStorageNoType.add(upname);
                                        tempStorageNoType.add(upart);
                                        tempStorageNoType.add(uptime);
                                        tempStorageNoType.add(uptype);

                                        nameMap.put(upname, tempStorageNoName);
                                        artMap.put(upart,tempStorageNoArt);
                                        timeMap.put(newuptime,tempStorageNoTime);
                                        typeMap.put(newtype,tempStorageNoType);
                                    }
                                    recyclerView = rootview.findViewById(R.id.recycler);
                                    RecyclerViewAdapter adapter = new RecyclerViewAdapter(mUpName, mUpUrl, mUpArt, mUpTime, mUpType,getActivity());
                                    recyclerView.setAdapter(adapter);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                }

                                else{
                                    noResult.setVisibility(View.VISIBLE);
                                }

                            }
                            catch (JSONException e){
                                e.printStackTrace();
                                Log.e("error happened",e.toString());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("ERROR", "Error occurred ", error);
                            upcomingRequestQueue.stop();
                        }
                    });
            upcomingRequestQueue.add(upcomingObjectRequest);
        }


        return rootview;
    }


    private void receiveVenue(){
        Intent i = getActivity().getIntent();
        String venueReceived = i.getStringExtra("VENUE");

        venuePlaceholder.setText(venueReceived);
    }

}
