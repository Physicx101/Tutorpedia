package com.example.prabowo.tutorpedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RequestTutor extends AppCompatActivity implements View.OnClickListener {
    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    private Button BTyes,BTno;
    private ImageView IVreqtutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_tutor);
        BTyes = (Button) findViewById(R.id.BTreqyes);
        BTyes.setOnClickListener(this);
        BTno = (Button) findViewById(R.id.BTreqno);
        BTno.setOnClickListener(this);
        IVreqtutor = (ImageView) findViewById(R.id.IVreqtutor);

        DatabaseReference ref = mRootref.child("Ideafuse");
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();

        ref.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (Integer.parseInt(snapshot.child("Request").child("no").getValue().toString())==0){
                    BTno.setVisibility(View.GONE);
                }
                if (Integer.parseInt(snapshot.child("Request").child("yes").getValue().toString())==0){
                    BTyes.setVisibility(View.GONE);
                }
                if (Integer.parseInt(snapshot.child("Request").child("yes").getValue().toString())==0 && Integer.parseInt(snapshot.child("Request").child("no").getValue().toString())==0){
                    IVreqtutor.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });



    }

    @Override
    public void onClick(View v) {
        if(v == BTyes){
            mRootref.child("Ideafuse").child("Request").child("no").setValue(0);
            mRootref.child("Ideafuse").child("Respond").setValue(1);
            BTno.setVisibility(View.GONE);
            finish();
            startActivity(getIntent());





        }
        if(v==BTno){
            mRootref.child("Ideafuse").child("Request").child("yes").setValue(0);
            BTyes.setVisibility(View.GONE);
            finish();
            startActivity(getIntent());
        }

    }
}
