package mypmt.myapps.com.models;

import java.util.List;

/**
 * Created by Sudheer on 01-03-2015.
 */
public class RouteInfoComplete {
    private String route_num;
    private String from_str,to_str,via_str;
    private List<String> Stop_List;
    private List<String> Timings;
    private boolean isReverse;

    public RouteInfoComplete(String route_num, String from_str, String to_str, String via_str, List<String> stop_List, List<String> timings, boolean isReverse) {
        this.route_num = route_num;
        this.from_str = from_str;
        this.to_str = to_str;
        this.via_str = via_str;
        Stop_List = stop_List;
        Timings = timings;
        this.isReverse = isReverse;
    }

    public RouteInfoComplete(String route_num, String from_str, String to_str, String via_str, List<String> stop_List, List<String> timings) {
        this.route_num = route_num;
        this.from_str = from_str;
        this.to_str = to_str;
        this.via_str = via_str;
        Stop_List = stop_List;
        Timings = timings;
    }

    public String getRoute_num() {
        return route_num;
    }

    public void setRoute_num(String route_num) {
        this.route_num = route_num;
    }

    public String getFrom_str() {
        return from_str;
    }

    public void setFrom_str(String from_str) {
        this.from_str = from_str;
    }

    public String getTo_str() {
        return to_str;
    }

    public void setTo_str(String to_str) {
        this.to_str = to_str;
    }

    public String getVia_str() {
        return via_str;
    }

    public void setVia_str(String via_str) {
        this.via_str = via_str;
    }

    public List<String> getStop_List() {
        return Stop_List;
    }

    public void setStop_List(List<String> stop_List) {
        Stop_List = stop_List;
    }

    public List<String> getTimings() {
        return Timings;
    }

    public void setTimings(List<String> timings) {
        Timings = timings;
    }

    public boolean isReverse() {
        return isReverse;
    }

    public void setReverse(boolean isReverse) {
        this.isReverse = isReverse;
    }

    @Override
    public String toString() {
        return this.route_num +" "+this.from_str+"->"+this.to_str+" via "+this.via_str+ "with "+this.Stop_List.toString()+" Times:"+this.Timings;

    }
}
