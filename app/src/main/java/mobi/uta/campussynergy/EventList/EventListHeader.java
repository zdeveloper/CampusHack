package mobi.uta.campussynergy.EventList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import mobi.uta.campussynergy.R;

/**
 * Created by Cameron on 2/8/15.
 */
public class EventListHeader implements EventListItem {

    private Context context;
    private String head;

    public EventListHeader(Context context, String head) {
        this.context = context;
        this.head = head;
    }

    private static class ViewHolder {
        TextView heading;
    }

    @Override
    public int getViewType() {
        return RowType.HEADER_ITEM.ordinal();
    }

    @Override
    public View getView(LayoutInflater inflater, View convertView) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.event_list_head, null);

            holder.heading = (TextView) convertView.findViewById(R.id.tv_event_head);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.heading.setText(head);


        return convertView;
    }
}
