package com.example.prabowo.tutorpedia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.prabowo.tutorpedia.CekSoal.FirebaseImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;


import java.util.List;


public class AdapterKomen extends RecyclerView.Adapter<AdapterKomen.ViewHolder> {


    private DatabaseReference databaseReference;
    ;
    private StorageReference mStorageRef;
    private FirebaseStorage storage;
    private FirebaseAuth firebaseAuth;
    private Button BTmantap;
    public static int pointsa,posisi,pointsi;
    private static String matkul,jenis;
    public   static String uidtarget;
    public static int counters;
    private boolean aa;

    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();

    private List<Komentar> mListItemKomen;
    private Context context;

    public AdapterKomen(List<Komentar> listItemKomentar, Context context) {
        this.mListItemKomen = listItemKomentar;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_komentar, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Komentar listItemKomen = mListItemKomen.get(position);

        holder.pengirim.setText(listItemKomen.getPengirim());
        holder.komentar.setText(listItemKomen.getKomen());
        holder.counter.setText(listItemKomen.getCounter());
        storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReferenceFromUrl("gs://tutorpedia-17ba0.appspot.com/FotoProfil/");
        StorageReference foto = mStorageRef.child(listItemKomen.getImgkomen() + "PP" + ".jpg");
        Glide.with(context)
                .using(new FirebaseImageLoader())
                .load(foto)
                .into(holder.Foto);

    }

    @Override
    public int getItemCount() {
        return mListItemKomen.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView Foto;
        private TextView pengirim;
        private TextView komentar;
        private TextView counter;


        public ViewHolder(View itemView) {
            super(itemView);


            IsiKonsultasi datakomen = new IsiKonsultasi();
            matkul = datakomen.Matkuldis;
            jenis= datakomen.Jenisdis;
            posisi= datakomen.posisiItemRecycler;

            pengirim = (TextView) itemView.findViewById(R.id.TVpengirimkomen);
            komentar = (TextView) itemView.findViewById(R.id.TVisikomen);
            Foto = (ImageView) itemView.findViewById(R.id.reviewAvatar);
            counter = (TextView) itemView.findViewById(R.id.TVcommentcounter);
            BTmantap = (Button) itemView.findViewById(R.id.BTmantap);
            BTmantap.setOnClickListener(this);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);



            firebaseAuth = FirebaseAuth.getInstance();
            FirebaseUser user = firebaseAuth.getCurrentUser();

            DatabaseReference ref = mRootref.child("User").child(user.getUid()).child("Point");
            ref.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    pointsa = dataSnapshot.getValue().hashCode();
//                TVpoint.setText(dataSnapshot.getValue().toString());

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });





//        points = FirebaseDatabase.getInstance().getReference().child("User").child(user.getUid()).child("Point").getKey();
//        point = Integer.parseInt(points);


        }




        @Override
        public void onClick(final View v) {





            final int recyclerItemPosition;

            int a=0;
            final Intent intent;
            switch (getAdapterPosition()){
                default:
                    recyclerItemPosition = getAdapterPosition();
                    firebaseAuth = FirebaseAuth.getInstance();
                    final FirebaseUser user = firebaseAuth.getCurrentUser();

                    DatabaseReference refx = mRootref.child("Konsultasi").child(matkul).child("Post"+posisi).child("Komentar").child("Komentar"+recyclerItemPosition).child("img");
                    refx.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            int a=1;
                           uidtarget = dataSnapshot.getValue().toString();

                            if(uidtarget.toString().equals(user.getUid().toString())|| pointsa<20) {
                                Toast.makeText(context,"Point Kurang atau Tidak Bisa Klik Komentar Sendiri",Toast.LENGTH_SHORT).show();}
                            else { // databaseReference.child("User").child(user.getUid()).child("Point").setValue(pointsa-20);
                            DatabaseReference refc = mRootref.child("User").child(String.valueOf(uidtarget)).child("Point");
                            refc.addValueEventListener(new ValueEventListener() {

                                int ag=0;
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    pointsi = dataSnapshot.getValue().hashCode();
                                    if(ag<1) {
                                        databaseReference.child("User").child(uidtarget).child("Point").setValue(pointsi + 20);//yang komennya di like nambah 20
                                        databaseReference.child("User").child(user.getUid()).child("Point").setValue(pointsa - 20);
                                    ag++;
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                                DatabaseReference refd = mRootref.child("Konsultasi").child(matkul).child("Post"+posisi).child("Komentar").child("Komentar"+getAdapterPosition()).child("counter");
                                refd.addValueEventListener(new ValueEventListener() {

                                    int ax=0;
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        counters = Integer.parseInt(dataSnapshot.getValue().toString());
                                        if(ax<1) {
                                            databaseReference.child("Konsultasi").child(matkul).child("Post"+posisi).child("Komentar").child("Komentar"+getAdapterPosition()).child("counter").setValue(counters+1);//yang komennya di like nambah 20
                                            ax++;
                                            Activity activity = (Activity) context;
                                            activity.finish();
                                            activity.startActivity(activity.getIntent());
                                        }
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                            //Toast.makeText(context,"Position "+recyclerItemPosition,Toast.LENGTH_SHORT).show();
                            databaseReference = FirebaseDatabase.getInstance().getReference();
                            firebaseAuth = FirebaseAuth.getInstance();
                            final FirebaseUser user = firebaseAuth.getCurrentUser();

                            //databaseReference.child("Cobacoba").child("satu").setValue(user.getUid());// buat ngecek user.getUID sama uidtarget sama apa ngga
                            //databaseReference.child("Cobacoba").child("dua").setValue(uidtarget);// buat ngecek user.getUID sama uidtarget sama apa ngga


                            //System.out.print(u);


                            }

                            /* jadi to yang error itu ketika like komennya sendiri, haruse khan ga nambah maupun kurang tapi malah nambah, nah kalo aku kasih if(user.getUID==uidtarget) jawabannya false terus padahal sama, trus bug yang kedua klik pertama mesti kebacanya nilai awa; pointnya 0 jadi yang ngomen ntar pointnya jadi -20 */



                        }




                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });//intent.putExtra("PosisiItemRecycler",recyclerItemPosition);
                    break;
            }
            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //context.startActivity(intent);


        }
    }
}







