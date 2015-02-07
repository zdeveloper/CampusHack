package mobi.uta.campussynergy.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import mobi.uta.campussynergy.R;

/**
 * Created by Cameron on 2/7/15.
 */
public class LoginLikesFragment extends Fragment{

    Button mButton1, mButton2;
    View mOverlay;
    public View.OnClickListener clicky = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    }

    public LoginLikesFragment() {}

    public static LoginLikesFragment newInstance() {
        return new LoginLikesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login_likes, container, false);
        mButton1 = (Button) root.findViewById(R.id.button1);
        mButton2 = (Button) root.findViewById(R.id.button2);
        mOverlay = root.findViewById(R.id.overlay);

        mButton1.setOnClickListener(clicky);
        mButton2.setOnClickListener(clicky);
        return root;
    }

    public View getOverlay() {
        return mOverlay;
    }

}
