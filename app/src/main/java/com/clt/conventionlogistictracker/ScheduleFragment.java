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


//                try {


//                } catch (Exception e) {
//                }

                //Log.d("WORK???3", jsonArray.toString());
            }catch(Exception e)
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



    public void loadAll() {
        //setupWeekview();
        // create an adapter
//        RecyclerViewAdapter adapter = new RecyclerViewAdapter<Schedule>(mScheduleList, R.layout.schedule_item_layout, BR.schedule);
//        RecyclerView newsListContainer = (RecyclerView) rootView.findViewById(R.id.schedule_recycler_view);
//        newsListContainer.setHasFixedSize(false);
////
////        // set adapter
//        newsListContainer.setAdapter(adapter);
//        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
//        newsListContainer.setLayoutManager(llm);
//        adapter.notifyDataSetChanged();
//        newsListContainer.invalidate();
//        adapter.notifyDataSetChanged();

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
                    WeekViewEvent event2a = new WeekViewEvent(1, "Project Apollo: Automated Helio", startTime2, endTime2);
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
                    event5a.setColor(Color.parseColor("#72d3bb"));
                    event2.add(event5a);
                    //TODO: this is your comparison
                    // WeekViewEvent event = new WeekViewEvent(1, "hello world", 2017, 4, 30, 1, 10, 2017, 4, 30, 3, 0);

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

