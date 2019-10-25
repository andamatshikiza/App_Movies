package com.example.movieapp.api;

import com.example.movieapp.Model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface apiService {
    @GET("movies/{sort_criteria}")
    Call<MovieResponse> getAllMovie(
            @Path("sort_criteria") String sort_criteria,
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int page

    );

}
