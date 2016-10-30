package com.client.maks.client;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

//import com.mapbox.mapboxsdk.MapboxAccountManager;
import com.google.android.gms.maps.MapFragment;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import fragments.FriendsFragment;
import fragments.GMapFragment;

//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v4.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    final private static String FKEY = "Fragment";
    private static GMapFragment mMapFragment;
    private Drawer result;
    private String currentFragment = "Map";
    private String titles[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //MapboxAccountManager.start(this, getString(R.string.accessToken));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mMapFragment = new GMapFragment();

        if (savedInstanceState != null) {
            currentFragment = savedInstanceState.getString(FKEY);
            Log.d(TAG, "onCreate: currentFragment is " + currentFragment);
            setToolBarTitle(currentFragment);
        } else {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frame, mMapFragment, "visible_fragment");
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
            setToolBarTitle(currentFragment);
        }

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(getResources().getDrawable(R.drawable.headerback))
                .addProfiles(
                        new ProfileDrawerItem().withName("Egor Zagny").withEmail("panEgorka@gmail.com").withIcon(getResources().getDrawable(R.drawable.header))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();
        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem map = new PrimaryDrawerItem().withName(R.string.map).withIdentifier(1);
        PrimaryDrawerItem myPlaces = new PrimaryDrawerItem().withName(R.string.my_Places).withIdentifier(2);
        PrimaryDrawerItem actions = new PrimaryDrawerItem().withName(R.string.actions).withIdentifier(3);
        PrimaryDrawerItem whereGo = new PrimaryDrawerItem().withName(R.string.whereGo).withIdentifier(4);
        PrimaryDrawerItem friends = new PrimaryDrawerItem().withName(R.string.friends).withIdentifier(5);
        PrimaryDrawerItem profile = new PrimaryDrawerItem().withName(R.string.profile).withIdentifier(6);


//create the drawer and remember the `Drawer` result object
        result = new DrawerBuilder()
                .withAccountHeader(headerResult)
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        map,
                        myPlaces,
                        actions,
                        whereGo,
                        friends,
                        profile,
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_settings)
                )
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Скрываем клавиатуру при открытии Navigation Drawer
                        InputMethodManager inputMethodManager = (InputMethodManager) MainActivity.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(), 0);
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                    }

                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {

                    }
                })
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Fragment fragment = new Fragment();
                        switch (position) {
                            case 1:
                                fragment = mMapFragment;
                                currentFragment = "Map";
                                break;
                            case 5:
                                fragment = new FriendsFragment();
                                currentFragment = "Friends";
                                break;
                        }
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.frame, fragment, "visible_fragment");
                        ft.addToBackStack(null);
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        ft.commit();
                        setToolBarTitle(currentFragment);
                        result.closeDrawer();
                        return true;
                    }
                })
                .build();
        getFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    @Override
                    public void onBackStackChanged() {
                        FragmentManager fragMan = getFragmentManager();
                        Fragment fragment = fragMan.findFragmentByTag("visible_fragment");
                        if (fragment instanceof GMapFragment) {
                            currentFragment = "Map";
                        }
                        if (fragment instanceof FriendsFragment) {
                            currentFragment = "Friends";
                        }
                        setToolBarTitle(currentFragment);

                    }
                }
        );
    }

    @Override
    public void onBackPressed() {
        if (result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
        FragmentManager fragMan = getFragmentManager();
        Fragment fragment = fragMan.findFragmentByTag("visible_fragment");
        if (fragment instanceof GMapFragment) {
            currentFragment = "Map";
            result.setSelection(1);
        }
        if (fragment instanceof FriendsFragment) {
            currentFragment = "Friends";
            result.setSelection(5);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(FKEY, currentFragment);
        Log.d(TAG, "onSaveInstanceState: adding " + currentFragment);

    }

    private void setToolBarTitle(String fragmentName) {
        Log.d(TAG, "setToolBarTitle: " + fragmentName);
        String title = "Meetify";
        if (fragmentName.equals("Map")) {
            title = "Map";
        }
        if (fragmentName.equals("Friends")) {
            title = "Friends";
        }
        getSupportActionBar().setTitle(title);
    }
}




