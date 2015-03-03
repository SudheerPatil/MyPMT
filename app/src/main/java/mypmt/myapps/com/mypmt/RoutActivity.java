package mypmt.myapps.com.mypmt;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import mypmt.myapps.com.models.JsonRouteInfoParser;
import mypmt.myapps.com.models.RouteInfoComplete;


public class RoutActivity extends ActionBarActivity {
    ListView stopListview;
    TextView FromText, ToText;
    ImageView route_Bus_icon;
    JsonRouteInfoParser jsonRouteInfoParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rout);
        FromText = (TextView) findViewById(R.id.route_from_txt);
        ToText = (TextView) findViewById(R.id.route_to_txt);
        route_Bus_icon = (ImageView) findViewById(R.id.rout_image);
        stopListview = (ListView) findViewById(R.id.rout_listview);
        jsonRouteInfoParser = new JsonRouteInfoParser(null);


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
    private class LoadRouteInfoTask extends AsyncTask{
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
            RouteInfoComplete routeInfoComplete =jsonRouteInfoParser.getRouteInfoComplete();
            if(routeInfoComplete == null)
                Log.i("Status:", "No information about Route available!");
            else
            {

             //FromText.setText(routeInfoComplete.getFrom_str0());
             //ToText.setText(routeInfoComplete.getFrom_str0());

            }
        }
    }
}
