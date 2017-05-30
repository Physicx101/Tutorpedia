package com.example.prabowo.tutorpedia;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.Marker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RequestTutor extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {


    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    LocationManager locationManager;
    LocationListener locationListener;

    private Button BTyes, BTno;
    private Switch Stutor;
    private ImageView IVreqtutor;
    private String KAtutor;
    private Location lastLocation;
    private int counter = 1;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_request_tutor);
        BTyes = (Button) findViewById(R.id.BTreqyes);
        BTyes.setOnClickListener(this);
        BTno = (Button) findViewById(R.id.BTreqno);
        BTno.setOnClickListener(this);
        IVreqtutor = (ImageView) findViewById(R.id.IVreqtutor);

        Stutor = (Switch) findViewById(R.id.switchtutor);




        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }


            firebaseAuth = FirebaseAuth.getInstance();
            final FirebaseUser user = firebaseAuth.getCurrentUser();
            DatabaseReference refx = mRootref.child("User").child(user.getUid());
            refx.addValueEventListener(new ValueEventListener() {


                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    KAtutor = snapshot.child("Tutor").getValue().toString();

                    DatabaseReference refdf = mRootref.child("Marker").child("Marker" + KAtutor);
                    firebaseAuth = FirebaseAuth.getInstance();

                    if (counter == 1) {
                        refdf.addValueEventListener(new ValueEventListener() {


                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                if (snapshot.child("status").getValue().toString().equals("buka")) {
                                    Stutor.setChecked(true);
                                    counter = 0;
                                }
                                if (snapshot.child("status").getValue().toString().equals("tutup")) {
                                    Stutor.setChecked(false);
                                    counter = 0;
                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }


                        });
                    }

                        Stutor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                if (counter == 0) {
                                    if (isChecked) {
                                        if (ActivityCompat.checkSelfPermission(RequestTutor.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(RequestTutor.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                            // TODO: Consider calling
                                            //    ActivityCompat#requestPermissions
                                            // here to request the missing permissions, and then overriding
                                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                            //                                          int[] grantResults)
                                            // to handle the case where the user grants the permission. See the documentation
                                            // for ActivityCompat#requestPermissions for more details.
                                            return;
                                        }

                                        if (mGoogleApiClient == null) {
                                            mGoogleApiClient = new GoogleApiClient.Builder(RequestTutor.this)
                                                    .addConnectionCallbacks(RequestTutor.this)
                                                    .addOnConnectionFailedListener(RequestTutor.this)
                                                    .addApi(LocationServices.API)
                                                    .build();
                                        }

//                                        if (Build.VERSION.SDK_INT < 23) {
//                                            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,locationListener );
//                                        }

                                        lastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

                                        mRootref.child("Marker").child("Marker" + KAtutor).child("longitude").setValue(lastLocation.getLongitude());
                                        mRootref.child("Marker").child("Marker" + KAtutor).child("latitude").setValue(lastLocation.getLatitude());
                                        mRootref.child("Marker").child("Marker" + KAtutor).child("status").setValue("buka");
                                        Stutor.setChecked(true);
                                        counter=2;
                                    } else {
                                        mRootref.child("Marker").child("Marker" + KAtutor).child("longitude").setValue(100000);
                                        mRootref.child("Marker").child("Marker" + KAtutor).child("latitude").setValue(100000);
                                        mRootref.child("Marker").child("Marker" + KAtutor).child("status").setValue("tutup");
                                        Toast.makeText(getApplicationContext(), "Terima Kasih", Toast.LENGTH_SHORT).show();
                                        Stutor.setChecked(false);
                                        counter=2;


                                    }
                                }
                            }
                        });

                    }



                @Override
                public void onCancelled(DatabaseError databaseError) {

                }


            });





        DatabaseReference refff = mRootref.child("Ideafuse");
        firebaseAuth = FirebaseAuth.getInstance();


        refff.addValueEventListener(new ValueEventListener() {


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

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
