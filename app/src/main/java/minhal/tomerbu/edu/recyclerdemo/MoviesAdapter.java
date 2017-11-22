package minhal.tomerbu.edu.recyclerdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ANDROID on 22/11/2017.
 */



//adapter: Adapts the ArrayList<Movie> to the UI RecyclerView
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>{

    //Fields:
    private ArrayList<Movie> movies;
    private Context context;
    //inflater -> takes an xml as a parameter and Creates a fully fledged android View from it.
    private LayoutInflater inflater;

    //Constructor:
    public MoviesAdapter(ArrayList<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }
    //creates a view holder:
    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //take an xml and convert it to a view object.
        View v = inflater.inflate(R.layout.movie_item, parent, false);

        //create a view holder and return it the caller of the method.
        return new MoviesViewHolder(v);
    }
    //take a movie and bind it to the view.
    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        //position: index of the current movie:
        Movie movie = movies.get(position);
        //holder: tvTitle, tvYear, ivPoster
        holder.tvTitle.setText(movie.getTitle());
        holder.tvYear.setText(String.valueOf(movie.getReleaseYear()));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    //one reusable view:
    //static inner class
    //JOB title: findView by id...
    public static class MoviesViewHolder extends RecyclerView.ViewHolder{
        //no encapsulation for efficiency:
        TextView tvTitle, tvYear;
        ImageView ivPoster;

        //constructor:
        public MoviesViewHolder(View v) {
            super(v);
            ivPoster = v.findViewById(R.id.ivPoster);
            tvTitle = v.findViewById(R.id.tvTitle);
            tvYear = v.findViewById(R.id.tvYear);
        }
    }
}


