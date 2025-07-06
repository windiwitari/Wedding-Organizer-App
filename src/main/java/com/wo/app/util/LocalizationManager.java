/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wo.app.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author windiwitari
 */
public class LocalizationManager {

    private static final String BUNDLE_BASE_NAME = "messages";
    
    
    private static ResourceBundle bundle;
    
    
    private static Locale currentLocale;


    public static void setLocale(Locale locale) {
        currentLocale = locale;

        bundle = ResourceBundle.getBundle(BUNDLE_BASE_NAME, currentLocale);
    }

    /**
     * Retrieves a translated string for the given key from the currently loaded resource bundle.
     * If no locale has been set, it defaults to Indonesian (id_ID).
     *
     * @param key The key of the string to retrieve (e.g., "login.button.login").
     * @return The translated string. If the key is not found, it will return a string like '!!!key!!!'.
     */
    public static String getString(String key) {

        if (bundle == null) {
            System.out.println("ResourceBundle not initialized. Defaulting to Indonesian locale.");
            setLocale(new Locale("id", "ID"));
        }
        
        try {
            return bundle.getString(key);
        } catch (Exception e) {

            return "!!!" + key + "!!!";
        }
    }

    /**
     * Gets the currently active locale.
     * Useful for saving the user's last language preference.
     *
     * @return The current Locale object.
     */
    public static Locale getCurrentLocale() {
        if (currentLocale == null) {
            // Default to Indonesian if not set.
            return new Locale("id", "ID");
        }
        return currentLocale;
    }
}
