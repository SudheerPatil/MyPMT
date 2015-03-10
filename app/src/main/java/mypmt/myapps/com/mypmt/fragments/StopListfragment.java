package mypmt.myapps.com.mypmt.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import mypmt.myapps.com.mypmt.R;

/**
 * Created by Sudheer on 08-03-2015.
 */
public class StopListfragment extends ListFragment {
    List<String> stopList;
    ListAdapter listAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stop_list_fragment_layout, container, false);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stopList = new ArrayList<String>();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void setStopList(List<String> stopList) {
        this.stopList.addAll(stopList);
        listAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, stopList);
        setListAdapter(listAdapter);
        Log.i("No of items newly inserted:", String.valueOf(listAdapter.getCount()));

    }

    public ListAdapter getAdapter() {
        return this.getListAdapter();
    }
}
