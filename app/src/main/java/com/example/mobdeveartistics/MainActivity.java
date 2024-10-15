package com.example.mobdeveartistics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.d("MainActivity", "LayoutManager set on RecyclerView");

        ArrayList<Data> dataList = DataGenerator.generateData();
        Log.d("MainActivity", "DataList size: " + dataList.size());
        adapter = new MyAdapter(dataList);
        recyclerView.setAdapter(adapter);
        Log.d("MainActivity", "Adapter set on RecyclerView");
        adapter.notifyDataSetChanged();
        Log.d("MainActivity", "Data added to RecyclerView");
    }
}