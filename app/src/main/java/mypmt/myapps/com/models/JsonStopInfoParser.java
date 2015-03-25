package mypmt.myapps.com.models;

import android.os.Environment;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import mypmt.myapps.com.mypmt.GlobalData;

/**
 * Created by android on 25-03-2015.
 */
public class JsonStopInfoParser {
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

    public void ParseJsonFile() throws FileNotFoundException, IOException, ParseException {
        File Rootdir = Environment.getExternalStorageDirectory();
        if (Rootdir != null) {
            //for now just parsing r326.json file
            this.mFile = new File(Rootdir.getPath() + "/" + GlobalData.APP_FOLDER + "/" + GlobalData.Temp_file2);

            JSONParser jsonParser = new JSONParser();
            this.freader = new FileReader(this.mFile);
            JSONObject jsonObject = (JSONObject) jsonParser.parse(this.freader);
            Set<Object> set = jsonObject.entrySet();
           // Iterator<Object> iterator = set.iterator();

        }
    }
}
