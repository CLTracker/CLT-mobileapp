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
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static android.R.attr.data;
import static com.clt.conventionlogistictracker.Exhibitor.company_name;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExhibitorsListFragment extends Fragment{

    ArrayList<Exhibitor> mExhibitorsList = new ArrayList<Exhibitor>();

    public ExhibitorsListFragment() {
        // Required empty public constructor
    }

    private class RetrieveFeedTask extends AsyncTask<String, Void, Void> {
        private Exception exception;

        @Override
        protected Void doInBackground(String... strings) {

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

                Log.d("does", response.toString());
                //ArrayList<Exhibitor> mExhibitors = new ArrayList<Exhibitor>();

                JSONArray jsonArray = new JSONArray(response.toString());
               // JSONArray jsonArray1 = response.getJSONArray("mExhibitors");
                for (int i=0; i<jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    Exhibitor exhibitor = new Exhibitor();
                    if (jsonArray != null) {
                        exhibitor.setCompanyName(object.getString("company_name"));
//                        exhibitor.setLogoUrl(object.getString("logo_url"));
//                        exhibitor.setConference(object.getString("conference"));
                    }
                    mExhibitorsList.add(exhibitor);
                }

                Log.d("DOES THIS FUCKING WORK", jsonArray.toString());

            }catch(Exception e)

            {
                Log.d("well", "\n\n\n"+e.toString()+"\n\n\n\n");
            }
            return null;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exhibitors_list, container, false);
        RetrieveFeedTask rt = new RetrieveFeedTask();
        rt.execute();
//        ArrayList<Exhibitor> mExhibitors = getExhibitorsList();
//        for (int i = 0; i < mExhibitors.size(); i++) {
//            Exhibitor _mExhibitors = mExhibitors.get(i);
//            mExhibitorsList.add(_mExhibitors);
//        }

        // create an adapter
        RecyclerViewAdapter adapter = new RecyclerViewAdapter<Exhibitor>(mExhibitorsList, R.layout.exhibitor_item_layout, BR.exhibitor);
        RecyclerView exhibitorListContainer = (RecyclerView) rootView.findViewById(R.id.exhibitors_list_recycler_view);
        exhibitorListContainer.setHasFixedSize(false);
        // set adapter
        exhibitorListContainer.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        exhibitorListContainer.setLayoutManager(llm);

        // Inflate the layout for this fragment
//
//        RequestQueue requestQueue =  Volley.newRequestQueue(getActivity().getApplicationContext());
//        String url = "http://0.0.0.0:5000/exhibitors/1";
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                try {
//                    if (response.length() > 0) {
//                        mExhibitorsList.clear();
//                        for (int i = 0; i < response.length(); i++) {
//                            JSONObject jsonObject = response.getJSONObject(i);
//                            Exhibitor obj = new Exhibitor();
//                            if (!jsonObject.isNull("company_name")) {
//                                Exhibitor.company_name = jsonObject.getString("company_name");
//                            }
//                            mExhibitorsList.add(obj);
//                        }
//                        adapter.notifyDataSetChanged();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                System.out.println("no");
//            }
//        });
//
//        requestQueue.add(jsonArrayRequest);


        return rootView;
    }

//    private ArrayList<Exhibitor> getExhibitorsList() {
//        //TODO Replace with call to service
//        ArrayList<Exhibitor> result = new ArrayList<Exhibitor>();
//
//        result.add(new Exhibitor("FLTR"));
//        result.add(new Exhibitor("CASH"));
//        result.add(new Exhibitor("SPOTS"));
//        result.add(new Exhibitor("Link"));
//        result.add(new Exhibitor("1"));
//        result.add(new Exhibitor("2"));
//        result.add(new Exhibitor("3"));
//        result.add(new Exhibitor("4"));
//        result.add(new Exhibitor("5"));
//        result.add(new Exhibitor("6"));
//        result.add(new Exhibitor("7"));
//        result.add(new Exhibitor("8"));
//        result.add(new Exhibitor("9"));
//        result.add(new Exhibitor("10"));
//        result.add(new Exhibitor("11"));
//        result.add(new Exhibitor("12"));
//
//        return result;
//    }

}
