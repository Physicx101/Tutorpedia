package com.example.prabowo.tutorpedia;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Prabowo on 14/02/2017.
 */

public class myAdapterEvent extends RecyclerView.Adapter<myAdapterEvent.ViewHolder> {

    private FirebaseAuth firebaseAuth;

    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();

    private List<ListEventItem> listItems;
    private Context context;

    public myAdapterEvent(List<ListEventItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public myAdapterEvent.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_event,parent,false);
        return new ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(myAdapterEvent.ViewHolder holder, int position) {

        ListEventItem listItem = listItems.get(position);

        holder.TVheadevent.setText(listItem.getJudulevent());
        holder.TVdescevent.setText(listItem.getDeskripsievent());
        Picasso.with(context)
                .load(listItem.getImageUrlevent())
                .into(holder.IVgambarevent);

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView TVheadevent;
        private TextView TVdescevent;
        public ImageView IVgambarevent;


        public ViewHolder(View itemView) {
            super(itemView);

            TVheadevent = (TextView) itemView.findViewById(R.id.TVheadevent);
            TVdescevent = (TextView) itemView.findViewById(R.id.TVdescevent);
            IVgambarevent = (ImageView) itemView.findViewById(R.id.IVgambarevent);
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
