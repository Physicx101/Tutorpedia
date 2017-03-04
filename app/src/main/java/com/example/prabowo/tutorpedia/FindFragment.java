package com.example.prabowo.tutorpedia;

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
public class FindFragment extends Fragment implements View.OnClickListener {

    private FirebaseDatabase eventfirebasedatabase;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayout linear;
    private LinearLayoutManager linearLayoutManager;
    int recyclerItemPosition;
    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();

    private List<ListItemTutor> mListItemTutors;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        linear = (LinearLayout) view.findViewById(R.id.LLayout);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

        mListItemTutors = new ArrayList<>();
        tambahInfo();

        return view;
    }

    @Override
    public void onClick(View v) {

    }


    int a = 100;

    public void tambahInfo() {


        DatabaseReference event =mRootref.child("Mentor");
        event.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //long max = dataSnapshot.getChildrenCount();
                    //for(int i = 1; i<=max ; i++){
                    ListItemTutor listItemTutor = new ListItemTutor(postSnapshot.child("nama").getValue().toString(),
                            postSnapshot.child("tanggallahir").getValue().toString(),
                            postSnapshot.child("img").getValue().toString(),
                            postSnapshot.child("lokasi").getValue().toString(),
                            postSnapshot.child("kontak").getValue().toString(),
                            postSnapshot.child("deskripsi").getValue().toString(),
                            postSnapshot.child("linkcv").getValue().toString());
                    mListItemTutors.add(listItemTutor);


                }

                adapter = new AdapterTutor(mListItemTutors, getActivity().getApplicationContext());
                recyclerView.setAdapter(adapter);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }
}
