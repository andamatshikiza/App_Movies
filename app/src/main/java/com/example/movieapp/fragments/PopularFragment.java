package com.example.movieapp.fragments;


import android.database.CursorJoiner;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
//import android.arch.lifecycle.ViewModel;
//import android.arch.paging.LivePagedListBuilder;
//import android.arch.paging.PagedList;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.movieapp.Adapter.PageLisAdapter;
import com.example.movieapp.Data.DataFactory;
import com.example.movieapp.Model.Result;
import com.example.movieapp.R;
import com.example.movieapp.ViewModel.MainFactory;
import com.example.movieapp.ViewModel.MainView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private PageLisAdapter adapter;
    private MainView viewmodel;
    private String sort_criteria = "popular";


    public PopularFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_popular2, container, false);

        viewmodel = ViewModelProviders.of(this,new MainFactory(sort_criteria)).get(MainView.class);
        recyclerView = (RecyclerView)view.findViewById(R.id.nav_view);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter  = new PageLisAdapter();

        recyclerView.setAdapter(adapter);

        viewmodel.getlistLiveData().observe(this, new Observer<PagedList<Result>>() {
            @Override
            public void onChanged(PagedList<Result> results) {
                if (results!=null){
                    adapter.submitList(results);
                }
            }
        });

        return view;
    }

}
