package mypmt.myapps.com.mypmt;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
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
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import mypmt.myapps.com.loaders.StopListLoader;
import mypmt.myapps.com.models.JsonStopListParser;


public class SearchActivity extends ActionBarActivity implements TextWatcher, AdapterView.OnItemClickListener , android.support.v4.app.LoaderManager.LoaderCallbacks<List<String>> {
    AutoCompleteTextView fromTextView, toTextView, Rout_NumTextView;
    private ArrayAdapter<String> adapter;
    List<String> sList;
    String[] route_list = {"Aaundh", "Baner", "Chinchwad", "Dapodi", "E-Square", "Fursungi", "Gangadham", "Hadpsar", "Ingale vasti",
            "Jam mil", "Kalewadi", "Narayan Peth", "Pimpale Gurav", "Pimple Nilakh", "Ram Nagar", "Shivajinagar", "Telco", "Urali-Kanchan", "Vadawane", "Wakad"};
    private static int LOADER_ID=1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        fromTextView = (AutoCompleteTextView) findViewById(R.id.fromTextView);
        toTextView = (AutoCompleteTextView) findViewById(R.id.toTextView);
        Rout_NumTextView = (AutoCompleteTextView) findViewById(R.id.Rout_NumTextView);

         adapter = new ArrayAdapter<String>(SearchActivity.this, R.layout.autoc_label, sList);
        fromTextView.setAdapter(adapter);
        toTextView.setAdapter(adapter);
        /*fromTextView.addTextChangedListener(this);
        toTextView.addTextChangedListener(this);*/
        fromTextView.setOnItemClickListener(this);
        toTextView.setOnItemClickListener(this);
        Rout_NumTextView.addTextChangedListener(this);
//        new LoadStopsTask().execute("");
        getSupportLoaderManager().initLoader(LOADER_ID,null,this);
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
            return true;
        } else if (id == R.id.action_by_route) {

            Rout_NumTextView.setVisibility(View.VISIBLE);
            fromTextView.setVisibility(View.GONE);
            toTextView.setVisibility(View.GONE);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String item_selected = parent.getItemAtPosition(position).toString();

        Toast.makeText(getApplication(), item_selected, Toast.LENGTH_SHORT).show();

    }

    @Override
    public android.support.v4.content.Loader onCreateLoader(int id, Bundle args) {
        return new StopListLoader(this);
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<List<String>> loader, List<String> data) {
        adapter.addAll(data);
    }


    @Override
    public void onLoaderReset(android.support.v4.content.Loader loader) {
    adapter.clear();

    }


    /*class LoadStopsTask extends AsyncTask<String, Boolean, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            Gson gson = new Gson();
            File myDir;
            if (isExternalStorageReadable()) {
                String path = Environment.getExternalStorageDirectory().getPath();
                Log.i("File path:", path);
                myDir = new File(path + "/MyPMT/");
                if (!myDir.exists()) {
                    myDir.mkdirs();
                    Log.i("Dir", myDir.getPath() + " Created!");
                    // Toast.makeText(getApplication(),"Directory created!",Toast.LENGTH_SHORT).show();

                } else {
                    // Toast.makeText(getApplication(),"Directory already exist!",Toast.LENGTH_SHORT).show();
                    Log.i("Dir", myDir.getPath() + " already exist!");
                }

            }


            return null;
        }
    }
*/
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
}
