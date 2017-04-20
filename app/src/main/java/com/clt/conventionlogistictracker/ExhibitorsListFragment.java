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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.clt.conventionlogistictracker.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import static android.R.attr.data;
import com.clt.conventionlogistictracker.Exhibitor;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExhibitorsListFragment extends Fragment{

    ArrayList<Exhibitor> mExhibitorsList = new ArrayList<Exhibitor>();
    View rootView;

    public ExhibitorsListFragment() {
        // Required empty public constructor
    }

    private class RetrieveFeedTask extends AsyncTask<Void, Void, ArrayList<Exhibitor>> {
        private Exception exception;

        ArrayList<Exhibitor> temp = new ArrayList<Exhibitor>();

        @Override
        protected ArrayList<Exhibitor> doInBackground(Void... arg0) {

            try{
                String url = "http://cltglobal.ddns.net:8080/exhibitors/1";
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
                        temp.add(new Exhibitor(object.getString("company_name")));
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
        protected void onPostExecute(ArrayList<Exhibitor> result) {
            super.onPostExecute(result);
            setList(result);
            loadAll();
            Log.d("WORK???4", result.toString());
        }
    }

    public void setList(ArrayList<Exhibitor> exlist){
        this.mExhibitorsList = exlist;
    }


    public void loadAll() {
        // create an adapter
        RecyclerViewAdapter adapter = new RecyclerViewAdapter<Exhibitor>(mExhibitorsList, R.layout.exhibitor_item_layout, BR.exhibitor);
        RecyclerView exhibitorListContainer = (RecyclerView) rootView.findViewById(R.id.exhibitors_list_recycler_view);
        exhibitorListContainer.setHasFixedSize(false);

        // set adapter
        exhibitorListContainer.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        exhibitorListContainer.setLayoutManager(llm);
        adapter.notifyDataSetChanged();
        exhibitorListContainer.invalidate();
        adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_exhibitors_list, container, false);
        RetrieveFeedTask rt = new RetrieveFeedTask();
        rt.execute();

        return rootView;
    }

}
