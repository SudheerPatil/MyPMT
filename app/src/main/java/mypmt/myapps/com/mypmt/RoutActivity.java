package mypmt.myapps.com.mypmt;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.List;

import mypmt.myapps.com.models.JsonRouteInfoParser;
import mypmt.myapps.com.models.RouteInfoComplete;


public class RoutActivity extends ActionBarActivity {
    ListView stopListview;
    TextView FromText, ToText, viaText;
    ImageView route_Bus_icon;
    JsonRouteInfoParser jsonRouteInfoParser;
    TableLayout route_timing_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rout);
        FromText = (TextView) findViewById(R.id.route_from_txt);
        ToText = (TextView) findViewById(R.id.route_to_txt);
        viaText = (TextView) findViewById(R.id.route_via_txt);
        route_Bus_icon = (ImageView) findViewById(R.id.rout_image);
        stopListview = (ListView) findViewById(R.id.rout_listview);
        route_timing_container = (TableLayout) findViewById(R.id.route_timings_container);
        jsonRouteInfoParser = new JsonRouteInfoParser(null);
        new LoadRouteInfoTask().execute(null);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rout, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }

    private class LoadRouteInfoTask extends AsyncTask {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object[] params) {
            jsonRouteInfoParser.ParseJsonFile(jsonRouteInfoParser);
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            //All initialization task will be done here!
            RouteInfoComplete routeInfoComplete = jsonRouteInfoParser.getRouteInfoComplete();
            if (routeInfoComplete == null)
                Log.i("Status:", "No information about Route available!");
            else {
                String HeadString = routeInfoComplete.getHead_title();
                String[] tokens = HeadString.split(" to ");
                if (HeadString != null) {
                    FromText.setText(tokens[0]);
                    String toString = tokens[1].replace("via " + routeInfoComplete.getVia_str0(), "");
                    ToText.setText(toString);
                }
                if (routeInfoComplete.getVia_str0() != null)
                    viaText.setText(routeInfoComplete.getVia_str0());
                if (routeInfoComplete.getTimings0() != null) {
                    List<String> timeL0 = routeInfoComplete.getTimings0();
                    int size = timeL0.size();
                    for (int i = 0; i < size / 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            //Adding row locgic is here...
                        }
                    }


                }
                if (routeInfoComplete.getTimings1() != null) {
                    List<String> timeL1 = routeInfoComplete.getTimings0();
                }

            }
        }
    }
}
