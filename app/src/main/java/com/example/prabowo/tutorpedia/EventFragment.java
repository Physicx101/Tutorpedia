package com.example.prabowo.tutorpedia;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.prabowo.tutorpedia.CekSoal.CekSoal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bogi on 19-Feb-17.
 */

public class EventFragment extends Fragment {

    private FirebaseDatabase eventfirebasedatabase;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayout linear;
    private LinearLayoutManager linearLayoutManager;
    int recyclerItemPosition;
    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();

    private List<ListEventItemBeneran> listItems;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycleVieweventBeneran);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        linear = (LinearLayout) view.findViewById(R.id.LLayouteventBeneran);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);

        /*listItems = new ArrayList<>();
        if (CekSoal.selesai==0) {
            ListEventItemBeneran sikat = new ListEventItemBeneran("Matematika SMA", "http://i.imgur.com/cNt6sqyh.jpg", "Jumlah : 40 Soal", "Waktu  : 90 Menit", "Nilai     : Belum Dikerjakan");
            listItems.add(0, sikat);
            adapter = new MyAdapterEventBeneran(listItems, getActivity().getApplicationContext());
            recyclerView.setAdapter(adapter);
        } else {
            ListEventItemBeneran sikat = new ListEventItemBeneran("Matematika SMA", "http://i.imgur.com/cNt6sqyh.jpg", "Jumlah : 40 Soal", "Waktu  : 90 Menit","Nilai     : "+CekSoal.NilaiAkhir);
            listItems.add(0, sikat);
            adapter = new MyAdapterEventBeneran(listItems, getActivity().getApplicationContext());
            recyclerView.setAdapter(adapter);
        }*/
        listItems = new ArrayList<>();
        tambahInfo();

        return view;
    }






    public void tambahInfo() {


        DatabaseReference event =mRootref.child("Event");
        event.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //long max = dataSnapshot.getChildrenCount();
                    //for(int i = 1; i<=max ; i++){
                    ListEventItemBeneran listItem = new ListEventItemBeneran(postSnapshot.child("desc").getValue().toString(),
                            postSnapshot.child("head").getValue().toString(),
                            postSnapshot.child("img").getValue().toString(),
                            postSnapshot.child("nomor").getValue().toString(),
                            postSnapshot.child("waktu").getValue().toString());
                    listItems.add(listItem);


                }

                adapter = new MyAdapterEventBeneran(listItems, getActivity().getApplicationContext());
                recyclerView.setAdapter(adapter);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }
}
