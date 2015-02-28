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
import java.util.Collections;
import java.util.List;
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

    public JsonStopListParser(String FilePath) {
        this.FilePath = FilePath;
        stop_list = new ArrayList<>();
    }

    /*
     *@return List<String> Values
     */
    public List<String> getStop_list() {
        return stop_list;
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
                    Set<Object> set = jsonObject.keySet();
                    this.stop_list = new ArrayList(set);
                    Collections.sort(this.stop_list);
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
