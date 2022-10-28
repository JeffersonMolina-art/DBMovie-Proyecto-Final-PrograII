package com.example.dbmovie.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.dbmovie.data.local.dao.MovieDao;
import com.example.dbmovie.data.local.entity.MovieEntenty;

@Database(entities = {MovieEntenty.class}, version = 1, exportSchema = false)
public abstract class MovieRoomDatabase extends RoomDatabase {

    public abstract MovieDao getMovieDao();

}
