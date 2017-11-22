package minhal.tomerbu.edu.recyclerdemo;

import android.os.Handler;
import android.support.annotation.Nullable;

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
import java.util.concurrent.Callable;

import javax.net.ssl.HttpsURLConnection;

/**
 * SRP
 */

public class MovieDatasource {

    //observer design pattern: loosely couple the listener.
    public interface OnMoviesArrivedListener{
        void onMoviesArrived(@Nullable ArrayList<Movie> movies, @Nullable Exception e);
        //void onError(Exception e);
    }

    private static final String address = "https://api.androidhive.info/json/movies.json";

    public static void getMovies(final OnMoviesArrivedListener listener){
        //manifest permission (INTERNET)

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //success (Exception is null): pass the movies to the listener:
                    ArrayList<Movie> movies = getMoviesSync();
                    listener.onMoviesArrived(movies, null);
                } catch (Exception e) {
                   //error (movies are null): pass the exception to the listener:
                   listener.onMoviesArrived(null, e);
                }
            }
        });

        thread.start();
    }

    private static ArrayList<Movie> getMoviesSync() throws IOException, JSONException { //Error vs exception //throwable -> Exception, Error
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
            //TODO: Genre...
            JSONArray genreArray = movieObject.getJSONArray("genre");
            String[] genres = new String[genreArray.length()];

            for (int j = 0; j < genreArray.length(); j++) {
                String g = genreArray.getString(j);
                genres[j] = g;
            }
            movies.add(new Movie(title, image, releaseYear, rating, genres));
        }
        return movies;
    }
    //read an input stream to a string
    private static String read(InputStream in) throws IOException {
        StringBuilder builder = new StringBuilder();

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line = null;
        while ((line = reader.readLine()) != null)
            builder.append(line);

        return builder.toString();
    }
}
