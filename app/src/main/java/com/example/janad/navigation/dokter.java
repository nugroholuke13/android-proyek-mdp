package com.example.janad.navigation;

public class dokter {
    private String kode, username, namaDokter, alamatDokter, kotaPraktek, kodeSpesialis, telp1, telp2, nomerSiup;

    public dokter(String kode, String username, String namaDokter, String alamatDokter, String kotaPraktek, String kodeSpesialis, String telp1, String telp2, String nomerSiup){
        this.kode = kode;
        this.username = username;
        this.namaDokter = namaDokter;
        this.alamatDokter = alamatDokter;
        this.kotaPraktek = kotaPraktek;
        this.kodeSpesialis = kodeSpesialis;
        this.telp1 = telp1;
        this.telp2 = telp2;
        this.nomerSiup = nomerSiup;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public String getAlamatDokter() {
        return alamatDokter;
    }

    public void setAlamatDokter(String alamatDokter) {
        this.alamatDokter = alamatDokter;
    }

    public String getKotaPraktek() {
        return kotaPraktek;
    }

    public void setKotaPraktek(String kotaPraktek) {
        this.kotaPraktek = kotaPraktek;
    }

    public String getKodeSpesialis() {
        return kodeSpesialis;
    }

    public void setKodeSpesialis(String kodeSpesialis) {
        this.kodeSpesialis = kodeSpesialis;
    }

    public String getTelp1() {
        return telp1;
    }

    public void setTelp1(String telp1) {
        this.telp1 = telp1;
    }

    public String getTelp2() {
        return telp2;
    }

    public void setTelp2(String telp2) {
        this.telp2 = telp2;
    }

    public String getNomerSiup() {
        return nomerSiup;
    }

    public void setNomerSiup(String nomerSiup) {
        this.nomerSiup = nomerSiup;
    }
}
