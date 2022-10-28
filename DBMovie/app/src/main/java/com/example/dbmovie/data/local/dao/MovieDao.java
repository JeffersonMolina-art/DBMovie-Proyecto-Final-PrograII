package com.example.dbmovie.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.dbmovie.data.local.entity.MovieEntenty;

import java.util.List;

public interface MovieDao {

    @Query("SELECT * FROM movies")
    LiveData<List<MovieEntenty>> loadMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveMovies(List<MovieEntenty> movieEntentyList);
}
