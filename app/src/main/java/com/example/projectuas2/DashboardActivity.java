package com.example.projectuas2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {
    private ArrayList<News> completeNewsArrayClass = new ArrayList<>();
    private RecyclerView dashRV;
    private ArrayList<News> newsArray = new ArrayList<>();
    private DashNewsAdapter newsAdapter;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("USERNAME");
        String password = bundle.getString("PASSWORD");

//        ambil dari firebase
        for (int i = 1; i < 3; i++) {
            String id = Integer.toString(i);
            databaseReference.child("news").child(id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    completeNewsArrayClass.add(snapshot.getValue(News.class));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        addDashData(username);

        newsAdapter = new DashNewsAdapter(this, newsArray);
        dashRV = findViewById(R.id.dash_news_rv);
        dashRV.setLayoutManager(new LinearLayoutManager(this));
        dashRV.setAdapter(newsAdapter);

        findViewById(R.id.dash_add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this,AddNewsActivity.class);
                intent.putExtra("USERNAME",username);
                intent.putExtra("PASSWORD",password);
                startActivity(intent);
            }
        });
    }

    public void addDashData(String uname) {
        ArrayList<News> completeNewsArray = completeNewsArrayClass;
        for (int i = 0; i <= completeNewsArray.size() - 1; i++) {
            News currentNews = completeNewsArray.get(i);
            if (currentNews.getCategory().equals(uname)) {
                newsArray.add(currentNews);
            } else {
                continue;
            }
        }
    }
    
}