package com.clt.conventionlogistictracker;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import java.util.ArrayList;

/**
 * Created by dongw on 4/4/17.
 */


public class RecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    private ArrayList<T> _items;
    private int _layoutId;
    private int _variableId;
    //private IViewEvents<T> _viewEvents;


    // TODO the ViewEvents stuff doesn't work with fragments - fragments can't get context, can only getActivity
    public RecyclerViewAdapter(ArrayList<T> items, int layoutId, int variableId) { //IViewEvents<T> viewEvents){
        _items = items;
        _layoutId = layoutId;
        _variableId = variableId;
        //_viewEvents = viewEvents;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(_layoutId, parent, false);
        return new RecyclerViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder,final int position) {
        holder.getViewDataBinding().setVariable(_variableId, _items.get(position));
        /*
        if(_viewEvents != null)
        {
            holder.getView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    _viewEvents.onClick(view, _items.get(position));
                }
            });
        }*/
    }
    @Override
    public int getItemCount() {
        return _items.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        private ViewDataBinding _itemBinding;
        private View _view;

        public RecyclerViewHolder(View view) {
            super(view);
            _view = view;
            _itemBinding = DataBindingUtil.bind(view);
        }

        public View getView()
        {
            return _view;
        }

        public ViewDataBinding getViewDataBinding()
        {
            return _itemBinding;
        }
    }
}
