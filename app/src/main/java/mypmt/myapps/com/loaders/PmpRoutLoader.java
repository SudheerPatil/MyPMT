package mypmt.myapps.com.loaders;

import android.annotation.TargetApi;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Build;

/**
 * Created by android on 11-02-2015.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PmpRoutLoader extends AsyncTaskLoader {
    public PmpRoutLoader(Context context) {
        super(context);
    }

    @Override
    public Object loadInBackground() {
        return null;
    }
}
