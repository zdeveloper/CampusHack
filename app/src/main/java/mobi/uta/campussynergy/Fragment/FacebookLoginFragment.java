package mobi.uta.campussynergy.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Request;
import com.facebook.RequestBatch;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphObject;
import com.facebook.widget.LoginButton;
import com.parse.ParseObject;

import java.util.Arrays;

import mobi.uta.campussynergy.Activity.MainActivity;
import mobi.uta.campussynergy.R;

/**
 * Created by Cameron on 2/7/15.
 */
public class FacebookLoginFragment extends Fragment implements View.OnClickListener {

    private Context context;
    private UiLifecycleHelper uiHelper;

    private Button bSkip;
    private TextView tv;

    public FacebookLoginFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        uiHelper = new UiLifecycleHelper(getActivity(), callback);
        uiHelper.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_facebook, container, false);
        context = view.getContext();

        LoginButton authButton = (LoginButton) view.findViewById(R.id.button_auth_facebook);
        authButton.setReadPermissions(Arrays.asList("user_friends"));
        authButton.setFragment(this);

        bSkip = (Button) view.findViewById(R.id.button_skip);
        bSkip.setOnClickListener(this);

        //TEMP
        tv = (TextView) view.findViewById(R.id.tv_temp);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_skip:
                getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new LoginInfoFragment())
                    .commit();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        uiHelper.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }

    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };

    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
            //userInfoTextView.setVisibility(View.VISIBLE);
            tv.setText("Logged in\n");

            String[] requestIds = {"me"};

            RequestBatch requestBatch = new RequestBatch();
            for (final String requestId : requestIds) {
                requestBatch.add(new Request(Session.getActiveSession(),
                        requestId, null, null, new Request.Callback() {
                    public void onCompleted(Response response) {
                        GraphObject graphObject = response.getGraphObject();
                        String s = "";
                        if (graphObject != null) {
                            if (graphObject.getProperty("id") != null) {
                                s = String.format("%s",
                                        graphObject.getProperty("id"));
                            }
                        }
                        SharedPreferences prefs = getActivity().getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString(getResources().getString(R.string.pref_facebook_id), s);
                        editor.commit();

                        //put in parse
                        ParseObject facebookid = new ParseObject("FacebookUsers");
                        facebookid.put("facebook_id", s);
                        facebookid.saveInBackground();

                        Intent i = new Intent(context, MainActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        getActivity().finish();

                    }
                }));
            }
            requestBatch.executeAsync();

        } else if (state.isClosed()) {
            //userInfoTextView.setVisibility(View.INVISIBLE);
        }
    }
}
