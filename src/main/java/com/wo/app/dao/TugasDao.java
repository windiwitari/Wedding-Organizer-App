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
import com.wo.app.model.Tugas;
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
public class TugasDao {

    private final MongoCollection<Document> collection;

    public TugasDao() {
        this.collection = DatabaseManager.getInstance().getDatabase().getCollection("tugas");
    }
    // Mengubah objek Tugas menjadi Document MongoDB untuk disimpan

    private Document tugasToDocument(Tugas tugas) {
        return new Document("idKlien", tugas.getIdKlien())
                .append("namaTugas", tugas.getNamaTugas())
                .append("tenggatWaktu", Date.from(tugas.getTenggatWaktu().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .append("status", tugas.getStatus())
                .append("penanggungJawab", tugas.getPenanggungJawab());
    }

    // Mengubah Document dari MongoDB menjadi objek Tugas
    private Tugas documentToTugas(Document doc) {
        Tugas tugas = new Tugas();
        tugas.setId(doc.getObjectId("_id"));
        tugas.setIdKlien(doc.getObjectId("idKlien"));
        tugas.setNamaTugas(doc.getString("namaTugas"));
        Date tanggal = doc.getDate("tenggatWaktu");
        if (tanggal != null) {
            tugas.setTenggatWaktu(tanggal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        tugas.setStatus(doc.getString("status"));
        tugas.setPenanggungJawab(doc.getString("penanggungJawab"));
        return tugas;
    }

    /**
     * Membuat tugas baru di database.
     *
     * @param tugas Objek Tugas yang akan disimpan.
     */
    public void create(Tugas tugas) {
        collection.insertOne(tugasToDocument(tugas));
    }

    /**
     * Method khusus untuk mencari semua tugas berdasarkan ID Klien.
     *
     * @param clientId ID dari klien yang tugasnya ingin dicari.
     * @return Sebuah List berisi objek Tugas milik klien tersebut.
     */
    public List<Tugas> findByClientId(ObjectId clientId) {
        List<Tugas> tugasList = new ArrayList<>();
        Bson filter = Filters.eq("idKlien", clientId);
        try (MongoCursor<Document> cursor = collection.find(filter).iterator()) {
            while (cursor.hasNext()) {
                tugasList.add(documentToTugas(cursor.next()));
            }
        }
        return tugasList;
    }

    /**
     * Method khusus untuk mengupdate status sebuah tugas (lebih efisien).
     *
     * @param tugasId ID dari tugas yang akan diupdate.
     * @param newStatus Status baru (misal: "Selesai").
     * @return true jika berhasil diupdate.
     */
    public boolean updateStatus(ObjectId tugasId, String newStatus) {
        Bson filter = Filters.eq("_id", tugasId);
        Bson update = Updates.set("status", newStatus);
        return collection.updateOne(filter, update).getModifiedCount() > 0;
    }

    /**
     * Menghapus sebuah tugas dari database berdasarkan ID-nya.
     *
     * @param id ID dari tugas yang akan dihapus.
     * @return true jika berhasil dihapus.
     */
    public boolean delete(ObjectId id) {
        DeleteResult result = collection.deleteOne(Filters.eq("_id", id));
        return result.getDeletedCount() > 0;
    }
}
