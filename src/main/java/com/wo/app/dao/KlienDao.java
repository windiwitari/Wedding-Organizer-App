/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wo.app.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.wo.app.model.Klien;
import com.wo.app.util.DatabaseManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author windiwitari
 */
public class KlienDao {

    private final MongoCollection<Document> collection;

    public KlienDao() {
        // Mengambil collection 'klien' dari database
        this.collection = DatabaseManager.getInstance().getDatabase().getCollection("klien");
    }

    // Method untuk mengubah Objek Klien menjadi Document MongoDB
    private Document klienToDocument(Klien klien) {
        return new Document("namaLengkap", klien.getNamaLengkap())
                .append("noTelepon", klien.getNoTelepon())
                .append("email", klien.getEmail())
                .append("tanggalPernikahan", Date.from(klien.getTanggalPernikahan().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .append("lokasi", klien.getLokasi())
                .append("jumlahTamu", klien.getJumlahTamu());
    }

    // Method untuk mengubah Document MongoDB menjadi Objek Klien
    private Klien documentToKlien(Document doc) {
        Klien klien = new Klien();
        klien.setId(doc.getObjectId("_id"));
        klien.setNamaLengkap(doc.getString("namaLengkap"));
        klien.setNoTelepon(doc.getString("noTelepon"));
        klien.setEmail(doc.getString("email"));
        Date tanggal = doc.getDate("tanggalPernikahan");
        if (tanggal != null) {
            klien.setTanggalPernikahan(tanggal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        klien.setLokasi(doc.getString("lokasi"));
        klien.setJumlahTamu(doc.getInteger("jumlahTamu", 0));
        return klien;
    }

    // CREATE
    public void create(Klien klien) {
        collection.insertOne(klienToDocument(klien));
    }

    // READ - Find All
    public List<Klien> findAll() {
        List<Klien> klienList = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                klienList.add(documentToKlien(cursor.next()));
            }
        }
        return klienList;
    }

    // UPDATE
    public boolean update(Klien klien) {
        if (klien.getId() == null) {
            return false;
        }

        Document doc = klienToDocument(klien);
        Bson filter = Filters.eq("_id", klien.getId());
        Bson updateOperation = new Document("$set", doc);
        UpdateResult result = collection.updateOne(filter, updateOperation);
        return result.getModifiedCount() > 0;
    }

    // DELETE
    public boolean delete(ObjectId id) {
        Bson filter = Filters.eq("_id", id);
        DeleteResult result = collection.deleteOne(filter);
        return result.getDeletedCount() > 0;
    }
}
