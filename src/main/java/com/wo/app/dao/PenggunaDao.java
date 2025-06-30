/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wo.app.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.wo.app.model.Pengguna;
import com.wo.app.util.DatabaseManager;
import com.wo.app.util.PasswordUtil;
import org.bson.Document;

/**
 *
 * @author windiwitari
 */
public class PenggunaDao {

    private final MongoCollection<Document> collection;

    public PenggunaDao() {
        // Mengambil collection 'pengguna' dari database
        this.collection = DatabaseManager.getInstance().getDatabase().getCollection("pengguna");
    }

    /**
     * Mengubah objek Document dari MongoDB menjadi objek Pengguna.
     *
     * @param doc Document yang akan diubah.
     * @return Objek Pengguna.
     */
    private Pengguna documentToPengguna(Document doc) {
        if (doc == null) {
            return null;
        }
        Pengguna pengguna = new Pengguna();
        pengguna.setUsername(doc.getString("username"));
        pengguna.setPasswordHash(doc.getString("passwordHash"));
        pengguna.setSalt(doc.getString("salt"));
        return pengguna;
    }

    /**
     * Mencari pengguna di database berdasarkan username.
     *
     * @param username Username yang akan dicari.
     * @return Objek Pengguna jika ditemukan, null jika tidak.
     */
    public Pengguna findByUsername(String username) {
        Document doc = collection.find(Filters.eq("username", username)).first();
        return documentToPengguna(doc);
    }

    /**
     * Membuat pengguna baru dan menyimpannya ke database. Method ini secara
     * otomatis akan melakukan hashing password.
     *
     * @param username Username pengguna baru.
     * @param plainPassword Password asli (belum di-hash).
     * @return true jika berhasil dibuat, false jika username sudah ada.
     */
    public boolean create(String username, String plainPassword) {
        // Cek dulu apakah username sudah ada
        if (findByUsername(username) != null) {
            System.out.println("Gagal membuat pengguna: Username '" + username + "' sudah ada.");
            return false;
        }

        // Buat salt baru untuk pengguna ini
        String salt = PasswordUtil.getSalt();
        // Hash password menggunakan salt yang baru dibuat
        String passwordHash = PasswordUtil.hashPassword(plainPassword, salt);

        // Buat document baru untuk disimpan
        Document doc = new Document("username", username)
                .append("passwordHash", passwordHash)
                .append("salt", salt);

        collection.insertOne(doc);
        System.out.println("Pengguna '" + username + "' berhasil dibuat.");
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Mencoba membuat pengguna admin awal...");
        PenggunaDao dao = new PenggunaDao();

        // Ganti "admin" dan "12345" jika Anda ingin username & password yang berbeda
        dao.create("admin", "12345");

        System.out.println("Proses selesai. Cek output di atas.");
        // Anda bisa menutup aplikasi setelah ini dijalankan.
        // Tambahkan System.exit(0) agar program berhenti setelah selesai.
        System.exit(0);
    }
}
