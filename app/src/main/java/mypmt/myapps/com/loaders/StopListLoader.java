package mypmt.myapps.com.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

import mypmt.myapps.com.models.JsonStopListParser;

/**
 * Created by android on 14-02-2015.
 */

public class StopListLoader extends AsyncTaskLoader<List<String>> {
    private List<String> stop_list;
    JsonStopListParser jsonStopListParser;


    public StopListLoader(Context context) {
        super(context);
    }

    @Override
    public List<String> loadInBackground() {
        stop_list = new ArrayList<String>();
        jsonStopListParser = new JsonStopListParser(null);
        jsonStopListParser.ParseJsonFile();
        stop_list = jsonStopListParser.getStop_list();
        return stop_list;
    }


    @Override
    public void deliverResult(List<String> data) {
        if (isReset()) {
            jsonStopListParser.releaseResources();
            return;
        }

        List<String> oldList = stop_list;
        stop_list = data;

        if (isStarted()) {
            super.deliverResult(data);
        }
        if (stop_list != null && stop_list != data) {
            jsonStopListParser.releaseResources();
        }


    }
//Reference website: http://www.androiddesignpatterns.com/2012/08/implementing-loaders.html

    @Override
    protected void onStartLoading() {
        if (stop_list != null) {
            deliverResult(stop_list);
        }
        //ther might be more code please check this: https://github.com/alexjlockwood/AppListLoader/blob/master/src/com/adp/loadercustom/loader/AppListLoader.java
        forceLoad();
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (stop_list != null) {
            jsonStopListParser.releaseResources();
            stop_list = null;
        }
    }

    @Override
    public void onCanceled(List<String> data) {
        super.onCanceled(data);
        jsonStopListParser.releaseResources();
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
    }
}
