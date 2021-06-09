package com.aoslec.networkjson_02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String urlAddr = "http://192.168.2.9:8080/test/students.json";
    Button button;
    ListView listView;
    ArrayList<JsonStudent> students;
    StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btn_network_con);
        listView = findViewById(R.id.lv_students);

        button.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_network_con:
                    try {
                        NetworkTask networkTask = new NetworkTask(MainActivity.this, urlAddr);
                        Object obj = networkTask.execute().get();
                        students = (ArrayList<JsonStudent>) obj;

                        adapter = new StudentAdapter(MainActivity.this, R.layout.custom_layout, students);
                        listView.setAdapter(adapter);

                        button.setText("Results");

                    }catch (Exception e){
                        e.printStackTrace();
                    }
            }
        }
    };

}