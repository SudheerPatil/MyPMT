package mypmt.myapps.com.models;

/**
 * Created by android on 11-02-2015.
 * All Copyrights reserved to patilsudheer1991@gmail.com
 */
public class RouteInfo {
    private String route_num;
    private String from_str,to_str,via_str;
    //String route_num_str;
    public RouteInfo(String route_num, String from_str, String to_str) {
        this.route_num = route_num;
        this.from_str = from_str;
        this.to_str = to_str;
    }

    public RouteInfo(String route_num, String from_str, String to_str, String via_str) {
        this.route_num = route_num;
        this.from_str = from_str;
        this.to_str = to_str;
        this.via_str = via_str;
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

    @Override
    public String toString() {
        return "RouteInfo{" +
                "route_num=" + route_num +
                ", from_str='" + from_str + '\'' +
                ", to_str='" + to_str + '\'' +
                ", via_str='" + via_str + '\'' +
                '}';
    }
}
