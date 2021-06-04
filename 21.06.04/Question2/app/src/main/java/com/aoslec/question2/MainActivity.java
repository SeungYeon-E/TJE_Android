package com.aoslec.question2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int a, b, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btn01);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("질문")
                        .setMessage("좌변을 선택하십시오")
                        .setCancelable(false)
                        .setPositiveButton("4", mClickListener)
                        .setNegativeButton("3", mClickListener)
                        .show();
            }
        });

    }

    DialogInterface.OnClickListener mClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == DialogInterface.BUTTON_POSITIVE){
                a = 4;
            }else {
                a = 3;
            }
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("질문")
                    .setMessage("우변을 선택하십시오")
                    .setCancelable(false)
                    .setPositiveButton("6", nClickListener)
                    .setNegativeButton("5", nClickListener)
                    .show();
        }
    };
    DialogInterface.OnClickListener nClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == DialogInterface.BUTTON_POSITIVE){
                b = 6;
            }else {
                b = 5;
            }
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("질문")
                    .setMessage("어떤 연산을 하시겠습니까?")
                    .setCancelable(false)
                    .setPositiveButton("덧셈", qClickListener)
                    .setNegativeButton("곱셈", qClickListener)
                    .show();
        }
    };
    DialogInterface.OnClickListener qClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == DialogInterface.BUTTON_POSITIVE){
                result =  a + b;
            }else {
                result = a * b;
            }
            TextView textView = findViewById(R.id.result);
            textView.setText("연산결과 :" + result);
            Toast.makeText(MainActivity.this, "연산을 완료했습니다.", Toast.LENGTH_SHORT).show();
        }
    };
}