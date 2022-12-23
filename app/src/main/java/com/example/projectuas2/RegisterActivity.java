package com.example.projectuas2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    EditText rIdEdt,rUnameEdit,rPassEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        rIdEdt = findViewById(R.id.main_id_edit);
        rUnameEdit = findViewById(R.id.main_uname_edit);
        rPassEdit = findViewById(R.id.main_pass_edit);

        findViewById(R.id.m_signup_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = rIdEdt.getText().toString();
                String uname = rUnameEdit.getText().toString();
                String pass = rPassEdit.getText().toString();

                User user = new User(uname, pass);
                databaseReference.child("users").child(id).setValue(user);

                Toast.makeText(RegisterActivity.this, "New User Registered Successfully!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(view.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }
}