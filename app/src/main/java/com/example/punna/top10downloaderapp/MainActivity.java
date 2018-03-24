package com.example.punna.top10downloaderapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ListView listapps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listapps=(ListView)findViewById(R.id.xmllistview);
        Log.d(TAG, "onCreate: starting asynl task");
        Downloaddata downloaddata=new Downloaddata();
        downloaddata.execute("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=25/xml");
        Log.d(TAG, "onCreate: done");
    }
    private class Downloaddata extends AsyncTask<String,Void,String>{
        private static final String TAG = "Downloaddata";
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG, "onPostExecute: "+s);
            ParseApplications parseApplications=new ParseApplications();
            parseApplications.parse(s);
//            ArrayAdapter<FeedEntry> arrayAdapter=new ArrayAdapter<FeedEntry>(MainActivity.this,R.layout.list_item,parseApplications.getApplications());
//            listapps.setAdapter(arrayAdapter);
            FeedAdapter feedAdapter=new FeedAdapter(MainActivity.this,R.layout.list_record,parseApplications.getApplications());
            listapps.setAdapter(feedAdapter);
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.d(TAG, "doInBackground: starts with"+strings[0]);
            String rssfeed =downloadxml(strings[0]);
            if(rssfeed==null){
                Log.e(TAG, "doInBackground: error loading" );
            }
            return rssfeed;
        }
        private String downloadxml(String urlpath){
            StringBuilder xmlresult=new StringBuilder();
            try{
                URL url=new URL(urlpath);
                HttpURLConnection connection=(HttpURLConnection)url.openConnection();
                int response =connection.getResponseCode();
                Log.d(TAG, "downloadxml: get response code"+response);
                //InputStream inputStream=connection.getInputStream();
                //InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));

                int charsRead;
                char[] inputbuffer=new char[500];
                while (true){
                    charsRead=reader.read(inputbuffer);
                    if(charsRead<0)
                        break;
                    if (charsRead>0){
                        xmlresult.append(String.copyValueOf(inputbuffer,0,charsRead));
                    }
                }
                reader.close();
                return xmlresult.toString();
            }catch (MalformedURLException e){
                Log.e(TAG, "downloadxml: Invalid url"+e.getMessage() );
            }
            catch (IOException e){
                Log.e(TAG, "downloadxml: IO exception reading data"+e.getMessage() );
            }
            catch (SecurityException e){
                Log.e(TAG, "downloadxml: "+e.getMessage());
                e.printStackTrace();
            }
            return null;
            
        }

    }
}
