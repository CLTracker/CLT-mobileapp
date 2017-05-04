package com.clt.conventionlogistictracker;


import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekViewEvent;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
//public abstract class ScheduleFragment extends Fragment implements WeekView.EventClickListener,MonthLoader.MonthChangeListener, WeekView.EventLongPressListener, WeekView.EmptyViewLongPressListener {
public class ScheduleFragment extends Fragment  {
    private ArrayList<Schedule> mScheduleList = new ArrayList<Schedule>();
    public ArrayList<WeekViewEvent> events = new ArrayList<>();
    public View rootView;
    public WeekView mWeekView;
    //public MonthLoader.MonthChangeListener mMonthChangeListener;

    public ScheduleFragment() {
        // Required empty public constructor
    }

    private class RetrieveFeedTask extends AsyncTask<Void, Void, ArrayList<WeekViewEvent>> {
        private Exception exception;

        ArrayList<WeekViewEvent> eventTemp = new ArrayList<>();
        ArrayList<Schedule> temp = new ArrayList<Schedule>();
        String startDate;
        String endDate;
        String titleEvent;
        Date parsedStartDate = null;
        Date parsedEndDate = null;
        @Override
        protected ArrayList<WeekViewEvent> doInBackground(Void... arg0) {

            try{
                String url = "http://cltglobal.ddns.net:8080/schedule/1";
                Log.d("does", url);

                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                //Log.d("WORK???1", response.toString());

                JSONArray jsonArray = new JSONArray(response.toString());
                for (int i=0; i<jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    if (jsonArray != null) {
                        temp.add(new Schedule(object.getString("title"),object.getString("start"),object.getString("end")));
                        titleEvent = object.getString("title");
                        startDate = object.getString("start");
                        endDate = object.getString("end");
                        if(endDate.equals("None")) {
                            endDate = "2017-05-01 10:00:00";
                        }
                        Log.d(startDate, endDate);
                        Log.d("WORK???2", temp.toString());
                        parsedStartDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDate);
                        parsedEndDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDate);
                        Calendar startTime = Calendar.getInstance();
                        startTime.setTime(parsedStartDate);
                        Calendar endTime = Calendar.getInstance();
                        endTime.setTime(parsedEndDate);

                        WeekViewEvent event = new WeekViewEvent(i, titleEvent, startTime, endTime);
                        eventTemp.add(event);


                    }
                }

            } catch(Exception e)
            {
                Log.d("NO WORK!!!", "\n\n\n"+e.toString()+"\n\n\n\n");
            }
            Log.d("Endback","fin");
            return eventTemp;
            //return temp;
        }
        protected void onPostExecute(/*ArrayList<Schedule> result*/ArrayList<WeekViewEvent> result) {
            super.onPostExecute(result);
            Log.d("onPost",result.toString());
            setList(result);
            //setupWeekview(result);
            //loadAll();
            //Log.d("Weekvieweventresult", result.toString());
        }
    }
    public void setList(ArrayList<WeekViewEvent> exlist){
        Log.d("setlistStart", exlist.toString());
        //setupWeekview(exlist);
        //loadAll();
        this.events = exlist;
        Log.d("setlistEnd", this.events.toString());
    }

    public void setupWeekview(final ArrayList<WeekViewEvent> exlist){
        Log.d("setWeekStart", exlist.toString());
        events = exlist;

        if (this.mWeekView != null) {

//            MonthLoader.MonthChangeListener mMonthChangeListener = new MonthLoader.MonthChangeListener() {
//                @Override
//                public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {
//                    // Populate the week view with some events.
//                    List<WeekViewEvent> events2 = exlist;//(newYear, newMonth);
//                    return events2;
//                }
//            };


            //set listener for month change
            this.mWeekView.setMonthChangeListener(new MonthLoader.MonthChangeListener() {
                @Override
                public ArrayList<? extends WeekViewEvent> onMonthChange(int i, int i1) {
                    ArrayList<WeekViewEvent> event2 = new ArrayList<>();
                    mWeekView.goToHour(8);
//                    events2 = new ArrayList<WeekViewEvent>(events);
////
////
                    Calendar startTime = Calendar.getInstance();
                    startTime.set(Calendar.HOUR_OF_DAY, 8);
                    startTime.set(Calendar.MINUTE, 0);
                    startTime.set(Calendar.DAY_OF_MONTH, 4);
                    startTime.set(Calendar.MONTH, i1);
                    startTime.set(Calendar.YEAR, 2017);
                    Calendar endTime = (Calendar) startTime.clone();
                    //endTime.set(Calendar.DAY_OF_MONTH, 29);
                    endTime.set(Calendar.MINUTE, 15);
                    WeekViewEvent event = new WeekViewEvent(1, "Damping System Test Drop Tower", startTime, endTime);
                    event.setColor(Color.parseColor("#ff8773"));
                    event2.add(event);

                    Calendar startTime2 = Calendar.getInstance();
                    startTime2.set(Calendar.HOUR_OF_DAY, 8);
                    startTime2.set(Calendar.MINUTE, 15);
                    startTime2.set(Calendar.DAY_OF_MONTH, 4);
                    startTime2.set(Calendar.MONTH, i1);
                    startTime2.set(Calendar.YEAR, 2017);
                    Calendar endTime2 = (Calendar) startTime2.clone();
                    endTime2.set(Calendar.MINUTE, 30);
                    WeekViewEvent event2a = new WeekViewEvent(1, "Project Apollo: Automated Heliodon V2.0", startTime2, endTime2);
                    event2a.setColor(Color.parseColor("#dc7772"));
                    event2.add(event2a);

                    Calendar startTime3 = Calendar.getInstance();
                    startTime3.set(Calendar.HOUR_OF_DAY, 8);
                    startTime3.set(Calendar.MINUTE, 30);
                    startTime3.set(Calendar.DAY_OF_MONTH, 4);
                    startTime3.set(Calendar.MONTH, i1);
                    startTime3.set(Calendar.YEAR, 2017);
                    Calendar endTime3 = (Calendar) startTime3.clone();
                    endTime3.set(Calendar.MINUTE, 45);
                    WeekViewEvent event3a = new WeekViewEvent(1, "Card Shoe Projection System", startTime3, endTime3);
                    event3a.setColor(Color.parseColor("#bc6972"));
                    event2.add(event3a);


                    Calendar startTime4 = Calendar.getInstance();
                    startTime4.set(Calendar.HOUR_OF_DAY, 8);
                    startTime4.set(Calendar.MINUTE, 45);
                    startTime4.set(Calendar.DAY_OF_MONTH, 4);
                    startTime4.set(Calendar.MONTH, i1);
                    startTime4.set(Calendar.YEAR, 2017);
                    Calendar endTime4 = (Calendar) startTime4.clone();
                    endTime4.set(Calendar.MINUTE, 60);
                    WeekViewEvent event4a = new WeekViewEvent(1, "Jox-T", startTime4, endTime4);
                    event4a.setColor(Color.parseColor("#9b5c72"));
                    event2.add(event4a);

                    Calendar startTime5 = Calendar.getInstance();
                    startTime5.set(Calendar.HOUR_OF_DAY, 9);
                    startTime5.set(Calendar.MINUTE, 0);
                    startTime5.set(Calendar.DAY_OF_MONTH, 4);
                    startTime5.set(Calendar.MONTH, i1);
                    startTime5.set(Calendar.YEAR, 2017);
                    Calendar endTime5 = (Calendar) startTime5.clone();
                    endTime5.set(Calendar.MINUTE, 15);
                    WeekViewEvent event5a = new WeekViewEvent(1, "FLTR", startTime5, endTime5);
                    event5a.setColor(Color.parseColor("#05D196"));
                    event2.add(event5a);

                    Calendar startTime6 = Calendar.getInstance();
                    startTime6.set(Calendar.HOUR_OF_DAY, 9);
                    startTime6.set(Calendar.MINUTE, 15);
                    startTime6.set(Calendar.DAY_OF_MONTH, 4);
                    startTime6.set(Calendar.MONTH, i1);
                    startTime6.set(Calendar.YEAR, 2017);
                    Calendar endTime6 = (Calendar) startTime6.clone();
                    endTime6.set(Calendar.MINUTE, 30);
                    WeekViewEvent event6a = new WeekViewEvent(1, "Scintillator and Silicon Photomutiplier High Energy Radiation Detector", startTime6, endTime6);
                    event6a.setColor(Color.parseColor("#40b28d"));
                    event2.add(event6a);

                    Calendar startTime7 = Calendar.getInstance();
                    startTime7.set(Calendar.HOUR_OF_DAY, 9);
                    startTime7.set(Calendar.MINUTE, 30);
                    startTime7.set(Calendar.DAY_OF_MONTH, 4);
                    startTime7.set(Calendar.MONTH, i1);
                    startTime7.set(Calendar.YEAR, 2017);
                    Calendar endTime7 = (Calendar) startTime7.clone();
                    endTime7.set(Calendar.MINUTE, 45);
                    WeekViewEvent event7a = new WeekViewEvent(1, "Casino Dice Project", startTime7, endTime7);
                    event7a.setColor(Color.parseColor("#b9611c"));
                    event2.add(event7a);

                    Calendar startTime8 = Calendar.getInstance();
                    startTime8.set(Calendar.HOUR_OF_DAY, 9);
                    startTime8.set(Calendar.MINUTE, 45);
                    startTime8.set(Calendar.DAY_OF_MONTH, 4);
                    startTime8.set(Calendar.MONTH, i1);
                    startTime8.set(Calendar.YEAR, 2017);
                    Calendar endTime8 = (Calendar) startTime8.clone();
                    endTime8.set(Calendar.MINUTE, 60);
                    WeekViewEvent event8a = new WeekViewEvent(1, "BREAK", startTime8, endTime8);
                    event8a.setColor(Color.parseColor("#ecd6a4"));
                    event2.add(event8a);

                    Calendar startTime9 = Calendar.getInstance();
                    startTime9.set(Calendar.HOUR_OF_DAY, 10);
                    startTime9.set(Calendar.MINUTE, 0);
                    startTime9.set(Calendar.DAY_OF_MONTH, 4);
                    startTime9.set(Calendar.MONTH, i1);
                    startTime9.set(Calendar.YEAR, 2017);
                    Calendar endTime9 = (Calendar) startTime9.clone();
                    endTime9.set(Calendar.MINUTE, 15);
                    WeekViewEvent event9a = new WeekViewEvent(1, "The Hi Ball", startTime9, endTime9);
                    event9a.setColor(Color.parseColor("#096c74"));
                    event2.add(event9a);

                    Calendar startTime10 = Calendar.getInstance();
                    startTime10.set(Calendar.HOUR_OF_DAY, 10);
                    startTime10.set(Calendar.MINUTE, 15);
                    startTime10.set(Calendar.DAY_OF_MONTH, 4);
                    startTime10.set(Calendar.MONTH, i1);
                    startTime10.set(Calendar.YEAR, 2017);
                    Calendar endTime10 = (Calendar) startTime10.clone();
                    endTime10.set(Calendar.MINUTE, 30);
                    WeekViewEvent event10a = new WeekViewEvent(1, "LVB Light Rail", startTime10, endTime10);
                    event10a.setColor(Color.parseColor("#79cdcd"));
                    event2.add(event10a);

                    Calendar startTime11 = Calendar.getInstance();
                    startTime11.set(Calendar.HOUR_OF_DAY, 10);
                    startTime11.set(Calendar.MINUTE, 30);
                    startTime11.set(Calendar.DAY_OF_MONTH, 4);
                    startTime11.set(Calendar.MONTH, i1);
                    startTime11.set(Calendar.YEAR, 2017);
                    Calendar endTime11 = (Calendar) startTime11.clone();
                    endTime11.set(Calendar.MINUTE, 45);
                    WeekViewEvent event11a = new WeekViewEvent(1, "CLT", startTime11, endTime11);
                    event11a.setColor(Color.parseColor("#009688"));
                    event2.add(event11a);

                    Calendar startTime12 = Calendar.getInstance();
                    startTime12.set(Calendar.HOUR_OF_DAY, 10);
                    startTime12.set(Calendar.MINUTE, 45);
                    startTime12.set(Calendar.DAY_OF_MONTH, 4);
                    startTime12.set(Calendar.MONTH, i1);
                    startTime12.set(Calendar.YEAR, 2017);
                    Calendar endTime12 = (Calendar) startTime12.clone();
                    endTime12.set(Calendar.MINUTE, 60);
                    WeekViewEvent event12a = new WeekViewEvent(1, "A.P.E.L", startTime12, endTime12);
                    event12a.setColor(Color.parseColor("#94b8b8"));
                    event2.add(event12a);

                    Calendar startTime13 = Calendar.getInstance();
                    startTime13.set(Calendar.HOUR_OF_DAY, 11);
                    startTime13.set(Calendar.MINUTE, 0);
                    startTime13.set(Calendar.DAY_OF_MONTH, 4);
                    startTime13.set(Calendar.MONTH, i1);
                    startTime13.set(Calendar.YEAR, 2017);
                    Calendar endTime13 = (Calendar) startTime13.clone();
                    endTime13.set(Calendar.MINUTE, 15);
                    WeekViewEvent event13a = new WeekViewEvent(1, "Micro Grid II: Controls and Revisions", startTime13, endTime13);
                    event13a.setColor(Color.parseColor("#aaaaaa"));
                    event2.add(event13a);

                    Calendar startTime14 = Calendar.getInstance();
                    startTime14.set(Calendar.HOUR_OF_DAY, 11);
                    startTime14.set(Calendar.MINUTE, 15);
                    startTime14.set(Calendar.DAY_OF_MONTH, 4);
                    startTime14.set(Calendar.MONTH, i1);
                    startTime14.set(Calendar.YEAR, 2017);
                    Calendar endTime14 = (Calendar) startTime14.clone();
                    endTime14.set(Calendar.MINUTE, 30);
                    WeekViewEvent event14a = new WeekViewEvent(1, "Coanda Quadcopter", startTime14, endTime14);
                    event14a.setColor(Color.parseColor("#eeb5b5"));
                    event2.add(event14a);

                    Calendar startTime15 = Calendar.getInstance();
                    startTime15.set(Calendar.HOUR_OF_DAY, 11);
                    startTime15.set(Calendar.MINUTE, 30);
                    startTime15.set(Calendar.DAY_OF_MONTH, 4);
                    startTime15.set(Calendar.MONTH, i1);
                    startTime15.set(Calendar.YEAR, 2017);
                    Calendar endTime15 = (Calendar) startTime15.clone();
                    endTime15.set(Calendar.MINUTE, 45);
                    WeekViewEvent event15a = new WeekViewEvent(1, "CASH", startTime15, endTime15);
                    event15a.setColor(Color.parseColor("#ee7777"));
                    event2.add(event15a);

                    Calendar startTime16 = Calendar.getInstance();
                    startTime16.set(Calendar.HOUR_OF_DAY, 11);
                    startTime16.set(Calendar.MINUTE, 45);
                    startTime16.set(Calendar.DAY_OF_MONTH, 4);
                    startTime16.set(Calendar.MONTH, i1);
                    startTime16.set(Calendar.YEAR, 2017);
                    Calendar endTime16 = (Calendar) startTime16.clone();
                    endTime16.set(Calendar.MINUTE, 60);
                    WeekViewEvent event16a = new WeekViewEvent(1, "Wastewater Evaporator", startTime16, endTime16);
                    event16a.setColor(Color.parseColor("#e7264c"));
                    event2.add(event16a);

                    Calendar startTime17 = Calendar.getInstance();
                    startTime17.set(Calendar.HOUR_OF_DAY, 12);
                    startTime17.set(Calendar.MINUTE, 0);
                    startTime17.set(Calendar.DAY_OF_MONTH, 4);
                    startTime17.set(Calendar.MONTH, i1);
                    startTime17.set(Calendar.YEAR, 2017);
                    Calendar endTime17 = (Calendar) startTime17.clone();
                    endTime17.set(Calendar.MINUTE, 15);
                    WeekViewEvent event17a = new WeekViewEvent(1, "The Electrical Tester", startTime17, endTime17);
                    event17a.setColor(Color.parseColor("#7e3141"));
                    event2.add(event17a);

                    Calendar startTime18 = Calendar.getInstance();
                    startTime18.set(Calendar.HOUR_OF_DAY, 12);
                    startTime18.set(Calendar.MINUTE, 15);
                    startTime18.set(Calendar.DAY_OF_MONTH, 4);
                    startTime18.set(Calendar.MONTH, i1);
                    startTime18.set(Calendar.YEAR, 2017);
                    Calendar endTime18 = (Calendar) startTime18.clone();
                    endTime18.set(Calendar.MINUTE, 30);
                    WeekViewEvent endTime18a = new WeekViewEvent(1, "Universally Expanding Cage ME Team", startTime18, endTime18);
                    endTime18a.setColor(Color.parseColor("#bb769b"));
                    event2.add(endTime18a);

                    Calendar startTime19 = Calendar.getInstance();
                    startTime19.set(Calendar.HOUR_OF_DAY, 12);
                    startTime19.set(Calendar.MINUTE, 30);
                    startTime19.set(Calendar.DAY_OF_MONTH, 4);
                    startTime19.set(Calendar.MONTH, i1);
                    startTime19.set(Calendar.YEAR, 2017);
                    Calendar endTime19 = (Calendar) startTime19.clone();
                    endTime19.set(Calendar.HOUR_OF_DAY, 14);
                    endTime19.set(Calendar.MINUTE, 0);
                    WeekViewEvent endTime19a = new WeekViewEvent(1, "LUNCH", startTime19, endTime19);
                    endTime19a.setColor(Color.parseColor("#84ac83"));
                    event2.add(endTime19a);

                    Calendar startTime20 = Calendar.getInstance();
                    startTime20.set(Calendar.HOUR_OF_DAY, 14);
                    startTime20.set(Calendar.MINUTE, 5);
                    startTime20.set(Calendar.DAY_OF_MONTH, 4);
                    startTime20.set(Calendar.MONTH, i1);
                    startTime20.set(Calendar.YEAR, 2017);
                    Calendar endTime20 = (Calendar) startTime20.clone();
                    endTime20.set(Calendar.MINUTE, 20);
                    WeekViewEvent endTime20a = new WeekViewEvent(1, "Universally Expanding Cage EE Team", startTime20, endTime20);
                    endTime20a.setColor(Color.parseColor("#76bbb2"));
                    event2.add(endTime20a);

                    Calendar startTime21 = Calendar.getInstance();
                    startTime21.set(Calendar.HOUR_OF_DAY, 14);
                    startTime21.set(Calendar.MINUTE, 20);
                    startTime21.set(Calendar.DAY_OF_MONTH, 4);
                    startTime21.set(Calendar.MONTH, i1);
                    startTime21.set(Calendar.YEAR, 2017);
                    Calendar endTime21 = (Calendar) startTime21.clone();
                    endTime21.set(Calendar.MINUTE, 35);
                    WeekViewEvent endTime21a = new WeekViewEvent(1, "Bombyx Mori Silk Antimicrobial Gel", startTime21, endTime21);
                    endTime21a.setColor(Color.parseColor("#b399bf"));
                    event2.add(endTime21a);

                    Calendar startTime22 = Calendar.getInstance();
                    startTime22.set(Calendar.HOUR_OF_DAY, 14);
                    startTime22.set(Calendar.MINUTE, 35);
                    startTime22.set(Calendar.DAY_OF_MONTH, 4);
                    startTime22.set(Calendar.MONTH, i1);
                    startTime22.set(Calendar.YEAR, 2017);
                    Calendar endTime22 = (Calendar) startTime22.clone();
                    endTime22.set(Calendar.MINUTE, 50);
                    WeekViewEvent endTime22a = new WeekViewEvent(1, "Demand Responsive Pedestrian", startTime22, endTime22);
                    endTime22a.setColor(Color.parseColor("#ccd0ac"));
                    event2.add(endTime22a);

                    Calendar startTime23 = Calendar.getInstance();
                    startTime23.set(Calendar.HOUR_OF_DAY, 14);
                    startTime23.set(Calendar.MINUTE, 50);
                    startTime23.set(Calendar.DAY_OF_MONTH, 4);
                    startTime23.set(Calendar.MONTH, i1);
                    startTime23.set(Calendar.YEAR, 2017);
                    Calendar endTime23 = (Calendar) startTime23.clone();
                    endTime23.set(Calendar.HOUR_OF_DAY, 15);
                    endTime23.set(Calendar.MINUTE, 5);
                    WeekViewEvent endTime23a = new WeekViewEvent(1, "Z-Med Alert", startTime23, endTime23);
                    endTime23a.setColor(Color.parseColor("#94b68c"));
                    event2.add(endTime23a);

                    Calendar startTime24 = Calendar.getInstance();
                    startTime24.set(Calendar.HOUR_OF_DAY, 15);
                    startTime24.set(Calendar.MINUTE, 5);
                    startTime24.set(Calendar.DAY_OF_MONTH, 4);
                    startTime24.set(Calendar.MONTH, i1);
                    startTime24.set(Calendar.YEAR, 2017);
                    Calendar endTime24 = (Calendar) startTime24.clone();
                    endTime24.set(Calendar.MINUTE, 20);
                    WeekViewEvent endTime24a = new WeekViewEvent(1, "Spots", startTime24, endTime24);
                    endTime24a.setColor(Color.parseColor("#6da178"));
                    event2.add(endTime24a);

                    Calendar startTime25 = Calendar.getInstance();
                    startTime25.set(Calendar.HOUR_OF_DAY, 15);
                    startTime25.set(Calendar.MINUTE, 20);
                    startTime25.set(Calendar.DAY_OF_MONTH, 4);
                    startTime25.set(Calendar.MONTH, i1);
                    startTime25.set(Calendar.YEAR, 2017);
                    Calendar endTime25 = (Calendar) startTime25.clone();
                    endTime25.set(Calendar.MINUTE, 35);
                    WeekViewEvent endTime25a = new WeekViewEvent(1, "Hailey’s Hand Version 3.0", startTime25, endTime25);
                    endTime25a.setColor(Color.parseColor("#528369"));
                    event2.add(endTime25a);

                    Calendar startTime26 = Calendar.getInstance();
                    startTime26.set(Calendar.HOUR_OF_DAY, 15);
                    startTime26.set(Calendar.MINUTE, 35);
                    startTime26.set(Calendar.DAY_OF_MONTH, 4);
                    startTime26.set(Calendar.MONTH, i1);
                    startTime26.set(Calendar.YEAR, 2017);
                    Calendar endTime26 = (Calendar) startTime26.clone();
                    endTime26.set(Calendar.MINUTE, 50);
                    WeekViewEvent endTime26a = new WeekViewEvent(1, "Micro Grid II: Load Share", startTime26, endTime26);
                    endTime26a.setColor(Color.parseColor("#3d6562"));
                    event2.add(endTime26a);

                    Calendar startTime27 = Calendar.getInstance();
                    startTime27.set(Calendar.HOUR_OF_DAY, 15);
                    startTime27.set(Calendar.MINUTE, 50);
                    startTime27.set(Calendar.DAY_OF_MONTH, 4);
                    startTime27.set(Calendar.MONTH, i1);
                    startTime27.set(Calendar.YEAR, 2017);
                    Calendar endTime27 = (Calendar) startTime27.clone();
                    endTime27.set(Calendar.HOUR_OF_DAY, 16);
                    endTime27.set(Calendar.MINUTE, 5);
                    WeekViewEvent endTime27a = new WeekViewEvent(1, "BREAK", startTime27, endTime27);
                    endTime27a.setColor(Color.parseColor("#ecd6a4"));
                    event2.add(endTime27a);

                    Calendar startTime28 = Calendar.getInstance();
                    startTime28.set(Calendar.HOUR_OF_DAY, 16);
                    startTime28.set(Calendar.MINUTE, 5);
                    startTime28.set(Calendar.DAY_OF_MONTH, 4);
                    startTime28.set(Calendar.MONTH, i1);
                    startTime28.set(Calendar.YEAR, 2017);
                    Calendar endTime28 = (Calendar) startTime28.clone();
                    endTime28.set(Calendar.MINUTE, 20);
                    WeekViewEvent endTime28a = new WeekViewEvent(1, "Harco Spray Gun", startTime28, endTime28);
                    endTime28a.setColor(Color.parseColor("#632b6c"));
                    event2.add(endTime28a);

                    Calendar startTime29 = Calendar.getInstance();
                    startTime29.set(Calendar.HOUR_OF_DAY, 16);
                    startTime29.set(Calendar.MINUTE, 20);
                    startTime29.set(Calendar.DAY_OF_MONTH, 4);
                    startTime29.set(Calendar.MONTH, i1);
                    startTime29.set(Calendar.YEAR, 2017);
                    Calendar endTime29 = (Calendar) startTime29.clone();
                    endTime29.set(Calendar.MINUTE, 35);
                    WeekViewEvent endTime29a = new WeekViewEvent(1, "UNLV Paradise Campus Flooding", startTime29, endTime29);
                    endTime29a.setColor(Color.parseColor("#c76b98"));
                    event2.add(endTime29a);

                    Calendar startTime30 = Calendar.getInstance();
                    startTime30.set(Calendar.HOUR_OF_DAY, 16);
                    startTime30.set(Calendar.MINUTE, 35);
                    startTime30.set(Calendar.DAY_OF_MONTH, 4);
                    startTime30.set(Calendar.MONTH, i1);
                    startTime30.set(Calendar.YEAR, 2017);
                    Calendar endTime30 = (Calendar) startTime30.clone();
                    endTime30.set(Calendar.MINUTE, 50);
                    WeekViewEvent endTime30a = new WeekViewEvent(1, "Carpel Tunnel Relief Massager", startTime30, endTime30);
                    endTime30a.setColor(Color.parseColor("#f09f9c"));
                    event2.add(endTime30a);

                    Calendar startTime31 = Calendar.getInstance();
                    startTime31.set(Calendar.HOUR_OF_DAY, 16);
                    startTime31.set(Calendar.MINUTE, 50);
                    startTime31.set(Calendar.DAY_OF_MONTH, 4);
                    startTime31.set(Calendar.MONTH, i1);
                    startTime31.set(Calendar.YEAR, 2017);
                    Calendar endTime31 = (Calendar) startTime31.clone();
                    endTime31.set(Calendar.HOUR_OF_DAY, 17);
                    endTime31.set(Calendar.MINUTE, 5);
                    WeekViewEvent endTime31a = new WeekViewEvent(1, "Linked", startTime31, endTime31);
                    endTime31a.setColor(Color.parseColor("#fcc3a3"));
                    event2.add(endTime31a);

                    Calendar startTime32 = Calendar.getInstance();
                    startTime32.set(Calendar.HOUR_OF_DAY, 17);
                    startTime32.set(Calendar.MINUTE, 5);
                    startTime32.set(Calendar.DAY_OF_MONTH, 4);
                    startTime32.set(Calendar.MONTH, i1);
                    startTime32.set(Calendar.YEAR, 2017);
                    Calendar endTime32 = (Calendar) startTime32.clone();
                    endTime32.set(Calendar.MINUTE, 20);
                    WeekViewEvent endTime32a = new WeekViewEvent(1, "Adaptive Power Supply", startTime32, endTime32);
                    endTime32a.setColor(Color.parseColor("#006d60"));
                    event2.add(endTime32a);

                    Calendar startTime33 = Calendar.getInstance();
                    startTime33.set(Calendar.HOUR_OF_DAY, 17);
                    startTime33.set(Calendar.MINUTE, 20);
                    startTime33.set(Calendar.DAY_OF_MONTH, 4);
                    startTime33.set(Calendar.MONTH, i1);
                    startTime33.set(Calendar.YEAR, 2017);
                    Calendar endTime33 = (Calendar) startTime33.clone();
                    endTime33.set(Calendar.MINUTE, 35);
                    WeekViewEvent endTime33a = new WeekViewEvent(1, "Hoover Dam Security Checkpoint", startTime33, endTime33);
                    endTime33a.setColor(Color.parseColor("#009481"));
                    event2.add(endTime33a);

                    Calendar startTime34 = Calendar.getInstance();
                    startTime34.set(Calendar.HOUR_OF_DAY, 17);
                    startTime34.set(Calendar.MINUTE, 35);
                    startTime34.set(Calendar.DAY_OF_MONTH, 4);
                    startTime34.set(Calendar.MONTH, i1);
                    startTime34.set(Calendar.YEAR, 2017);
                    Calendar endTime34 = (Calendar) startTime34.clone();
                    endTime34.set(Calendar.MINUTE, 50);
                    WeekViewEvent endTime34a = new WeekViewEvent(1, "Impact Solutions", startTime34, endTime34);
                    endTime34a.setColor(Color.parseColor("#17b297"));
                    event2.add(endTime34a);
                    //TODO: this is your comparison
                    // WeekViewEvent event = new WeekViewEvent(1, "hello world", 2017, 4, 30, 1, 10, 2017, 4, 30, 3, 0);
                    Calendar startTime35 = Calendar.getInstance();
                    startTime35.set(Calendar.HOUR_OF_DAY, 12);
                    startTime35.set(Calendar.MINUTE, 0);
                    startTime35.set(Calendar.DAY_OF_MONTH, 3);
                    startTime35.set(Calendar.MONTH, i1);
                    startTime35.set(Calendar.YEAR, 2017);
                    Calendar endTime35 = (Calendar) startTime35.clone();
                    endTime35.set(Calendar.HOUR_OF_DAY, 17);
                    endTime35.set(Calendar.MINUTE, 0);
                    WeekViewEvent endTime35a = new WeekViewEvent(1, "Senior Design Competition Setup", startTime35, endTime35);
                    endTime35a.setColor(Color.parseColor("#bac6d0"));
                    event2.add(endTime35a);

                    Calendar startTime36 = Calendar.getInstance();
                    startTime36.set(Calendar.HOUR_OF_DAY, 17);
                    startTime36.set(Calendar.MINUTE, 30);
                    startTime36.set(Calendar.DAY_OF_MONTH, 5);
                    startTime36.set(Calendar.MONTH, i1);
                    startTime36.set(Calendar.YEAR, 2017);
                    Calendar endTime36 = (Calendar) startTime36.clone();
                    endTime36.set(Calendar.HOUR_OF_DAY, 18);
                    endTime36.set(Calendar.MINUTE, 45);
                    WeekViewEvent endTime36a = new WeekViewEvent(1, "Viewing of Senior Design Projects", startTime36, endTime36);
                    endTime36a.setColor(Color.parseColor("#ff8773"));
                    event2.add(endTime36a);

                    Calendar startTime37 = Calendar.getInstance();
                    startTime37.set(Calendar.HOUR_OF_DAY, 18);
                    startTime37.set(Calendar.MINUTE, 45);
                    startTime37.set(Calendar.DAY_OF_MONTH, 5);
                    startTime37.set(Calendar.MONTH, i1);
                    startTime37.set(Calendar.YEAR, 2017);
                    Calendar endTime37 = (Calendar) startTime37.clone();
                    endTime37.set(Calendar.HOUR_OF_DAY, 22);
                    endTime37.set(Calendar.MINUTE, 0);
                    WeekViewEvent endTime37a = new WeekViewEvent(1, "Senior Design Awards Dinner", startTime37, endTime37);
                    endTime37a.setColor(Color.parseColor("#bd3a3a"));
                    event2.add(endTime37a);
////

//////                    Log.d("yoooooooo", "hello");
                    Log.d("setWeekendEX", exlist.toString());
                    Log.d("setWeekendEVN", exlist.toString());
                    //if (i == ) {
                    //}
                    return event2;

                    //TODO: Handle month change (return new list of events)
                }
            });
        }}
    private boolean eventMatches(WeekViewEvent event, int year, int month) {
        return (event.getStartTime().get(Calendar.YEAR) == year && event.getStartTime().get(Calendar.MONTH) == month) || (event.getEndTime().get(Calendar.YEAR) == year && event.getEndTime().get(Calendar.MONTH) == month);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_schedule, container, false);
        mWeekView = (WeekView) rootView.findViewById(R.id.weekView);
//// Set an action when any event is clicked.
//        mWeekView.setOnEventClickListener(this);
//
//// The week view has infinite scrolling horizontally. We have to provide the events of a
//// month every time the month changes on the week view.
//        mWeekView.setMonthChangeListener(this);
//
//// Set long press listener for events.
//        mWeekView.setEventLongPressListener(this);
        ScheduleFragment.RetrieveFeedTask rt = new ScheduleFragment.RetrieveFeedTask();
        rt.execute();
        Log.d("onCre", events.toString());

        setupWeekview(events);
        return rootView;
    }

}
