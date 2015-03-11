package mypmt.myapps.com.models;

import android.os.Environment;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mypmt.myapps.com.mypmt.GlobalData;

/**
 * Created by Sudheer on 01-03-2015.
 */
public class JsonRouteInfoParser {
    public JsonRouteInfoParser(File mFile) {
        this.mFile = mFile;
        this.routeInfoComplete = new RouteInfoComplete();
    }

    private File mFile;
    private FileReader freader;
    private RouteInfoComplete routeInfoComplete;

    public void releaseResources() {
        if (freader != null) {
            try {
                freader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void ParseJsonFile(JsonRouteInfoParser jsonRouteInfoParser) {
        File Rootdir = Environment.getExternalStorageDirectory();
        if (Rootdir != null) {
            //for now just parsing r326.json file
            jsonRouteInfoParser.mFile = new File(Rootdir.getPath() + "/" + GlobalData.APP_FOLDER + "/" + GlobalData.Temp_file);
            if (!jsonRouteInfoParser.mFile.exists()) {
                return;
            } else {
                JSONParser jsonParser = new JSONParser();
                try {
                    jsonRouteInfoParser.freader = new FileReader(jsonRouteInfoParser.mFile);
                    JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonRouteInfoParser.freader);
                    Set<Object> set = jsonObject.entrySet();
                    Iterator<Object> iterator = set.iterator();
                    while (iterator.hasNext()) {
                        //System.out.println(iterator.next().toString());
                        Map.Entry main_entry = (Map.Entry) iterator.next();

                        String Route_Name = main_entry.getKey().toString();
                        String Route_num = Route_Name.substring(Route_Name.indexOf("Route"), Route_Name.indexOf(":"));
                        if(Route_Name.contains("via "))
                        this.routeInfoComplete.setVia_str0(Route_Name.substring(Route_Name.indexOf("via ")+4));//setting via string
                        this.routeInfoComplete.setHead_title(Route_Name.replace(Route_num+":",""));            // setting title of route
                        this.routeInfoComplete.setRoute_num(Route_num);                                        // setting route number

                        JSONArray array = (JSONArray) main_entry.getValue();//jsonObject.get(iterator.next());
                        //System.out.println("Array size:" + array.size());
                        //System.out.println(array.toString());
                        JSONObject jsonObject0 = (JSONObject) array.get(0);
                        Iterator iterator0_1 = jsonObject0.entrySet().iterator();

                        JSONArray TripTime1 = (JSONArray) jsonObject0.get("TripTime");

                        this.routeInfoComplete.setTimings1(toList(TripTime1));                              //Setting forword direction timings
                        jsonObject0.remove("TripTime");
                        Set<JSONObject> set1 = jsonObject0.entrySet();
                        Iterator<JSONObject> iterator1 = set1.iterator();
                        while (iterator1.hasNext()) {
                            Map.Entry entry = (Map.Entry) iterator1.next();
                            JSONArray Stops1 = (JSONArray) entry.getValue();
                            this.routeInfoComplete.setStop_List1(toList(Stops1));
                        }

                        JSONObject jsonObject1 = (JSONObject) array.get(1);
                        JSONArray TripTime0 = (JSONArray) jsonObject1.get("TripTime");
                        this.routeInfoComplete.setTimings0(toList(TripTime0));
                        jsonObject1.remove("TripTime");
                        Set<JSONObject> set0 = jsonObject1.entrySet();
                        Iterator<JSONObject> iterator0 = set0.iterator();
                        while (iterator0.hasNext()) {
                            Map.Entry entry = (Map.Entry) iterator0.next();
                            JSONArray Stops0 = (JSONArray) entry.getValue();
                            this.routeInfoComplete.setStop_List0(toList(Stops0));
                        }

                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private List<String> toList(JSONArray Jarray) {
        List<String> sampleList = new ArrayList<String>();
        for (Object obj : Jarray)
            sampleList.add(obj.toString());
        return sampleList;
    }
    public RouteInfoComplete getRouteInfoComplete(){
        return this.routeInfoComplete;
    }
}
