package com.example.akbar.smartcity.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsModelUser {

    @SerializedName("kode")
    private String kode;
    @SerializedName("pesan")
    private String pesan;
    @SerializedName("result")
    private List<ModelUser> result;

    public ResponsModelUser(){}

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<ModelUser> getResult() {
        return result;
    }

    public void setResult(List<ModelUser> result) {
        this.result = result;
    }
}
