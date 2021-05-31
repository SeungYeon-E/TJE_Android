package com.aoslec.calculator01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Double.*;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnSub, btnMul, btnDiv;
    EditText num1, num2;
    TextView textResult;
    String str1, str2;
    Double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("계산기");

        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);

        textResult = findViewById(R.id.textresult);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str1 = num1.getText().toString();
                str2 = num2.getText().toString();
                if(str1.trim().equals("")||str2.trim().equals("")){
                    Toast.makeText(MainActivity.this, "숫자를 입력하세요!", Toast.LENGTH_LONG).show();
                }else{
                    result = Double.parseDouble(str1) + Double.parseDouble(str2);
                    textResult.setText("계산 결과 : " + result.toString());
                }
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str1 = num1.getText().toString();
                str2 = num2.getText().toString();
                if(str1.trim().equals("")||str2.trim().equals("")){
                    Toast.makeText(MainActivity.this, "숫자를 입력하세요!", Toast.LENGTH_LONG).show();
                }else{
                    result = Double.parseDouble(str1) - Double.parseDouble(str2);
                    textResult.setText("계산 결과 : " + result.toString());
                }
            }
        });
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str1 = num1.getText().toString();
                str2 = num2.getText().toString();
                if(str1.trim().equals("")||str2.trim().equals("")){
                    Toast.makeText(MainActivity.this, "숫자를 입력하세요!", Toast.LENGTH_LONG).show();
                }else{
                    result = Double.parseDouble(str1) * Double.parseDouble(str2);
                    textResult.setText("계산 결과 : " + result.toString());
                }
            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str1 = num1.getText().toString();
                str2 = num2.getText().toString();
                if(str1.trim().equals("")||str2.trim().equals("")){
                    Toast.makeText(MainActivity.this, "숫자를 입력하세요!", Toast.LENGTH_LONG).show();
                }else{
                    result = Double.parseDouble(str1) / Double.parseDouble(str2);
                    textResult.setText("계산 결과 : " + result.toString());
                }
            }
        });


    }
}