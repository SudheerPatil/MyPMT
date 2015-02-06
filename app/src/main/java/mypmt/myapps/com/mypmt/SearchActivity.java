package mypmt.myapps.com.mypmt;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;


public class SearchActivity extends ActionBarActivity {
    AutoCompleteTextView fromTextView, toTextView, Rout_NumTextView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        fromTextView = (AutoCompleteTextView) findViewById(R.id.fromTextView);
        toTextView = (AutoCompleteTextView) findViewById(R.id.toTextView);
        Rout_NumTextView = (AutoCompleteTextView) findViewById(R.id.Rout_NumTextView);
        /*anim_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_up);
        anim_down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_down);
        anim_up.setAnimationListener(this);
        anim_down.setAnimationListener(this);*/
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
            //   toTextView.startAnimation(anim_down);
            //fromTextView.startAnimation(anim_up);
            Rout_NumTextView.setVisibility(View.GONE);
            fromTextView.setVisibility(View.VISIBLE);
            toTextView.setVisibility(View.VISIBLE);
            return true;
        } else if (id == R.id.action_by_route) {

            Rout_NumTextView.setVisibility(View.VISIBLE);
            //  fromTextView.startAnimation(anim_up);
            //toTextView.startAnimation(anim_down);
            fromTextView.setVisibility(View.GONE);
            toTextView.setVisibility(View.GONE);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
