package com.aoslec.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text1;
    Button btnresult, btnmain;
    Handler handler = new Handler();
    FrameLayout flaout1, flaout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("당신의 BMI");
        // main text Content - BMI 색상 초록색 설정
        text1 = findViewById(R.id.text_01);

        String content = text1.getText().toString();
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
        text1.setText(spannableString);

        btnresult = findViewById(R.id.btnresult);
        btnmain = findViewById(R.id.btnmain);

        btnresult.setOnClickListener(mclickListener);
        btnmain.setOnClickListener(mclickListener);

        flaout1 = findViewById(R.id.prolayout);
        flaout2 = findViewById(R.id.flaout);


    }
    View.OnClickListener mclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnresult:
                    flaout1.setVisibility(View.VISIBLE);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            flaout1.setVisibility(View.INVISIBLE);
                            flaout2.setVisibility(View.VISIBLE);
                        }
                    }, 3000); //딜레이 타임 조절
                    break;
                case R.id.btnmain:
                    flaout1.setVisibility(View.INVISIBLE);
                    flaout2.setVisibility(View.INVISIBLE);
                    break;
            }
        }
    };


}