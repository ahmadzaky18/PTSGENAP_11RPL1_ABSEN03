package com.ptsgenap_11rpl1_absen03;

public class DataActivity {
    //inisialisasi variabel
    String idBarang; //idMahasiswa didatabase merupakan int (Auto Increment)
    String kodeBarang; //namaMahasiswa didatabase merupakan string
    String namaBarang; //nimMahasiswa didatabase merupakan string
    String jenisBarang; //kelasMahasiswa didatabase merupakan string

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getJenisBarang() {
        return jenisBarang;
    }

    public void setJenisBarang(String jenisBarang) {
        this.jenisBarang = jenisBarang;
    }
}