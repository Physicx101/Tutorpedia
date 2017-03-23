package com.example.prabowo.tutorpedia;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * Created by Fauziw97 on 3/4/17.
 */

public class ProfileTestFragment extends Fragment implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private Button BTlogout;
    private DatabaseReference databaseReference;
    private TextView userEmail;
    private TextView userName;
    private StorageReference mStorageRef;
    private FirebaseStorage storage;
    private ImageView fotoProfil;
    private String nama;
    private ImageView BTfoto;
    private RelativeLayout ringkasan,riwayat,bantuan;
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

        userEmail = (TextView) view.findViewById(R.id.user_email);
        userEmail.setText(user.getEmail());
        userName = (TextView) getActivity().findViewById(R.id.user_profile_name);
        BTfoto = (ImageView) getActivity().findViewById(R.id.user_profile_photo);
        BTfoto.setOnClickListener(this);
        ringkasan = (RelativeLayout) getActivity().findViewById(R.id.ringkasanAkun);
        ringkasan.setOnClickListener(this);
        riwayat = (RelativeLayout) getActivity().findViewById(R.id.riwayatTes);
        riwayat.setOnClickListener(this);
        bantuan = (RelativeLayout) getActivity().findViewById(R.id.bantuan);
        bantuan.setOnClickListener(this);




        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

        BTlogout = (Button) view.findViewById(R.id.BTlogout);

        BTlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                getActivity().finish();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });


        DatabaseReference ref = mRootref.child("User").child(user.getUid()).child("nama");
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userName.setText(dataSnapshot.getValue().toString());
                nama = dataSnapshot.getValue().toString();
                storage = FirebaseStorage.getInstance();
                mStorageRef = storage.getReferenceFromUrl("gs://tutorpedia-17ba0.appspot.com/FotoProfil/");
                StorageReference foto = mStorageRef.child(nama + ".jpg");
                Glide.with(getContext())
                        .using(new FirebaseImageLoader())
                        .load(foto)
                        .into(BTfoto);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    @Override
    public void onClick(View view) {

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


                StorageReference foto = mStorageRef.child(nama + ".jpg");

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



                StorageReference foto = mStorageRef.child(nama + ".jpg");

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
