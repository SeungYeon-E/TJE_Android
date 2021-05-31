package com.aoslec.calculator02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnSub, btnMul, btnDiv;
    EditText edit1, edit2;
    TextView textResult;
    String result;
    Integer num1, num2;

    //10개 숫자 버튼 배열
    Button[] numButtons = new Button[10];
    //10개 숫자 버튼의 id 값 배열
    Integer[] numBtnIds = {R.id.BtnNum0,R.id.BtnNum1,R.id.BtnNum2,R.id.BtnNum3,R.id.BtnNum4,R.id.BtnNum5,R.id.BtnNum6,R.id.BtnNum7,R.id.BtnNum8,R.id.BtnNum9};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("계산기");

        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);

        edit1 = findViewById(R.id.num1);
        edit2 = findViewById(R.id.num2);

        textResult = findViewById(R.id.textresult);

        btnAdd.setOnClickListener(mClickListener);
        btnSub.setOnClickListener(mClickListener);
        btnMul.setOnClickListener(mClickListener);
        btnDiv.setOnClickListener(mClickListener);

        //숫자 버튼 10개를 대입
        for (int i = 0; i < numBtnIds.length; i++) {
            numButtons[i] = findViewById(numBtnIds[i]);
        }
        //숫자 버튼 10개에 대해서 클릭 이벤트 처리
        for (int i = 0; i < numBtnIds.length; i++) {
            final int index = i;

            numButtons[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //포커스가 되어 있는 에디트 텍스트에 숫자 추가
                    if (edit1.isFocused() == true) {
                        num1 = Integer.parseInt(edit1.getText().toString() + numButtons[index].getText().toString());
                        edit1.setText(Integer.toString(num1));
                    } else if (edit2.isFocused() == true) {
                        num2 = Integer.parseInt(edit2.getText().toString() + numButtons[index].getText().toString());
                        edit2.setText(Integer.toString(num2));
                    } else {
                        Toast.makeText(MainActivity.this, "숫자를 입력하세요!", Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
    }
    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            num1 = Integer.parseInt(edit1.getText().toString());
            num2 = Integer.parseInt(edit2.getText().toString());

            switch (v.getId()) {
                case R.id.btnAdd:
                    result = Integer.toString(num1 + num2);
                    break;
                case R.id.btnSub:
                    result = Integer.toString(num1 - num2);
                    break;
                case R.id.btnMul:
                    result = Integer.toString(num1 * num2);
                    break;
                case R.id.btnDiv:
                    result = Double.toString(num1 / (double) num2);
                    break;
            }
            textResult.setText("계산결과 : " + result);
        }
    };

}