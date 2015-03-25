package mypmt.myapps.com.mypmt;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mypmt.myapps.com.adapters.RouteInfoAdapter;
import mypmt.myapps.com.adapters.RouteLabelAdapter;
import mypmt.myapps.com.customs.views.CustomAutoComplete;
import mypmt.myapps.com.loaders.RouteListLoader;
import mypmt.myapps.com.loaders.StopListLoader;
import mypmt.myapps.com.models.JsonRouteListParser;
import mypmt.myapps.com.models.JsonStopListParser;
import mypmt.myapps.com.models.RouteInfo;


public class SearchActivity extends ActionBarActivity implements AdapterView.OnItemClickListener, android.support.v4.app.LoaderManager.LoaderCallbacks, View.OnClickListener, TextWatcher {
    AutoCompleteTextView fromTextView, toTextView;
    CustomAutoComplete Rout_NumTextView;
    private ArrayAdapter<String> stops_adapter;
    private RouteLabelAdapter route_adapter;
    ImageButton swipe_btn;
    List<String> sList;
    JsonRouteListParser jsonRouteListParser;
    List<RouteInfo> rList;
    ArrayList<RouteInfo> searchList;
    ListView route_listview;
    RouteInfoAdapter routeInfoAdapter;
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
        route_listview = (ListView) findViewById(R.id.route_listview);
        sList = new ArrayList<String>();
        rList = new ArrayList<RouteInfo>();
        searchList = new ArrayList<RouteInfo>();
        stops_adapter = new ArrayAdapter<String>(SearchActivity.this, R.layout.autoc_label, sList);
        route_adapter = new RouteLabelAdapter(SearchActivity.this, rList);

        fromTextView.setAdapter(stops_adapter);
        toTextView.setAdapter(stops_adapter);

        Rout_NumTextView.setAdapter(route_adapter);
        fromTextView.addTextChangedListener(this);
        toTextView.addTextChangedListener(this);
        fromTextView.setOnItemClickListener(this);
        toTextView.setOnItemClickListener(this);
        Rout_NumTextView.setOnItemClickListener(this);

        swipe_btn.setOnClickListener(this);
//        new LoadStopsTask().execute("");
        getSupportLoaderManager().initLoader(STOP_LIST_LOADER_ID, null, this);

        routeInfoAdapter = new RouteInfoAdapter(this, searchList);
        route_listview.setAdapter(routeInfoAdapter);
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
        if (fromTextView.getText().length() > 0 && fromTextView.getText().length() > 0) {
            Log.i("AutoComplte textview Status :", "Not Empty");
            CharSequence from = fromTextView.getText();
            CharSequence to = toTextView.getText();
            SearchLists(from, to);

        } else
            Toast.makeText(this, "Incomplete information!", Toast.LENGTH_SHORT).show();

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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (fromTextView.getText().length() > 0 && fromTextView.getText().length() > 0) {
            Log.i("AutoComplte textview Status :", "Not Empty");
            CharSequence from = fromTextView.getText();
            CharSequence to = toTextView.getText();

        }
    }

    /*
    * @from text is from Stop
    * @to text is To Stop
    * */
    private void SearchLists(CharSequence from, CharSequence to) {
        /*if (jsonRouteListParser == null)
            jsonRouteListParser = new JsonRouteListParser("");
        try {
            jsonRouteListParser.ParseJsonFile();
        } catch (IOException e) {
            Toast.makeText(this, "File may be damaged or moved!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (ParseException e) {
            Toast.makeText(this, "Invalid data! Can not parse!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }catch (ArrayIndexOutOfBoundsException e){
            Toast.makeText(this, "Array Index out of bound exception!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        if (!TextUtils.isEmpty(from) && !TextUtils.isEmpty(to))
            new updateListTask().execute(jsonRouteListParser.getRoute_list());
        */

    }

    private class updateListTask extends AsyncTask<List<RouteInfo>, Void, Void> {
        String fromText;
        String toText;
        List<RouteInfo> PublishinList;
        HashMap<String, String> hashMap;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            fromText = fromTextView.getText().toString();
            toText = toTextView.getText().toString();
            PublishinList = new ArrayList<RouteInfo>();
            JsonStopListParser jsonStopListParser = new JsonStopListParser(null);
            if (hashMap != null) {
                hashMap = jsonStopListParser.getStop_url_map();
                Log.i("Hash Map::", hashMap.toString());
            }
        }

        @Override
        protected Void doInBackground(List<RouteInfo>... params) {
           /* RouteInfo tempinfo = new RouteInfo(null, fromText, toText);
            if (params[0] != null) {
                List<RouteInfo> routeInfosbkList = params[0];
                for (RouteInfo r : routeInfosbkList) {
                    if (r.equals(tempinfo)) {
                        PublishinList.add(r);
                    }

                }
            }
            */
            //Checking both stops details are present or not? if yes match all possible records or else download that details...


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (route_listview != null) {
                if (PublishinList.size() > 0) {
                    routeInfoAdapter.setSearchList(PublishinList);
                }
                //set thiis publishing result adapter of listview
            }

        }
    }
}
