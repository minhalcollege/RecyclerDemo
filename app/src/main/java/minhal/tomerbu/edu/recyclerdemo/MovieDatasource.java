package minhal.tomerbu.edu.recyclerdemo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * SRP
 */

public class MovieDatasource {
    private final String address = "https://api.androidhive.info/json/movies.json";

    private ArrayList<Movie> getMovies() throws IOException, JSONException { //Error vs exception //throwable -> Exception, Error
        ArrayList<Movie> movies = new ArrayList<>();

        URL url = new URL(address);

        //polymorphic method, //con.getResponseCode()...
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

        InputStream in = con.getInputStream();
        String json = read(in);
        JSONArray jsonArray = new JSONArray(json);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject movieObject = jsonArray.getJSONObject(i);

            String title = movieObject.getString("title");
            int releaseYear = movieObject.getInt("releaseYear");
            String image = movieObject.getString("image");
            double rating = movieObject.getDouble("rating");
            movies.add(new Movie(title, image, releaseYear, rating));
        }
        return movies;
    }
    //read an input stream to a string
    private String read(InputStream in) throws IOException {
        StringBuilder builder = new StringBuilder();

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line = null;
        while ((line = reader.readLine()) != null)
            builder.append(line);

        return builder.toString();
    }
}
