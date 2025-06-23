/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wo.app.model;

import org.bson.types.ObjectId;

/**
 *
 * @author windiwitari
 */
public class Vendor {

    private ObjectId id;
    private String namaVendor;
    private String kategori; // Misal: Katering, Fotografi, Dekorasi, dll.
    private String kontakPerson;
    private String noTelepon;
    private String email;
    private String deskripsiPaket; // Untuk deskripsi dan harga

    // Getters and Setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNamaVendor() {
        return namaVendor;
    }

    public void setNamaVendor(String namaVendor) {
        this.namaVendor = namaVendor;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getKontakPerson() {
        return kontakPerson;
    }

    public void setKontakPerson(String kontakPerson) {
        this.kontakPerson = kontakPerson;
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

    public String getDeskripsiPaket() {
        return deskripsiPaket;
    }

    public void setDeskripsiPaket(String deskripsiPaket) {
        this.deskripsiPaket = deskripsiPaket;
    }

}
