package com.example.projectuas2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditNewsActivity extends AppCompatActivity {
    EditText eIdEdt, eTiEdt, eCatEdt, eAgeEdt, eConEdt;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_news);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        Bundle bundle = getIntent().getExtras();
        String title = bundle.getString("TITLE");
        String id = bundle.getString("ID");
        String age = bundle.getString("AGE");
        String category = bundle.getString("CATEGORY");
        String writer = bundle.getString("WRITER");
        String content = bundle.getString("CONTENT");

        eIdEdt = findViewById(R.id.up_id_edt);
        eTiEdt = findViewById(R.id.up_title_edt);
        eCatEdt = findViewById(R.id.up_cat_edt);
        eAgeEdt = findViewById(R.id.up_age_edt);
        eConEdt = findViewById(R.id.up_content_edt);

        eIdEdt.setText(id);
        eTiEdt.setText(title);
        eCatEdt.setText(category);
        eAgeEdt.setText(age);
        eConEdt.setText(content);

        findViewById(R.id.up_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                News updateData = new News();
                String id = eIdEdt.getText().toString();
                String title = eTiEdt.getText().toString();
                String category = eCatEdt.getText().toString();
                int age = Integer.parseInt(eAgeEdt.getText().toString());
                String content = eConEdt.getText().toString();

                updateData.setId(id);
                updateData.setCategory(category);
                updateData.setWriter(writer);
                updateData.setContent(content);
                updateData.setMinimumAge(age);
                updateData.setTitle(title);
                databaseReference.child("news").child(id).setValue(updateData);
                Toast.makeText(EditNewsActivity.this, "News updated successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}