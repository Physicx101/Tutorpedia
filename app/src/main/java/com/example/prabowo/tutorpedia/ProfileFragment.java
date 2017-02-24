package com.example.prabowo.tutorpedia;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Bogi on 19-Feb-17.
 */

public class ProfileFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private TextView TVuseremail;
    private Button BTlogout;

    private DatabaseReference databaseReference;
    private EditText ETnama,ETalamat;
    private Button BTsave;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        firebaseAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

        if (firebaseAuth.getCurrentUser() == null){
            getActivity().finish();
            startActivity(new Intent(this.getActivity(),Login.class));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();
        ETnama = (EditText) view.findViewById(R.id.ETnama);
        ETalamat = (EditText) view.findViewById(R.id.ETalamat);
        BTsave = (Button) view.findViewById(R.id.BTsave);

        FirebaseUser user = firebaseAuth.getCurrentUser();



        TVuseremail= (TextView) view.findViewById(R.id.TVuseremail);

        TVuseremail.setText("Email anda : " + user.getEmail() );
        BTlogout = (Button) view.findViewById(R.id.BTlogout);

        BTlogout.setOnClickListener(this);
        BTsave.setOnClickListener(this);



        return view;
    }

    private void saveUserInfo(){
        String nama = ETnama.getText().toString().trim();
        String alamat = ETalamat.getText().toString().trim();

        InfoUser infoUser = new InfoUser(nama,alamat);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child("User").child(user.getUid()).setValue(infoUser);

        Toast.makeText(this.getActivity(),"Information Saved",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View v) {

        if(v == BTlogout){

            firebaseAuth.signOut();
            getActivity().finish();
            startActivity(new Intent(this.getActivity(),Login.class));}

        if(v == BTsave){
            saveUserInfo();
            startActivity(new Intent(this.getActivity(),MainActivity.class));
        }

    }
}
