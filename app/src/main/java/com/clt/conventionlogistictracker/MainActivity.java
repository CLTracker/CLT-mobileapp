package com.clt.conventionlogistictracker;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private NavigationView mNavigationDrawer;
    private Toolbar mToolbar;
    private ActionMenuView amvMenu;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationDrawer = (NavigationView) findViewById(R.id.navigation_view);


        amvMenu = (ActionMenuView) mToolbar.findViewById(R.id.amvMenu);
        amvMenu.setOnMenuItemClickListener(new ActionMenuView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return onOptionsItemSelected(item);
            }
        });

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(null);
        setupDrawerContent(mNavigationDrawer);
        /** Back arrow uses
         * getSupportActionBar().setDisplayHomeAsUpEnabled(true); */
    }

    /** Gets the icons for the action bar */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // use amvMenu here
        inflater.inflate(R.menu.toolbar_menu, amvMenu.getMenu());
        return super.onCreateOptionsMenu(menu);
    }

    /** Do stuff with action bar icons */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /** Set up the side menu */
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        //selectDrawerItem(item);
                        return true;
                    }
                });
    }

    /** Do stuff with the navigation options */
    /*public void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.home:
                fragmentClass = FirstFragment.class;
                break;
            case R.id.exhibitors_list:
                break;
            case R.id.floor_plan:
                break;
            case R.id.schedule:
                break;
            case R.id.about:
                break;
            default:
                fragmentClass = FirstFragment.class;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Fragment fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace().replace(R.id.frame_content, fragment).commit();

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawer.closeDrawers();
    }*/
}