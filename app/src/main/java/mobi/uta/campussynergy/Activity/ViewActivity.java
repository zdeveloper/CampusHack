package mobi.uta.campussynergy.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import mobi.uta.campussynergy.R;

/**
 * Created by Cameron on 2/7/15.
 */
public class ViewActivity extends ActionBarActivity {

    public static final String EVENT_ID = "id";

    private int eventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        eventId = getIntent().getExtras().getInt(EVENT_ID);
    }
}
