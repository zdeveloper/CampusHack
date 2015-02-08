package mobi.uta.campussynergy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import mobi.uta.campussynergy.EventList.EventListItem;

/**
 * Created by Cameron on 2/8/15.
 */
public class EventListAdapter extends ArrayAdapter<EventListItem> {

    private Context context;
    private LayoutInflater mInflater;
    private List<EventListItem> items;

    public EventListAdapter(Context context, List<EventListItem> items) {
        super(context, 0, items);
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.items = items;
    }

    @Override
    public int getViewTypeCount() {
        return EventListItem.RowType.values().length;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getViewType();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        return getItem(position).getView(mInflater, convertView);
    }

}
