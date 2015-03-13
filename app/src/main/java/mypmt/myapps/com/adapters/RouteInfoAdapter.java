package mypmt.myapps.com.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mypmt.myapps.com.models.RouteInfo;
import mypmt.myapps.com.mypmt.R;

/**
 * Created by android on 13-03-2015.
 */
public class RouteInfoAdapter extends BaseAdapter {
    Activity activity;
    List<RouteInfo> infoList;
    private static LayoutInflater inflater;

    public RouteInfoAdapter(Activity activity, ArrayList<RouteInfo> infoList) {
        this.infoList = infoList;
        this.activity = activity;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        return this.infoList.size();
    }

    @Override
    public RouteInfo getItem(int position) {
        return infoList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.custom_routinfo_label, null);
            holder = new ViewHolder();
            holder.fromtextview = (TextView) convertView.findViewById(R.id.fromtextview);
            holder.totextview = (TextView) convertView.findViewById(R.id.totextview);
            holder.viatextview = (TextView) convertView.findViewById(R.id.viatextview);
            holder.routnumtextview = (TextView) convertView.findViewById(R.id.routnumtextview);
            convertView.setTag(holder);

        } else
            holder = (ViewHolder) convertView.getTag();
        if(infoList.size()>0)
        {
            RouteInfo temp_info =infoList.get(position);
            holder.routnumtextview.setText(temp_info.getRoute_num());
            holder.fromtextview.setText(temp_info.getFrom_str());
            holder.totextview.setText(temp_info.getTo_str());
            holder.viatextview.setText(temp_info.getVia_str());
        }


        return convertView;
    }

    /**
     * ****** Create a holder Class to contain inflated xml file elements ********
     */
    public static class ViewHolder {

        public TextView fromtextview;
        public TextView totextview;
        public TextView viatextview;
        public TextView routnumtextview;

    }
    public void setSearchList(List<RouteInfo> search_list){
        if(this.infoList!=null)
        {
            this.infoList.clear();
            this.infoList.addAll(search_list);
        }
        notifyDataSetChanged();

    }
}
