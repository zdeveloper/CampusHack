package mobi.uta.campussynergy.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.lang.reflect.Array;
import java.util.ArrayList;

import mobi.uta.campussynergy.R;

/**
 * Created by James_Motha_Fucka on 2/7/15.
 */
public class LoginLikesFragment extends Fragment{

    ArrayList<Boolean> picked;
    ImageButton buttonGreekLife, buttonTech, buttonAcademics, buttonReligion, buttonStudyGroups, buttonMusic;
    View mOverlay;
    public View.OnClickListener clicky = new View.OnClickListener() {


        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.button1:
                    toggle(0);
                    break;
                case R.id.button2:
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

                    break;
            }
        }
    };

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
        picked = new ArrayList<>();

        picked.add(Boolean.FALSE);
        picked.add(Boolean.FALSE);
        picked.add(Boolean.FALSE);
        picked.add(Boolean.FALSE);
        picked.add(Boolean.FALSE);
        picked.add(Boolean.FALSE);

        View root = inflater.inflate(R.layout.fragment_login_likes, container, false);
        buttonGreekLife = (ImageButton) root.findViewById(R.id.button1);
        buttonTech = (ImageButton) root.findViewById(R.id.button2);
        buttonAcademics = (ImageButton) root.findViewById(R.id.button3);
        buttonReligion = (ImageButton) root.findViewById(R.id.button4);
        buttonStudyGroups = (ImageButton) root.findViewById(R.id.button5);
        buttonMusic = (ImageButton) root.findViewById(R.id.button6);
        mOverlay = root.findViewById(R.id.overlay);

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

    public void toggle(int check){

        if (picked.get(check) == Boolean.TRUE){

            picked.get(check).equals(Boolean.FALSE);
        }
        else{
            picked.get(check).equals(Boolean.TRUE);
        }

    }

    public ArrayList<Boolean> getPicked(){

        return picked;
    }



}
