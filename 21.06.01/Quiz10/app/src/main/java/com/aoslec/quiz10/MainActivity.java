package com.aoslec.quiz10;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox checkBox;
    FrameLayout frameLayout;
    Button button;
    RadioGroup radioGroup;

    RadioButton[] checks;
    //10개 숫자 버튼의 id 값 배열
    Integer[] intarr;

    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("좋아하는 동물 선택하기");

        checkBox = findViewById(R.id.startcheck);
        checkBox.setOnCheckedChangeListener(clickListener);

        frameLayout = findViewById(R.id.flayout);

        button = findViewById(R.id.btn_01);
        button.setOnClickListener(mClickListener);

        //10개 숫자 버튼 배열
        checks = new RadioButton[3];
        //10개 숫자 버튼의 id 값 배열
        intarr = new Integer[]{R.id.dog,R.id.cat,R.id.rabbit};

        for (int i = 0; i < checks.length; i++) {
            checks[i] = findViewById(intarr[i]);
        }

        imageView = findViewById(R.id.imageview_01);

        radioGroup = findViewById(R.id.rbg_01);

    }

    CompoundButton.OnCheckedChangeListener clickListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked == true){
                frameLayout.setVisibility(View.VISIBLE);
            }else {
                frameLayout.setVisibility(View.INVISIBLE);
            }
        }
    };

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String str = "";
            Drawable drw;
            Integer inte = 0;
            for (int i=0;i<checks.length;i++){
                if(checks[i].isChecked() == true){
                    str = Integer.toString(checks[i].getId());
                    /*drw = "R.drawable." + checks[i].getText().toString();*/
                    /*str = checks[i].getId();*/
                    imageView.setImageResource(Integer.parseInt("R.drawable." + str));
                }
            }

            /*switch (radioGroup.getCheckedRadioButtonId()){
                case R.id.RdoDog:
                    imageView.setImageResource(R.drawable.dog);
                    break;
                case R.id.RdoCat:
                    imageView.setImageResource(R.drawable.cat);
                    break;
                case R.id.RdoRabbit:
                    imageView.setImageResource(R.drawable.rabbit);
                    break;
                default:
                    Toast.makeText(getApplicationContext(),"동물을 선택하세요.", Toast.LENGTH_SHORT).show();

            }*/
        }
    };
}