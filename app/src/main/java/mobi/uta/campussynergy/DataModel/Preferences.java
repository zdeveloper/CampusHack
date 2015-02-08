package mobi.uta.campussynergy.DataModel;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by zedd on 2/7/15.
 */
public class Preferences {
    private ArrayList<Event> eventList = new ArrayList<>();

    public static String pref_file = "MyPREFERENCES";

    public Preferences(){
        queryParseForEvents();
    }

    public ArrayList<Event> getEvent(){
        return eventList;
    }

    void queryParseForEvents() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Events");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                for (ParseObject object : parseObjects) {
                    Event event = new Event();
                    event.setTitle(object.getString("title"));
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


                    Calendar tomorrow = Calendar.getInstance();
                    tomorrow.set(Calendar.DAY_OF_MONTH, tomorrow.get(Calendar.DAY_OF_MONTH) + 1);

                    Calendar future = Calendar.getInstance();
                    future.set(Calendar.DAY_OF_MONTH, tomorrow.get(Calendar.DAY_OF_MONTH) + 2);

                    String headerText;
                    long headerId;
                    if(calendarStart.compareTo(tomorrow) < 0){
                        //put on today
                        headerText = "TODAY";
                        headerId = 0;
                        Log.d("Tag", "Setting it to today");
                    } else if(calendarStart.compareTo(tomorrow) >=0 && calendarStart.compareTo(future) <0){
                        //put on tomorrow
                        headerText = "TOMORROW";
                        headerId = 1;
                        Log.d("Tag", "Setting it to TOMORROW");
                    } else {
                        // put on future
                        headerText = "FUTURE";
                        headerId = 2;
                        Log.d("Tag", "Setting it to FUTURE");
                    }

                    event.setHeaderId(headerId);
                    event.setHeader(headerText);

                    eventList.add(event);
                    Log.d("DEBUG", "EVENT: " + event.getTitle() + " - DESCRIPTION: " + event.getDesctiption());
                }

            }
        });
    }
}
