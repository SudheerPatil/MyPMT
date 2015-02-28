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
import java.util.Set;

import mypmt.myapps.com.mypmt.GlobalData;

/**
 * Created by android on 23-02-2015.
 */
public class JsonRouteListParser {
    private String FilePath;
    private List<RouteInfo> route_list;
    private File mFile;
    private FileReader freader;

    public JsonRouteListParser(String FilePath) {
        this.FilePath = FilePath;
        route_list = new ArrayList<RouteInfo>();
    }

    public List<RouteInfo> getRoute_list() {
        return route_list;
    }

    public void releaseResources() {
        if (freader != null) {
            try {
                freader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public void ParseJsonFile() {
        File Rootdir = Environment.getExternalStorageDirectory();
        if (Rootdir != null) {
            mFile = new File(Rootdir.getPath() + "/" + GlobalData.APP_FOLDER + "/" + GlobalData.ROUTE_INDET_FILE);
            if (!mFile.exists()) {
                return;
            } else {
                JSONParser jsonParser = new JSONParser();
                try {
                    freader = new FileReader(mFile);
                    JSONObject jsonObject = (JSONObject) jsonParser.parse(freader);
                    Set<Object> set = jsonObject.keySet();
                    //System.out.println(set);
                    Iterator<Object> itrator = set.iterator();
                    while (itrator.hasNext()) {
                        System.out.println(itrator.next().toString());

                        JSONArray route_details_array = (JSONArray) jsonObject.get(itrator.next());
                        JSONObject jsonObject1 = (JSONObject) route_details_array.get(0);

                        System.out.print(" " + jsonObject1.get("rout"));
                        System.out.print(" " + jsonObject1.get("lnk"));
                        this.route_list.add(new RouteInfo(itrator.next().toString(),
                                                           jsonObject1.get("rout").toString(),
                                                           null));
                    }

                        // Collections.sort(this.route_list);
                        //System.out.println(this.route_list.toString());
                    }catch(FileNotFoundException e){
                        e.printStackTrace();
                    }catch(ParseException e){
                        e.printStackTrace();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
