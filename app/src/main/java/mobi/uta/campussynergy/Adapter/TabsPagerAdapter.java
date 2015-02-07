package mobi.uta.campussynergy.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import mobi.uta.campussynergy.Fragment.MapViewFragment;
import mobi.uta.campussynergy.Fragment.RecomendedFragment;
import mobi.uta.campussynergy.Fragment.ViewEventFragment;

/**
 * Created by zedd on 2/7/15.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                return new RecomendedFragment();
            case 1:
                return new ViewEventFragment();
            case 2:
                return new MapViewFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }

}