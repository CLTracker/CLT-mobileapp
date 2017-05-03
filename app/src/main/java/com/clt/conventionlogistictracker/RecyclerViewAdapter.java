package com.clt.conventionlogistictracker;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
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

        /* Set up onClick to grab the bio of the companies
         * Display the bio of the companies in an AlertDialog
         */
        @Override
        public void onClick(View v) {
            Log.d("RecyclerView onClick", "Element " + getAdapterPosition() + " clicked.");

            String message = mExhibitors.get(getAdapterPosition()).getBio();
            if (message == null) {
                message = "No bio set by this exhibitor.";
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle(mExhibitors.get(getAdapterPosition()).getCompanyName());
            builder.setMessage(message);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

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

    /* Inflate the layout
     * Add the cardviews into the container
     */
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exhibitor_item_layout, parent, false);
        return new RecyclerViewHolder(view);
    }

    /* Bind the view
     * Loads the image of mExhibitor into the cardview
     */
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
