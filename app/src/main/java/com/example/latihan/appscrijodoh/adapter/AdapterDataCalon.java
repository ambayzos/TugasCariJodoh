package com.example.latihan.appscrijodoh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.latihan.appscrijodoh.R;
import com.example.latihan.appscrijodoh.entity.User;

import java.util.ArrayList;

public class AdapterDataCalon extends RecyclerView.Adapter<AdapterDataCalon.DataCalonViewHolder> {

    private ArrayList<User> uList;
    private Context context;

    public AdapterDataCalon(ArrayList<User> uList, Context context) {
        this.uList = uList;
        this.context = context;
    }


    @NonNull
    @Override
    public DataCalonViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_data_calon, parent, false);
        return new AdapterDataCalon.DataCalonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataCalonViewHolder holder, int position) {
        Glide.with(context).load(uList.get(position).getDataFoto()).into(holder.imgView);
        holder.txNama.setText("Nama : "+uList.get(position).getNamaLengkap());
        holder.txUmur.setText("Umur : "+uList.get(position).getAge());
        holder.txUsername.setText("Username : "+uList.get(position).getUsername());
    }

    @Override
    public int getItemCount() {

        return (uList == null) ? 0 : uList.size();
    }

    public class DataCalonViewHolder extends RecyclerView.ViewHolder {
        public TextView txNama, txUmur, txUsername;
        public ImageView imgView;
        public DataCalonViewHolder(@NonNull  View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.img_item_photo);
            txNama = itemView.findViewById(R.id.txtNama);
            txUmur = itemView.findViewById(R.id.txtUmur);
            txUsername = itemView.findViewById(R.id.txtUsername);

        }
    }
}
