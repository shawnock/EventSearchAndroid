package com.example.shawnocked.eventsearch;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomListAdapter extends ArrayAdapter {
    //to reference the Activity
    private final Activity context;

    //to store the animal images
    private final Integer[] imageIDarray;

    //to store the list of name
    private final String[] nameArray;

    //to store the list of venue
    private final String[] venueArray;

    //to store the list of venue
    private final String[] timeArray;

    //to store the list of eventId
    private final String[] idArray;

    public CustomListAdapter(Activity context, String[] idArrayParam, String[] nameArrayParam, String[] venueArrayParam, String[] timeArrayParam, Integer[] imageIDArrayParam){

        super(context,R.layout.listview_row , nameArrayParam);
        this.context=context;
        this.idArray = idArrayParam;
        this.imageIDarray = imageIDArrayParam;
        this.nameArray = nameArrayParam;
        this.venueArray = venueArrayParam;
        this.timeArray = timeArrayParam;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview_row, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView idTextField = (TextView) rowView.findViewById(R.id.event_id);
        final TextView nameTextField = (TextView) rowView.findViewById(R.id.event_name);
        final TextView venueTextField = (TextView) rowView.findViewById(R.id.venue_place);
        final TextView dateTextField = (TextView) rowView.findViewById(R.id.date_time);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.imageview1);
        final ImageView favImage = (ImageView) rowView.findViewById(R.id.list_fav);

        favImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String viewTag = String.valueOf(view.getTag());
                String getName = nameTextField.getText().toString();
                String toastNote1 = getName + "was added to favorites";
                String toastNote2 = getName + "was removed from favorites";

                if (viewTag.equals("white")){
                    favImage.setImageResource(R.drawable.heart_fill_red);
                    favImage.setTag("red");
                    Toast.makeText(getContext(), toastNote1, Toast.LENGTH_SHORT).show();
                }
                else {
                    favImage.setImageResource(R.drawable.heart_outline_black);
                    favImage.setTag("white");
                    Toast.makeText(getContext(), toastNote2, Toast.LENGTH_SHORT).show();
                }
            }
        });

        //this code sets the values of the objects to values from the arrays
        idTextField.setText(idArray[position]);
        nameTextField.setText(nameArray[position]);
        venueTextField.setText(venueArray[position]);
        dateTextField.setText(timeArray[position]);
        imageView.setImageResource(imageIDarray[position]);

        return rowView;

    };

}

