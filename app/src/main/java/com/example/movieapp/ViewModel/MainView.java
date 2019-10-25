package com.example.movieapp.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.movieapp.Data.DataFactory;
import com.example.movieapp.Model.Result;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static com.example.movieapp.Constants.Constant.Number_of_threads;
import static com.example.movieapp.Constants.Constant.page_size;
import static com.example.movieapp.Constants.Constant.pageloader;
import static com.example.movieapp.Constants.Constant.prefered_Distance;

public class MainView extends ViewModel {

    private  String sort_criteria;
    private LiveData<PagedList<Result>> listLiveData;

    public MainView(String sort_criteria){
        this.sort_criteria = sort_criteria;

        Executor executor = Executors.newFixedThreadPool(Number_of_threads);
        DataFactory factory = new DataFactory(sort_criteria);
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(pageloader)
                .setPageSize(page_size)
                .setPrefetchDistance(prefered_Distance)
                .build();

        listLiveData = new LivePagedListBuilder<>(factory,config)
                .setFetchExecutor(executor)
                .build();

    }

    public LiveData<PagedList<Result>> getlistLiveData(){
        return listLiveData;

    }


}
