package com.example.shawnocked.eventsearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
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

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {

    TextView receiver;
    TextView noresult;
    RelativeLayout progressBar;

    ListView listView;

    JSONArray idArray;
    JSONArray timeArray;
    JSONArray nameArray;
    JSONArray categoryArray;
    JSONArray venueArray;

    ArrayList<String> idArrayList = new ArrayList<String>();
    ArrayList<String> nameArrayList = new ArrayList<String>();
    ArrayList<String> timeArrayList = new ArrayList<String>();
    ArrayList<String> venueArrayList = new ArrayList<String>();
    ArrayList<String> categoryArrayList = new ArrayList<String>();

    String[] id_array;
    String[] time_array;
    String[] name_array;
    Integer[] image_array;
    String[] venue_array;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        progressBar = findViewById(R.id.progress_bar);
        showProgressBar();

        noresult = findViewById(R.id.noresult);

        receiver = findViewById(R.id.receiver);
        final String sender = this.getIntent().getExtras().getString("SENDERKEY");

        if(sender != null)
        {
            this.receiveData();

//            Toast.makeText(this, "Received", Toast.LENGTH_SHORT).show();
            final RequestQueue requestQueue = Volley.newRequestQueue(this);
            String url = receiver.getText().toString();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>(){

                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                if(response.getString("fail").equals("empty")){
                                    hideProgressBar();
                                    listView = (ListView) findViewById(R.id.listview);
                                    listView.setVisibility(View.GONE);
                                    noresult.setVisibility(View.VISIBLE);
                                }
                                else {
                                    try {
                                        // http://androidbackendshawn666.us-west-1.elasticbeanstalk.com/route?keyword=Lakers&choice=All&distance=100&measurement=Miles&userLocation=37.421,-122.084

                                        idArray = response.getJSONArray("id");
                                        timeArray = response.getJSONArray("dateTime");
                                        nameArray = response.getJSONArray("name");
                                        categoryArray = response.getJSONArray("category");
                                        venueArray = response.getJSONArray("venue");
                                        Log.i("id array", idArray.toString());
                                        Log.i("time array", timeArray.toString());
                                        Log.i("name array", nameArray.toString());
                                        Log.i("category array", categoryArray.toString());
                                        Log.i("venue array", venueArray.toString());

                                        for(int i = 0; i < idArray.length(); i++){
                                            try {

                                                String idString = idArray.getString(i);
                                                idArrayList.add(idString);


                                                String nameString = nameArray.getString(i);
                                                nameArrayList.add(nameString);


                                                String timeString = timeArray.getString(i);
                                                timeArrayList.add(timeString);


                                                String venueString = venueArray.getString(i);
                                                venueArrayList.add(venueString);


                                                String categoryString = categoryArray.getString(i);
                                                categoryArrayList.add(categoryString);

                                            }
                                            catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        id_array = new String[idArrayList.size()];
                                        for(int j = 0; j < idArrayList.size(); j++){
                                            String id = idArrayList.get(j);
                                            id_array[j] = id;
                                        }

                                        name_array = new String[nameArrayList.size()];
                                        for(int j = 0; j < nameArrayList.size(); j++){
                                            String name = nameArrayList.get(j);
                                            name_array[j] = name;
                                        }

                                        time_array = new String[timeArrayList.size()];
                                        for(int j = 0; j < timeArrayList.size(); j++){
                                            String time = timeArrayList.get(j);
                                            time_array[j] = time;
                                        }

                                        venue_array = new String[venueArrayList.size()];
                                        for(int j = 0; j < venueArrayList.size(); j++){
                                            String venue = venueArrayList.get(j);
                                            venue_array[j] = venue;
                                        }

                                        image_array = new Integer[categoryArrayList.size()];
                                        for(int j = 0; j < categoryArrayList.size(); j++){
                                            String cate = categoryArrayList.get(j);
                                            if(cate.contains("Music") || cate.equals("Music") || cate.equals("music")){
                                                image_array[j] = R.drawable.music_icon;
                                            }
                                            else if(cate.contains("Sports") || cate.equals("Sports") || cate.equals("sports")){
                                                image_array[j] = R.drawable.sport_icon;
                                            }
                                            else if(cate.contains("Arts") || cate.equals("Arts & Theatre") || cate.equals("Arts&Theatre") ||cate.equals("arts & theatre") || cate.equals("arts&theatre")){
                                                image_array[j] = R.drawable.art_icon;
                                            }
                                            else if(cate.contains("Miscellaneous") || cate.equals("Miscellaneous") || cate.equals("miscellaneous")){
                                                image_array[j] = R.drawable.miscellaneous_icon;
                                            }
                                            else{
                                                image_array[j] = R.drawable.film_icon;
                                            }
                                        }

                                        hideProgressBar();
                                        CustomListAdapter listAdapter = new CustomListAdapter(EventActivity.this, id_array, name_array, venue_array, time_array,image_array);

                                        listView = (ListView) findViewById(R.id.listview);
                                        listView.setVisibility(View.VISIBLE);
                                        listView.setAdapter(listAdapter);

                                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                // handle listItem click here

                                                // listView.getItemAtPosition(i).toString()
                                                String event_id = ((TextView)(view.findViewById(R.id.event_id))).getText().toString();
                                                String eventnameReady = ((TextView)(view.findViewById(R.id.event_name))).getText().toString();
                                                String eventvenueReady = ((TextView)(view.findViewById(R.id.venue_place))).getText().toString();


                                                Intent intent = new Intent(EventActivity.this, DetailsActivity.class);
                                                intent.putExtra("TEST", event_id);
                                                intent.putExtra("NAME", eventnameReady);
                                                intent.putExtra("VENUE", eventvenueReady);

                                                if(eventnameReady.contains("vs.")){
                                                    String team1 = eventnameReady.substring(0, eventnameReady.indexOf(" vs"));
                                                    String team2 = eventnameReady.substring(eventnameReady.indexOf("s.")+ 3, eventnameReady.length());
                                                    intent.putExtra("TEAM1", team1);
                                                    intent.putExtra("TEAM2", team2);
                                                }
                                                else{
                                                    intent.putExtra("ART", eventnameReady);
                                                }

                                                EventActivity.this.startActivity(intent);

                                            }
                                        });

                                    }
                                    catch (JSONException e){
                                        e.printStackTrace();
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle Error
                            Log.e("ERROR", "Error occurred ", error);
                            requestQueue.stop();
                        }
                    });

            Log.i("jsObjRequest",  "" + jsonObjectRequest);
            requestQueue.add(jsonObjectRequest);

        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void receiveData(){
        Intent i = getIntent();
        String keyword = i.getStringExtra("KEYWORD");
        String distance = i.getStringExtra("DISTANCE");
        String category = i.getStringExtra("CATEGORY");
        String measurement = i.getStringExtra("MEASUREMENT");
        String locationChoice = i.getStringExtra("LOCATIONCHOICE");

        receiver.setText("http://androidbackendshawn666.us-west-1.elasticbeanstalk.com/route?keyword=" + keyword + "&choice=" + category + "&distance=" + distance + "&measurement=" + measurement + "&userLocation=" + locationChoice);
        Log.i("url", receiver.getText().toString());
    }

    // Show progress
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    // Hide progress
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }


}
