package com.example.shawnocked.eventsearch;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
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

import org.json.JSONException;
import org.json.JSONObject;

public class EventInfoFragment extends Fragment {

    TextView idReceiver;

    TextView eventAot;
    TextView eventAotText;
    RelativeLayout aotLayout;

    TextView eventVenueInfo;
    TextView eventVenueInfoText;
    RelativeLayout venueLayout;

    TextView eventTime;
    TextView eventTimeText;
    RelativeLayout timeLayout;

    TextView eventCate;
    TextView eventCateText;
    RelativeLayout cateLayout;

    TextView eventPrice;
    TextView eventPriceText;
    RelativeLayout priceLayout;

    TextView eventTicketStatus;
    TextView eventTicketStatusText;
    RelativeLayout statusLayout;

    TextView eventBuy;
    TextView eventBuyText;
    RelativeLayout buyLayout;

    TextView eventSeatmap;
    TextView eventSeatmapText;
    RelativeLayout seatMapLayout;

    String eventTitle;
    String eventAots;
    String eventVenueResponse;
    String eventTimeResponse;
    String eventCateResponse;
    String eventPriceResponse;
    String eventTicketStatusResponse;
    String eventBuyResponse;
    String eventSeatmapResponse;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_eventinfo, container, false);

        idReceiver = (TextView)rootview.findViewById(R.id.eventIdHolder);

        eventAot = (TextView)rootview.findViewById(R.id.aot);
        eventAotText = (TextView)rootview.findViewById(R.id.aottext);
        aotLayout = (RelativeLayout) rootview.findViewById(R.id.aot_layout);

        eventVenueInfo = (TextView)rootview.findViewById(R.id.venue_info);
        eventVenueInfoText = (TextView)rootview.findViewById(R.id.venue_info_text);
        venueLayout = (RelativeLayout) rootview.findViewById(R.id.venue_layout);

        eventTime = (TextView)rootview.findViewById(R.id.time_info);
        eventTimeText = (TextView) rootview.findViewById(R.id.time_info_text);
        timeLayout = (RelativeLayout) rootview.findViewById(R.id.time_layout);

        eventCate = (TextView) rootview.findViewById(R.id.category_info);
        eventCateText = (TextView) rootview.findViewById(R.id.category_info_text);
        cateLayout = (RelativeLayout) rootview.findViewById(R.id.category_layout);

        eventPrice = (TextView) rootview.findViewById(R.id.price_info);
        eventPriceText = (TextView) rootview.findViewById(R.id.price_info_text);
        priceLayout = (RelativeLayout) rootview.findViewById(R.id.price_layout);

        eventTicketStatus = (TextView) rootview.findViewById(R.id.ticket_status_info);
        eventTicketStatusText = (TextView) rootview.findViewById(R.id.ticket_status_info_text);
        statusLayout = (RelativeLayout) rootview.findViewById(R.id.status_layout);

        eventBuy = (TextView) rootview.findViewById(R.id.buy_ticket_info);
        eventBuyText = (TextView) rootview.findViewById(R.id.buy_ticket_info_text);
        buyLayout = (RelativeLayout) rootview.findViewById(R.id.buy_layout);

        eventSeatmap = (TextView) rootview.findViewById(R.id.seatmap_info);
        eventSeatmapText = (TextView) rootview.findViewById(R.id.seatmap_info_text);
        seatMapLayout = (RelativeLayout) rootview.findViewById(R.id.seatmap_layout);

        final String sender = getActivity().getIntent().getExtras().getString("TEST");

        if(sender != null){
            this.receiveDataTest();
//            Toast.makeText(getActivity(), idReceiver.getText().toString(), Toast.LENGTH_SHORT).show();

            final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            String theId = idReceiver.getText().toString();
            String detailUrl = "http://androidbackendshawn666.us-west-1.elasticbeanstalk.com/eventdetail?eventId=" + theId;
            JsonObjectRequest detailsObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, detailUrl, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                eventTitle = response.getString("title");
                                eventAots = response.getString("performers");
                                eventVenueResponse = response.getString("eventvenue");
                                eventTimeResponse = response.getString("eventtime");
                                eventCateResponse = response.getString("eventcategory");
                                eventPriceResponse = response.getString("eventprice");
                                eventTicketStatusResponse = response.getString("eventticketstatus");
                                eventBuyResponse = response.getString("eventticketurl");
                                eventSeatmapResponse = response.getString("eventseatmap");

                                //String performerReceive = response.getString("performers");

                                Log.i("title", eventTitle);
                                Log.i("aots", eventAots);
                                Log.i("venue", eventVenueResponse);
                                Log.i("time", eventTimeResponse);
                                Log.i("category", eventCateResponse);
                                Log.i("price", eventPriceResponse);
                                Log.i("ticketStatus", eventTicketStatusResponse);
                                Log.i("buyfrom", eventBuyResponse);
                                Log.i("seatmap", eventSeatmapResponse);


                                eventAotText.setText(eventAots);
                                if (eventVenueResponse.equals("null")){
                                    venueLayout.setVisibility(View.GONE);
                                }
                                else{
                                    venueLayout.setVisibility(View.VISIBLE);
                                    eventVenueInfoText.setText(eventVenueResponse);
                                }

                                if (eventTimeResponse.equals("null")){
                                    timeLayout.setVisibility(View.GONE);
                                }
                                else{
                                    timeLayout.setVisibility(View.VISIBLE);
                                    eventTimeText.setText(eventTimeResponse);
                                }

                                if (eventCateResponse.equals("null")){
                                    cateLayout.setVisibility(View.GONE);
                                }
                                else {
                                    cateLayout.setVisibility(View.VISIBLE);
                                    eventCateText.setText(eventCateResponse);
                                }

                                if(eventPriceResponse.equals("null")){
                                    priceLayout.setVisibility(View.GONE);
                                }
                                else{
                                    priceLayout.setVisibility(View.VISIBLE);
                                    eventPriceText.setText(eventPriceResponse);
                                }

                                eventTicketStatusText.setText(eventTicketStatusResponse);

                                eventBuyText.setMovementMethod(LinkMovementMethod.getInstance());
                                eventBuyText.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                                        browserIntent.setData(Uri.parse(eventBuyResponse));
                                        startActivity(browserIntent);
                                    }
                                });

                                eventSeatmapText.setMovementMethod(LinkMovementMethod.getInstance());
                                eventSeatmapText.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                                        browserIntent.setData(Uri.parse(eventSeatmapResponse));
                                        startActivity(browserIntent);
                                    }
                                });

                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                                Log.e("error happened",e.toString());
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("ERROR", "Error occurred ", error);
                            requestQueue.stop();
                        }

        });

            requestQueue.add(detailsObjectRequest);
        }
        return rootview;
    }

    private void receiveDataTest(){
        Intent i = getActivity().getIntent();
        String listItem = i.getStringExtra("TEST");

        idReceiver.setText(listItem);
        Log.i("received", idReceiver.getText().toString());
    }
}
