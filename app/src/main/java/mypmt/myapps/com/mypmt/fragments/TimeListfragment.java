package mypmt.myapps.com.mypmt.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mypmt.myapps.com.mypmt.R;

/**
 * Created by Sudheer on 08-03-2015.
 */
public class TimeListfragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.time_list_fragment_layout,container,false);
        return  view;
    }
}
