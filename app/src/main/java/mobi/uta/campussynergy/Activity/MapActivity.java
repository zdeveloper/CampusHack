package mobi.uta.campussynergy.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import mobi.uta.campussynergy.DataModel.Event;
import mobi.uta.campussynergy.R;

public class MapActivity extends ActionBarActivity implements OnMapReadyCallback {

    GoogleMap map;

    private ArrayList<Event> eventList = new ArrayList<>();


    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.fragment_map);
        queryParseForEvents();
        ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(39.935013, -100.283203), 10));


        drawTheMarkers();
    }

    HashMap<Marker, String> hashMap = new HashMap<>();

    boolean markersDrawn = false;
    void drawTheMarkers() {
        if (!eventList.isEmpty() && map != null && !markersDrawn) {
            markersDrawn = true;
            for (Event event : eventList) {

                MarkerOptions markerOptions = new MarkerOptions()
                        .position(new LatLng(event.getLocation().getLatitude(), event.getLocation().getLongitude()))
                        .title(event.getTitle());

                Marker m = map.addMarker(markerOptions);

                hashMap.put(m, event.getObjectId());

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {

                        Intent i = new Intent(context, ViewActivity.class);
                        i.putExtra(ViewActivity.EVENT_ID, hashMap.get(marker));
                        //Log.d("DEBUG map id", hashMap.get(marker));
                        context.startActivity(i);
                        return true;
                    }
                });

            }
        }
    }


    void queryParseForEvents() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Events");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                for (ParseObject object : parseObjects) {
                    Event event = new Event();
                    event.setTitle(object.getString("title"));
                    event.setObjectId(object.getObjectId());
                    event.setDesctiption(object.getString("description"));
                    event.setFb_author(object.getString("fb_author"));
                    event.setFb_page(object.getString("fb_page"));
                    event.setImg_url(object.getString("img_url"));
                    event.setType(object.getInt("type"));
                    event.setFb_page(object.getString("pageColor"));
                    event.setLocation(object.getParseGeoPoint("location"));

                    Calendar calendarStart = Calendar.getInstance();
                    calendarStart.setTime(object.getDate("startDate"));
                    event.setStartCal(calendarStart);

                    Calendar calendarEnd = Calendar.getInstance();
                    calendarEnd.setTime(object.getDate("endDate"));
                    event.setEndCal(calendarEnd);

                    eventList.add(event);
                    Log.d("DEBUG + MAP", "EVENT: " + event.getTitle() + " - DESCRIPTION: " + event.getDesctiption());
                }
                //ok we have all the events
                drawTheMarkers();
            }
        });
    }
}
