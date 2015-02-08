package mobi.uta.campussynergy.Fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import mobi.uta.campussynergy.Activity.MainActivity;
import mobi.uta.campussynergy.Adapter.MyStickyListHeadersAdapter;
import mobi.uta.campussynergy.DataModel.Preferences;
import mobi.uta.campussynergy.R;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class EventListFragment extends Fragment {

    private Preferences preferences;
    private StickyListHeadersListView expandableStickyList;
    public EventListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //get preferences
        preferences = ((MainActivity)getActivity()).preferences;

        View rootView =  inflater.inflate(R.layout.fragment_event_list, container, false);

        expandableStickyList = (StickyListHeadersListView) rootView.findViewById(R.id.fragment_sticky_list);
        MyStickyListHeadersAdapter adapter = new MyStickyListHeadersAdapter(getActivity(), preferences.getEvent());
        expandableStickyList.setAdapter(adapter);
        expandableStickyList.setOnHeaderClickListener(new StickyListHeadersListView.OnHeaderClickListener() {
            @Override
            public void onHeaderClick(StickyListHeadersListView l, View header, int itemPosition, long headerId, boolean currentlySticky) {

            }
        });

        return rootView;
    }

}