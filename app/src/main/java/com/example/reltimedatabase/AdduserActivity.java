package com.example.reltimedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AdduserActivity extends AppCompatActivity {

    EditText title,name,city;
    Button add,showsingle,showrealtime;
    TextView display;
    DatabaseReference user;
    FloatingActionButton showdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        showdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdduserActivity.this,postlistActivity.class));
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                push();
            }
        });

        showsingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singletimeshow();
            }
        });

        showrealtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realtimeshow();
            }
        });

    }

    private void realtimeshow() {
        user.child("-MPO6F4dxy3rgLIYc0Hc")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String data = "title : "+snapshot.child("title").getValue(String.class)+"\n"
                                +"name : "+snapshot.child("name").getValue(String.class)+"\n"
                                +"city : "+snapshot.child("city").getValue(String.class);
                        display.setText(data);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void singletimeshow() {

        user.child("-MPO6F4dxy3rgLIYc0Hc")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String data = "title : "+snapshot.child("title").getValue(String.class)+"\n"
                                +"name : "+snapshot.child("name").getValue(String.class)+"\n"
                                +"city : "+snapshot.child("city").getValue(String.class);
                        display.setText(data);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    private void push() {
        HashMap<String,Object> map =new HashMap<>();
        map.put("title",title.getText().toString());
        map.put("name",name.getText().toString());
        map.put("city",city.getText().toString());
        user.push()
                .setValue(map)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(AdduserActivity.this, "complete", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AdduserActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                reset();
                Toast.makeText(AdduserActivity.this, "sucess", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void reset() {
        title.setText("");
        name.setText("");
        city.setText("");
    }

    private void init() {
        title=findViewById(R.id.ettitle);
        name=findViewById(R.id.etname);
        city=findViewById(R.id.etcity);
        add=findViewById(R.id.badd);
        showsingle=findViewById(R.id.bshowone);
        showrealtime=findViewById(R.id.bshowrealtime);
        display=findViewById(R.id.display);
        showdata=findViewById(R.id.showdata);
        user = FirebaseDatabase.getInstance().getReference().child("User");
    }

}