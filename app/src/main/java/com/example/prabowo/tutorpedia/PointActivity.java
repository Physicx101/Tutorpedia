package com.example.prabowo.tutorpedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.prabowo.tutorpedia.CekSoal.FirebaseImageLoader;
import com.example.prabowo.tutorpedia.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Random;
import java.util.UUID;

public class PointActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();
    private static int point;

    private static String points;
    private TextView TVpoint,TVhargatpoin,TVkodehadiahinfo,TVkodehadiahinfo2,TVkodehadiah,TVkodehadiahuid;
    private Button BTpoint;
    private String Pointnya,Hadiah;
    private ImageView IVhadiahpoint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);

        TVkodehadiah = (TextView) findViewById(R.id.TVkodehadiah);
        TVkodehadiahuid = (TextView) findViewById(R.id.TVkodehadiahuid);
        TVkodehadiahinfo = (TextView) findViewById(R.id.TVkodehadiahinfo);
        TVkodehadiahinfo2 = (TextView) findViewById(R.id.TVkodehadiahinfo2);


        Bundle extras = getIntent().getExtras();
        Pointnya = extras.getString("Point");
        Hadiah = extras.getString("Hadiah");
        TVhargatpoin = (TextView) findViewById(R.id.TVhargatpoin);
        TVhargatpoin.setText(Pointnya);

        IVhadiahpoint = (ImageView) findViewById(R.id.IVhadiahpoint);
        int id = getResources().getIdentifier("com.example.prabowo.tutorpedia:drawable/" + Hadiah, null, null);
        IVhadiahpoint.setImageResource(id);


        TVpoint = (TextView) findViewById(R.id.TVpoint);
        BTpoint = (Button) findViewById(R.id.BTtukarpoint);
        BTpoint.setOnClickListener(this);



        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();


        DatabaseReference ref = mRootref.child("User").child(user.getUid()).child("Point");
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                point = Integer.parseInt(dataSnapshot.getValue().toString());
                TVpoint.setText(dataSnapshot.getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        points = FirebaseDatabase.getInstance().getReference().child("User").child(user.getUid()).child("Point").getKey();
        TVpoint.setText(points);

    }

    @Override
    public void onClick(View v) {



        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(point>=100){
            databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child("User").child(user.getUid()).child("Point").setValue(point-100);
            String uuid = UUID.randomUUID().toString();
            TVkodehadiah.setText("Kode hadiah : " + uuid);
            TVkodehadiahuid.setText("Kode User : "+ user.getUid().toString());
            TVkodehadiahinfo.setText("SELAMAT ANDA MENDAPAT " + Hadiah.toUpperCase() + " ! ");
            TVkodehadiahinfo2.setText("Emailen Sak iki");
        }

        if(v == BTpoint){
            if (point< 100){
                Toast.makeText(this,"Saldo Kurang",Toast.LENGTH_SHORT).show();
            }

            /*if (point>= 100){
                databaseReference.child("User").child(user.getUid()).child("Point").setValue(point-50);
                //;
            }*/


        }

    }
}
