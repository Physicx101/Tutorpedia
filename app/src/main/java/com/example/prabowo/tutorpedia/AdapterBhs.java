package com.example.prabowo.tutorpedia;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prabowo.tutorpedia.CekSoal.MapelModel;



import java.util.ArrayList;

/**
 * Created by Bogi on 10-Apr-17.
 */

public class AdapterBhs extends RecyclerView.Adapter<AdapterBhs.ViewHolder> {
    private ArrayList<MapelModel> list;
    private Context context;

    public AdapterBhs(ArrayList<MapelModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public AdapterBhs.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mapel, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterBhs.ViewHolder holder, int position) {

        holder.TV_nama_mapel.setText(list.get(position).getNamaMapel());
        holder.IV_gambar_mapel.setImageResource(list.get(position).getGambarMapel());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView TV_nama_mapel;
        private ImageView IV_gambar_mapel;


        public ViewHolder(View itemView) {
            super(itemView);

            TV_nama_mapel = (TextView) itemView.findViewById(R.id.mapel_name);
            IV_gambar_mapel = (ImageView) itemView.findViewById(R.id.mapel_image);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
        }

        public void onClick(final View v) {
            int itemPosition = getAdapterPosition();
            if(itemPosition == 0) {
                Intent intent = new Intent(context, Transisi.class);
                intent.putExtra("Matkul", "Bing");
                context.startActivity(intent);
            }

            if(itemPosition == 1) {
                Intent intent = new Intent(context, Transisi.class);
                intent.putExtra("Matkul", "Bindo");
                context.startActivity(intent);
            }


        }
    }
}
