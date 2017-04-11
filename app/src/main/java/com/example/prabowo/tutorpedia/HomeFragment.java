package com.example.prabowo.tutorpedia;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Bogi on 19-Feb-17.
 */

public class HomeFragment extends android.support.v4.app.Fragment implements TabLayout.OnTabSelectedListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);


        //Initializing the tablayout
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("IPA"));
        tabLayout.addTab(tabLayout.newTab().setText("IPS"));
        tabLayout.addTab(tabLayout.newTab().setText("BAHASA"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = (ViewPager) view.findViewById(R.id.pager);

        //Creating our pager adapter
        Pager adapter = new Pager(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        //Adding onTabSelectedListener to swipe views
        tabLayout.addOnTabSelectedListener(this);

        return view;
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
