package mypmt.myapps.com.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mypmt.myapps.com.models.RouteInfo;
import mypmt.myapps.com.mypmt.R;

/**
 * Created by android on 24-02-2015.
 */
public class RouteLabelAdapter extends BaseAdapter implements Filterable {
    List<RouteInfo> rList;
    Context mContext;
    LayoutInflater inflater;
    static Filter filter;

    public RouteLabelAdapter(Context mContext, List<RouteInfo> rList) {
        this.mContext = mContext;
        this.rList = rList;
        this.inflater = LayoutInflater.from(this.mContext);
    }

    @Override
    public int getCount() {
        return rList.size();
    }

    @Override
    public Object getItem(int position) {
        return rList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        lbelViewHolder lbelViewHolder;

        if (convertView == null) {
            lbelViewHolder = new lbelViewHolder();

            convertView = inflater.inflate(R.layout.autoc_route_label, parent, false);

            lbelViewHolder.rout_num_txt = (TextView) convertView.findViewById(R.id.autoc_route_num);
            lbelViewHolder.rout_fromto_txt = (TextView) convertView.findViewById(R.id.autoc_route_fromto_txt);
            convertView.setTag(lbelViewHolder);
        } else {
            lbelViewHolder = (lbelViewHolder) convertView.getTag();
        }
        RouteInfo r_info = rList.get(position);
        if (r_info != null) {
            lbelViewHolder.rout_num_txt.setText(r_info.getRoute_num());
            lbelViewHolder.rout_fromto_txt.setText(r_info.getFrom_str());
        }

        return convertView;
    }


    @Override
    public Filter getFilter() {
        if (filter == null)
            return filter = new MyFilter();
        return filter;
    }

    public void addAll(Collection<? extends RouteInfo> collection) {

        if (rList != null) {
            rList.addAll(collection);
        }
        notifyDataSetChanged();
    }

    public void clear() {
        if (rList != null)
            rList.clear();

    }

    private static class lbelViewHolder {
        TextView rout_num_txt;
        TextView rout_fromto_txt;
    }

    public void insert(RouteInfo object, int index) {

        if (rList != null) {
            rList.add(index, object);

        }
        notifyDataSetChanged();
    }

    private class MyFilter extends Filter {

        ArrayList<RouteInfo> tempList;
        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            // this will be done in different thread
            // so you could even download this data from internet
            Log.i("Filter", "Performing Filtering...");
            Log.i("prefix",prefix.toString());
            FilterResults results = new FilterResults();
            if (rList == null)
                rList = new ArrayList<RouteInfo>();
            tempList = new ArrayList<RouteInfo>();
            if (prefix == null || prefix.length() == 0) {
                notifyDataSetChanged();
                ArrayList<RouteInfo> allMatching = (ArrayList<RouteInfo>) rList;//new ArrayList<RouteInfo>();
                results.values = allMatching;
                results.count = allMatching.size();
                tempList = (ArrayList<RouteInfo>)rList;
                System.out.println(results.values.toString());
            } else {
                String prefixString = prefix.toString().toLowerCase();
                ArrayList<RouteInfo> values;
                values = new ArrayList<RouteInfo>(tempList);//doubt
                final int count = values.size();
                final ArrayList<RouteInfo> newValues = new ArrayList<RouteInfo>();
                for (int i = 0; i < count; i++) {
                    final RouteInfo value = values.get(i);
                    final String valueText = value.toString().toLowerCase();
                    // First match against the whole, non-splitted value
                    if (valueText.startsWith(prefixString)) {
                        newValues.add(value);
                    } else {
                        final String[] words = valueText.split(" ");
                        final int wordCount = words.length;

                        // Start at index 0, in case valueText starts with space(s)
                        for (int k = 0; k < wordCount; k++) {
                            if (words[k].startsWith(prefixString)) {
                                newValues.add(value);
                                break;
                            }
                        }
                    }
                }

                results.values = newValues;
                results.count = newValues.size();
                System.out.println(results.values.toString());
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            Log.i("Filter:", "Publishing Filtered Result!");
            if(tempList!=null)
                tempList.clear();
            tempList = (ArrayList<RouteInfo>) results.values;
            System.out.println(rList.toString());
            if (results.count > 0) {
                notifyDataSetChanged();
                System.out.println(rList.toString());
            } else {
                notifyDataSetInvalidated();
            }
        }
    }

}
