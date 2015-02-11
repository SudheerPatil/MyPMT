package mypmt.myapps.com.loaders;


import android.annotation.TargetApi;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Build;

import java.util.List;

import mypmt.myapps.com.models.RouteInfo;

/**
 * Created by android on 11-02-2015.
 */


public class PmpRoutLoader extends android.support.v4.content.AsyncTaskLoader<List<RouteInfo>> {

    public PmpRoutLoader(Context context) {
        super(context);
    }

    @Override
    public List<RouteInfo> loadInBackground() {
        return null;
    }
}
