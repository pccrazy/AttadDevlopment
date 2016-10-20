package ev.com.evshopapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import Fragments.Account_fragment;
import Fragments.Courses_fragment;
import Fragments.Foss_fragment;
import Fragments.Home_fragment;
import Fragments.Messages_fragment;
import Fragments.Orders_fragment;
import Fragments.WishList_fragment;
import NavDrower.CouNavItem;
import SQliteDatabase.NavigationItemsSQLiteInfo;

public class MainHome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private boolean doubleBackToExitPressedTwice;

    NavigationView navigationView;
    CouNavItem couNavItem;
    NavigationItemsSQLiteInfo myDB;
    private Handler mHandler = new Handler();
    public Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedTwice = false;
        }
    };

    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        couNavItem=new CouNavItem();
        myDB = new NavigationItemsSQLiteInfo(this);

        ChickData();


        Home_fragment homeFragment = new Home_fragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .setCustomAnimations(R.anim.anim_slide_in_from_left, R.anim.anim_slide_out_from_left)
                .replace(R.id.LayoutForFragment,
                        homeFragment,
                        homeFragment.getTag()
                ).commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                couNavItem.setCountwhishlist((couNavItem.getCountwhishlist()+1));
                couNavItem.setCountOrder((couNavItem.getCountOrder()+1));
                couNavItem.setCountmessages((couNavItem.getCountmessages()+1));

                setMenuCounterWhishlist(R.id.nav_Wishlist);
                setMenuCounterOrders(R.id.nav_Order);
                setMenuCounterMessages(R.id.nav_messages);

                SavingOnSQlite();

                Snackbar.make(view, "new data added and saved on SQlite ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
         toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedTwice) {

                this.moveTaskToBack(true);
                return;
            }
            this.doubleBackToExitPressedTwice = true;
            mHandler.postDelayed(mRunnable, 2000);
            Toast.makeText(this, getResources().getString(R.string.clickbacktoexit), Toast.LENGTH_SHORT).show();


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_home, menu);
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles

        toggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Home) {

            Home_fragment homeFragment = new Home_fragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .setCustomAnimations(R.anim.anim_slide_in_from_left, R.anim.anim_slide_out_from_left)
                    .replace(R.id.LayoutForFragment,
                            homeFragment,
                            homeFragment.getTag()
                    ).commit();

        } else if (id == R.id.nav_Account) {
            Account_fragment accountFragment = new Account_fragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .setCustomAnimations(R.anim.anim_slide_in_from_left, R.anim.anim_slide_out_from_left)
                    .replace(R.id.LayoutForFragment,
                            accountFragment,
                            accountFragment.getTag()
                    ).commit();

        } else if (id == R.id.nav_Wishlist) {

            WishList_fragment wishListFragment = new WishList_fragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .setCustomAnimations(R.anim.anim_slide_in_from_left, R.anim.anim_slide_out_from_left)
                    .replace(R.id.LayoutForFragment,
                            wishListFragment,
                            wishListFragment.getTag()
                    ).commit();

        } else if (id == R.id.nav_Order) {
            Orders_fragment ordersFragment = new Orders_fragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .setCustomAnimations(R.anim.anim_slide_in_from_left, R.anim.anim_slide_out_from_left)
                    .replace(R.id.LayoutForFragment,
                            ordersFragment,
                            ordersFragment.getTag()
                    ).commit();

        } else if (id == R.id.nav_messages) {
            Messages_fragment messagesFragment = new Messages_fragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .setCustomAnimations(R.anim.anim_slide_in_from_left, R.anim.anim_slide_out_from_left)
                    .replace(R.id.LayoutForFragment,
                            messagesFragment,
                            messagesFragment.getTag()
                    ).commit();

        }else if (id == R.id.nav_Courses) {
            Courses_fragment coursesFragment = new Courses_fragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .setCustomAnimations(R.anim.anim_slide_in_from_left, R.anim.anim_slide_out_from_left)
                    .replace(R.id.LayoutForFragment,
                            coursesFragment,
                            coursesFragment.getTag()
                    ).commit();

        }else if (id == R.id.nav_foss) {
            Foss_fragment fossFragment = new Foss_fragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .setCustomAnimations(R.anim.anim_slide_in_from_left, R.anim.anim_slide_out_from_left)
                    .replace(R.id.LayoutForFragment,
                            fossFragment,
                            fossFragment.getTag()
                    ).commit();

        }else if (id == R.id.nav_LiveSupport) {

        }else if (id == R.id.nav_ContactUs){

        }else if (id == R.id.nav_Location){

        }else if (id == R.id.nav_OurTeam){

        }else if (id == R.id.nav_Settings){

        }else if (id == R.id.nav_About){

        }



            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setMenuCounterWhishlist(@IdRes int itemId) {
        TextView view = (TextView) navigationView.getMenu().findItem(itemId).getActionView();
        view.setText(String.valueOf(couNavItem.getCountwhishlist()));
    }

    private void setMenuCounterOrders(@IdRes int itemId) {
        TextView view = (TextView) navigationView.getMenu().findItem(itemId).getActionView();
        view.setText(String.valueOf(couNavItem.getCountOrder()));
    }
    private void setMenuCounterMessages(@IdRes int itemId) {
        TextView view = (TextView) navigationView.getMenu().findItem(itemId).getActionView();
        view.setText(String.valueOf(couNavItem.getCountmessages()));
    }

    public void ChickData(){
        Cursor res = myDB.getAllData();

        while (res.moveToNext()) {
            couNavItem.setCountwhishlist(Integer.parseInt(res.getString(1)));
            couNavItem.setCountOrder(Integer.parseInt(res.getString(2)));
            couNavItem.setCountmessages(Integer.parseInt(res.getString(3)));
        }

    }

    public void SavingOnSQlite() {

        boolean isinserted = myDB.UpdateData(couNavItem.getCountwhishlist(),couNavItem.getCountOrder(),couNavItem.getCountmessages());

        if (isinserted) {
            Toast.makeText(MainHome.this, "Data updated", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(MainHome.this, "Data updated", Toast.LENGTH_SHORT).show(); }

    }



}
