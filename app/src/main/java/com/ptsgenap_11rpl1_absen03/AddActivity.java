package com.ptsgenap_11rpl1_absen03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

public class AddActivity extends AppCompatActivity {
    private static final String TAG = "AddActivity"; //untuk melihat log
    private EditText etKode, etNama, etJenis; //pembuatan variabel edittext
    private Button btnTambah, btnLihat; //pembuatan variabel button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Log.d(TAG, "onCreate: inisialisasi"); //untuk log pada oncreate

        etKode = findViewById(R.id.editcode); //inisialisasi value etNim
        etNama = findViewById(R.id.editNama); //inisialisasi value etNama
        etJenis = findViewById(R.id.editJenis); //inisialisasi value etKelas
        btnTambah = findViewById(R.id.btnTambah); //inisialisasi value btnTambah
        btnLihat = findViewById(R.id.btnLihat); //inisialisasi value btnLihat

        AndroidNetworking.initialize(getApplicationContext()); //inisialisasi library FAN
        aksiButton();//memanggil fungsi aksiButton()
    }
    public void aksiButton(){
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kode = etKode.getText().toString(); //mengambil Value etNim menjadi string
                String nama = etNama.getText().toString(); //mengambil Value etNim menjadi string
                String jenis = etJenis.getText().toString(); //mengambil Value etNim menjadi string
                if (kode.equals("")||nama.equals("")||jenis.equals("")){
                    Toast.makeText(getApplicationContext(),"Semua data harus diisi" , Toast.LENGTH_SHORT).show();
                    //memunculkan toast saat form masih ada yang kosong
                } else {
                    tambahData(kode,nama,jenis); //memanggil fungsi tambahData()

                    etKode.setText(""); //mengosongkan form setelah data berhasil ditambahkan
                    etNama.setText(""); //mengosongkan form setelah data berhasil ditambahkan
                    etJenis.setText(""); //mengosongkan form setelah data berhasil ditambahkan
                }
            }
        });
        btnLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //untuk pindah keActivity lain saat buttonlihat dipencet
                Intent intent = new Intent(AddActivity.this, ReadActivity.class);
                startActivity(intent);
            }
        });
    }


    public void tambahData(String kode, String nama, String jenis){
        //koneksi ke file create.php, jika menggunakan localhost gunakan ip sesuai dengan ip kamu
        AndroidNetworking.post("http://192.168.0.106/crud/create.php")
                .addBodyParameter("b_id", "") //id bersifat Auto_Increment tidak perlu diisi/(diisi NULL) cek create.php
                .addBodyParameter("b_kode",kode) //mengirimkan data nim_mahasiswa yang akan diisi dengan varibel kode
                .addBodyParameter("b_nama", nama) //mengirimkan data nama_mahasiswa yang akan diisi dengan varibel nama
                .addBodyParameter("b_jenis", jenis) //mengirimkan data kelas_mahasiswa yang akan diisi dengan varibel jenis
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Handle Response
                        Log.d(TAG, "onResponse: " + response); //untuk log pada onresponse
                        Toast.makeText(getApplicationContext(),"Data berhasil ditambahkan" , Toast.LENGTH_SHORT).show();
                        //memunculkan Toast saat data berhasil ditambahkan

                    }
                    @Override
                    public void onError(ANError error) {
                        //Handle Error
                        Log.d(TAG, "onError: Failed" + error); //untuk log pada onerror
                        Toast.makeText(getApplicationContext(),"Data gagal ditambahkan", Toast.LENGTH_SHORT).show();
                        //memunculkan Toast saat data gagal ditambahkan
                    }
                });
    }
}