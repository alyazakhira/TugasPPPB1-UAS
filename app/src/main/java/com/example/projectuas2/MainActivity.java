package com.example.projectuas2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText mUnameEdt, mPassEdt;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = FirebaseDatabase.getInstance().getReference();
                
        mUnameEdt = findViewById(R.id.main_uname_edit);
        mPassEdt = findViewById(R.id.main_pass_edit);

        findViewById(R.id.regis_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.m_login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lUname = mUnameEdt.getText().toString();
                String lPass = mPassEdt.getText().toString();
                authenticate(lUname,lPass);

            }
        });
    }

    private void authenticate(String lUname, String lPass) {
        ArrayList<User> userData = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            String id = Integer.toString(i);
            databaseReference.child("users").child(id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    userData.add(snapshot.getValue(User.class));
                    for (User current : userData) {
                        Log.d("array user", current.getUsername() + current.getPassword());
                        if (current.getUsername().equals(lUname) && current.getPassword().equals(lPass)){
                            Toast.makeText(MainActivity.this, "Login Success!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this,ChooseCatActivity.class);
                            intent.putExtra("USERNAME", current.getUsername());
                            intent.putExtra("PASSWORD",current.getPassword());
                            startActivity(intent);
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


    }
}