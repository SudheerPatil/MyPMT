package mypmt.myapps.com.models;

import java.util.List;

/**
 * Created by android on 25-03-2015.
 */
public class StopInfoComplete {
    String StopName;
    List<RouteInfo> routeInfoList;

    public StopInfoComplete() {
    }

    public StopInfoComplete(String stopName, List<RouteInfo> routeInfoList) {
        StopName = stopName;
        this.routeInfoList = routeInfoList;
    }

    public List<RouteInfo> getRouteInfoList() {
        return routeInfoList;
    }

    public void setRouteInfoList(List<RouteInfo> routeInfoList) {
        this.routeInfoList = routeInfoList;
    }

    public String getStopName() {
        return StopName;
    }

    public void setStopName(String stopName) {
        StopName = stopName;
    }
}
