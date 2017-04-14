package com.example.prabowo.tutorpedia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.prabowo.tutorpedia.CekSoal.FirebaseImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Prabowo on 14/02/2017.
 */

public class MyAdapterKonsultasi extends RecyclerView.Adapter<MyAdapterKonsultasi.ViewHolder> {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;;
    private StorageReference mStorageRef;
    private FirebaseStorage storage;
    public static String a;




    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();


    private List<ListItemKonsultasi> listItems;
    private Context context;

    public MyAdapterKonsultasi(List<ListItemKonsultasi> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public MyAdapterKonsultasi.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_list_item_konsultasi,parent,false);
        return new ViewHolder(v);
    }




    @Override
    public void onBindViewHolder(MyAdapterKonsultasi.ViewHolder holder, int position) {

        ListItemKonsultasi listItem = listItems.get(position);
        /*DatabaseReference komentar = mRootref.child("JumlahKomentarBenar").child(Matkul).child("Post" + posisiItemRecycler);
        komentar.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                num = dataSnapshot.getValue().hashCode();
                //String jumlahKomen = String.valueOf(num);
                TVjumlahkomen.setText(num + " Komentar");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        holder.TVheadkonsultasi.setText(listItem.getJudulkonsultasi());
        holder.TVdesckonsultasi.setText(listItem.getDeskripsikonsultasi());
        holder.TVnamaposter.setText(listItem.getPosterName());
        storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReferenceFromUrl("gs://tutorpedia-17ba0.appspot.com/FotoProfil/");
        StorageReference foto = mStorageRef.child(listItem.getImageUrlkonsultasi()+"Konsultasi.jpg");
        StorageReference fotoposter = mStorageRef.child(listItem.getPosterImage() + "PP" + ".jpg");
        Glide.with(context)
                .using(new FirebaseImageLoader())
                .load(foto)
                .into(holder.IVgambarkonsultasi);
        Glide.with(context)
                .using(new FirebaseImageLoader())
                .load(fotoposter)
                .into(holder.IVfotoposter);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView TVheadkonsultasi;
        private TextView TVdesckonsultasi;
        public ImageView IVgambarkonsultasi;
        private TextView TVnamaposter;
        private ImageView IVfotoposter;
        private ImageView IVkomentar;
        private TextView TVjumlahkomen;


        public ViewHolder(View itemView) {
            super(itemView);



            TVheadkonsultasi = (TextView) itemView.findViewById(R.id.TVheadkonsultasi);
            TVdesckonsultasi = (TextView) itemView.findViewById(R.id.TVdesckonsultasi);
            IVgambarkonsultasi = (ImageView) itemView.findViewById(R.id.IVgambarkonsultasi);
            TVnamaposter = (TextView) itemView.findViewById(R.id.user_name_konsul);
            IVfotoposter = (ImageView) itemView.findViewById(R.id.user_image_konsul);
            //IVkomentar = (ImageView) itemView.findViewById(R.id.iv_komentar);
            //TVjumlahkomen = (TextView) itemView.findViewById(R.id.tv_komentar);


            itemView.setClickable(true);
            itemView.setOnClickListener(this);


        }





        public void onClick(final View v) {
            int recyclerItemPosition;




            final Intent intent;
            Konsultasi datah=new Konsultasi();
            //System.out.print("Tes 2" + datah.a + datah.b);
            switch (getAdapterPosition()){
                default:

                    recyclerItemPosition = getAdapterPosition();
                    intent = new Intent(context,IsiKonsultasi.class);
                    intent.putExtra("PosisiItemRecycler",recyclerItemPosition);
                    intent.putExtra("Matkuldis",datah.a);
                    intent.putExtra("Jenisdis", datah.b);

                    break;
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);


        }
    }

}
