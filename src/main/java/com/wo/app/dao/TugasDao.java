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


    public void create(Tugas tugas) {
        collection.insertOne(tugasToDocument(tugas));
    }


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


    public boolean updateStatus(ObjectId tugasId, String newStatus) {
        Bson filter = Filters.eq("_id", tugasId);
        Bson update = Updates.set("status", newStatus);
        return collection.updateOne(filter, update).getModifiedCount() > 0;
    }


    public boolean delete(ObjectId id) {
        DeleteResult result = collection.deleteOne(Filters.eq("_id", id));
        return result.getDeletedCount() > 0;
    }
}
