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
public class Tugas {

    private ObjectId id;
    private ObjectId idKlien; // Kunci untuk menghubungkan ke collection Klien
    private String namaTugas;
    private LocalDate tenggatWaktu;
    private String status; 
    private String penanggungJawab;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getIdKlien() {
        return idKlien;
    }

    public void setIdKlien(ObjectId idKlien) {
        this.idKlien = idKlien;
    }

    public String getNamaTugas() {
        return namaTugas;
    }

    public void setNamaTugas(String namaTugas) {
        this.namaTugas = namaTugas;
    }

    public LocalDate getTenggatWaktu() {
        return tenggatWaktu;
    }

    public void setTenggatWaktu(LocalDate tenggatWaktu) {
        this.tenggatWaktu = tenggatWaktu;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPenanggungJawab() {
        return penanggungJawab;
    }

    public void setPenanggungJawab(String penanggungJawab) {
        this.penanggungJawab = penanggungJawab;
    }
}
