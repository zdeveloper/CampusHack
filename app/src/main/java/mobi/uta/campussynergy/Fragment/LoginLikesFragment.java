package mobi.uta.campussynergy.Fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Button;

import java.util.ArrayList;

import mobi.uta.campussynergy.Activity.MainActivity;
import mobi.uta.campussynergy.R;

/**
 * Created by Jstaud on 2/7/15.
 */
public class LoginLikesFragment extends Fragment {

    ArrayList<Boolean> picked;
    Button buttonGreekLife, buttonTech, buttonAcademics, buttonReligion, buttonStudyGroups, buttonMusic;
    View mOverlay;
    private Context context;

    public View.OnClickListener clicky = new View.OnClickListener() {


        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                /*case R.id.button1:
                    toggle(buttonGreekLife);
                    break;*/
                /*case R.id.button2:
                    toggle(1);
                    break;
                case R.id.button3:
                    toggle(2);
                    break;
                case R.id.button4:
                    toggle(3);

                    break;
                case R.id.button5:
                    toggle(4);
                    break;
                case R.id.button6:
                    toggle(5);
                    break;*/

                case R.id.button_back:
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, new LoginInfoFragment())
                                .commit();
                    break;
                case R.id.button_next:
                    if(isInfoValidated()) {
                        Intent i = new Intent(context, MainActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        getActivity().finish();
                    }
                    break;
            }
        }
    };

    public LoginLikesFragment() {
    }

    public static LoginLikesFragment newInstance() {
        return new LoginLikesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login_likes, container, false);
        context = root.getContext();

        Button bNext = (Button) root.findViewById(R.id.button_next);
        Button bBack = (Button) root.findViewById(R.id.button_back);

        bNext.setOnClickListener(clicky);
        bBack.setOnClickListener(clicky);

        buttonGreekLife = (Button) root.findViewById(R.id.button1);
        buttonGreekLife.setEnabled(false);
        buttonTech = (Button) root.findViewById(R.id.button2);
        buttonAcademics = (Button) root.findViewById(R.id.button3);
        buttonReligion = (Button) root.findViewById(R.id.button4);
        buttonStudyGroups = (Button) root.findViewById(R.id.button5);
        buttonMusic = (Button) root.findViewById(R.id.button6);

        buttonGreekLife.setOnClickListener(clicky);
        buttonTech.setOnClickListener(clicky);
        buttonAcademics.setOnClickListener(clicky);
        buttonReligion.setOnClickListener(clicky);
        buttonStudyGroups.setOnClickListener(clicky);
        buttonMusic.setOnClickListener(clicky);
        return root;
    }

    public View getOverlay() {
        return mOverlay;
    }

    public void toggle(Button check){


    }

    public ArrayList<Boolean> getPicked(){

        return picked;
    }

    private boolean isInfoValidated() {
        //Fix dis shit
        return true;
    }


}
