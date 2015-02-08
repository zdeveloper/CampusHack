package mobi.uta.campussynergy.EventList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import mobi.uta.campussynergy.Activity.ViewActivity;
import mobi.uta.campussynergy.DataModel.Event;
import mobi.uta.campussynergy.R;

/**
 * Created by Cameron on 2/8/15.
 */
public class EventListRow implements EventListItem  {

    private Context context;
    private Event event;

    public EventListRow(Context context, Event event) {
        this.context = context;
        this.event = event;
    }

    private static class ViewHolder {
        TextView title, time, date;
        String objectId;
        LinearLayout layout;
    }

    public Event getEvent(){
        return this.event;
    }


    @Override
    public int getViewType() {
        return EventListItem.RowType.LIST_ITEM.ordinal();
    }

    @Override
    public View getView(LayoutInflater inflater, View convertView) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.event_list_row, null);

            holder.title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.date = (TextView) convertView.findViewById(R.id.tv_date);
            holder.time = (TextView) convertView.findViewById(R.id.tv_time);
            holder.layout = (LinearLayout) convertView.findViewById(R.id.fuckyourspaguettecode);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        SimpleDateFormat time = new SimpleDateFormat("HH:MM");
        SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");


        holder.title.setText(event.getTitle());
        holder.objectId = event.getObjectId();
        holder.time.setText(time.format(event.getStartCal().getTime()) + " - " + event.getEndCal().getTime());
        holder.date.setText(date.format(event.getStartCal().getTime()));


        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ViewActivity.class);
                i.putExtra(ViewActivity.EVENT_ID, event.getObjectId());
                context.startActivity(i);
            }
        });


        return convertView;
    }
}
