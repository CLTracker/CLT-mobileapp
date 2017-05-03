package com.clt.conventionlogistictracker;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dongw on 5/2/17.
 */

public class NewsViewAdapter extends RecyclerView.Adapter<NewsViewAdapter.NewsViewHolder> {

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CardView card;
        private TextView title;
        private TextView announcement;
        private TextView author;
        public ImageView imageView;

        public NewsViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.firstLine);
            announcement = (TextView) itemView.findViewById(R.id.secondLine);
            author = (TextView) itemView.findViewById(R.id.thirdLine);
            imageView = (ImageView) itemView.findViewById(R.id.company_logo);
        }

        @Override
        public void onClick(View v) {
            Log.d("RecyclerView onClick", "Element " + getAdapterPosition() + " clicked.");

            if (mOnEntryClickListener != null) {
                mOnEntryClickListener.onEntryClick(v, getLayoutPosition());
            }
        }
    }

    private Context mContext;
    private ArrayList<News> mNews;

    public NewsViewAdapter(Context context, ArrayList<News> arrayNews) {
        mContext = context;
        mNews = arrayNews;
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_layout, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        News news = mNews.get(position);

        Picasso.with(mContext)
                .load(news.getLogoUrl())
                .into(holder.imageView);
        holder.title.setText(news.getTitle());
        holder.announcement.setText(news.getText());
        holder.author.setText(news.getAuthor());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    private com.clt.conventionlogistictracker.RecyclerViewAdapter.OnEntryClickListener mOnEntryClickListener;

    public interface OnEntryClickListener {
        void onEntryClick(View view, int position);
    }

    public void setOnEntryClickListener(com.clt.conventionlogistictracker.RecyclerViewAdapter.OnEntryClickListener onEntryClickListener) {
        mOnEntryClickListener = onEntryClickListener;
    }
}