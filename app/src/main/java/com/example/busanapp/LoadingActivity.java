package com.example.busanapp;
// 편의시설 네비 드로워 어플

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.busanapp.calendar.CalendarFragment;
import com.example.busanapp.ui.home.BusFragment;
import com.example.busanapp.ui.home.DisabledFragment;
import com.example.busanapp.ui.home.FindFoodFragment;
import com.example.busanapp.ui.home.FindHospitalFragment;
import com.example.busanapp.ui.home.FoodFragment;
import com.example.busanapp.home.HomeFragment;
import com.example.busanapp.ui.home.MapFragment;
import com.example.busanapp.ui.home.HospitalFragment;
import com.example.busanapp.ui.home.ParkingFragment;
import com.example.busanapp.ui.home.PublicartFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import noman.googleplaces.Place;
import noman.googleplaces.PlacesException;
import noman.googleplaces.PlacesListener;


public class LoadingActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ActivityCompat.OnRequestPermissionsResultCallback, PlacesListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                break;

            case R.id.nav_hospital:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HospitalFragment()).commit();
                break;
            case R.id.nav_find_hospital:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FindHospitalFragment()).commit();
                break;

            case R.id.nav_parking:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ParkingFragment()).commit();
                break;

            case R.id.nav_bus:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new BusFragment()).commit();
                break;
            case R.id.nav_disabled_person:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DisabledFragment()).commit();
                break;

            case R.id.nav_food:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FoodFragment()).commit();
                break;

            case R.id.nav_find_food:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FindFoodFragment()).commit();
                break;


            case R.id.nav_publicart:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PublicartFragment()).commit();
                break;

            case R.id.nav_calendar:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CalendarFragment()).commit();
                break;

            case R.id.nav_map:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MapFragment()).commit();
                break;
            default:
                break;
        }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onPlacesFailure(PlacesException e) {
    }

    @Override
    public void onPlacesStart() {
    }

    @Override
    public void onPlacesSuccess(final List<Place> places) {
    }

    @Override
    public void onPlacesFinished() {

    }
}