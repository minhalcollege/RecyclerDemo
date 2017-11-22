package minhal.tomerbu.edu.recyclerdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieDatasource.OnMoviesArrivedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MovieDatasource.getMovies(this);
    }


    @Override
    public void onMoviesArrived(@Nullable final ArrayList<Movie> movies, @Nullable final Exception e) {
        //The movies are received in a background thread.

        //runOnUIThread(Runnable runnable)
        //run on UI thread... a method that runs code on the ui (main) thread.


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //code that runs on the UI (Main) Thread...!
                if (movies != null)
                    updateUI(movies);
                else if (e != null){
                    Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

    private void updateUI(ArrayList<Movie> movies) {
        //1) find the recycler by id.
        RecyclerView rvMovies = (RecyclerView) findViewById(R.id.rvMovies);


        //the adapter takes movies and provides Views for the movies.
        //2) MoviesAdapter adapter = new Movies adapter (movies, context)
        MoviesAdapter adapter = new MoviesAdapter(movies, this);

        //3) recycler -> take the adapter.
        rvMovies.setAdapter(adapter);

        //4)
        rvMovies.setLayoutManager(new LinearLayoutManager(null));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
