package com.example.dbmovie.data.remote.model;

import com.example.dbmovie.data.local.entity.MovieEntenty;

import java.util.List;

public class MoviesResponse {
    private List<MovieEntenty> results;

    public List<MovieEntenty> getResults() {
        return results;
    }

    public void setResults(List<MovieEntenty> results) {
        this.results = results;
    }
}
