package com.example.movieapp.Data;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.movieapp.Model.MovieResponse;
import com.example.movieapp.api.APIClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.movieapp.Constants.Constant.APIKEY;
import static com.example.movieapp.Constants.Constant.LANGUAGE;
import static com.example.movieapp.Constants.Constant.PAGE_ONE;
import static com.example.movieapp.Constants.Constant.PREVIOUS_PAGE_ONE;
import static com.example.movieapp.Constants.Constant.PREVIUS_PAGE_TWO;

public class MovieDataSource extends PageKeyedDataSource {

    private String sort_criteria;

    public MovieDataSource(String sort_criteria){
        this.sort_criteria = sort_criteria;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull final LoadInitialCallback callback) {

        APIClient.getInstance().getapiService().getAllMovie(sort_criteria,APIKEY,LANGUAGE,PAGE_ONE)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        if (response.isSuccessful()){
                            callback.onResult(response.body().getResults(),PREVIOUS_PAGE_ONE,PREVIUS_PAGE_TWO);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams params, @NonNull LoadCallback callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams params, @NonNull final LoadCallback callback) {
final int currentPage = (int) params.key;

        APIClient.getInstance().getapiService().getAllMovie(sort_criteria,APIKEY,LANGUAGE,currentPage)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        if (response.isSuccessful()){
                            int nextPage = currentPage+1;
                            callback.onResult(response.body().getResults(),nextPage);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {

                    }
                });
    }
}
