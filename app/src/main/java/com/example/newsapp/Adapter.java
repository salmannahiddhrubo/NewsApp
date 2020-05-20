package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsapp.model.Article;
import com.example.newsapp.model.Headlines;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context context;
    private Headlines headlines;

    public Adapter(Context context, Headlines headlines) {
        this.context = context;
        this.headlines = headlines;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



        holder.tvDate.setText(headlines.getArticles().get(position).getPublishedAt());
        holder.tvSource.setText(headlines.getArticles().get(position).getSource().getName());
        holder.tvTitle.setText(headlines.getArticles().get(position).getTitle());
        String imageUrl =headlines.getArticles().get(position).getUrlToImage();
        Picasso .with(context).load(imageUrl).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return headlines.getArticles().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,tvSource,tvDate;
        ImageView imageView;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSource = itemView.findViewById(R.id.tvSource);
            tvDate = itemView.findViewById(R.id.tvDate);
            imageView = itemView.findViewById(R.id.image);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }



}
