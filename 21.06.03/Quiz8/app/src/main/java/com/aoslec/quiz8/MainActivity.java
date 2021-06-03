package com.aoslec.quiz8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button small, large;
    TextView textView;
    int size = 20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        small = findViewById(R.id.small);
        large = findViewById(R.id.large);

        textView = findViewById(R.id.text01);

        small.setOnClickListener(mClickListener);
        large.setOnClickListener(mClickListener);

        textView.setTextSize(size);

    }
    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.small:
                    size -= 5;
                    textView.setTextSize(size);
                    break;
                case R.id.large:
                    size += 5;
                    textView.setTextSize(size);
                    break;
            }
        }
    };
}