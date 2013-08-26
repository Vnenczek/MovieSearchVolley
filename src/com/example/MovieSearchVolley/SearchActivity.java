package com.example.MovieSearchVolley;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.*;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class SearchActivity extends Activity {

    private TextView movieTitleView;
    private EditText searchEditText;
    private ImageView imageView;

    private static final String URL = "http://api.themoviedb.org/3/";
    private String API_KEY = null;
    private Configuration configuration = null;
    private RequestQueue queue;
    private Gson gson;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        movieTitleView = (TextView) findViewById(R.id.MovieTitle);
        searchEditText = (EditText) findViewById(R.id.movieEditText);
        imageView = (ImageView) findViewById(R.id.PosterImageView);
        Button searchButton = (Button) findViewById(R.id.searchButton);

        API_KEY = getApiKey();
        queue = Volley.newRequestQueue(this);
        gson = new Gson();
        getAPIConfiguration();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchEditText.getText().toString();
                findViewById(R.id.ProgressBar1).setVisibility(View.VISIBLE);
                findMovie(query);
            }
        });
    }

    /**
     * Request with custom header for The Movie Database
     */
    private class MoviesRequest extends JsonObjectRequest {
        public MoviesRequest(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
            super(method, url, jsonRequest, listener, errorListener);
        }

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            Map<String, String> map = new HashMap<String, String>();
            map.put("Accept", "application/json");
            return map;
        }
    }

    private class MovieResponseListener implements Response.Listener<JSONObject> {

        @Override
        public void onResponse(JSONObject response) {
            findViewById(R.id.ProgressBar1).setVisibility(View.GONE);
            MoviesList moviesList = gson.fromJson(response.toString(), MoviesList.class);
            if (moviesList.getTotal_results() > 0 && moviesList.getResults().get(0).getPoster_path() != null) {
                movieTitleView.setText("Movie: " + moviesList.getResults().get(0).getTitle());
                findPoster(moviesList.getResults().get(0).getPoster_path());

            } else {
                movieTitleView.setText("Nothing found!");
                imageView.setVisibility(View.GONE);
            }
        }
    }

    private class MovieResponseErrorListener implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {
            movieTitleView.setText("Error: " + error.toString());
            findViewById(R.id.ProgressBar1).setVisibility(View.GONE);
        }
    }

    public void findMovie(String query) {
        String URL = makeSearchURL(query);
        MoviesRequest moviesRequest = new MoviesRequest(Request.Method.GET, URL, null, new MovieResponseListener(), new MovieResponseErrorListener());
        queue.add(moviesRequest);
    }

    public void findPoster(String url) {
        StringBuilder sb = new StringBuilder();
        sb.append(configuration.getImages().getBase_url());
        sb.append(configuration.getImages().getPoster_sizes()[3]);
        sb.append('/');
        sb.append(url.substring(1));

        ImageRequest posterRequest = new ImageRequest(sb.toString(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imageView.setImageBitmap(response);
                imageView.setVisibility(View.VISIBLE);
            }
        }, 2000, 2000, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );

        queue.add(posterRequest);

    }

    private String makeSearchURL(String query) {
        String encodedQuery = null;
        try {
            encodedQuery = URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        sb.append(URL);
        sb.append("search/movie");
        sb.append("?api_key=");
        sb.append(API_KEY);
        sb.append("&query=");
        sb.append(encodedQuery);

        return sb.toString();
    }

    private String getApiKey() {
        String api_key = null;
        AssetManager am = getAssets();
        try {
            InputStream is = am.open("api_key.txt");
            api_key = convertStreamToString(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return api_key;
    }

    private String convertStreamToString(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = is.read();
        while (i != -1) {
            baos.write(i);
            i = is.read();
        }
        return baos.toString();
    }

    private void getAPIConfiguration() {
        StringBuilder sb = new StringBuilder();
        sb.append(URL);
        sb.append("configuration");
        sb.append("?api_key=");
        sb.append(API_KEY);
        MoviesRequest configRequest = new MoviesRequest(Request.Method.GET, sb.toString(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                configuration = gson.fromJson(response.toString(), Configuration.class);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        queue.add(configRequest);
    }
}
