package mobi.uta.campussynergy.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import mobi.uta.campussynergy.DataModel.Preferences;
import mobi.uta.campussynergy.R;

/**
 * Created by Cameron on 2/7/15.
 */
public class ViewActivity extends ActionBarActivity {

    public static final String EVENT_ID = "EventIndex";

    TextView title, description, hour;
    ImageView cover;
    Button goingBtn;
    Context context;

    ParseObject event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        context = this;
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Events");
        query.getInBackground(getIntent().getStringExtra(EVENT_ID), new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, com.parse.ParseException e) {

                if (e == null) {
                    // object will be your game score
                    event = parseObject;
                    displayEvent( parseObject);
                    goingBtn.setVisibility(View.VISIBLE);
                } else {
                    // something went wrong
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }
            }

        });

        title = (TextView) findViewById(R.id.activity_view_title);
        description = (TextView) findViewById(R.id.activity_view_description);
        hour = (TextView) findViewById(R.id.activity_view_hours);
        cover = (ImageView) findViewById(R.id.activity_view_description_cover);
        goingBtn = (Button) findViewById(R.id.activity_view_goingbtn);

        goingBtn.setVisibility(View.GONE);
        goingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showLoading(true);
                //event is database, ignore, else put in data base
                SharedPreferences prefs = getSharedPreferences(Preferences.pref_file, Context.MODE_PRIVATE);
                final String userId = prefs.getString(getResources().getString(R.string.pref_facebook_id), "TEST");

                ParseQuery<ParseObject> query = ParseQuery.getQuery("RSVP");
                query.whereEqualTo("user_id", userId);
                query.whereEqualTo("event_id", event.getObjectId().toString());
                query.getFirstInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject parseObject, com.parse.ParseException e) {
                        if (e != null) {
                            //ok so item not in database so add it
                            ParseObject rsvp = new ParseObject("RSVP");
                            rsvp.put("user_id", userId);
                            rsvp.put("event_id", event.getObjectId().toString());
                            rsvp.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(com.parse.ParseException e) {
                                    //addToCalendar();
                                    showLoading(false);
                                }
                            });
                        } else {
                            //addToCalendar();
                            showLoading(false);
                        }
                    }
                });
            }
        });
    }


    void showLoading(boolean val) {
        goingBtn.setEnabled(!val);
        if (val) {
            goingBtn.setText("Loading...");
        } else {
            goingBtn.setText("Going");
        }
    }

    void addToCalendar() {

        Calendar calendarStart = Calendar.getInstance();
        calendarStart.setTime(event.getDate("startDate"));

        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(event.getDate("endDate"));

        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", calendarStart.getTimeInMillis());
        intent.putExtra("endTime", calendarEnd.getTimeInMillis());
        intent.putExtra("title", event.getString("title"));
        intent.putExtra("description", event.getString("description"));
        intent.putExtra("eventLocation", event.getParseGeoPoint("location").getLatitude() + "," + event.getParseGeoPoint("location").getLongitude());
        startActivity(intent);
    }


    void displayEvent(final ParseObject event) {
        ((MapFragment) getFragmentManager().findFragmentById(R.id.activity_view_map)).getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                //add map marker
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(event.getParseGeoPoint("location").getLatitude(), event.getParseGeoPoint("location").getLongitude()))
                        .title(event.getString("title")));

                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(event.getParseGeoPoint("location").getLatitude(), event.getParseGeoPoint("location").getLongitude()), 10));
            }
        });


        //setData
        title.setText(event.getString("title"));
        description.setText(event.getString("description"));
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yyyy");
        hour.setText(sdf.format(event.getDate("startDate").getTime()) + " - " + sdf.format(event.getDate("endDate").getTime()) + "\n" + sdf2.format(event.getDate("startDate").getTime()));

        //get image
        Picasso.with(context).load(event.getString("img_url")).into(cover);

    }
}
