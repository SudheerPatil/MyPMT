package mypmt.myapps.com.adapters;

import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * Created by android on 06-03-2015.
 */
public class CustomPageAdpter extends PagerAdapter {
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //here we have to return page title...
        return super.getPageTitle(position);
    }
}
