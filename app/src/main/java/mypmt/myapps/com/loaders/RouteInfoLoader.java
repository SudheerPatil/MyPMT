package mypmt.myapps.com.loaders;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Created by Sudheer on 01-03-2015.
 */
public class RouteInfoLoader extends AsyncTaskLoader {

    public RouteInfoLoader(Context context) {
        super(context);
    }

    @Override
    public Object loadInBackground() {
        return null;
    }
}
