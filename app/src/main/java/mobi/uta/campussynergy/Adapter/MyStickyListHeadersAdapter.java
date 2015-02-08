package mobi.uta.campussynergy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import mobi.uta.campussynergy.DataModel.Event;
import mobi.uta.campussynergy.R;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by zedd on 2/7/15.
 */
public class MyStickyListHeadersAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private ArrayList<Event> events;
    private LayoutInflater inflater;
    private static int counter = 0;

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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        holder.time.setText(sdf.format(events.get(position).getStartCal().getTime()) + " - " + sdf.format(events.get(position).getStartCal().getTime()));

        return convertView;
    }

    static long headerId = -1;

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

        holder.text.setText(events.get(position).getHeader());
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