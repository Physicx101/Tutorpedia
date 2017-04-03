package com.example.prabowo.tutorpedia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
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

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        linearLayoutManager = new LinearLayoutManager(this.getActivity());
        linear = (LinearLayout) view.findViewById(R.id.LLayout);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);




        mListItemTutors = new ArrayList<>();
        tambahInfo();

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
                            postSnapshot.child("email").getValue().toString(),
                            postSnapshot.child("img").getValue().toString(),
                            postSnapshot.child("lokasi").getValue().toString(),
                            postSnapshot.child("kontak").getValue().toString(),
                            postSnapshot.child("deskripsi").getValue().toString(),
                            postSnapshot.child("linkcv").getValue().toString());
                    mListItemTutors.add(listItemTutor);


                    adapter = new AdapterTutor(mListItemTutors, getActivity());
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }
}
