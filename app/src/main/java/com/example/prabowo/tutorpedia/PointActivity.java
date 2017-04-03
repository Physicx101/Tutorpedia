package com.example.prabowo.tutorpedia;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
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

import static com.example.prabowo.tutorpedia.CekSoal.RecyclerViewAdapter.context;

public class PointActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();
    public static int points;

    private static String pointss;
    private TextView TVpoint,TVhargatpoin,TVkodehadiahinfo,TVkodehadiahinfo2,TVkodehadiah,TVkodehadiahuid;
    private Button BTpoint;
    private static String Pointnya,Hadiah;
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
                points = dataSnapshot.getValue().hashCode();
//                TVpoint.setText(dataSnapshot.getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        points = FirebaseDatabase.getInstance().getReference().child("User").child(user.getUid()).child("Point").getKey();
//        point = Integer.parseInt(points);
        pointss = String.valueOf(points);
        TVpoint.setText(pointss);

    }

    @Override
    public void onClick(View v) {

        int hargahadiah = Integer.parseInt(Pointnya);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("User").child(user.getUid()).child("Point").setValue(points-hargahadiah);
        String uuid = UUID.randomUUID().toString();

        String alert1 = "SELAMAT ANDA MENDAPAT " + Hadiah.toUpperCase() + " !";
        String alert2 = "Kode hadiah: " + uuid;
        String alert3 = "Kode user: " + user.getUid().toString();
        String alert4 = "Email identitas dan bukti ini ke: tutorpedia@gmail.com";
        /*TVkodehadiah.setText("Kode hadiah : " + uuid);
        TVkodehadiahuid.setText("Kode User : "+ user.getUid().toString());
        TVkodehadiahinfo.setText("SELAMAT ANDA MENDAPAT " + Hadiah.toUpperCase() + " ! ");
        TVkodehadiahinfo2.setText("Email identitas dan bukti ini ke : tutorpedia@gmail.com");*/

        if(points>=hargahadiah){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage(alert1 +"\n"+"\n" + alert2 +"\n"+"\n" + alert3 +"\n"+"\n" + alert4);
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }

        if(v == BTpoint){
            if (points< hargahadiah){
                Toast.makeText(this,"Saldo Kurang",Toast.LENGTH_SHORT).show();
            }

            /*if (point>= 100){
                databaseReference.child("User").child(user.getUid()).child("Point").setValue(point-50);
                //;
            }*/


        }

    }
}
