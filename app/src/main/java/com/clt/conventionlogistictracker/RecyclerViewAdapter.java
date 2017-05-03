package com.clt.conventionlogistictracker;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dongw on 4/4/17.
 */


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView;
        public ImageView imageView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textView = (TextView) itemView.findViewById(R.id.company_name);
            imageView = (ImageView) itemView.findViewById(R.id.company_logo);
        }

        @Override
        public void onClick(View v) {
            Log.d("RecyclerView onClick", "Element " + getAdapterPosition() + " clicked.");

            Toast toast = Toast.makeText(mContext, "Got to " + getAdapterPosition() + " here", Toast.LENGTH_SHORT);
            toast.show();

            if (mOnEntryClickListener != null) {
                mOnEntryClickListener.onEntryClick(v, getLayoutPosition());
            }
        }
    }

    private Context mContext;
    private ArrayList<Exhibitor> mExhibitors;

    public RecyclerViewAdapter(Context context, ArrayList<Exhibitor> arrayExhibitors) {
        mContext = context;
        mExhibitors = arrayExhibitors;
    }

    @Override
    public int getItemCount() {
        return mExhibitors.size();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exhibitor_item_layout, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Exhibitor exhibitor = mExhibitors.get(position);

        Picasso.with(mContext)
                .load(exhibitor.getLogoUrl())
                .into(holder.imageView);
        holder.textView.setText(exhibitor.getCompanyName());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    private OnEntryClickListener mOnEntryClickListener;

    public interface OnEntryClickListener {
        void onEntryClick(View view, int position);
    }

    public void setOnEntryClickListener(OnEntryClickListener onEntryClickListener) {
        mOnEntryClickListener = onEntryClickListener;
    }


}
