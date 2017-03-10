package com.example.prabowo.tutorpedia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Fauziw97 on 3/4/17.
 */

public class ProfileTestFragment extends Fragment implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private Button BTlogout;
    private DatabaseReference databaseReference;
    private TextView userEmail;
    private TextView userName;
    private StorageReference mStorageReference;
    private FirebaseStorage storage;
    private ImageView fotoProfil;
    private String nama;
    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();

    String[] titleprofile = new String[] {"Ringkasan Akun", "Riwayat Tes", "Bantuan"};

    int[] imageprofile = new int[] {R.drawable.ic_account_circle_black_24dp, R.drawable.ic_class_black_24dp
            , R.drawable.ic_help_black_24dp};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_profile, container, false);
        firebaseAuth = FirebaseAuth.getInstance();


        if (firebaseAuth.getCurrentUser() == null){
            getActivity().finish();
            startActivity(new Intent(this.getActivity(),LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();
        userEmail = (TextView) view.findViewById(R.id.user_email);
        userEmail.setText(user.getEmail());
        userName = (TextView) view.findViewById(R.id.user_profile_name);
        DatabaseReference ref = mRootref.child("User").child(user.getUid()).child("nama");

        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userName.setText(dataSnapshot.getValue().toString());
                nama = dataSnapshot.getValue().toString();
                storage = FirebaseStorage.getInstance();
                mStorageReference = storage.getReferenceFromUrl("gs://tutorpedia-17ba0.appspot.com/FotoProfil/");
                StorageReference foto = mStorageReference.child(nama + ".jpg");
               /* Glide.with(getContext())
                        .using(new FirebaseImageLoader())
                        .load(foto)
                        .into(fotoProfil);*/
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);




        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < 3; i++) {
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

    @Override
    public void onClick(View view) {

    }
}