package com.clt.conventionlogistictracker;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private ArrayList<News> mNewsList = new ArrayList<News>();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        ArrayList<News> mNews = getNewsList();
        for(int i = 0; i < mNews.size(); i++) {
            News _mNews = mNews.get(i);
            mNewsList.add(_mNews);
        }

        // create an adapter
        RecyclerViewAdapter adapter = new RecyclerViewAdapter<News>(mNewsList, R.layout.news_item_layout, BR.news);
        RecyclerView newsListContainer = (RecyclerView) rootView.findViewById(R.id.news_list_recycler_view);
        newsListContainer.setHasFixedSize(false);
        // set adapter
        newsListContainer.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        newsListContainer.setLayoutManager(llm);
        // Inflate the layout for this fragment
        return rootView;
    }

    private ArrayList<News> getNewsList() {
        //TODO Replace with call to service
        ArrayList<News> result = new ArrayList<News>();

        result.add(new News("CES Kicks Off!", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "));
        result.add(new News("Free Swag at Samsung Booth", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "));
        result.add(new News("New Drone Hits the Stage", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "));
        result.add(new News("Apple Announcement", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "));
        result.add(new News("Tesla Speech @2pm", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "));
        result.add(new News("Free Food in West Wing", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "));
        result.add(new News("CES Kicks Off!", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "));
        result.add(new News("Free Swag at Samsung Booth", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "));
        result.add(new News("New Drone Hits the Stage", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "));
        result.add(new News("Apple Announcement", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "));
        result.add(new News("Tesla Speech @2pm", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "));
        result.add(new News("Free Food in West Wing", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "));
        result.add(new News("9", "hello"));
        result.add(new News("10", "hello"));
        result.add(new News("11", "hello"));
        result.add(new News("12", "hello"));

        return result;
    }

}
