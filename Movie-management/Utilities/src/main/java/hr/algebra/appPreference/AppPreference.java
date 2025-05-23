/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.appPreference;

import java.util.prefs.Preferences;

/**
 *
 * @author Lovric
 */
public class AppPreference {

    private static final Preferences prefs = Preferences.userRoot().node("MovieManager");
    private static final String LOGGED_IN_USER = "loggedInUser";

    public static void saveLoggedInUser(String username) {
        prefs.put(LOGGED_IN_USER, username);
    }

    public static String getLoggedInUser() {
        return prefs.get(LOGGED_IN_USER, null);
    }

    public static void clearLoggedInUser() {
        prefs.remove(LOGGED_IN_USER);
    }
}
