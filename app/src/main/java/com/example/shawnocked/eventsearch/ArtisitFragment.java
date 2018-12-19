package com.example.shawnocked.eventsearch;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ArtisitFragment extends Fragment {

    TextView temp1;
    TextView temp2;

    LinearLayout teamLayout;
    TextView team1Name;
    ImageView team1Pic1;
    ImageView team1Pic2;
    ImageView team1Pic3;
    ImageView team1Pic4;
    ImageView team1Pic5;
    ImageView team1Pic6;
    ImageView team1Pic7;
    ImageView team1Pic8;

    TextView team2Name;
    ImageView team2Pic1;
    ImageView team2Pic2;
    ImageView team2Pic3;
    ImageView team2Pic4;
    ImageView team2Pic5;
    ImageView team2Pic6;
    ImageView team2Pic7;
    ImageView team2Pic8;

    LinearLayout otherLayout;
    TextView art1Name;
    TextView firstName;
    TextView firstNameText;
    TextView art1Follwer;
    TextView art1FollwerText;
    TextView art1Popularity;
    TextView art1PopularityText;
    TextView check1;
    TextView check1Text;

    ImageView art1Pic1;
    ImageView art1Pic2;
    ImageView art1Pic3;
    ImageView art1Pic4;
    ImageView art1Pic5;
    ImageView art1Pic6;
    ImageView art1Pic7;
    ImageView art1Pic8;

    TextView art2Name;
    TextView secondName;
    TextView secondNameText;
    TextView art2Follwer;
    TextView art2FollwerText;
    TextView art2Popularity;
    TextView art2PopularityText;
    TextView check2;
    TextView check2Text;

    ImageView art2pic1;
    ImageView art2pic2;
    ImageView art2pic3;
    ImageView art2pic4;
    ImageView art2pic5;
    ImageView art2pic6;
    ImageView art2pic7;
    ImageView art2pic8;

    JSONArray team1Arr;
    JSONArray team2Arr;

    JSONArray art1Arr;
    JSONArray art2Arr;
    JSONArray photosArr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_artisit, container, false);

        temp1 =(TextView) rootview.findViewById(R.id.tempurl);
        temp2 =(TextView) rootview.findViewById(R.id.tempurl2);

        teamLayout = (LinearLayout) rootview.findViewById(R.id.teamlayout);
        team1Name = (TextView) rootview.findViewById(R.id.team1);
        team2Name = (TextView) rootview.findViewById(R.id.team2);

        team1Pic1 = (ImageView) rootview.findViewById(R.id.team1pic1);
        team1Pic2 = (ImageView) rootview.findViewById(R.id.team1pic2);
        team1Pic3 = (ImageView) rootview.findViewById(R.id.team1pic3);
        team1Pic4 = (ImageView) rootview.findViewById(R.id.team1pic4);
        team1Pic5 = (ImageView) rootview.findViewById(R.id.team1pic5);
        team1Pic6 = (ImageView) rootview.findViewById(R.id.team1pic6);
        team1Pic7 = (ImageView) rootview.findViewById(R.id.team1pic7);
        team1Pic8 = (ImageView) rootview.findViewById(R.id.team1pic8);

        team2Pic1 = (ImageView) rootview.findViewById(R.id.team2pic1);
        team2Pic2 = (ImageView) rootview.findViewById(R.id.team2pic2);
        team2Pic3 = (ImageView) rootview.findViewById(R.id.team2pic3);
        team2Pic4 = (ImageView) rootview.findViewById(R.id.team2pic4);
        team2Pic5 = (ImageView) rootview.findViewById(R.id.team2pic5);
        team2Pic6 = (ImageView) rootview.findViewById(R.id.team2pic6);
        team2Pic7 = (ImageView) rootview.findViewById(R.id.team2pic7);
        team2Pic8 = (ImageView) rootview.findViewById(R.id.team2pic8);

        otherLayout = (LinearLayout) rootview.findViewById(R.id.otherlayout);
        art1Name = (TextView) rootview.findViewById(R.id.art1);
        art2Name = (TextView) rootview.findViewById(R.id.art2);

        firstName = (TextView) rootview.findViewById(R.id.art_name);
        art1Follwer = (TextView) rootview.findViewById(R.id.art_follower);
        art1Popularity = (TextView) rootview.findViewById(R.id.art_popularity);
        check1 = (TextView) rootview.findViewById(R.id.art_check);
        firstNameText = (TextView) rootview.findViewById(R.id.art_name_text);
        art1FollwerText = (TextView) rootview.findViewById(R.id.art_follower_text);
        art1PopularityText = (TextView) rootview.findViewById(R.id.art_popularity_text);
        check1Text = (TextView) rootview.findViewById(R.id.art_check_text);

        secondName = (TextView) rootview.findViewById(R.id.art_name2);
        art2Follwer = (TextView) rootview.findViewById(R.id.art_follower2);
        art2Popularity = (TextView) rootview.findViewById(R.id.art_popularity2);
        check2 = (TextView) rootview.findViewById(R.id.art_check2);
        secondNameText = (TextView) rootview.findViewById(R.id.art_name_text2);
        art2FollwerText = (TextView) rootview.findViewById(R.id.art_follower_text2);
        art2PopularityText = (TextView) rootview.findViewById(R.id.art_popularity_text2);
        check2Text = (TextView) rootview.findViewById(R.id.art_check_text2);

        art1Pic1 = (ImageView) rootview.findViewById(R.id.art1pic1);
        art1Pic2 = (ImageView) rootview.findViewById(R.id.art1pic2);
        art1Pic3 = (ImageView) rootview.findViewById(R.id.art1pic3);
        art1Pic4 = (ImageView) rootview.findViewById(R.id.art1pic4);
        art1Pic5 = (ImageView) rootview.findViewById(R.id.art1pic5);
        art1Pic6 = (ImageView) rootview.findViewById(R.id.art1pic6);
        art1Pic7 = (ImageView) rootview.findViewById(R.id.art1pic7);
        art1Pic8 = (ImageView) rootview.findViewById(R.id.art1pic8);

        art2pic1 = (ImageView) rootview.findViewById(R.id.art2pic1);
        art2pic2 = (ImageView) rootview.findViewById(R.id.art2pic2);
        art2pic3 = (ImageView) rootview.findViewById(R.id.art2pic3);
        art2pic4 = (ImageView) rootview.findViewById(R.id.art2pic4);
        art2pic5 = (ImageView) rootview.findViewById(R.id.art2pic5);
        art2pic6 = (ImageView) rootview.findViewById(R.id.art2pic6);
        art2pic7 = (ImageView) rootview.findViewById(R.id.art2pic7);
        art2pic8 = (ImageView) rootview.findViewById(R.id.art2pic8);

        final String team1 = getActivity().getIntent().getExtras().getString("TEAM1");
        final String team2 = getActivity().getIntent().getExtras().getString("TEAM2");

        if(team1 != null && team2 != null){
            otherLayout.setVisibility(View.GONE);
            teamLayout.setVisibility(View.VISIBLE);
            team1Name.setText(team1);
            team2Name.setText(team2);
            String cleanTeam1 = team1.trim().replaceAll(" ", "+");
            String cleanTeam2 = team2.trim().replaceAll(" ", "+");
            final RequestQueue artisitRequestQueue = Volley.newRequestQueue(getActivity());
            String artUrl = "http://androidbackendshawn666.us-west-1.elasticbeanstalk.com/customizedsearch2?q=" + cleanTeam1 + "&q2=" + cleanTeam2 + "&cx=004394602356333104116:vfroutcovcg&imgSize=huge&imgType=news&num=8&searchType=image&key=AIzaSyBlMveOdqCYRAHF-JabEw1nesTH0q7XMLA";
            JsonObjectRequest artisitObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, artUrl, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                team1Arr = response.getJSONArray("team1");
                                team2Arr = response.getJSONArray("team2");

                                for(int i = 0; i < team1Arr.length(); i++){
                                    String picUrl1 = team1Arr.getString(i);
                                    String picUrl2 = team2Arr.getString(i);

                                    if(i == 0){
                                        Picasso.get().load(picUrl1).into(team1Pic1);
                                        Picasso.get().load(picUrl2).into(team2Pic1);
                                    }
                                    else if(i == 1){
                                        Picasso.get().load(picUrl1).into(team1Pic2);
                                        Picasso.get().load(picUrl2).into(team2Pic2);
                                    }
                                    else if(i == 2){
                                        Picasso.get().load(picUrl1).into(team1Pic3);
                                        Picasso.get().load(picUrl2).into(team2Pic3);
                                    }
                                    else if(i == 3){
                                        Picasso.get().load(picUrl1).into(team1Pic4);
                                        Picasso.get().load(picUrl2).into(team2Pic4);
                                    }
                                    else if(i == 4){
                                        Picasso.get().load(picUrl1).into(team1Pic5);
                                        Picasso.get().load(picUrl2).into(team2Pic5);
                                    }
                                    else if(i == 5){
                                        Picasso.get().load(picUrl1).into(team1Pic6);
                                        Picasso.get().load(picUrl2).into(team2Pic6);
                                    }
                                    else if(i == 6){
                                        Picasso.get().load(picUrl1).into(team1Pic7);
                                        Picasso.get().load(picUrl2).into(team2Pic7);
                                    }
                                    else if(i == 7){
                                        Picasso.get().load(picUrl1).into(team1Pic8);
                                        Picasso.get().load(picUrl2).into(team2Pic8);
                                    }
                                }

                            }catch (JSONException e){
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("ERROR", "Error occurred ", error);
                            artisitRequestQueue.stop();
                        }
                    });

            artisitRequestQueue.add(artisitObjectRequest);
        }

        else{
            final String artisit = getActivity().getIntent().getExtras().getString("ART");
            if(artisit != null){
                teamLayout.setVisibility(View.GONE);
                otherLayout.setVisibility(View.VISIBLE);

                String cleanArtisit;
                if (artisit.contains("gaga") || artisit.contains("Gaga") || artisit.contains("GAGA")){
                    cleanArtisit = "Lady+Gaga";
                }
                else if(artisit.contains("pink") || artisit.contains("Pink") || artisit.contains("PINK")){
                    cleanArtisit = "Pink";
                }
                else if(artisit.contains("west") || artisit.contains("West") || artisit.contains("WEST")){
                    cleanArtisit = "Kanye+West";
                }
                else if(artisit.contains("cardi") || artisit.contains("Cardi") || artisit.contains("CARDI")){
                    cleanArtisit = "Cardi+B";
                }
                else if(artisit.contains("taylor") || artisit.contains("Taylor") || artisit.contains("TAYLOR")){
                    cleanArtisit = "Taylor+Swift";
                }
                else if(artisit.contains("bieber") || artisit.contains("Bieber") || artisit.contains("BIEBER")){
                    cleanArtisit = "Justin+Bieber";
                }
                else{
                    cleanArtisit = artisit;
                }
                final RequestQueue artisitRequestQueue = Volley.newRequestQueue(getActivity());
                String artUrl = "http://androidbackendshawn666.us-west-1.elasticbeanstalk.com/musichandle?artist=" + cleanArtisit + "&cx=004394602356333104116:vfroutcovcg&imgSize=huge&imgType=news&num=8&searchType=image&key=AIzaSyBlMveOdqCYRAHF-JabEw1nesTH0q7XMLA";
                JsonObjectRequest artisitObjectRequest = new JsonObjectRequest
                        (Request.Method.GET, artUrl, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String checkcheck = response.getString("check");
                                    if(checkcheck.equals("false")){
                                        art1Arr = response.getJSONArray("art1");
                                        art2Arr = response.getJSONArray("art2");
                                        photosArr = response.getJSONArray("photo");

                                        String tempUrl;
                                        for (int i = 0; i < art1Arr.length(); i++){
                                            if(i == 0){
                                                String tempname = art1Arr.getString(i);
                                                firstNameText.setText(tempname);
                                                art1Name.setText(tempname);
                                            }
                                            else if(i == 1){
                                                String tempfollower = art1Arr.getString(i);
                                                art1FollwerText.setText(tempfollower);
                                            }
                                            else if(i == 2){
                                                String temppopularity = art1Arr.getString(i);
                                                art1PopularityText.setText(temppopularity);
                                            }
                                            else if(i == 3){
                                                tempUrl = art1Arr.getString(i);
                                                temp1.setText(tempUrl);
                                            }
                                        }
                                        check1Text.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                                                browserIntent.setData(Uri.parse(temp1.getText().toString()));
                                                startActivity(browserIntent);
                                            }
                                        });

                                        for(int i = 0; i < 8; i++){
                                            String picUrl1 = photosArr.getString(i);
                                            if(i == 0){
                                                Picasso.get().load(picUrl1).into(art1Pic1);
                                            }
                                            else if(i == 1){
                                                Picasso.get().load(picUrl1).into(art1Pic2);
                                            }
                                            else if(i == 2){
                                                Picasso.get().load(picUrl1).into(art1Pic3);
                                            }
                                            else if(i == 3){
                                                Picasso.get().load(picUrl1).into(art1Pic4);
                                            }
                                            else if(i == 4){
                                                Picasso.get().load(picUrl1).into(art1Pic5);
                                            }
                                            else if(i == 5){
                                                Picasso.get().load(picUrl1).into(art1Pic6);
                                            }
                                            else if(i == 6){
                                                Picasso.get().load(picUrl1).into(art1Pic7);
                                            }
                                            else if(i == 7){
                                                Picasso.get().load(picUrl1).into(art1Pic8);
                                            }
                                        }

                                        String tempUrl2;
                                        for (int i = 0; i < art2Arr.length(); i++){
                                            if(i == 0){
                                                String tempname = art2Arr.getString(i);
                                                secondNameText.setText(tempname);
                                                art2Name.setText(tempname);
                                            }
                                            else if(i == 1){
                                                String tempfollower = art2Arr.getString(i);
                                                art2FollwerText.setText(tempfollower);
                                            }
                                            else if(i == 2){
                                                String temppopularity = art2Arr.getString(i);
                                                art2PopularityText.setText(temppopularity);
                                            }
                                            else if(i == 3){
                                                tempUrl2 = art2Arr.getString(i);
                                                temp2.setText(tempUrl2);
                                            }
                                        }
                                        check2Text.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                                                browserIntent.setData(Uri.parse(temp2.getText().toString()));
                                                startActivity(browserIntent);
                                            }
                                        });

                                        for(int i = 8; i < photosArr.length(); i++){
                                            String picUrl2 = photosArr.getString(i);

                                            if(i == 8){
                                                Picasso.get().load(picUrl2).into(art2pic1);
                                            }
                                            else if(i == 9){
                                                Picasso.get().load(picUrl2).into(art2pic2);
                                            }
                                            else if(i == 10){
                                                Picasso.get().load(picUrl2).into(art2pic3);
                                            }
                                            else if(i == 11){
                                                Picasso.get().load(picUrl2).into(art2pic4);
                                            }
                                            else if(i == 12){
                                                Picasso.get().load(picUrl2).into(art2pic5);
                                            }
                                            else if(i == 13){
                                                Picasso.get().load(picUrl2).into(art2pic6);
                                            }
                                            else if(i == 14){
                                                Picasso.get().load(picUrl2).into(art2pic7);
                                            }
                                            else if(i == 15){
                                                Picasso.get().load(picUrl2).into(art2pic8);
                                            }
                                        }

                                        Log.i("art1", art1Arr.toString());
                                        Log.i("art2", art2Arr.toString());
                                        Log.i("photos", photosArr.toString());
                                    }
                                    else{
                                        otherLayout.setVisibility(View.GONE);
                                        temp1.setText("No Record");
                                        temp1.setVisibility(View.VISIBLE);
                                    }

                                }
                                catch (JSONException e){
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("ERROR", "Error occurred ", error);
                                artisitRequestQueue.stop();
                            }
                        });
                artisitRequestQueue.add(artisitObjectRequest);
            }

        }

        return rootview;
    }
}
