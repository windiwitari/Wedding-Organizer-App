/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wo.app.util;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author windiwitari
 */
public class DatabaseManager {

    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "wedding_organizer_db";
    private static DatabaseManager instance;
    private final MongoDatabase database;

    private DatabaseManager() {
        try {
            MongoClient mongoClient = MongoClients.create(CONNECTION_STRING);
            database = mongoClient.getDatabase(DATABASE_NAME);
            System.out.println("Koneksi ke MongoDB berhasil!");
        } catch (Exception e) {
            System.err.println("Gagal koneksi ke MongoDB: " + e.getMessage());
            throw new RuntimeException("Gagal menginisialisasi koneksi database.", e);
        }
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public MongoDatabase getDatabase() {
        return database;
    }

}
