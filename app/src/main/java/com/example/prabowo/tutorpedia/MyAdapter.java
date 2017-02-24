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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private FirebaseAuth firebaseAuth;

    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();

    private List<ListItem> listItems;
    private Context context;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ListItem listItem = listItems.get(position);

        holder.TVhead.setText(listItem.getNama());
        holder.TVdesc.setText(listItem.getTanggallahir());
        holder.TVtanggal.setText(listItem.getAsal());
        holder.TVnohp.setText(listItem.getNohp());
        Picasso.with(context)
                .load(listItem.getImageUrl())
                .into(holder.IVgambar);

    }

    @Override
    public int getItemCount() {
        return listItems.size();
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
                intent = new Intent(context,IsiEvent.class);
                intent.putExtra("PosisiItemRecycler",recyclerItemPosition);
                break;
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);


            }
        }
    }






