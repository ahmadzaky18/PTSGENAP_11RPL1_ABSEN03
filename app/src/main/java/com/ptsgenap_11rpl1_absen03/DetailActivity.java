package com.ptsgenap_11rpl1_absen03;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity"; //untuk melihat log
    EditText editTextKode1, editTextNama1, editTextJenis1;
    Button buttonMainUpdate, buttonMaindelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        editTextKode1 = findViewById(R.id.editTextKode1);
        editTextNama1 = findViewById(R.id.editTextNama1);
        editTextJenis1 = findViewById(R.id.editTextJenis1);
        buttonMainUpdate = findViewById(R.id.buttonMainUpdate);
        buttonMaindelete = findViewById(R.id.buttonMaindelete);

        editTextKode1.setText(getIntent().getStringExtra("events2"));
        editTextNama1.setText(getIntent().getStringExtra("events"));
        editTextJenis1.setText(getIntent().getStringExtra("events4"));

        buttonMainUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AndroidNetworking.initialize(getApplicationContext()); //inisialisasi library FAN
                AndroidNetworking.post("http://192.168.0.106/crud/update.php")
                        .addBodyParameter("b_id", getIntent().getStringExtra("events3")) //id bersifat Auto_Increment tidak perlu diisi/(diisi NULL) cek create.php
                        .addBodyParameter("b_kode",editTextKode1.getText().toString().trim().toUpperCase()) //mengirimkan data nim_mahasiswa yang akan diisi dengan varibel nim
                        .addBodyParameter("b_nama", editTextNama1.getText().toString().trim().toUpperCase()) //mengirimkan data nama_mahasiswa yang akan diisi dengan varibel nama
                        .addBodyParameter("b_jenis", editTextJenis1.getText().toString().trim().toUpperCase()) //mengirimkan data kelas_mahasiswa yang akan diisi dengan varibel kelas
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //Handle Response
                                Log.d(TAG, "onResponse: " + response); //untuk log pada onresponse
                                Toast.makeText(getApplicationContext(),"Data berhasil diupdate" , Toast.LENGTH_SHORT).show();
                                //memunculkan Toast saat data berhasil ditambahkan

                            }
                            @Override
                            public void onError(ANError error) {
                                //Handle Error
                                Log.d(TAG, "onError: Failed" + error); //untuk log pada onerror
                                Toast.makeText(getApplicationContext(),"Data tidak berhasil diupdate" , Toast.LENGTH_SHORT).show();
                                //memunculkan Toast saat data gagal ditambahkan
                            }
                        });


            }
        });
        buttonMaindelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AndroidNetworking.initialize(getApplicationContext()); //inisialisasi library FAN
                AndroidNetworking.post("http://192.168.0.106/crud/delete.php")
                        .addBodyParameter("b_id", getIntent().getStringExtra("events3")) //id bersifat Auto_Increment tidak perlu diisi/(diisi NULL) cek create.php
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //Handle Response
                                Log.d(TAG, "onResponse: " + response); //untuk log pada onresponse
                                Toast.makeText(getApplicationContext(),"Data berhasil didelete" , Toast.LENGTH_SHORT).show();
                                //memunculkan Toast saat data berhasil ditambahkan


                            }

                            @Override
                            public void onError(ANError error) {
                                //Handle Error
                                Log.d(TAG, "onError: Failed" + error); //untuk log pada onerror
                                Toast.makeText(getApplicationContext(),"Data tidak berhasil didelete" , Toast.LENGTH_SHORT).show();
                                //memunculkan Toast saat data gagal ditambahkan
                            }
                        });

            }
        });

    }
}
