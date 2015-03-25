package mypmt.myapps.com.models;

import android.os.Environment;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mypmt.myapps.com.mypmt.GlobalData;

/**
 * Created by android on 14-02-2015.
 */
public class JsonStopListParser {
    String FilePath;
    List<String> stop_list;
    File mFile;
    FileReader freader;
    static HashMap<String, String> stop_url_map;

    public JsonStopListParser(String FilePath) {
        this.FilePath = FilePath;
        stop_list = new ArrayList<>();
        // stop_url_map; /*= new HashMap<String, String>();*/

    }

    /*
     *@return List<String> Values
     */
    public List<String> getStop_list() {
        return stop_list;
    }

    /*
    *@return HashMap<String,String> ListDetails of all stops.
    */
    public HashMap<String, String> getStop_url_map() {
        return stop_url_map;
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
            mFile = new File(Rootdir.getPath() + "/" + GlobalData.APP_FOLDER + "/" + GlobalData.STOP_INDEX_FILE);
            if (!mFile.exists()) {
                return;
            } else {
                JSONParser jsonParser = new JSONParser();
                try {
                    freader = new FileReader(mFile);
                    JSONObject jsonObject = (JSONObject) jsonParser.parse(freader);
                    // Set<Object> set = jsonObject.keySet();
                    Set<JSONObject> entry_set = jsonObject.entrySet();
                    stop_url_map = new HashMap<String, String>();
                    Iterator<JSONObject> iterator = entry_set.iterator();
                    while (iterator.hasNext()) {
                        Map.Entry entry = (Map.Entry) iterator.next();
                        stop_url_map.put(entry.getKey().toString(), entry.getValue().toString());
                        //JSONArray Stops1 = (JSONArray) entry.getValue();
                        //  this.routeInfoComplete.setStop_List1(toList(Stops1));
                    }
                    this.stop_list.addAll(stop_url_map.keySet());
                    //Collections.sort(this.stop_list);           //I have to improve sorting method
                    //System.out.println(this.stop_list.toString());
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
}
