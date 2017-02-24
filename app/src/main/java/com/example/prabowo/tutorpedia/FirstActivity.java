package com.example.prabowo.tutorpedia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference databaseReference;
    private EditText ETtambahjudul,ETtambahdesc;
    private Button BTtambah;
    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        ETtambahjudul = (EditText) findViewById(R.id.ETtambahjudul);
        ETtambahdesc = (EditText) findViewById(R.id.ETtambahdesc);
        BTtambah = (Button) findViewById(R.id.BTtambah);
        BTtambah.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String judul = ETtambahjudul.getText().toString().trim();
        String desc = ETtambahdesc.getText().toString().trim();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("MM-dd-yyyy");
// you can get seconds by adding  "...:ss" to it


        String localTime = date.format(currentLocalTime);

        //ListItem listItem = new ListItem(judul,desc,"https://image.ibb.co/fuP3yv/headbar.png",localTime);
        //databaseReference.child("post").setValue(listItem);
        Toast.makeText(this,"Berhasil",Toast.LENGTH_LONG).show();


    }








}
