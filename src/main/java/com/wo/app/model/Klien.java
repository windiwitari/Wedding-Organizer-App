/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wo.app.model;

import java.time.LocalDate;
import org.bson.types.ObjectId;

/**
 *
 * @author windiwitari
 */
public class Klien {

    private ObjectId id;
    private String namaLengkap;
    private String noTelepon;
    private String email;
    private LocalDate tanggalPernikahan;
    private String lokasi;
    private int jumlahTamu;

    // Constructors
    public Klien() {
    }

    // Getters and Setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getTanggalPernikahan() {
        return tanggalPernikahan;
    }

    public void setTanggalPernikahan(LocalDate tanggalPernikahan) {
        this.tanggalPernikahan = tanggalPernikahan;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public int getJumlahTamu() {
        return jumlahTamu;
    }

    public void setJumlahTamu(int jumlahTamu) {
        this.jumlahTamu = jumlahTamu;
    }
}
