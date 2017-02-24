package com.example.prabowo.tutorpedia.CekSoal;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.prabowo.tutorpedia.R;
import com.example.prabowo.tutorpedia.Soal;

import static com.example.prabowo.tutorpedia.CekSoal.RecyclerViewAdapter.context;

/**
 * Created by Tommy on 2/17/2017.
 */

public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView NomorSoal;
   // public ImageView countryPhoto;


    public RecyclerViewHolders(View itemView) {
        super(itemView);
        NomorSoal = (TextView) itemView.findViewById(R.id.nomor_soal);

        itemView.setClickable(true);
        itemView.setOnClickListener(this);

    }

    public void onClick(final View v) {

        int recyclerItemPosition;

        final Intent intent;
        switch (getAdapterPosition()){
            default:
                recyclerItemPosition = getAdapterPosition();
                Soal.nomor=recyclerItemPosition+1;

                intent = new Intent(context,Soal.class);
                break;
        }
        NomorSoal.setBackgroundColor(context.getColor(R.color.cardview_dark_background));

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);


    }
}
