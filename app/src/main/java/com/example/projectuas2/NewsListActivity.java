package com.example.projectuas2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NewsListActivity extends AppCompatActivity {
    private NewsArray completeNewsArrayClass;
    private RecyclerView recyclerView;
    private ArrayList<News> newsArray = new ArrayList<>();
    private NewsAdapter newsAdapter;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        databaseReference = FirebaseDatabase.getInstance().getReference();

//        get user age and preferred category
        Bundle bundle = getIntent().getExtras();
        String userCategory = bundle.getString("CATEGORY");
        String yearString = bundle.getString("YEAR");
        String username = bundle.getString("USERNAME");
        String password = bundle.getString("PASSWORD");
        int userAge = 2022 - Integer.parseInt(yearString);

//        add data to array with method
        completeNewsArrayClass = new NewsArray();
//        addPreferredNewsData(userAge,userCategory);

//        the recycle view
        newsAdapter = new NewsAdapter(this, newsArray);
        recyclerView = findViewById(R.id.recycleview_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(newsAdapter);

        findViewById(R.id.mode_edt_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),DashboardActivity.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("PASSWORD", password);
                startActivity(intent);
            }
        });
    }

    //    add user-preferred news from news arraylist on News
//    public void addPreferredNewsData(int age, String category){
//        ArrayList <News> completeNewsArray = completeNewsArrayClass.news;
//        for (int i = 0; i <= completeNewsArray.size()-1; i++){
//            News currentNews = completeNewsArray.get(i);
//            if (currentNews.getCategory().equals(category) && currentNews.getMinimumAge() <= age){
//                newsArray.add(currentNews);
//            } else {
//                continue;
//            }
//        }
//    }



}