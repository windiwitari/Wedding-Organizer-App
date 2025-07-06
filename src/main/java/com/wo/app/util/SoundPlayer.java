/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wo.app.util;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author windiwitari
 */
public class SoundPlayer {

    public static void playNotificationSound() {
        try {

            URL soundUrl = SoundPlayer.class.getResource("/notification.wav");
            if (soundUrl == null) {
                System.err.println("File suara 'notification.wav' tidak ditemukan di resources!");
                return;
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundUrl);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            System.err.println("Gagal memainkan suara notifikasi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
