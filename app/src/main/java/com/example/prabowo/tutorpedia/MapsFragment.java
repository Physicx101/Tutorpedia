package com.example.prabowo.tutorpedia;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.prabowo.tutorpedia.CekSoal.RecyclerViewAdapter.context;

public class MapsFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, OnMapReadyCallback, View.OnClickListener, GoogleMap.OnMyLocationButtonClickListener {

    private GoogleMap mMap;
    private FloatingSearchView mSearchView;
    LocationManager locationManager;
    LocationListener locationListener;
    private Location lastLocation;
    private GoogleApiClient mGoogleApiClient;
    private HashMap<Marker,Integer> mHashMap = new HashMap<Marker,Integer>();
    private Marker markernih;
    private ArrayList<Markerku> mListItemMarker = new ArrayList<Markerku>();
    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                    Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (lastKnownLocation != null) {
                        LatLng userLocation = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
                        //mMap.addMarker(new MarkerOptions().position(userLocation).title("My location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 17));
                    }
                }
            }
        }
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        // Get the button view
        View locationButton = ((View) view.findViewById(1).getParent()).findViewById(2);

        // and next place it, for exemple, on bottom right (as Google Maps app)
        RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
        // position on right bottom
        rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        rlp.setMargins(0, 0, 30, 30);



        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSearchView = (FloatingSearchView) view.findViewById(R.id.floating_search_view);
        mSearchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.action_math) {
                    mRootref.child("Marker").child("Marker2").child("longitude").setValue(100000);
                    mRootref.child("Marker").child("Marker2").child("latitude").setValue(100000);
                    getActivity().finish();
                    startActivity(getActivity().getIntent());
                }
                if (id == R.id.action_phys) {
                    mRootref.child("Marker").child("Marker3").child("longitude").setValue(100000);
                    mRootref.child("Marker").child("Marker3").child("latitude").setValue(100000);
                    getActivity().finish();
                    startActivity(getActivity().getIntent());
                }
                if (id == R.id.action_chem) {
                    mRootref.child("Marker").child("Marker2").child("latitude").setValue(3.587674);
                    mRootref.child("Marker").child("Marker2").child("longitude").setValue(98.69051);
                    mRootref.child("Marker").child("Marker3").child("latitude").setValue(3.587545);
                    mRootref.child("Marker").child("Marker3").child("longitude").setValue(98.691154);
                }
                if (id == R.id.action_bio) {
                    mRootref.child("Marker").child("Marker2").child("latitude").setValue(3.587674);
                    mRootref.child("Marker").child("Marker2").child("longitude").setValue(98.69051);
                    mRootref.child("Marker").child("Marker3").child("latitude").setValue(3.587545);
                    mRootref.child("Marker").child("Marker3").child("longitude").setValue(98.691154);
                }
            }
        });

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }






        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(getActivity(), "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }


    public Marker createMarker(double latitude, double longitude, String title, String snippet,String kode) {



        return mMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f)
                .title(title)
                .snippet(snippet)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mmanhehe)));



    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;



        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                for(int i = 0 ; i < mListItemMarker.size() ; i++ ) {

                    if(mListItemMarker.get(i).getTitle().equals(marker.getTitle())){
                        Log.i("MARKER", mListItemMarker.get(i).getKode());
                        Intent intent = new Intent(getContext(), IsiTutor.class);
                        intent.putExtra("kodetutor", Integer.parseInt(mListItemMarker.get(i).getKode().toString()));
                        startActivity(intent);
                    }

                }

                return false;
            }
        });

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);



            final ProgressDialog Dialog = new ProgressDialog(getActivity(), R.style.AppTheme_Dark_Dialog);
        Dialog.setMessage("Loading.... ");
        Dialog.show();

        DatabaseReference ref = mRootref.child("Marker");


        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    mListItemMarker.add(new Markerku
                            (Double.parseDouble(postSnapshot.child("latitude").getValue().toString()),
                                    Double.parseDouble(postSnapshot.child("longitude").getValue().toString()),
                                    postSnapshot.child("status").getValue().toString(),
                                    postSnapshot.child("snippet").getValue().toString(),
                                    postSnapshot.child("title").getValue().toString(),
                                    postSnapshot.child("kode").getValue().toString()));
                }
                for(int i = 0 ; i < mListItemMarker.size() ; i++ ) {

                    markernih = createMarker(mListItemMarker.get(i).getLatitude(), mListItemMarker.get(i).getLongitude(), mListItemMarker.get(i).getTitle(), mListItemMarker.get(i).getSnippet(),mListItemMarker.get(i).getKode());
                    mHashMap.put(markernih, i);
                    Dialog.hide();




                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });

        for(int i = 0 ; i < mListItemMarker.size() ; i++ ) {

            createMarker(mListItemMarker.get(i).getLatitude(), mListItemMarker.get(i).getLongitude(), mListItemMarker.get(i).getTitle(), mListItemMarker.get(i).getSnippet(),mListItemMarker.get(i).getKode());

        }







        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.i("Location", location.toString());
                LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 17));
                locationManager.removeUpdates(this);
                //mMap.addMarker(new MarkerOptions().position(userLocation).title("My location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };




        if (Build.VERSION.SDK_INT < 23) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            mMap.setMyLocationEnabled(true);


        } else {

            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

            } else {
                mMap.setMyLocationEnabled(true);

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                //lastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                if(lastKnownLocation != null) {
                    LatLng userLocation = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
                    //mMap.addMarker(new MarkerOptions().position(userLocation).title("My location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 17));
                }
            }
        }

    }





    @Override
    public void onClick(View view) {

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
