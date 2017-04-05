package com.clt.conventionlogistictracker;

import android.view.View;

/**
 * Created by dongw on 4/4/17.
 */

public interface IViewEvents<T> {
    void onClick(View view, T clickedItem);
}
