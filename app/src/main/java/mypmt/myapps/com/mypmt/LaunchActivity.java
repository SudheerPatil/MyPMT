package mypmt.myapps.com.mypmt;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;


public class LaunchActivity extends ActionBarActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        //Code for setting Reference image
        //AssetManager am = this.getAssets();
       /* ImageView img=(ImageView)findViewById(R.id.imageView);
        InputStream stream = null;
        try {
            stream = am.open("bus_flat.png");
            Drawable d=Drawable.createFromStream(stream,"bus_flat.png");
            img.setImageDrawable(d);
        } catch (IOException e) {
            Log.e("Error!", "Cant find bus_flat.png.");
            e.printStackTrace();
        }
        finally {
             try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        */
        ImageButton searchBtn=(ImageButton)findViewById(R.id.searchButton);
        searchBtn.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_launch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id){
        //noinspection SimplifiableIfStatement
            case R.id.action_settings:
               return true;
            case R.id.action_about:
                startActivity(new Intent(this,AboutMe.class));
                return true;
            case R.id.action_tips:
                return true;
            case R.id.action_help:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int view_id=v.getId();
        Intent activity_intent;
        if(view_id==R.id.searchButton){
            activity_intent=new Intent(this,SearchActivity.class);
            startActivity(activity_intent);
        }//else if(view_id==R.id.Something){}
    }
}
