package mobi.uta.campussynergy.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import mobi.uta.campussynergy.DataModel.Event;
import mobi.uta.campussynergy.R;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by zedd on 2/7/15.
 */
public class MyStickyListHeadersAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private ArrayList<Event> events;
    private LayoutInflater inflater;

    public MyStickyListHeadersAdapter(Context context, ArrayList<Event> events) {
        inflater = LayoutInflater.from(context);
        this.events = events;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.event_list_fragment_title);
            holder.time = (TextView) convertView.findViewById(R.id.event_list_fragment_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(events.get(position).getTitle());
        //SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        holder.time.setText(sdf.format(events.get(position).getStartTime()) + " - " + sdf.format(events.get(position).getEndTime()));

        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.list_header, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.event_list_fragment_header);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        //set header text as first char in name
        Date today = new Date();
        Date tommorrow = new Date();
        tommorrow.setDate( today.getDate() + 1);

        Date future = new Date();
        future.setDate( tommorrow.getDate() + 1);


        Log.d( "DEBUG", "Today: " + today.toString() + " \nTomorrow " + tommorrow.toString() + " \nFuture " + future.toString());

        String headerText;
        long headerId;
        if(events.get(position).getStartTime().before(today) == true){
            //put on today
            headerText = "TODAY";
            headerId = 0;
            Log.d("Tag", "Setting it to today");

        } else if(events.get(position).getStartTime().before(tommorrow) == true){
            //put on tomorrow
            headerText = "TOMORROW";
            headerId =1;
            Log.d("Tag", "Setting it to TOMORROW");
        } else {
            // put on future
            headerText = "FUTURE";
            headerId =2;
            Log.d("Tag", "Setting it to FUTURE");
        }

        events.get(position).setHeaderId(headerId);
        holder.text.setText(headerText);
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        //return the first character of the country as ID because this is what headers are based upon
        return events.get(position).getHeaderId();
    }

    class HeaderViewHolder {
        TextView text;
    }

    class ViewHolder {
        TextView title, time;
    }

}