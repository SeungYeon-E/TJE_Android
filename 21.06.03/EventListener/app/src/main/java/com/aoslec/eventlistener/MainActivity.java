package com.aoslec.eventlistener;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv_01);

        button = findViewById(R.id.btn_01);

        imageView = findViewById(R.id.image_01);

        textView.setOnTouchListener(onTouchListener);

        button.setOnLongClickListener(onClickListener);

        imageView.setOnTouchListener(onTouchListener);


    }

    View.OnLongClickListener onClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            imageView.setImageResource(R.drawable.mov07);
            return true;
        }
    };
    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (v.getId()){
                case R.id.tv_01:
                    Toast.makeText(MainActivity.this,"Hello World!",Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.image_01:
                    Toast.makeText(MainActivity.this, "겨울왕국을 클릭하셨습니다~",Toast.LENGTH_SHORT).show();
                    return true;
            }

            return false;
        }
    };
}