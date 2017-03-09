package com.example.prabowo.tutorpedia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Fauziw97 on 3/4/17.
 */

public class ProfileTestFragment extends Fragment {
    private FirebaseAuth firebaseAuth;
    private Button BTlogout;

    String[] titleprofile = new String[] {"Ringkasan Akun", "Pesan Masuk", "Riwayat Tes", "Pengaturan", "Bantuan"};

    int[] imageprofile = new int[] {R.drawable.ic_account_circle_black_24dp, R.drawable.ic_mail_black_24dp, R.drawable.ic_class_black_24dp
            , R.drawable.ic_settings_black_24dp, R.drawable.ic_help_black_24dp};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_profile, container, false);
        firebaseAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < 5; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", titleprofile[i]);
            hm.put("listview_image", Integer.toString(imageprofile[i]));
            list.add(hm);
        }

        String[] from = {"listview_image", "listview_title"};
        int[] to = {R.id.profile_item_icon, R.id.profile_item_title};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity().getBaseContext(), list, R.layout.profile_item, from, to);
        ListView listView = (ListView) view.findViewById(R.id.listprofile);
        listView.setAdapter(simpleAdapter);

        BTlogout = (Button) view.findViewById(R.id.BTlogout);

        BTlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                getActivity().finish();
                startActivity(new Intent(getActivity(),LoginActivity.class));}
        });

        return view;
    }
}