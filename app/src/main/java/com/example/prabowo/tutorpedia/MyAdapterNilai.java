package com.example.prabowo.tutorpedia;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
 * Created by Prabowo on 14/02/2017.
 */

public class MyAdapterNilai extends RecyclerView.Adapter<MyAdapterNilai.ViewHolder> {

    private FirebaseAuth firebaseAuth;

    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();

    private List<ListItemNilai> listItems;
    private Context context;

    public MyAdapterNilai(List<ListItemNilai> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public MyAdapterNilai.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_list_item_nilai,parent,false);
        return new ViewHolder(v);
    }




    @Override
    public void onBindViewHolder(MyAdapterNilai.ViewHolder holder, int position) {

        ListItemNilai listItem = listItems.get(position);

        holder.TVheadnilai.setText(listItem.getJudulnilai());
        holder.TVdescnilai.setText(listItem.getNilai());
        /*Picasso.with(context)
                .load(listItem.getImageUrlkonsultasi())
                .into(holder.IVgambarkonsultasi);*/

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView TVheadnilai;
        private TextView TVdescnilai;


        public ViewHolder(View itemView) {
            super(itemView);

            TVheadnilai = (TextView) itemView.findViewById(R.id.TVheadnilai);
            TVdescnilai = (TextView) itemView.findViewById(R.id.TVdescnilai);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);


        }



        public void onClick(final View v) {


        }
    }

}
