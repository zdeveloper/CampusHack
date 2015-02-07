package mobi.uta.campussynergy.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Toast;

import com.parse.Parse;

import mobi.uta.campussynergy.Fragment.FacebookLoginFragment;
import mobi.uta.campussynergy.R;

/**
 * Created by Cameron on 2/7/15.
 */
public class LoginActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //initialize parse
        Parse.initialize(this, "QuoI3WPv5g9LyP4awzhZEH8FvRKIgWgFEdFJSTmB", "DsDAvLDiDSLQ9VFOLRte3Ck7Yk1MmJONfeUWjZ5V");

        //If user has facebook ID stored
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String data = sharedPref.getString(getResources().getString(R.string.pref_facebook_id), "");

        if(data != "") {
            Intent i = new Intent(this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            finish();
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new FacebookLoginFragment())
                    .commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
