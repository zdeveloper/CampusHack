package mobi.uta.campussynergy.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import mobi.uta.campussynergy.R;

/**
 * Created by Cameron on 2/7/15.
 */
public class LoginInfoFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_info, container, false);

        Button bNext = (Button) view.findViewById(R.id.button_next);
        Button bBack = (Button) view.findViewById(R.id.button_back);

        bNext.setOnClickListener(this);
        bBack.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_next:
                if(isInfoValidated()) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new LoginLikesFragment())
                        .commit();
                }
                break;
            case R.id.button_back:
                getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new FacebookLoginFragment())
                    .commit();
                break;
        }
    }

    private boolean isInfoValidated() {
        //fix dis shit
        return true;
    }
}
