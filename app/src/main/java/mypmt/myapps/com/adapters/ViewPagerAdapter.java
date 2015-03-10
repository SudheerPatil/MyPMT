package mypmt.myapps.com.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import mypmt.myapps.com.loaders.StopListLoader;
import mypmt.myapps.com.mypmt.fragments.StopListfragment;
import mypmt.myapps.com.mypmt.fragments.TimeListfragment;

/**
 * Created by Sudheer on 08-03-2015.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    CharSequence[] titles;
    int NumofTabs;
    StopListfragment stopListfragment;
    TimeListfragment timeListfragment;

    public ViewPagerAdapter(FragmentManager fm, CharSequence[] titles, int NumofTabs) {
        super(fm);
        this.titles = titles;
        this.NumofTabs = NumofTabs;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            /*if (stopListfragment == null)
                stopListfragment = new StopListfragment();
            return stopListfragment;*/
            StopListfragment.newInstance();
        } else {
            if (timeListfragment == null)
                timeListfragment = new TimeListfragment();
            return timeListfragment;
        }

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.titles[position];
    }

    @Override
    public int getCount() {
        return NumofTabs;
    }

    public void setStopLists(List<String> stopLists) {
        stopListfragment.setStopList(stopLists);
        notifyDataSetChanged();
    }

    public void setTimeLists(List<String> timeLists) {
        timeListfragment.setTimeList(timeLists);
        notifyDataSetChanged();
    }

}
