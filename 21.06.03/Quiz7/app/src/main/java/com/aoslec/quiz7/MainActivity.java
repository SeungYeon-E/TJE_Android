package com.aoslec.quiz7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv_01;
    Button btnApple, btnOrange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_01 = findViewById(R.id.tv_01);

        btnApple = findViewById(R.id.apple);
        btnOrange = findViewById(R.id.orange);

        btnApple.setOnClickListener(mClickListener);
        btnOrange.setOnClickListener(mClickListener);


    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.apple:
                    tv_01.setText("APPLE");
                    break;
                case R.id.orange:
                    tv_01.setText("ORANGE");
                    break;
            }
        }
    };
}