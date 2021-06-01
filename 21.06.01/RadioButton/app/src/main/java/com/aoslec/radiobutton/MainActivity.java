package com.aoslec.radiobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = findViewById(R.id.rg_01);
        radioGroup.setOnCheckedChangeListener(checkedChangeListener);


    }

    RadioGroup.OnCheckedChangeListener checkedChangeListener = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            String str = "";
            RadioButton radioButton = findViewById(checkedId);
            str = radioButton.getText().toString();
            Toast.makeText(MainActivity.this, str + "is checked.", Toast.LENGTH_LONG).show();
        }
    };
}