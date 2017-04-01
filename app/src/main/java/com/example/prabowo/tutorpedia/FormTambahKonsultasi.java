package com.example.prabowo.tutorpedia;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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
import java.util.Random;

import static com.example.prabowo.tutorpedia.IsiKonsultasi.jumlah;

public class FormTambahKonsultasi extends AppCompatActivity implements View.OnClickListener{

    private DatabaseReference databaseReference;
    String Matkul;
    private EditText ETtambahjudul, ETtambahdesc, ETtambahimg;
    public static int langkah = 1;
    private static int jmlpost;
    private FloatingActionButton fab;
    private ImageView foto;
    private FirebaseStorage storage;
    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();
    private StorageReference mStorageRef;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    Random rand = new Random();
    long  random = rand.nextLong();
    private String poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_tambah_konsultasi);





        ETtambahjudul = (EditText) findViewById(R.id.ETtambahjudulkonsul);
        ETtambahdesc = (EditText) findViewById(R.id.ETtambahdesckonsul);
        //ETtambahimg = (EditText)findViewById(R.id.ETtambahimgkonsul);

        databaseReference = FirebaseDatabase.getInstance().getReference();


        fab = (FloatingActionButton) findViewById(R.id.fabaddkonsul);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref2 = mRootref.child("User").child(user.getUid());

        ref2.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {

                poster = snapshot.child("nama").getValue().toString();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });

        if (v == fab){

            langkah = 1;
            Bundle extras = getIntent().getExtras();
            Matkul = extras.getString("Matkul");


            final DatabaseReference ref = mRootref.child("JumlahKomentar").child(Matkul);
            ref.addValueEventListener(new ValueEventListener() {

                                          @Override
                                          public void onDataChange(DataSnapshot snapshot) {
                                              if (langkah == 1) {
                                                  Integer jumlah = Integer.parseInt(snapshot.getValue().toString());
                                                  databaseReference.child("JumlahKomentar").child("Matematika").setValue(jumlah+1);
                                                  System.out.println("Jumlah komen baru: " + jumlah);

                                                  databaseReference.child("Konsultasi").child(Matkul).child("Post" + jumlah).child("judul").setValue(ETtambahjudul.getText().toString());
                                                  databaseReference.child("Konsultasi").child(Matkul).child("Post" + jumlah).child("deskripsi").setValue(ETtambahdesc.getText().toString());
                                                  databaseReference.child("Konsultasi").child(Matkul).child("Post" + jumlah).child("img").setValue(random);
                                                  databaseReference.child("Konsultasi").child(Matkul).child("Post" + jumlah).child("foto").setValue(user.getUid().toString());
                                                  databaseReference.child("Konsultasi").child(Matkul).child("Post" + jumlah).child("poster").setValue(poster);
                                                  databaseReference.child("Konsultasi").child(Matkul).child("Post" + jumlah).child("Komentar")
                                                          .child("Komentar0").child("pengirim").setValue("Admin");
                                                  databaseReference.child("Konsultasi").child(Matkul).child("Post" + jumlah).child("Komentar")
                                                          .child("Komentar0").child("desc").setValue("Pertamax Gan");
                                                  databaseReference.child("Konsultasi").child(Matkul).child("Post" + jumlah).child("Komentar")
                                                          .child("Komentar0").child("img").setValue("Random");
                                                  databaseReference.child("JumlahKomentarBenar").child(Matkul).child("Post" + jumlah).setValue(1);

                                                  Toast.makeText(getApplicationContext(),"Konsultasi Sudah Terupload ", Toast.LENGTH_LONG).show();

                                              }
                                              langkah++;

                                          }


                                          @Override
                                          public void onCancelled(DatabaseError databaseError) {

                                          }

                                      }
            );




        }
        finish();

    }



    public void upload(View view) {




            final CharSequence[] options = {"Ambil Foto", "Pilih dari Galeri", "Kembali"};


            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Ambil gambar dari :");

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


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        foto = (ImageView) findViewById(R.id.imageView4);
        storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReferenceFromUrl("gs://tutorpedia-17ba0.appspot.com/FotoProfil/");
        if (resultCode == RESULT_OK) {

            if (requestCode == 1) {

                Bitmap mphoto = (Bitmap) data.getExtras().get("data");
                foto.setImageBitmap(mphoto);
                foto.setMaxHeight(100);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                mphoto.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] dataimage = baos.toByteArray();


                StorageReference foto = mStorageRef.child(random+"Konsultasi.jpg");

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

                Cursor c = this.getContentResolver().query(selectedImage, filePath, null, null, null);

                c.moveToFirst();

                int columnIndex = c.getColumnIndex(filePath[0]);

                String picturePath = c.getString(columnIndex);

                c.close();

                Bitmap thumbnail = BitmapFactory.decodeFile(picturePath);

                Log.w("Gambar dari Galeri", picturePath + "");
                foto.setImageBitmap(thumbnail);
                foto.setMaxHeight(100);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
                builder.setContentText("Foto Telah Terupload");



                StorageReference foto = mStorageRef.child(random+"Konsultasi.jpg");

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




