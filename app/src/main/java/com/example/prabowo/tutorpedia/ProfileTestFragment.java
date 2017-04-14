package com.example.prabowo.tutorpedia;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
import com.example.prabowo.tutorpedia.CekSoal.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import static android.app.Activity.RESULT_OK;


/**
 * Created by Fauziw97 on 3/4/17.
 */

public class ProfileTestFragment extends Fragment implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private DatabaseReference databaseReference;
    private TextView userEmail;
    private TextView userName;
    private TextView userPoint;
    private StorageReference mStorageRef;
    private FirebaseStorage storage;
    private ImageView fotoProfil;
    private String nama;
    private String UID;
    private TextView TVpangkat;
    private ImageView BTfoto;
    private ProgressBar pangkat;
    public static int points,pangkats;
    private static String pointuser;
    private RelativeLayout ringkasan,riwayat,bantuan,keluar,tpoin;
    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_profile, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){


        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();


        if (user == null) {
            getActivity().finish();
            startActivity(new Intent(this.getActivity(),LoginActivity.class));
        }
        pangkat = (ProgressBar) getActivity().findViewById(R.id.PBpangkat);
        TVpangkat = (TextView) getActivity().findViewById(R.id.TVpangkat);
        userEmail = (TextView) getActivity().findViewById(R.id.email_user);
        userEmail.setText(user.getEmail());
        userName = (TextView) getActivity().findViewById(R.id.user_profile_name);
        BTfoto = (ImageView) getActivity().findViewById(R.id.user_profile_photo);
        BTfoto.setOnClickListener(this);
        tpoin = (RelativeLayout) getActivity().findViewById(R.id.point_user);
        tpoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ListHadiahPoint.class));
            }
        });
        userPoint = (TextView) getActivity().findViewById(R.id.TV_point);
        //ringkasan = (RelativeLayout) getActivity().findViewById(R.id.ringkasanAkun);
        //ringkasan.setOnClickListener(this);
        riwayat = (RelativeLayout) getActivity().findViewById(R.id.riwayatTes);
        riwayat.setOnClickListener(this);
        bantuan = (RelativeLayout) getActivity().findViewById(R.id.bantuan);
        bantuan.setOnClickListener(this);
        keluar = (RelativeLayout) getActivity().findViewById(R.id.keluar);
        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                getActivity().finish();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });




        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);








        UID = user.getUid().toString();

        DatabaseReference refpoin = mRootref.child("User").child(user.getUid()).child("Point");
        refpoin.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                points = dataSnapshot.getValue().hashCode();
                pointuser = String.valueOf(points);
                userPoint.setText(pointuser);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        DatabaseReference refpangkat = mRootref.child("User").child(user.getUid()).child("Pangkat");
        refpangkat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pangkats = dataSnapshot.getValue().hashCode();

                if(pangkats<100){
                    TVpangkat.setText("Pendatang Baru");
                    float total = 100;
                    float aa = (pangkats/total);
                    int bb=Math.round(aa*100);
                    pangkat.setProgress(bb);}
                if (pangkats>100){
                    TVpangkat.setText("Pejuang");
                    float total = 250;
                    float aa = (pangkats/total);
                    int bb=Math.round(aa*100);
                    pangkat.setProgress(bb);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        final ProgressDialog Dialog = new ProgressDialog(getActivity());
        Dialog.setMessage("Loading Data .... ");
        Dialog.show();
        DatabaseReference ref = mRootref.child("User").child(user.getUid());
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userName.setText(dataSnapshot.child("nama").getValue().toString());
                nama = dataSnapshot.child("nama").getValue().toString();
                storage = FirebaseStorage.getInstance();

                mStorageRef = storage.getReferenceFromUrl("gs://tutorpedia-17ba0.appspot.com/FotoProfil/");
                StorageReference foto = mStorageRef.child(UID + "PP"+".jpg");

                Glide.with(getContext())
                        .using(new FirebaseImageLoader())
                        .load(foto)
                        .signature(new StringSignature(Long.toString(System.currentTimeMillis()))).centerCrop()
                        .into(BTfoto);
                Dialog.hide();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onClick(View view) {

        if(view == riwayat){getActivity().finishAffinity();
            startActivity(new Intent(this.getActivity(),NilaiActivity.class));
        }

        if(view == bantuan){
            getActivity().finishAffinity();
            startActivity(new Intent(this.getActivity(),CreditActivity.class));
        }

        if(view == ringkasan) {getActivity().finishAffinity();
            startActivity(new Intent(this.getActivity(),Ringkasan.class));
        }

        if (view == BTfoto) {
            System.out.println("1");
            final CharSequence[] options = {"Ambil Foto", "Pilih dari Galeri", "Kembali"};


            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setTitle("Ganti foto profil dari :");

            builder.setItems(options, new DialogInterface.OnClickListener() {

                @Override

                public void onClick(DialogInterface dialog, int item) {

                    if (options[item].equals("Ambil Foto"))

                    {

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                        startActivityForResult(intent, 1);

                    } else if (options[item].equals("Pilih dari Galeri"))

                    {

                        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                        startActivityForResult(intent, 2);


                    } else if (options[item].equals("Kembali")) {

                        dialog.dismiss();

                    }

                }

            });

            builder.show();
        }

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        BTfoto = (ImageView) getView().findViewById(R.id.user_profile_photo);
        storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReferenceFromUrl("gs://tutorpedia-17ba0.appspot.com/FotoProfil/");
        if (resultCode == RESULT_OK) {

            if (requestCode == 1) {

                Bitmap mphoto = (Bitmap) data.getExtras().get("data");
                BTfoto.setImageBitmap(mphoto);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                mphoto.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] dataimage = baos.toByteArray();


                StorageReference foto = mStorageRef.child(UID +"PP"+ ".jpg");

                UploadTask uploadTask = foto.putBytes(dataimage);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.

                    }
                });

            } else if (requestCode == 2) {


                Uri selectedImage = data.getData();


                String[] filePath = {MediaStore.Images.Media.DATA};

                Cursor c = getActivity().getContentResolver().query(selectedImage, filePath, null, null, null);

                c.moveToFirst();

                int columnIndex = c.getColumnIndex(filePath[0]);

                String picturePath = c.getString(columnIndex);

                c.close();

                Bitmap thumbnail = BitmapFactory.decodeFile(picturePath);

                Log.w("Gambar dari Galeri", picturePath + "");
                BTfoto.setImageBitmap(thumbnail);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity());
                builder.setContentText("Foto Telah Terupload");



                StorageReference foto = mStorageRef.child(UID+"PP"+".jpg");

                UploadTask uploadTask = foto.putFile(selectedImage);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.

                    }
                });


            }


        }
    }


}
