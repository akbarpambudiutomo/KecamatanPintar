package com.example.akbar.smartcity.model;

import com.google.gson.annotations.SerializedName;

public class ModelUser {
    @SerializedName("nama_lengkap")
    private
    String nama_lengkap;
    @SerializedName("email")
    private
    String email;
    @SerializedName("no_tlpn")
    private
    String no_tlpn;
    @SerializedName("alamat")
    private
    String alamat;
    @SerializedName("username")
    private
    String username;
    @SerializedName("password")
    private
    String password;
    @SerializedName("foto")
    private
    String foto;

    public ModelUser(){};

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNo_tlpn() {
        return no_tlpn;
    }

    public void setNo_tlpn(String no_tlpn) {
        this.no_tlpn = no_tlpn;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
