package com.assignment.materialdesign.xmlpullparsep;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BartApp extends AppCompatActivity {
    String BART_STATION_URL = "http://api.bart.gov/api/stn.aspx?cmd=stns&key=MW9S-E7SL-26DU-VV8V";
    List<Station> stations = null;

    Context context;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bart_app);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;


        new GetInfo().execute(BART_STATION_URL);
        listView = (ListView) findViewById(R.id.list);

    }


    }

    public class GetInfo extends AsyncTask<String, ProgressBar, List<String>> {

        @Override
        protected List<String> doInBackground(String... params) {
            List<String> list = new ArrayList<>();
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream= connection.getInputStream();

                XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
                XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
                xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                xmlPullParser.setInput(inputStream, null);
                list = parserXML(xmlPullParser, list);
            } catch (IOException | XmlPullParserException e) {
                e.printStackTrace();
            }

            Log.i("Station infor: ", list.toString());
            return list;
        }

        @Override
        protected void onPostExecute(List<String> strings) {
            ListView listView = null;

            ListAdapter listAdapter = new ListAdapter(context,android.R.layout.simple_list_item_1,strings);
            assert listView != null;
            listView.setAdapter(listAdapter);
        }


    private List<String> parserXML(XmlPullParser xmlPullParser, List<String> list) throws XmlPullParserException, IOException {
        int eventType = xmlPullParser.getEventType();
        Station stations = null;
        List<String> station = new ArrayList<>();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            String name = null;
        Context context;    switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    station = new ArrayList<>();
                    break;
                case XmlPullParser.START_TAG:
                    name = xmlPullParser.getName();
                    if (name.equals("station")) {

                        station = new Station();
                    } else if (station != null) {
                        if (name.equals("name")) {







                            station.setStation_name(xmlPullParser.nextText());
                        } else if (name.equals("abbr")) {
                            station.setStation_abbr(xmlPullParser.nextText());
                        }
                    }
                        break;

                case XmlPullParser.END_TAG:
                    name = xmlPullParser.getName();
                    if (name.equalsIgnoreCase("station") && station != null) {
                        stations.add(station);
                    }
                    break;
            }
            eventType = xmlPullParser.next();
        }

        for (int i = 0; i < stations.size(); i++) {
            list.add(stations.get(i).getStation_name());
        }
        return list;
    }
}
