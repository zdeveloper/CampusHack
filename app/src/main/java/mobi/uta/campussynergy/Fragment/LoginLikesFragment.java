package mobi.uta.campussynergy.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import mobi.uta.campussynergy.R;

/**
 * Created by Cameron on 2/7/15.
 */
public class LoginLikesFragment extends Fragment {

    Button mButton1, mButton2;
    View mOverlay;

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
        return root;
    }

    public View getOverlay() {
        return mOverlay;
    }

    public String hitTest(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        if (x > mButton1.getLeft() && mButton1.getRight() > x &&
                y > mButton1.getTop() && mButton1.getBottom() > y)
        {
            return "Button 1";
        } else if (x > mButton2.getLeft() && mButton2.getRight() > x &&
                y > mButton2.getTop() && mButton2.getBottom() > y)
        {
            return "Button 2";
        } else {
            return "None";
        }
    }
}
