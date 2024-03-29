package mobi.uta.campussynergy.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import mobi.uta.campussynergy.Activity.MainActivity;
import mobi.uta.campussynergy.Adapter.EventListAdapter;
import mobi.uta.campussynergy.DataModel.Event;
import mobi.uta.campussynergy.DataModel.Preferences;
import mobi.uta.campussynergy.EventList.EventListItem;
import mobi.uta.campussynergy.EventList.EventListRow;
import mobi.uta.campussynergy.R;


public class RecomendedFragment extends Fragment {

    private Preferences preferences;
    private ListView lv;

    private EventListAdapter arrayAdapter;
    private ArrayList<EventListItem> eventList;

    private Context context;

    public RecomendedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_event_list, container, false);
        context = rootView.getContext();

        eventList = new ArrayList<EventListItem>();

        //get preferences
        preferences = ((MainActivity)getActivity()).preferences;

        lv = (ListView) rootView.findViewById(R.id.lv_events);
        queryParseForEvents();

        return rootView;
    }

    void queryParseForEvents() {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(5) + 1;

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Events");
        query.setLimit(randomInt);
        query.addAscendingOrder("location");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                for (ParseObject object : parseObjects) {
                    Event event = new Event();
                    event.setObjectId(object.getObjectId());
                    event.setTitle(object.getString("title"));
                    event.setDesctiption(object.getString("description"));
                    event.setFb_author(object.getString("fb_author"));
                    event.setFb_page(object.getString("fb_page"));
                    event.setImg_url(object.getString("img_url"));
                    event.setType(object.getInt("type"));
                    event.setFb_page(object.getString("pageColor"));

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

                    eventList.add(new EventListRow(context, event));
                    Log.d("DEBUG", "EVENT: " + event.getTitle() + " - DESCRIPTION: " + event.getDesctiption());
                }

                arrayAdapter = new EventListAdapter(context, eventList);
                lv.setAdapter(arrayAdapter);
            }
        });
    }
}
