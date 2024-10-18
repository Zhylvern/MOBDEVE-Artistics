package com.example.mobdeveartistics.activities.feed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mobdeveartistics.activities.profile.ProfileActivity;
import com.example.mobdeveartistics.models.Data;
import com.example.mobdeveartistics.models.DataGenerator;
import com.example.mobdeveartistics.R;
import com.example.mobdeveartistics.adapters.FeedAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FeedAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.d("MainActivity", "LayoutManager set on RecyclerView");

        ArrayList<Data> dataList = DataGenerator.generateData();
        Log.d("MainActivity", "DataList size: " + dataList.size());
        adapter = new FeedAdapter(dataList);
        recyclerView.setAdapter(adapter);
        Log.d("MainActivity", "Adapter set on RecyclerView");
        adapter.notifyDataSetChanged();
        Log.d("MainActivity", "Data added to RecyclerView");
    }

    public void onClickProfile(View v) {
        Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(i);
    }
}