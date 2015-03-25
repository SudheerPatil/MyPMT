package mypmt.myapps.com.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mypmt.myapps.com.models.JsonRouteListParser;
import mypmt.myapps.com.models.RouteInfo;

/**
 * Created by android on 23-02-2015.
 */
public class RouteListLoader extends AsyncTaskLoader<List<RouteInfo>> {
    private List<RouteInfo> route_list;
    JsonRouteListParser jsonRouteListParser;

    public RouteListLoader(Context context) {
        super(context);
    }

    @Override
    public List<RouteInfo> loadInBackground() {
        route_list = new ArrayList<RouteInfo>();
        jsonRouteListParser = new JsonRouteListParser(null);
        try {
            jsonRouteListParser.ParseJsonFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.route_list = jsonRouteListParser.getRoute_list();
        return this.route_list;
    }

    @Override
    public void deliverResult(List<RouteInfo> data) {
        if (isReset()) {
            jsonRouteListParser.releaseResources();
            return;
        }

        List<RouteInfo> oldList = route_list;
        route_list = data;

        if (isStarted()) {
            if (data != null)
                super.deliverResult(data);
        }
        if (route_list != null && route_list != data) {
            jsonRouteListParser.releaseResources();
        }
    }

//Reference website: http://www.androiddesignpatterns.com/2012/08/implementing-loaders.html

    @Override
    protected void onStartLoading() {
        if (route_list != null) {
            deliverResult(route_list);
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
        if (route_list != null) {
            jsonRouteListParser.releaseResources();
            route_list = null;
        }
    }

    @Override
    public void onCanceled(List<RouteInfo> data) {
        super.onCanceled(data);
        jsonRouteListParser.releaseResources();
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
    }
}
