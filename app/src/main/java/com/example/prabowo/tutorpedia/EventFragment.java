package com.example.prabowo.tutorpedia;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        linearLayoutManager = new LinearLayoutManager(this.getActivity());
        linear = (LinearLayout) view.findViewById(R.id.LLayouteventBeneran);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycleVieweventBeneran);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);



        listItems = new ArrayList<>();
        tambahInfo();

    }

    @Override
    public void onResume() {
        super.onResume();
    }






    public void tambahInfo() {
        //for(int i = 1; i<=max ; i++){
        final ProgressDialog Dialog = new ProgressDialog(getActivity());
        Dialog.setMessage("Fetching file .... ");
        Dialog.show();


        DatabaseReference event =mRootref.child("Event");
        event.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //long max = dataSnapshot.getChildrenCount();

                    ListEventItemBeneran listItem = new ListEventItemBeneran(postSnapshot.child("head").getValue().toString(),
                            postSnapshot.child("img").getValue().toString(),
                            postSnapshot.child("desc").getValue().toString(),
                            postSnapshot.child("nomor").getValue().toString(),
                            postSnapshot.child("waktu").getValue().toString());
                    listItems.add(listItem);

                    adapter = new AdapterEvent(listItems, getActivity());
                    recyclerView.setAdapter(adapter);
                    Dialog.hide();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }
}
