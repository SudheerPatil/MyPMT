package mypmt.myapps.com.mypmt;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.util.List;

import mypmt.myapps.com.adapters.ViewPagerAdapter;
import mypmt.myapps.com.customs.views.SlidingTabLayout;
import mypmt.myapps.com.models.JsonRouteInfoParser;
import mypmt.myapps.com.models.RouteInfoComplete;
import mypmt.myapps.com.mypmt.fragments.StopListfragment;
import mypmt.myapps.com.mypmt.fragments.TimeListfragment;


public class RoutActivity extends ActionBarActivity {
    private static boolean FORWORD = true;
    TextView FromText, ToText, viaText;
    ImageView route_Bus_icon;
    JsonRouteInfoParser jsonRouteInfoParser;
    FragmentManager fragmentManager;
    ViewPagerAdapter viewPagerAdapter;
    ViewPager viewPager;
    SlidingTabLayout slidingTabLayout;
    FloatingActionButton fab;
    RouteInfoComplete routeInfoComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rout);
        CharSequence titles[] = {"STOPS", "TIMINGS"};
        FromText = (TextView) findViewById(R.id.route_from_txt);
        ToText = (TextView) findViewById(R.id.route_to_txt);
        viaText = (TextView) findViewById(R.id.route_via_txt);
        route_Bus_icon = (ImageView) findViewById(R.id.rout_image);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        fab = (FloatingActionButton) findViewById(R.id.swipe_btn);

        jsonRouteInfoParser = new JsonRouteInfoParser(null);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), titles, titles.length);
        viewPager.setAdapter(viewPagerAdapter);
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.slidingtablayout);
        slidingTabLayout.setDistributeEvenly(true);

        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.sliding_strip_color);
            }

        });

        slidingTabLayout.setViewPager(viewPager);

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1)
            new LoadRouteInfoTask().execute(null);
        else
            new LoadRouteInfoTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "Swiped Locations!", Toast.LENGTH_SHORT).show();
                SwipeData();
            }
        });

    }

    private void SwipeData() {
        CharSequence tempString = "";
        tempString = FromText.getText();
        FromText.setText(ToText.getText());
        ToText.setText(tempString);
        if (routeInfoComplete.getStop_List1() != null) {
            StopListfragment stopListfragment = (StopListfragment) viewPagerAdapter.getItem(0);//getStopListFragment
            TimeListfragment timeListfragment =(TimeListfragment)viewPagerAdapter.getItem(1);
            if (!FORWORD) { viewPagerAdapter.getItem(0);
                stopListfragment.setStopList(routeInfoComplete.getStop_List1());
                timeListfragment.setTimeList(routeInfoComplete.getTimings1());
                FORWORD=false;
            } else {
                stopListfragment.setStopList(routeInfoComplete.getStop_List0());
                timeListfragment.setTimeList(routeInfoComplete.getTimings0());
                FORWORD=true;
            }
            viewPagerAdapter.notifyDataSetChanged();
        }
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
            routeInfoComplete = jsonRouteInfoParser.getRouteInfoComplete();
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            //All initialization task will be done here!

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
                /*if (routeInfoComplete.getStop_List1() != null) {
                    StopListfragment stopListfragment = (StopListfragment) viewPagerAdapter.getItem(0);//getStopListFragment
                    stopListfragment.setStopList(routeInfoComplete.getStop_List1());
                    viewPagerAdapter.notifyDataSetChanged();
                }*/
                if (routeInfoComplete.getTimings1() != null) {
                    List<String> timeL1 = routeInfoComplete.getTimings0();
                    TimeListfragment timeListFragment = (TimeListfragment) viewPagerAdapter.getItem(1);
                    timeListFragment.setTimeList(timeL1);
                }
                if (routeInfoComplete.getTimings0() != null) {
                    List<String> timeL0 = routeInfoComplete.getTimings0();
                    TimeListfragment timeListFragment = (TimeListfragment) viewPagerAdapter.getItem(1);
                    timeListFragment.setTimeList(timeL0);
                }
                if (routeInfoComplete.getStop_List0() != null) {
                    StopListfragment stopListfragment = (StopListfragment) viewPagerAdapter.getItem(0);//getStopListFragment
                    stopListfragment.setStopList(routeInfoComplete.getStop_List0());
                    viewPagerAdapter.notifyDataSetChanged();
                }


            }
        }
    }
}
