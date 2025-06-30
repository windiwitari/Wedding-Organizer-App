/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wo.app.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.wo.app.model.Vendor;
import com.wo.app.util.DatabaseManager;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
/**
 *
 * @author windiwitari
 */
public class VendorDao {
    private final MongoCollection<Document> collection;

    public VendorDao() {
        this.collection = DatabaseManager.getInstance().getDatabase().getCollection("vendor");
    }

    private Document vendorToDocument(Vendor vendor) {
        return new Document("namaVendor", vendor.getNamaVendor())
                .append("noTelepon", vendor.getNoTelepon())
                .append("email", vendor.getEmail())
                .append("deskripsiPaket", vendor.getDeskripsiPaket());
        
    }

    private Vendor documentToVendor(Document doc) {
        Vendor vendor = new Vendor();
        vendor.setId(doc.getObjectId("_id"));
        vendor.setNamaVendor(doc.getString("namaVendor"));
        vendor.setNoTelepon(doc.getString("noTelepon"));
        vendor.setEmail(doc.getString("email"));
        vendor.setDeskripsiPaket(doc.getString("deskripsiPaket"));
        
        // ----------------------------------------------------
        
        return vendor;
    }

    public void create(Vendor vendor) {
        collection.insertOne(vendorToDocument(vendor));
    }

    public List<Vendor> findAll() {
        List<Vendor> vendorList = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                vendorList.add(documentToVendor(cursor.next()));
            }
        }
        return vendorList;
    }

    public boolean update(Vendor vendor) {
        if (vendor.getId() == null) return false;
        
        Bson filter = Filters.eq("_id", vendor.getId());
        Document doc = vendorToDocument(vendor);
        Bson updateOperation = new Document("$set", doc);
        UpdateResult result = collection.updateOne(filter, updateOperation);
        return result.getModifiedCount() > 0;
    }

    public boolean delete(ObjectId id) {
        Bson filter = Filters.eq("_id", id);
        DeleteResult result = collection.deleteOne(filter);
        return result.getDeletedCount() > 0;
    }
}
