package com.aoslec.buttonimage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btndog, btncat;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("버튼 클릭 이미지 출력");

        btndog = findViewById(R.id.dog);
        btncat = findViewById(R.id.cat);

        imageView = findViewById(R.id.imageview_01);

        btndog.setOnClickListener(clickListener);
        btncat.setOnClickListener(clickListener);

    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.dog:
                    imageView.setImageResource(R.drawable.dog);
                    break;
                case R.id.cat:
                    imageView.setImageResource(R.drawable.cat);
                    break;

            }
        }
    };

}