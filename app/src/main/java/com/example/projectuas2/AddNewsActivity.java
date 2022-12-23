package com.example.projectuas2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewsActivity extends AppCompatActivity {
    EditText aIdEdt, aTiEdt, aCatEdt, aAgeEdt, aConEdt;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("USERNAME");
        String password = bundle.getString("PASSWORD");

        aIdEdt = findViewById(R.id.addn_id_edt);
        aTiEdt = findViewById(R.id.addn_title_edt);
        aCatEdt = findViewById(R.id.addn_cat_edt);
        aAgeEdt = findViewById(R.id.addn_age_edt);
        aConEdt = findViewById(R.id.addn_content_edt);

        findViewById(R.id.addn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                News addData = new News();
                String id = aIdEdt.getText().toString();
                String title = aTiEdt.getText().toString();
                String category = aCatEdt.getText().toString();
                int age = Integer.parseInt(aAgeEdt.getText().toString());
                String writer = username;
                String content = aConEdt.getText().toString();

                addData.setId(id);
                addData.setCategory(category);
                addData.setWriter(writer);
                addData.setContent(content);
                addData.setMinimumAge(age);
                addData.setTitle(title);
                databaseReference.child("news").child(id).setValue(addData);
                Toast.makeText(AddNewsActivity.this, "News added successfully!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}