package com.example.movieapp.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.Model.Result;
import com.example.movieapp.R;
import com.example.movieapp.databinding.VideoItemsBinding;
import com.squareup.picasso.Picasso;

import static com.example.movieapp.Constants.Constant.image_size;
import static com.example.movieapp.Constants.Constant.image_url;

public class PageLisAdapter extends PagedListAdapter<Result,PageLisAdapter.MViewModel> {
    public PageLisAdapter() {
        super(diffCallback);
    }

    @NonNull
    @Override
    public MViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VideoItemsBinding itemsBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.video_items,parent,false
        );
        return new MViewModel(itemsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MViewModel holder, int position) {

      holder.bind(getItem(position));
    }

    public static  DiffUtil.ItemCallback<Result> diffCallback = new DiffUtil.ItemCallback<Result>() {
        @Override
        public boolean areItemsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {
            return oldItem.equals(newItem);
        }
    };

    public  class MViewModel extends RecyclerView.ViewHolder{

        private VideoItemsBinding itemsBinding;
        public MViewModel(VideoItemsBinding videoItemsBinding){
            super(videoItemsBinding.getRoot());

            itemsBinding = videoItemsBinding;
        }

        public void bind(Result item) {
            if (item!=null){
                String thumbUrl = image_url+image_size+item.getBackdropPath();
                Picasso.get().load(thumbUrl).into(itemsBinding.vids);
                itemsBinding.title.setText(item.getTitle());


            }
        }
    }
}
