package mypmt.myapps.com.models;

import java.util.List;

/**
 * Created by Sudheer on 01-03-2015.
 */
public class RouteInfoComplete {
    private String route_num;
    private String from_str0,to_str0,via_str0;
    private List<String> Stop_List0;
    private List<String> Timings0;
    private List<String> Stop_List1;
    private List<String> Timings1;
    private boolean isReverse;

    public RouteInfoComplete(String route_num, String from_str0, String to_str0, String via_str0, List<String> stop_List0, List<String> timings0, List<String> stop_List1, List<String> timings1, boolean isReverse) {
        this.route_num = route_num;
        this.from_str0 = from_str0;
        this.to_str0 = to_str0;
        this.via_str0 = via_str0;
        Stop_List0 = stop_List0;
        Timings0 = timings0;
        Stop_List1 = stop_List1;
        Timings1 = timings1;
        this.isReverse = isReverse;
    }

    public RouteInfoComplete(String route_num, String from_str0, String to_str0, String via_str0, List<String> stop_List0, List<String> timings0, List<String> stop_List1, List<String> timings1) {
        this.route_num = route_num;
        this.from_str0 = from_str0;
        this.to_str0 = to_str0;
        this.via_str0 = via_str0;
        Stop_List0 = stop_List0;
        Timings0 = timings0;
        Stop_List1 = stop_List1;
        Timings1 = timings1;
    }

    public RouteInfoComplete() {
    }

    public String getRoute_num() {
        return route_num;
    }

    public void setRoute_num(String route_num) {
        this.route_num = route_num;
    }

    public String getFrom_str0() {
        return from_str0;
    }

    public void setFrom_str0(String from_str0) {
        this.from_str0 = from_str0;
    }

    public String getTo_str0() {
        return to_str0;
    }

    public void setTo_str0(String to_str0) {
        this.to_str0 = to_str0;
    }

    public String getVia_str0() {
        return via_str0;
    }

    public void setVia_str0(String via_str0) {
        this.via_str0 = via_str0;
    }

    public List<String> getStop_List0() {
        return Stop_List0;
    }

    public void setStop_List0(List<String> stop_List0) {
        Stop_List0 = stop_List0;
    }

    public List<String> getTimings0() {
        return Timings0;
    }

    public void setTimings0(List<String> timings0) {
        Timings0 = timings0;
    }

    public List<String> getStop_List1() {
        return Stop_List1;
    }

    public void setStop_List1(List<String> stop_List1) {
        Stop_List1 = stop_List1;
    }

    public List<String> getTimings1() {
        return Timings1;
    }

    public void setTimings1(List<String> timings1) {
        Timings1 = timings1;
    }

    public boolean isReverse() {
        return isReverse;
    }

    public void setReverse(boolean isReverse) {
        this.isReverse = isReverse;
    }
}
