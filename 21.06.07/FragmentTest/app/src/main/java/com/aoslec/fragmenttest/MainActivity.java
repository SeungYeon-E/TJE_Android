package com.aoslec.fragmenttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements ToolBarFragment.ToolbarListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("Message","onCreate_main");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onButtonClick(int position, String text) {
        Log.v("Message","onButtonClick_main");
        TextFragment textFragment = (TextFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_2);
        textFragment.changeTextProperties(position, text);
    }
}