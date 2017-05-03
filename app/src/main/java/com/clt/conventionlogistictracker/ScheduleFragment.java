package com.clt.conventionlogistictracker;


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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {

    private ArrayList<Schedule> mScheduleList = new ArrayList<Schedule>();
    View rootView;


    public ScheduleFragment() {
        // Required empty public constructor
    }
/*
    private class RetrieveFeedTask extends AsyncTask<Void, Void, ArrayList<Schedule>> {
        private Exception exception;

        ArrayList<Schedule> temp = new ArrayList<Schedule>();

        @Override
        protected ArrayList<Schedule> doInBackground(Void... arg0) {

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

                Log.d("WORK???1", response.toString());

                JSONArray jsonArray = new JSONArray(response.toString());
                for (int i=0; i<jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    if (jsonArray != null) {
                        temp.add(new Schedule(object.getString("start_time"),object.getString("event_name"),object.getString("end_time")));
                        Log.d("WORK???2", temp.toString());
                    }
                }
                Log.d("WORK???3", jsonArray.toString());
            }catch(Exception e)
            {
                Log.d("NO WORK!!!", "\n\n\n"+e.toString()+"\n\n\n\n");
            }
            return temp;
        }
        protected void onPostExecute(ArrayList<Schedule> result) {
            super.onPostExecute(result);
            setList(result);
            loadAll();
            Log.d("WORK???4", result.toString());
        }
    }
    public void setList(ArrayList<Schedule> exlist){
        this.mScheduleList = exlist;
    }

    public void loadAll() {
        // create an adapter
        RecyclerViewAdapter adapter = new RecyclerViewAdapter<Schedule>(mScheduleList, R.layout.schedule_item_layout, BR.schedule);
        RecyclerView newsListContainer = (RecyclerView) rootView.findViewById(R.id.schedule_recycler_view);
        newsListContainer.setHasFixedSize(false);

        // set adapter
        newsListContainer.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        newsListContainer.setLayoutManager(llm);
        adapter.notifyDataSetChanged();
        newsListContainer.invalidate();
        adapter.notifyDataSetChanged();
    }
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_schedule, container, false);
        //ScheduleFragment.RetrieveFeedTask rt = new ScheduleFragment.RetrieveFeedTask();
        //rt.execute();
        return rootView;
    }

}
