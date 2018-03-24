package com.example.punna.top10downloaderapp;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by punna on 2/11/2018.
 */

public class ParseApplications {
    private static final String TAG = "ParseApplications";
    private ArrayList<FeedEntry> applications;

    public ParseApplications() {
        this.applications=new ArrayList<>();
    }

    public ArrayList<FeedEntry> getApplications() {
        return applications;
    }
    public boolean parse(String xmldata){
        boolean status=true;
        FeedEntry currentRecord=null;
        boolean inentry=false;
        String textvalue="";

        try {
            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp=factory.newPullParser();
            xpp.setInput(new StringReader(xmldata));
            int eventtype=xpp.getEventType();
            while(eventtype!=XmlPullParser.END_DOCUMENT){
                String tagname=xpp.getName();
                switch (eventtype){
                    case XmlPullParser.START_TAG:
                        Log.d(TAG, "parse: starting tag for"+tagname);
                        if("entry".equalsIgnoreCase(tagname)){
                            inentry=true;
                            currentRecord=new FeedEntry();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textvalue=xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        Log.d(TAG, "parse: Ending tag for"+tagname);
                        if(inentry){
                            if("entry".equalsIgnoreCase(tagname)){
                                applications.add(currentRecord);
                                inentry=false;
                            }
                            else if ("name".equalsIgnoreCase(tagname)){
                                currentRecord.setName(textvalue);
                            }
                            else if ("artist".equalsIgnoreCase(tagname))
                                currentRecord.setArtist(textvalue);
                            else if ("releaseDate".equalsIgnoreCase(tagname))
                                currentRecord.setReleasedate(textvalue);
                            else if ("summary".equalsIgnoreCase(tagname))
                                currentRecord.setSummary(textvalue);
                            else if ("image".equalsIgnoreCase(tagname))
                                currentRecord.setImageurl(textvalue);
                        }
                        break;
                    default:
                        //nohing to do
                }
                eventtype=xpp.next();
            }

        }catch (Exception e){
            status=false;
            e.printStackTrace();
        }
        for (FeedEntry app:applications){
            Log.d(TAG, "******************");
            Log.d(TAG, currentRecord.toString());
        }
        return status;
    }
}
