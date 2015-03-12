package mypmt.myapps.com.mypmt.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mypmt.myapps.com.customs.views.FlowLayout;
import mypmt.myapps.com.mypmt.R;

/**
 * Created by Sudheer on 08-03-2015.
 */
public class TimeListfragment extends Fragment {
    List<String> timeList;
    FlowLayout flowlayout;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.time_list_fragment_layout, container, false);

        flowlayout = (FlowLayout) view.findViewById(R.id.flowlayout);
        return view;
    }

    public void setTimeList(List<String> timeLists) {
        if (this.timeList != null)
        {
            if(flowlayout.getChildCount()<=this.timeList.size())
            {
                flowlayout.removeAllViewsInLayout();
            }
            this.timeList.clear();

        }
        this.timeList.addAll(timeLists);
        if (!this.timeList.isEmpty()) {
            for (String timeString : this.timeList) {
                FlowLayout.LayoutParams lp = new FlowLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                // LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.setMargins(4, 2, 4, 2);

                TextView tText = new TextView(getActivity());
                tText.setLayoutParams(lp);
                tText.setPadding(4, 4, 4, 4);
                tText.setTextAppearance(getActivity(), android.R.attr.textAppearanceMedium);
                tText.setBackgroundResource(R.drawable.rounded_corner);
                tText.setTextColor(Color.WHITE);
                tText.setText(timeString);
                flowlayout.addView(tText);
            }
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        timeList = new ArrayList<String>();


    }
}
