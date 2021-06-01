package com.aoslec.checkbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox cb1,cb2,cb3,cb4;
    CheckBox[] checks;
    //10개 숫자 버튼의 id 값 배열
    Integer[] intarr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //10개 숫자 버튼 배열
        checks = new CheckBox[4];
        //10개 숫자 버튼의 id 값 배열
        intarr = new Integer[]{R.id.cb_01,R.id.cb_02,R.id.cb_03,R.id.cb_04};

        for (int i = 0; i < checks.length; i++) {
            checks[i] = findViewById(intarr[i]);
            checks[i].setOnCheckedChangeListener(checkChangeListener);
        }


    }//OnCreate

    CompoundButton.OnCheckedChangeListener checkChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            String str = "";
            //글자값 다합치려고 변수 선언

            for (int i=0;i<checks.length;i++){
                if(checks[i].isChecked() == true){
                    str += checks[i].getText().toString();
                }
            }
            Toast.makeText(MainActivity.this, str + "is checked.", Toast.LENGTH_LONG).show();

            /*switch (buttonView.getId()){
                case R.id.cb_01:
                    if (isChecked == true){
                        str += "운동";

                    }
                    break;
                case R.id.cb_02:
                    if (isChecked == true){
                        str += "요리";

                    }
                    break;
                case R.id.cb_03:
                    if (isChecked == true){
                        str += "독서";

                    }
                    break;
                case R.id.cb_04:
                    if (isChecked == true){
                        str += "여행";

                    }
                    break;
            }
            Toast.makeText(MainActivity.this, str + "is checked.", Toast.LENGTH_LONG).show();*/
        }
    };


}//MainActivity