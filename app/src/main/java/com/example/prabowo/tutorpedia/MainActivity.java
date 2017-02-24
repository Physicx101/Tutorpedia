package com.example.prabowo.tutorpedia;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            HomeFragment homeFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).addToBackStack(null).commit();

            BottomNavigationView bottomNavigationView = (BottomNavigationView)
                    findViewById(R.id.bottom_navigation);


            BottomNavigationHelper.disableShiftMode(bottomNavigationView);

            bottomNavigationView.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.action_home:
                                    HomeFragment homeFragment = new HomeFragment();
                                    getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).addToBackStack(null).commit();
                                    break;
                                case R.id.action_event:
                                    EventFragment eventFragment = new EventFragment();
                                    getSupportFragmentManager().beginTransaction().replace(R.id.container, eventFragment).addToBackStack(null).commit();
                                    break;
                                case R.id.action_find:
                                    FindFragment findFragment = new FindFragment();
                                    getSupportFragmentManager().beginTransaction().replace(R.id.container, findFragment).addToBackStack(null).commit();
                                    break;
                                case R.id.action_account:
                                    ProfileFragment profileFragment = new ProfileFragment();
                                    getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).addToBackStack(null).commit();
                                    break;
                            }
                            return true;
                        }
                    });
        }
    }
}
