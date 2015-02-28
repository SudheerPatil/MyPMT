package mypmt.myapps.com.mypmt;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mypmt.myapps.com.adapters.RouteLabelAdapter;
import mypmt.myapps.com.customs.views.CustomAutoComplete;
import mypmt.myapps.com.loaders.RouteListLoader;
import mypmt.myapps.com.loaders.StopListLoader;
import mypmt.myapps.com.models.RouteInfo;


public class SearchActivity extends ActionBarActivity implements AdapterView.OnItemClickListener, android.support.v4.app.LoaderManager.LoaderCallbacks, View.OnClickListener {
    AutoCompleteTextView fromTextView, toTextView;
    CustomAutoComplete Rout_NumTextView;
    private ArrayAdapter<String> stops_adapter;
    private RouteLabelAdapter route_adapter;
    ImageButton swipe_btn;
    List<String> sList;
    List<RouteInfo> rList;
    /*String[] route_list = {"Aaundh", "Baner", "Chinchwad", "Dapodi", "E-Square", "Fursungi", "Gangadham", "Hadpsar", "Ingale vasti",
            "Jam mil", "Kalewadi", "Narayan Peth", "Pimpale Gurav", "Pimple Nilakh", "Ram Nagar", "Shivajinagar", "Telco", "Urali-Kanchan", "Vadawane", "Wakad"};*/
    private static int STOP_LIST_LOADER_ID = 1;
    private static int ROUTE_LIST_LOADER_ID = 2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        fromTextView = (AutoCompleteTextView) findViewById(R.id.fromTextView);
        toTextView = (AutoCompleteTextView) findViewById(R.id.toTextView);
        Rout_NumTextView = (CustomAutoComplete) findViewById(R.id.Rout_NumTextView);
        swipe_btn = (ImageButton) findViewById(R.id.swipe_btn);
        sList = new ArrayList<String>();
        rList = new ArrayList<RouteInfo>();

        stops_adapter = new ArrayAdapter<String>(SearchActivity.this, R.layout.autoc_label, sList);
        route_adapter = new RouteLabelAdapter(SearchActivity.this, rList);

        fromTextView.setAdapter(stops_adapter);
        toTextView.setAdapter(stops_adapter);

        Rout_NumTextView.setAdapter(route_adapter);
        /*fromTextView.addTextChangedListener(this);
        toTextView.addTextChangedListener(this);*/
        fromTextView.setOnItemClickListener(this);
        toTextView.setOnItemClickListener(this);
        Rout_NumTextView.setOnItemClickListener(this);

        swipe_btn.setOnClickListener(this);
//        new LoadStopsTask().execute("");
        getSupportLoaderManager().initLoader(STOP_LIST_LOADER_ID, null, this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        } else if (id == R.id.action_by_location) {

            Rout_NumTextView.setVisibility(View.GONE);
            fromTextView.setVisibility(View.VISIBLE);
            toTextView.setVisibility(View.VISIBLE);
            swipe_btn.setVisibility(View.VISIBLE);

            return true;
        } else if (id == R.id.action_by_route) {
            getSupportLoaderManager().initLoader(ROUTE_LIST_LOADER_ID, null, this);
            Rout_NumTextView.setVisibility(View.VISIBLE);
            fromTextView.setVisibility(View.GONE);
            toTextView.setVisibility(View.GONE);
            swipe_btn.setVisibility(View.GONE);
            // new JsonRouteListParser("").ParseJsonFile();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String item_selected = parent.getItemAtPosition(position).toString();

        Toast.makeText(getApplication(), item_selected, Toast.LENGTH_SHORT).show();

    }

    @Override
    public android.support.v4.content.Loader onCreateLoader(int id, Bundle args) {
        if (id == STOP_LIST_LOADER_ID)
            return new StopListLoader(this);
        else
            return new RouteListLoader(this);
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
            if (data != null) {
                if (loader.getId() == STOP_LIST_LOADER_ID)
                    stops_adapter.addAll((java.util.Collection<? extends String>) data);
                else if (loader.getId() == ROUTE_LIST_LOADER_ID)
                    route_adapter.addAll((java.util.Collection<? extends RouteInfo>) data);
            }

        } else {
            int i = 0;
            if (loader.getId() == STOP_LIST_LOADER_ID) {
                for (String s : (java.util.Collection<? extends String>) data)
                    stops_adapter.insert(s.toString(), i++);
            } else {
                for (RouteInfo s : (java.util.Collection<? extends RouteInfo>) data)
                    route_adapter.insert(s, i++);
            }
        }

    }


    @Override
    public void onLoaderReset(android.support.v4.content.Loader loader) {
        if (loader.getId() == STOP_LIST_LOADER_ID)
            stops_adapter.clear();
        else if (loader.getId() == ROUTE_LIST_LOADER_ID)
            route_adapter.clear();
    }


    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {

        if (R.id.swipe_btn == v.getId())
            swipeValues();

    }

    private void swipeValues() {
        Editable temp;
        if (fromTextView != null) {
            temp = fromTextView.getText();
            fromTextView.setText(toTextView.getText());
            toTextView.setText(temp);
        }

    }


}
