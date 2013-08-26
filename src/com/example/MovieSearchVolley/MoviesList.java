package com.example.MovieSearchVolley;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: tomek
 * Date: 8/22/13
 * Time: 6:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class MoviesList {
    private int page;
    private ArrayList<ResultMovie> results;
    private int total_pages;
    private int total_results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<ResultMovie> getResults() {
        return results;
    }

    public void setResults(ArrayList<ResultMovie> results) {
        this.results = results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }
}

class ResultMovie {
    private boolean adult;
    private String backdrop_path;
    private int id;
    private String original_title;
    private String release_date;
    private String poster_path;
    private double popularity;
    private String title;
    private double vote_average;
    private int vote_count;

    boolean isAdult() {
        return adult;
    }

    void setAdult(boolean adult) {
        this.adult = adult;
    }

    String getBackdrop_path() {
        return backdrop_path;
    }

    void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    String getOriginal_title() {
        return original_title;
    }

    void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    double getPopularity() {
        return popularity;
    }

    void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    String getPoster_path() {
        return poster_path;
    }

    void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    String getRelease_date() {
        return release_date;
    }

    void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    double getVote_average() {
        return vote_average;
    }

    void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    int getVote_count() {
        return vote_count;
    }

    void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }
}
