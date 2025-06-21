package org.example.CulturaMYTrip.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class GeocodingService {

    private static final String API_KEY = "AIzaSyCA7zqlzBcyQlAeqS0FnUC4saLSR8dF_1M";

    public static double[] getCoordinates(String place) throws Exception {
        String urlString = String.format(
            "https://maps.googleapis.com/maps/api/geocode/json?address=%s&key=%s",
            place.replace(" ", "+"), API_KEY
        );

        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
            new InputStreamReader(conn.getInputStream())
        );

        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) response.append(inputLine);
        in.close();

        JSONObject json = new JSONObject(response.toString());
        if (!json.getString("status").equals("OK")) {
            throw new Exception("Geocoding API error: " + json.getString("status"));
        }

        JSONObject location = json
            .getJSONArray("results")
            .getJSONObject(0)
            .getJSONObject("geometry")
            .getJSONObject("location");

        return new double[] {
            location.getDouble("lat"),
            location.getDouble("lng")
        };
    }
}