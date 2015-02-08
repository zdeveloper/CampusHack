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
                    event.setType(object.getString("type"));
                    event.setFb_page(object.getString("pageColor"));

                    Calendar calendarStart = Calendar.getInstance();
                    calendarStart.setTime(object.getDate("startDate"));
                    event.setStartCal(calendarStart);

                    Calendar calendarEnd = Calendar.getInstance();
                    calendarEnd.setTime(object.getDate("endDate"));
                    event.setEndCal(calendarEnd);

                    eventList.add(event);
                    Log.d("DEBUG", "EVENT: " + event.getTitle() + " - DESCRIPTION: " + event.getDesctiption());
                }

            }
        });
    }
}
