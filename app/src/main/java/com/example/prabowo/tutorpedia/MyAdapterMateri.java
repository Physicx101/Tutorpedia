package com.example.prabowo.tutorpedia;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class MyAdapterMateri extends RecyclerView.Adapter<MyAdapterMateri.ViewHolder> {

    private FirebaseAuth firebaseAuth;

    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();

    private List<ListItemMateri> listItems;
    private Context context;

    public MyAdapterMateri(List<ListItemMateri> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public MyAdapterMateri.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_list_item_materi,parent,false);
        return new ViewHolder(v);
    }




    @Override
    public void onBindViewHolder(MyAdapterMateri.ViewHolder holder, int position) {

        ListItemMateri listItem = listItems.get(position);

        holder.TVheadMateri.setText(listItem.getJudulmateri());
        holder.TVdescMateri.setText(listItem.getDeskripsimateri());
        /*Picasso.with(context)
                .load(listItem.getImageUrlmateri())
                .into(holder.IVgambarMateri);*/

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView TVheadMateri;
        private TextView TVdescMateri;
        public ImageView IVgambarMateri;
        private Button BTdownloadmateri;


        public ViewHolder(View itemView) {
            super(itemView);

            TVheadMateri = (TextView) itemView.findViewById(R.id.TVheadmateri);
            TVdescMateri = (TextView) itemView.findViewById(R.id.TVdescmateri);
            //IVgambarMateri = (ImageView) itemView.findViewById(R.id.IVgambarmateri);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
        }

        public void onClick(final View v) {
            int recyclerItemPosition;

            final Intent intent;
            switch (getAdapterPosition()){


                default:
                    ListItemMateri listItem = listItems.get(getAdapterPosition());

                    recyclerItemPosition = getAdapterPosition();
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(listItem.getLinkmateri()));
                    intent.putExtra("PosisiItemRecycler",recyclerItemPosition);


                    break;
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);



        }
    }

}