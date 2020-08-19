package com.example.tema1;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.LinkedList;

public class SecondDescendantActivity extends AppCompatActivity {
    public final LinkedList<String> mList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private ButtonListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_descendant);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        for (int i = 1; i <= 10; i++) {
            mList.addLast("" + i);
        }
        mRecyclerView = findViewById(R.id.recylerview);
        mAdapter = new ButtonListAdapter(this, mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void GoToFirst(View view) {
        Intent i = new Intent(this, FirstDescendantActivity.class);
        startActivity(i);
    }
}