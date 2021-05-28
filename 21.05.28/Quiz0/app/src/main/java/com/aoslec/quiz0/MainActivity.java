package com.aoslec.quiz0;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button  buttonRed, buttonGreen, buttonBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonRed = findViewById(R.id.btnRed);
        buttonGreen = findViewById(R.id.btnGreen);
        buttonBlue = findViewById(R.id.btnBlue);

        buttonRed.setOnClickListener(onClickListener);
        buttonGreen.setOnClickListener(onClickListener);
        buttonBlue.setOnClickListener(onClickListener);

       /* buttonRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,  "빨간색버튼을 눌렀습니다!", Toast.LENGTH_SHORT).show();//타이핑 다 안해도 된다!!

            }
        });
        buttonGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,  "녹색버튼을 눌렀습니다!", Toast.LENGTH_SHORT).show();//타이핑 다 안해도 된다!!

            }
        });
        buttonBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,  "파란색버튼을 눌렀습니다!", Toast.LENGTH_SHORT).show();//타이핑 다 안해도 된다!!

            }
        });*/


    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String colorString = "";
            switch (v.getId()){
                case R.id.btnRed:
                    colorString = "빨간색";
                    break;
                case R.id.btnGreen:
                    colorString = "녹색";
                    break;
                case R.id.btnBlue:
                    colorString = "파란색";
                    break;
            }
            Toast.makeText(MainActivity.this,  colorString + "버튼을 눌렀습니다!", Toast.LENGTH_SHORT).show();
        }
    };

}