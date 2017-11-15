package minhal.tomerbu.edu.recyclerdemo;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Model class for Movie (json example).
 */
public class Movie {
    private final String title;
    private final String image;
    private double rating;
    private final int releaseYear;
    private ArrayList<String> genres = new ArrayList<>();

    //constructor:
    public Movie(String title, String image, int releaseYear, double rating, String... genre) {
        this.title = title;
        this.image = image;
        this.rating = rating;
        this.releaseYear = releaseYear;

        //for each item in the var args...
        //append the genre to the list

        if (genre != null)
            Collections.addAll(genres, genre);
    }

    //toString, Getters and Setters
    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", rating=" + rating +
                ", releaseYear=" + releaseYear +
                ", genres=" + genres +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public double getRating() {
        return rating;
    }

    public int getReleaseYear() {
        return releaseYear;
    }
}
