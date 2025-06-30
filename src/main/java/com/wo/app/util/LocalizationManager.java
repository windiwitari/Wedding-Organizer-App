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
    // The base name of the properties files.
    // Java will look for files like "messages_en_US.properties" or "messages_id_ID.properties".
    private static final String BUNDLE_BASE_NAME = "messages";

    // The currently loaded resource bundle.
    private static ResourceBundle bundle;

    // The currently active locale.
    private static Locale currentLocale;

    /**
     * Sets the application's locale and loads the corresponding resource bundle.
     * This method should be called when the user changes the language.
     *
     * @param locale The new Locale to set (e.g., new Locale("en", "US")).
     */
    public static void setLocale(Locale locale) {
        currentLocale = locale;
        // Load the resource bundle for the given locale.
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
        // Default to Indonesian if the bundle hasn't been initialized yet.
        if (bundle == null) {
            System.out.println("ResourceBundle not initialized. Defaulting to Indonesian locale.");
            setLocale(new Locale("id", "ID"));
        }
        
        try {
            return bundle.getString(key);
        } catch (Exception e) {
            // Return the key itself as an error indicator if it's not found.
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
