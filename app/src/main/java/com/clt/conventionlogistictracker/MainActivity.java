package com.clt.conventionlogistictracker;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {


    private DrawerLayout mDrawer;
    private NavigationView mNavigationDrawer;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar();
        setupDrawer();
        //new ExhibitorsListFragment.RetrieveFeedTask().execute(url);

        if(savedInstanceState == null) {
            NewsFragment newsFragment = new NewsFragment();
            FragmentManager managerHome = getSupportFragmentManager();
            managerHome.beginTransaction().replace(R.id.frame_content, newsFragment, newsFragment.getTag()).commit();
        }
    }


    /** Sets up the toolbar
     *  Uses the default Android back btn as menu (hamburger) btn
     */
    private void setToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.hamburger_action);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /** Do stuff with action bar icons */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /** Sets up the drawer
     * Fills in cases for drawer menu
     */
    private void setupDrawer() {
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationDrawer = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        NewsFragment newsFragment = new NewsFragment();
                        FragmentManager managerHome = getSupportFragmentManager();
                        managerHome.beginTransaction().replace(R.id.frame_content, newsFragment, newsFragment.getTag()).commit();
                        break;
                    case R.id.exhibitors_list:
                        ExhibitorsListFragment exhibitorsListFragment = new ExhibitorsListFragment();
                        FragmentManager manager = getSupportFragmentManager();
                        manager.beginTransaction().replace(R.id.frame_content , exhibitorsListFragment, exhibitorsListFragment.getTag()).commit();
                        break;
                    case R.id.floor_plan:
                        FloorPlansFragment floorPlansFragment = new FloorPlansFragment();
                        FragmentManager manager2 = getSupportFragmentManager();
                        manager2.beginTransaction().replace(R.id.frame_content , floorPlansFragment).commit();
                        break;
                    case R.id.schedule:
                        ScheduleFragment scheduleFragment = new ScheduleFragment();
                        FragmentManager manager3 = getSupportFragmentManager();
                        manager3.beginTransaction().replace(R.id.frame_content , scheduleFragment).commit();
                        break;
                    default:
                        break;
                }
                menuItem.setChecked(true);
                setTitle(menuItem.getTitle());
                mDrawer.closeDrawers();
                return true;
            }
        });
    }
}