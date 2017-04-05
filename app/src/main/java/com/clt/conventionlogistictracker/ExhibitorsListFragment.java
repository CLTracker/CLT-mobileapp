package com.clt.conventionlogistictracker;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clt.conventionlogistictracker.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExhibitorsListFragment extends Fragment{

    ArrayList<Exhibitor> mExhibitorsList = new ArrayList<Exhibitor>();

    public ExhibitorsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_exhibitors_list, container, false);

        ArrayList<Exhibitor> mExhibitors = getExhibitorsList();
        for (int i = 0; i < mExhibitors.size(); i++) {
            Exhibitor _mExhibitors = mExhibitors.get(i);
            mExhibitorsList.add(_mExhibitors);
        }

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
        return rootView;
    }

    private ArrayList<Exhibitor> getExhibitorsList() {
        //TODO Replace with call to service
        ArrayList<Exhibitor> result = new ArrayList<Exhibitor>();

        result.add(new Exhibitor("FLTR"));
        result.add(new Exhibitor("CASH"));
        result.add(new Exhibitor("SPOTS"));
        result.add(new Exhibitor("Link"));
        result.add(new Exhibitor("1"));
        result.add(new Exhibitor("2"));
        result.add(new Exhibitor("3"));
        result.add(new Exhibitor("4"));
        result.add(new Exhibitor("5"));
        result.add(new Exhibitor("6"));
        result.add(new Exhibitor("7"));
        result.add(new Exhibitor("8"));
        result.add(new Exhibitor("9"));
        result.add(new Exhibitor("10"));
        result.add(new Exhibitor("11"));
        result.add(new Exhibitor("12"));

        return result;
    }

}
