package com.aoslec.networkjson_03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String urlAddr = "http://172.30.1.8:8080/test/students.json";
    Button button;
    ArrayList<JsonStudent> students;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
//    RecyclerView.Adapter adapter;
    StudentAdapter adapter;

    ItemTouchHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btn_network_con);
        recyclerView = findViewById(R.id.recycler_students);

        button.setOnClickListener(onClickListener);
    }

    final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_network_con:
                    try {
                        NetworkTask networkTask = new NetworkTask(MainActivity.this, urlAddr);
                        Object obj = networkTask.execute().get();
                        students = (ArrayList<JsonStudent>) obj;

                        layoutManager = new LinearLayoutManager(MainActivity.this);
                        recyclerView.setLayoutManager(layoutManager);

                        adapter = new StudentAdapter(MainActivity.this, R.layout.card_layout, students);
                        recyclerView.setAdapter(adapter);

                        button.setText("Results");

                        //ItemTouchHelper 생성
                        helper = new ItemTouchHelper(new MySwipeHelper(adapter));
                        //RecyclerView에 ItemTouchHelper 붙이기
                        helper.attachToRecyclerView(recyclerView);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }
    };
}