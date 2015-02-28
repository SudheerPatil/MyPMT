package mypmt.myapps.com.mypmt;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import org.apache.http.util.ByteArrayBuffer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class SplashActivity extends Activity {

    ProgressBar index_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        index_progress = (ProgressBar) findViewById(R.id.progress_index);
        new DownloadStopListTask().execute(GlobalData.STOPS_INDEX_URL);
        new DownloadRouteListTask().execute(GlobalData.ROUTE_INDEX_URL);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class DownloadStopListTask extends AsyncTask<String, Integer, Boolean> {


        @Override
        protected Boolean doInBackground(String... params) {
            Boolean Result = new Boolean(false);
            String url = params[0];
            FileOutputStream fos = null;
            int counter_progrss = 0;
            try {
                File Roots = Environment.getExternalStorageDirectory();
                File file = new File("StopIndex.json");
                File myDir = null;
                if (Roots != null) {
                    myDir = new File(Roots.getPath() + "/MyPMT");
                    if (!myDir.exists())
                        myDir.mkdirs();

                }
                File tempFile = new File(myDir + "/" + file.getPath());
                if (tempFile.exists()) {
                    publishProgress(50);
                    return Result = true;
                }

                URL address = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) address.openConnection();
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    Log.i("Status:", "Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage());
                }
                InputStream is = connection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                ByteArrayBuffer bab = new ByteArrayBuffer(64);
                int current = 0;

                while ((current = bis.read()) != -1) {
                    publishProgress(++counter_progrss);
                    bab.append((byte) current);
                }


                fos = new FileOutputStream(new File(myDir.getPath() + "/" + GlobalData.STOP_INDEX_FILE));
                fos.write(bab.toByteArray());


            } catch (IOException e) {
                e.printStackTrace();
                return Result;
            } finally {
                try {
                    if (fos != null)
                        fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return Result = true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            index_progress.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            Intent i = new Intent(SplashActivity.this, SearchActivity.class);
            startActivity(i);
            finish();
        }
    }


    private class DownloadRouteListTask extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            Boolean result = new Boolean(false);
            String url = params[0];
            FileOutputStream fos = null;
            int counter_progrss = 0;
            try {
                File Roots = Environment.getExternalStorageDirectory();
                File file = new File("RouteIndex.json");
                File myDir = null;
                if (Roots != null) {
                    myDir = new File(Roots.getPath() + "/MyPMT");
                    if (!myDir.exists())
                        myDir.mkdirs();

                }
                File tempFile = new File(myDir + "/" + file.getPath());
                if (tempFile.exists()) {
                    publishProgress(100);
                    return result = true;
                }
                URL address = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) address.openConnection();
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    Log.i("Status:", "Server returned HTTP " + connection.getResponseCode() + " " + connection.getResponseMessage());
                }
                InputStream is = connection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                ByteArrayBuffer bab = new ByteArrayBuffer(64);
                int current = 0;

                while ((current = bis.read()) != -1) {
                    publishProgress(++counter_progrss+50);
                    bab.append((byte) current);
                }


                fos = new FileOutputStream(new File(myDir.getPath() + "/" + GlobalData.ROUTE_INDET_FILE));
                fos.write(bab.toByteArray());
                result = true;
            } catch (IOException ioexception) {
                ioexception.printStackTrace();
            } finally {
                try {
                    if (fos != null)
                        fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            index_progress.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
        }


    }
}
