package mobi.uta.campussynergy.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.parse.ParseObject;
import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.SimpleFacebookConfiguration;
import com.sromku.simple.fb.entities.Profile;
import com.sromku.simple.fb.listeners.OnFriendsListener;
import com.sromku.simple.fb.listeners.OnLoginListener;

import java.util.List;

import mobi.uta.campussynergy.Adapter.TabsPagerAdapter;
import mobi.uta.campussynergy.DataModel.Preferences;
import mobi.uta.campussynergy.Fragment.EventListFragment;
import mobi.uta.campussynergy.Fragment.LoginLikesFragment;
import mobi.uta.campussynergy.Fragment.RecomendedFragment;
import mobi.uta.campussynergy.R;

public class MainActivity extends ActionBarActivity implements
        ActionBar.TabListener {

    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    private LoginLikesFragment loginFrag;

    public static Preferences preferences;
    private static final int CODE_QR = 289;

    SimpleFacebook mSimpleFacebook;

    // Tab titles
    private String[] tabs = {"All", "Recommended", "Friends"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initilization
        preferences = new Preferences();
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getSupportActionBar();
        mAdapter = new TabsPagerAdapter( getFragmentManager());

        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }

        getFragmentManager().beginTransaction()
                .replace(R.id.main_container, new EventListFragment())
                .commit();


        /**
         * on swiping the viewpager make respective tab selected
         * */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

        viewPager.setCurrentItem(1);




        //FACEBOOK STUFF

        Permission[] permissions = new Permission[] {
                Permission.USER_FRIENDS,
                Permission.EMAIL,
                Permission.USER_ABOUT_ME
        };

        SimpleFacebookConfiguration configuration = new SimpleFacebookConfiguration.Builder()
                .setAppId("1521984621398779")
                .setNamespace("mobicampus")
                .setPermissions(permissions)
                .build();
        SimpleFacebook.setConfiguration(configuration);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:

                return true;

            case R.id.action_qr:
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
                startActivityForResult(intent, CODE_QR);
                return true;

            case R.id.action_map_view:
                Intent i = new Intent(this, MapActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        mSimpleFacebook.onActivityResult(this, requestCode, resultCode, intent);

        if (requestCode == CODE_QR) {
            if (resultCode == RESULT_OK) {

                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");

                String mobiString = "mobi.campussynergy://";

                if(contents.startsWith(mobiString)) {
                    String id = contents.substring(mobiString.length());
                    Intent i = new Intent(getBaseContext(), ViewActivity.class);
                    i.putExtra(ViewActivity.EVENT_ID, id);
                    startActivity(i);
                }
            }
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {
        // on tab selected
        // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

    }


    @Override
    public void onResume() {
        super.onResume();
        mSimpleFacebook = SimpleFacebook.getInstance(this);
        mSimpleFacebook.login(onLoginListener);
    }

    OnLoginListener onLoginListener = new OnLoginListener() {
        @Override
        public void onLogin() {
            // change the state of the button or do whatever you want
            Log.i("debug", "Logged in");
            mSimpleFacebook.getFriends(onFriendsListener);
        }

        @Override
        public void onNotAcceptingPermissions(Permission.Type type) {
            // user didn't accept READ or WRITE permission
            Log.w("debug", String.format("You didn't accept %s permissions", type.name()));
        }

        @Override
        public void onThinking() {

        }

        @Override
        public void onException(Throwable throwable) {

        }

        @Override
        public void onFail(String s) {

        }
    };

    OnFriendsListener onFriendsListener = new OnFriendsListener() {
        @Override
        public void onComplete(List<Profile> friends) {
            Log.i("debug", "Number of friends = " + friends.size());
            for(Profile friend : friends){

                Log.i("debug", "Friend = " + friend.getId() + " - " + friend.getBio() );


            }
        }
    };


}