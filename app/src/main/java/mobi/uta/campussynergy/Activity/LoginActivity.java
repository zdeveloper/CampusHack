package mobi.uta.campussynergy.Activity;

import android.app.Activity;
import android.os.Bundle;

import mobi.uta.campussynergy.Fragment.FacebookLoginFragment;
import mobi.uta.campussynergy.R;

/**
 * Created by Cameron on 2/7/15.
 */
public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new FacebookLoginFragment())
                    .commit();
        }
    }
}
