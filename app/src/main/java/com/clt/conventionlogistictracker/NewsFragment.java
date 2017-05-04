package com.clt.conventionlogistictracker;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
public class NewsFragment extends Fragment {

    private ArrayList<News> mNewsList = new ArrayList<News>();
    private View rootView;

    public NewsFragment() {
        // Required empty public constructor
    }

    private class RetrieveFeedTask extends AsyncTask<Void, Void, ArrayList<News>> {
        private Exception exception;

        ArrayList<News> temp = new ArrayList<News>();

        @Override
        protected ArrayList<News> doInBackground(Void... arg0) {

            try{
                String url = "https://clt.website/news/1";
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
                        temp.add(new News(object.getString("title"),object.getString("text"),object.getString("author"), object.getString("logo_url")));
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
        protected void onPostExecute(ArrayList<News> result) {
            super.onPostExecute(result);
            setList(result);
            loadAll();
            Log.d("WORK???4", result.toString());
        }
    }
    public void setList(ArrayList<News> exlist){
        this.mNewsList = exlist;
    }

    public void loadAll() {
        RecyclerView newsListContainer = (RecyclerView) rootView.findViewById(R.id.news_list_recycler_view);
        NewsViewAdapter adapter = new NewsViewAdapter(getActivity().getApplicationContext(), mNewsList);
        newsListContainer.setHasFixedSize(false);
        newsListContainer.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        newsListContainer.setLayoutManager(llm);
        adapter.notifyDataSetChanged();
        newsListContainer.invalidate();
        adapter.notifyDataSetChanged();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        NewsFragment.RetrieveFeedTask rt = new RetrieveFeedTask();
        rt.execute();

        return rootView;
    }
}
