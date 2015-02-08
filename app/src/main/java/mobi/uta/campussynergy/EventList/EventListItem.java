package mobi.uta.campussynergy.EventList;

import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Cameron on 2/8/15.
 */
public interface EventListItem {

    public enum RowType {
        LIST_ITEM,
        HEADER_ITEM
    }

    public int getViewType();
    public View getView(LayoutInflater inflater, View convertView);

}
