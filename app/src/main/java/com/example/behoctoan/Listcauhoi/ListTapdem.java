package com.example.behoctoan.Listcauhoi;

import android.graphics.Bitmap;

public class ListTapdem {
    private int id;
    private byte[] anh;
    private String dapana;
    private String dapanb;
    private String dapanc;
    private String dapand;
    private String dapandung;

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getDapana() {
        return dapana;
    }

    public void setDapana(String dapana) {
        this.dapana = dapana;
    }

    public String getDapanb() {
        return dapanb;
    }

    public void setDapanb(String dapanb) {
        this.dapanb = dapanb;
    }

    public String getDapanc() {
        return dapanc;
    }

    public void setDapanc(String dapanc) {
        this.dapanc = dapanc;
    }

    public String getDapand() {
        return dapand;
    }

    public void setDapand(String dapand) {
        this.dapand = dapand;
    }

    public String getDapandung() {
        return dapandung;
    }

    public void setDapandung(String dapandung) {
        this.dapandung = dapandung;
    }


    public ListTapdem(int id, byte[] anh, String dapana, String dapanb, String dapanc, String dapand, String dapandung) {
        this.id = id;
        this.anh = anh;
        this.dapana = dapana;
        this.dapanb = dapanb;
        this.dapanc = dapanc;
        this.dapand = dapand;
        this.dapandung = dapandung;
    }
}

