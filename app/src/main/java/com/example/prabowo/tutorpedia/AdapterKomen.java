package com.example.prabowo.tutorpedia;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.prabowo.tutorpedia.CekSoal.FirebaseImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();

    private List<Komentar> mListItemKomen;
    private Context context;

    public AdapterKomen(List<Komentar> listItemKomentar, Context context) {
        this.mListItemKomen = listItemKomentar;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_komentar, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Komentar listItemKomen = mListItemKomen.get(position);

        holder.pengirim.setText(listItemKomen.getPengirim());
        holder.komentar.setText(listItemKomen.getKomen());
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


    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView Foto;
        private TextView pengirim;
        private TextView komentar;


        public ViewHolder(View itemView) {
            super(itemView);

            pengirim = (TextView) itemView.findViewById(R.id.TVpengirimkomen);
            komentar = (TextView) itemView.findViewById(R.id.TVisikomen);
            Foto = (ImageView) itemView.findViewById(R.id.reviewAvatar);

        }


    }
}







