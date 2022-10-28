package com.example.dbmovie.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.dbmovie.app.MyApp;
import com.example.dbmovie.data.local.MovieRoomDatabase;
import com.example.dbmovie.data.local.dao.MovieDao;
import com.example.dbmovie.data.local.entity.MovieEntenty;
import com.example.dbmovie.data.network.NetworkBoundResource;
import com.example.dbmovie.data.network.Resource;
import com.example.dbmovie.data.remote.ApiConstants;
import com.example.dbmovie.data.remote.MovieApiService;
import com.example.dbmovie.data.remote.RequestInterceptor;
import com.example.dbmovie.data.remote.model.MoviesResponse;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {

    private final MovieApiService movieApiService;
    private final MovieDao movieDao;

    public MovieRepository(){
        //Datos locales
        MovieRoomDatabase movieRoomDatabase = Room.databaseBuilder(
                MyApp.getContext(),
                MovieRoomDatabase.class,
                "db_movies"
        ).build();
        movieDao = movieRoomDatabase.getMovieDao();

        //RequestInterceptor: incluir en la cabecera (URL) de la
        //peticion el API_KEY que autoriza al usuario
        OkHttpClient.Builder okHttPClientBuilder = new OkHttpClient.Builder();
        okHttPClientBuilder.addInterceptor(new RequestInterceptor());
        OkHttpClient cliente = okHttPClientBuilder.build();

        //Remote > Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL )
                .client(cliente)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieApiService = retrofit.create(MovieApiService.class);
    }

    public LiveData<Resource<List<MovieEntenty>>> getPopularMovies() {
        //Tipo que devuelve, tipo que devuelve la api con retrofit
        return new NetworkBoundResource<List<MovieEntenty>, MoviesResponse>() {

            @Override
            protected void saveCallResult(@NonNull MoviesResponse item) {
                movieDao.saveMovies(item.getResults());
            }

            @NonNull
            @Override
            protected LiveData<List<MovieEntenty>> loadFromDb() {
                //datos que dispongamos en room base local
                return movieDao.loadMovies();
            }

            @NonNull
            @Override
            protected Call<MoviesResponse> createCall() {
                //obtenemos los datos de la API remota
                return movieApiService.loadPopularMovies();
            }
        }.getAsLiveData();
    }
}
