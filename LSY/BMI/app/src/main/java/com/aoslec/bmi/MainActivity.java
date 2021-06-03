package com.aoslec.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView text, tv02;
    Button btnResult, btnMain;
    Handler handler = new Handler();
    FrameLayout fLaout1, fLaout2;
    EditText et01;
    EditText et02;
    EditText et03;
    EditText et04;
    EditText et05;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("당신의 BMI");
        // main text Content - BMI 색상 초록색 설정
        text = findViewById(R.id.text_01);

        String content = text.getText().toString();
        SpannableString spannableString = new SpannableString(content);
        String word = "BMI";
        int start = content.indexOf(word);
        int end = start + word.length();
        // 색상바꾸기
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#AD66CEDB")),start,end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // BOLD STYLE
        spannableString.setSpan(new StyleSpan(Typeface.BOLD),start,end,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // font size (1.1배)
        spannableString.setSpan(new RelativeSizeSpan(1.1f),start,end,SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setText(spannableString);

        btnResult = findViewById(R.id.btnresult);
        btnMain = findViewById(R.id.btnmain);

        btnResult.setOnClickListener(mClickListener);
        btnMain.setOnClickListener(mClickListener);

        fLaout1 = findViewById(R.id.prolayout);
        fLaout2 = findViewById(R.id.flaout);

        et01 = findViewById(R.id.et1);
        et02 = findViewById(R.id.et2);
        et03 = findViewById(R.id.et3);
        et04 = findViewById(R.id.et4);
        et05 = findViewById(R.id.et5);

        tv02 = findViewById(R.id.tv02);

    }
    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnresult:
                    String text1 = et01.getText().toString();
                    String text2 = et02.getText().toString();

                    if(text1.length() == 0 || text2.length() == 0 ){
                        Toast.makeText(MainActivity.this, "빈값이 있습니다!", Toast.LENGTH_SHORT).show();
                        break;
                    }else{
                        et03.setText(text1);
                        et04.setText(text2);
                        double iet03 = Double.parseDouble(et01.getText().toString());
                        double iet04 = Double.parseDouble(et02.getText().toString());
                        double BMI = iet04 / (iet03/100.0 * iet03/100.0);
                        et05.setText(String.format("%.2f", BMI));
                        String result;

                        if (BMI >= 30) {
                            result = "비만입니다!";
                        } else if (BMI >= 25 && BMI < 30) {
                            result = "과제충입니다!";
                        } else if (BMI >= 20 && BMI < 25) {
                            result = "정상입니다!";
                        } else {
                            result = "저체중입니다!";
                        }
                        tv02.setText(result);
                        fLaout1.setVisibility(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                fLaout1.setVisibility(View.INVISIBLE);
                                fLaout2.setVisibility(View.VISIBLE);
                            }
                        }, 1000); //딜레이 타임
                        // 조절
                    }
                    break;
                case R.id.btnmain:
                    fLaout1.setVisibility(View.INVISIBLE);
                    fLaout2.setVisibility(View.INVISIBLE);
                    break;
            }
        }
    };
}