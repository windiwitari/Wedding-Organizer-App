/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.wo.app.main;

import com.wo.app.view.MainView;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
/**
 *
 * @author windiwitari
 */
public class Main {

    public static void main(String[] args) {
         // Set Look and Feel agar tampilan lebih modern
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.err.println("Gagal set Look and Feel: " + e.getMessage());
        }

        // Menjalankan GUI di Event Dispatch Thread (praktik terbaik Swing)
        SwingUtilities.invokeLater(() -> {
            new MainView().setVisible(true);
        });
    }
}
