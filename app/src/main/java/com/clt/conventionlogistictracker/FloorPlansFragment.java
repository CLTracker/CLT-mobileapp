package com.clt.conventionlogistictracker;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.squareup.picasso.Picasso;

public class FloorPlansFragment extends Fragment {

    public FloorPlansFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_floor_plans, container, false);
        /*WebView mWebView = (WebView) rootView.findViewById(R.id.floor_plan);
        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setDisplayZoomControls(false);
        mWebView.getSettings().setDomStorageEnabled(true);

        mWebView.loadUrl("http://i.imgur.com/uxbA4N2.jpg");*/


        TouchImageView imageView = (TouchImageView) rootView.findViewById(R.id.floor_plan);

        Picasso.with(getContext())
                .load("http://i.imgur.com/uxbA4N2.jpg")
                .into(imageView);
        // Inflate the layout for this fragment
        return rootView;
    }
}
