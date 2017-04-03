package com.example.prabowo.tutorpedia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Fauziw97 on 4/3/17.
 */

public class Ringkasan extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private TextView TVuseremail;


    private DatabaseReference databaseReference;
    private EditText ETnama, ETalamat;
    private Button BTsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);
        firebaseAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Ringkasan Akun");


        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();
        ETnama = (EditText) findViewById(R.id.ETnama);

        BTsave = (Button) findViewById(R.id.BTsave);

        FirebaseUser user = firebaseAuth.getCurrentUser();


        TVuseremail = (TextView) findViewById(R.id.TVuseremail);

        TVuseremail.setText("Email anda : " + user.getEmail() );


        BTsave.setOnClickListener(this);


    }

    private void saveUserInfo() {
        String nama = ETnama.getText().toString().trim();





        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child("User").child(user.getUid()).child("nama").setValue(nama);

        Toast.makeText(getApplicationContext(), "Information Saved", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View v) {


        if (v == BTsave) {
            saveUserInfo();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

    }
}

