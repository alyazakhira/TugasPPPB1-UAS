package com.example.projectuas2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShowNewsActivity extends AppCompatActivity {
    TextView dNewsTitle,dNewsWri,dNewsCon;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_news);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        //        assign data from intent
        Bundle bundle = getIntent().getExtras();
        String sTitle = bundle.getString("TITLE");
        String sWri = bundle.getString("WRITER");
        String sCon = bundle.getString("CONTENT");
        String sCat = bundle.getString("CATEGORY");
        String sId = bundle.getString("ID");
        String sAge = bundle.getString("AGE");

//        assign layout variable
        dNewsTitle = findViewById(R.id.show_news_title);
        dNewsWri = findViewById(R.id.show_writer);
        dNewsCon = findViewById(R.id.show_content);

        findViewById(R.id.show_edit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),EditNewsActivity.class);
                intent.putExtra("TITLE",sTitle);
                intent.putExtra("WRITER",sWri);
                intent.putExtra("CONTENT",sCon);
                intent.putExtra("ID",sId);
                intent.putExtra("AGE",sAge);
                intent.putExtra("CATEGORY",sCat);
                startActivity(intent);
            }
        });

        findViewById(R.id.show_del_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                News deleteData = new News();
                deleteData.setTitle(sTitle);
                deleteData.setMinimumAge(Integer.parseInt(sAge));
                deleteData.setContent(sCon);
                deleteData.setWriter(sWri);
                deleteData.setId(sId);
                deleteData.setCategory(sCat);
                databaseReference.child("users").child(sId).removeValue();
                Toast.makeText(ShowNewsActivity.this, "News deleted successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }
}