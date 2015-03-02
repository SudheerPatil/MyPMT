package mypmt.myapps.com.mypmt;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import mypmt.myapps.com.models.JsonRouteInfoParser;


public class RoutActivity extends ActionBarActivity {
    ListView stopListview;
    TextView FromText, ToText;
    ImageView route_Bus_icon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rout);
        FromText = (TextView) findViewById(R.id.route_from_txt);
        ToText = (TextView) findViewById(R.id.route_to_txt);
        route_Bus_icon = (ImageView) findViewById(R.id.rout_image);
        stopListview = (ListView) findViewById(R.id.rout_listview);
        JsonRouteInfoParser jsonRouteInfoParser = new JsonRouteInfoParser(null);
        jsonRouteInfoParser.ParseJsonFile(jsonRouteInfoParser);
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
}
