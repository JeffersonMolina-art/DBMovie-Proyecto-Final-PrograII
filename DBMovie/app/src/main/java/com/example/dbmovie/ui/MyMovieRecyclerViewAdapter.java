package com.example.dbmovie.ui;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dbmovie.R;
import com.example.dbmovie.data.local.entity.MovieEntenty;
import com.example.dbmovie.data.remote.ApiConstants;


import java.util.List;


public class MyMovieRecyclerViewAdapter extends RecyclerView.Adapter<MyMovieRecyclerViewAdapter.ViewHolder> {

    private final List<MovieEntenty> mValues;
    Context ctx;

    public MyMovieRecyclerViewAdapter(Context context, List<MovieEntenty> items) {
        ctx = context;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        Glide.with(ctx)
                .load(ApiConstants.IMAGE_API_PREFIX + holder.mItem.getPosterPath())
                .into(holder.imageViewCover);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView imageViewCover;
        public MovieEntenty mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            imageViewCover = view.findViewById(R.id.imageViewCover);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}