package com.example.prabowo.tutorpedia;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Prabowo on 02/02/2017.
 */

public class AdapterTutor extends RecyclerView.Adapter<AdapterTutor.ViewHolder> {

    private FirebaseAuth firebaseAuth;

    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();

    private List<ListItemTutor> mListItemTutors;
    private Context context;

    public AdapterTutor(List<ListItemTutor> listItemTutors, Context context) {
        this.mListItemTutors = listItemTutors;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_tutor,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ListItemTutor listItemTutor = mListItemTutors.get(position);

        holder.TVhead.setText(listItemTutor.getNama());
        holder.TVdesc.setText(listItemTutor.getTanggallahir());
        holder.TVtanggal.setText(listItemTutor.getAsal());
        holder.TVnohp.setText(listItemTutor.getNohp());
        Picasso.with(context)
                .load(listItemTutor.getImageUrl())
                .into(holder.IVgambar);

    }

    @Override
    public int getItemCount() {
        return mListItemTutors.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView TVhead;
        private TextView TVdesc;
        public ImageView IVgambar;
        private TextView TVtanggal;
        private TextView TVnohp;


        public ViewHolder(View itemView) {
            super(itemView);

            TVtanggal = (TextView) itemView.findViewById(R.id.TVdate);
            TVhead = (TextView) itemView.findViewById(R.id.TVhead);
            TVdesc = (TextView) itemView.findViewById(R.id.TVdesc);
            IVgambar = (ImageView) itemView.findViewById(R.id.IVgambar);
            TVnohp = (TextView) itemView.findViewById(R.id.TVnohp);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
        }

        public void onClick(final View v) {
            int recyclerItemPosition;

            final Intent intent;
            switch (getAdapterPosition()){
                default:
                 recyclerItemPosition = getAdapterPosition();
                intent = new Intent(context,IsiTutor.class);
                intent.putExtra("PosisiItemRecycler",recyclerItemPosition);
                break;
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);


            }
        }
    }






