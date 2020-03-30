package com.ptsgenap_11rpl1_absen03;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListBarangAdapter extends RecyclerView.Adapter<ListBarangAdapter.ViewHolder> {
    private Context mctx;

    private List<DataActivity> dataBarangs; //inisialisasi List dengan object DataMahasiswa


    //construktor ListMahasiswaAdapter
    public ListBarangAdapter(ReadActivity readActivity, List<DataActivity> dataBarangs , Context mctx ) {
        this.dataBarangs = dataBarangs;
        this.mctx = mctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate view yang akan digunakan yaitu layout list_mahasiswa_row.xml
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list, parent, false);
        ViewHolder holder = new ViewHolder(v); //inisialisasi ViewHolder
        return holder;
    } //fungsi yang dijalankan saat ViewHolder dibuat

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataActivity data = dataBarangs.get(position); //inisialisasi object DataMahasiwa
        holder.mNama.setText(data.getNamaBarang()); //menset value view "mNama" sesuai data dari getNamaMahasiswa();
        holder.mKode.setText(data.getKodeBarang()); //menset value view "mNim" sesuai data dari getNimMahasiswa();
        holder.mJenis.setText(data.getJenisBarang()); //menset value view "mNim" sesuai data dari getNimMahasiswa();
    }

    @Override
    public int getItemCount() {
        return dataBarangs.size(); //mengambil item sesuai urutan
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mNama, mKode, mJenis; //inisialisasi variabel

        public ViewHolder(View itemView) {
            super(itemView);
            mNama = itemView.findViewById(R.id.textListBarangKode); //find layout sesuai dengan yg di list_mahasiswa_row.xml
            mKode = itemView.findViewById(R.id.textListBarangNama); //find layout sesuai dengan yg di list_mahasiswa_row.xml
            mJenis = itemView.findViewById(R.id.textListBarangJenis); //find layout sesuai dengan yg di list_mahasiswa_row.xml
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mctx,"Kamu memilih "+dataBarangs.get(getAdapterPosition()).getNamaBarang() , Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(mctx, DetailActivity.class);

                    i.putExtra("events", dataBarangs.get(getAdapterPosition()).getNamaBarang());
                    i.putExtra("events2", dataBarangs.get(getAdapterPosition()).getKodeBarang());
                    i.putExtra("events3", dataBarangs.get(getAdapterPosition()).getIdBarang());
                    i.putExtra("events4", dataBarangs.get(getAdapterPosition()).getJenisBarang());
                    mctx.startActivity(i);
                }
            });
        }
    }
}