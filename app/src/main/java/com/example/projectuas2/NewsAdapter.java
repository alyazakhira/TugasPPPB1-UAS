package com.example.projectuas2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
    private final ArrayList<News> newsList;
    private final LayoutInflater inflater;

    public NewsAdapter(Context context, ArrayList<News> newsList) {
        this.newsList = newsList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        attach layout using inflater
        View view = inflater.inflate(R.layout.item_news, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        assign variable with news attribute
        News newsItem = newsList.get(position);
        String nTitle = newsItem.getTitle();
        String nCategory = newsItem.getCategory();
        String nAge = Integer.toString(newsItem.getMinimumAge());
        String nContent = newsItem.getContent();
        String nWriter = newsItem.getWriter();
        String nId = newsItem.getId();

//        showing attribute on holder in recycle view
        holder.iNewsTitle.setText(nTitle);
        holder.iNewsCategory.setText(nCategory);
        holder.iNewsAge.setText(nAge);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailNewsIntent = new Intent(view.getContext(),DetailNewsActivity.class);
                detailNewsIntent.putExtra("TITLE",nTitle);
                detailNewsIntent.putExtra("WRITER",nWriter);
                detailNewsIntent.putExtra("CONTENT",nContent);
                view.getContext().startActivity(detailNewsIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView iNewsTitle;
        TextView iNewsCategory;
        TextView iNewsAge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iNewsTitle = itemView.findViewById(R.id.item_title_news);
            iNewsCategory = itemView.findViewById(R.id.item_category_news);
            iNewsAge = itemView.findViewById(R.id.item_age_news);
        }
    }
}

