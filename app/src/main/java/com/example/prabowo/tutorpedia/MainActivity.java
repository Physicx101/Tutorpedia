package com.example.prabowo.tutorpedia;


import android.support.annotation.IdRes;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;


import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            HomeFragment homeFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).addToBackStack(null).commit();

            BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
            bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
                @Override
                public void onTabSelected(@IdRes int tabId) {
                    switch (tabId) {
                        case R.id.tab_home:
                            HomeFragment homeFragment = new HomeFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).addToBackStack(null).commit();
                            break;
                        case R.id.tab_event:
                            EventFragment eventFragment = new EventFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.container, eventFragment).addToBackStack(null).commit();
                            break;
                        case R.id.tab_find:
                            FindFragment findFragment = new FindFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.container, findFragment).addToBackStack(null).commit();
                            break;
                        case R.id.tab_account:
                            ProfileFragment profileFragment = new ProfileFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).addToBackStack(null).commit();
                            break;
                    }
                }
            });
        }
    }
}
