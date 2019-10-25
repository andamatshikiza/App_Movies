package com.example.movieapp.ViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainFactory extends ViewModelProvider.NewInstanceFactory {

    private String sort_criteria;

    public MainFactory(String sort_criteria){
        this.sort_criteria = sort_criteria;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainView(sort_criteria);
    }
}
