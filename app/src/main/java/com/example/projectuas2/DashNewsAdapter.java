package com.example.projectuas2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DashNewsAdapter extends RecyclerView.Adapter<DashNewsAdapter.DashViewHolder>{
    private final ArrayList<News> newsList;
    private final LayoutInflater inflater;

    public DashNewsAdapter(Context context, ArrayList<News> newsList) {
        this.newsList = newsList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public DashNewsAdapter.DashViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        attach layout using inflater
        View view = inflater.inflate(R.layout.item_news, parent,false);
        return new DashViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashViewHolder holder, int position) {
        //        assign variable with news attribute
        News newsItem = newsList.get(position);
        String nTitle = newsItem.getTitle();
        String nCategory = newsItem.getCategory();
        String nAge = Integer.toString(newsItem.getMinimumAge());
        String nContent = newsItem.getContent();
        String nWriter = newsItem.getWriter();
        String nID = newsItem.getId();

//        showing attribute on holder in recycle view
        holder.iNewsTitle.setText(nTitle);
        holder.iNewsCategory.setText(nCategory);
        holder.iNewsAge.setText(nAge);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailNewsIntent = new Intent(view.getContext(),ShowNewsActivity.class);
                detailNewsIntent.putExtra("TITLE",nTitle);
                detailNewsIntent.putExtra("WRITER",nWriter);
                detailNewsIntent.putExtra("CONTENT",nContent);
                detailNewsIntent.putExtra("ID",nID);
                detailNewsIntent.putExtra("AGE",nAge);
                detailNewsIntent.putExtra("CATEGORY",nCategory);
                view.getContext().startActivity(detailNewsIntent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class DashViewHolder extends RecyclerView.ViewHolder {
        TextView iNewsTitle;
        TextView iNewsCategory;
        TextView iNewsAge;

        public DashViewHolder(@NonNull View itemView) {
            super(itemView);
            iNewsTitle = itemView.findViewById(R.id.item_title_news);
            iNewsCategory = itemView.findViewById(R.id.item_category_news);
            iNewsAge = itemView.findViewById(R.id.item_age_news);
        }
    }
}
