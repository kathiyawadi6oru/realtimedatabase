package com.example.reltimedatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class postlistActivity extends AppCompatActivity {
    private RecyclerView recycler;
    private postadapter adapter;
    private FloatingActionButton adddata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postlist);

        recycler=findViewById(R.id.recycler);
        adddata=findViewById(R.id.adddata);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<post> options =
                new FirebaseRecyclerOptions.Builder<post>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("User"), post.class)
                        .build();
        adapter = new postadapter(options,this);
        recycler.setAdapter(adapter);

        adddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(postlistActivity.this, AdduserActivity.class));
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}