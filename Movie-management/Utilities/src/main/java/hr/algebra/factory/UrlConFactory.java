/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.factory;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

/**
 *
 * @author Lovric
 */
public class UrlConFactory {

    private static final int TIMEOUT = 10000;
    private static final String REQUEST_METHOD = "GET";
    private static final String USER_AGENT = "User-Agent";
    private static final String MOZILLA = "Mozilla/5.0";

    public static HttpURLConnection getCon(String path) throws Exception {
        URL url = URI.create(path).toURL();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setConnectTimeout(TIMEOUT);
        con.setReadTimeout(TIMEOUT);
        con.setRequestMethod(REQUEST_METHOD);
        con.addRequestProperty(USER_AGENT, MOZILLA);

        return con;
    }

    private UrlConFactory() {
    }

}
