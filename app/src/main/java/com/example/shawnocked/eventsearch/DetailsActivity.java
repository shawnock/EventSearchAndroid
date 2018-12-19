package com.example.shawnocked.eventsearch;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = "DetailsActivity";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    ImageView toolbarFav;
    ImageView toolbarTwitter;
    TextView spaceCreater;

    Toolbar toolbar;
    TabLayout tabLayout;



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        final int[] ICONS = new int[]{
                R.drawable.event_outline,
                R.drawable.artisit_outline,
                R.drawable.venue_outline,
                R.drawable.upcoming_outline
        };

        // toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout) findViewById(R.id.details_tabs);

        tabLayout.getTabAt(0).setIcon(ICONS[0]);
        tabLayout.getTabAt(1).setIcon(ICONS[1]);
        tabLayout.getTabAt(2).setIcon(ICONS[2]);
        tabLayout.getTabAt(3).setIcon(ICONS[3]);

        spaceCreater = (TextView) findViewById(R.id.onlyforspace);

        final String sender = this.getIntent().getExtras().getString("NAME");
        if(sender != null){
            if (sender.length() > 21){
                spaceCreater.setVisibility(View.GONE);
                String cutSender = sender.substring(0, 21);
                cutSender = cutSender + "...";
                toolbar.setTitle(cutSender);
            }
            else {
                spaceCreater.setVisibility(View.INVISIBLE);
                toolbar.setTitle(sender);
            }
        }
        toolbarFav = (ImageView) findViewById(R.id.toolbar_fav);
        toolbarFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String viewTag = String.valueOf(toolbarFav.getTag());
                if (viewTag.equals("white1")){
                    toolbarFav.setImageResource(R.drawable.heart_fill_red);
                    toolbarFav.setTag("red1");
                }
                else {
                    toolbarFav.setImageResource(R.drawable.heart_fill_white);
                    toolbarFav.setTag("white1");
                }
            }
        });

        toolbarTwitter = (ImageView) findViewById(R.id.toolbar_twitter);
        toolbarTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tweetIntent = null;

                try {
                    // get the Twitter app if possible
                    DetailsActivity.this.getPackageManager().getPackageInfo("com.twitter.android", 0);
                    tweetIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/shawnock666"));
                    tweetIntent.putExtra(Intent.EXTRA_TEXT, "This is a Test.");
                    tweetIntent.setType("text/plain");
                    tweetIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (Exception e) {
                    // no Twitter app, revert to browser
                    tweetIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/google"));
                }
                DetailsActivity.this.startActivity(tweetIntent);

            }

        });

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // set up viewPager with the section adapter
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.details_tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new EventInfoFragment(), "EVENT");
        adapter.addFragment(new ArtisitFragment(), "ARTIST(S)");
        adapter.addFragment(new VenueDetailsFragment(), "VENUE");
        adapter.addFragment(new UpcomingFragment(), "UPCOMING");
        viewPager.setAdapter(adapter);
    }

}
