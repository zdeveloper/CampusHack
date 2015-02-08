package mobi.uta.campussynergy.Adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import mobi.uta.campussynergy.Fragment.EventListFragment;
import mobi.uta.campussynergy.Fragment.FriendsFragment;
import mobi.uta.campussynergy.Fragment.RecomendedFragment;

/**
 * Created by zedd on 2/7/15.
 */
public class TabsPagerAdapter extends FragmentStatePagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                return new RecomendedFragment();
            case 1:
                return new EventListFragment();
            case 2:
                return new FriendsFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }

}