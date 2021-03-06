package com.aoslec.callactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("Message","onCreate_Main");
        call = findViewById(R.id.call);
        call.setOnClickListener(mClickListener);

    }
    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,SubActivity.class);
            startActivity(intent);
        }
    };
    @Override
    protected void onStart() {
        Log.v("Message","onStart_Main");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.v("Message","onResume_Main");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.v("Message","onPause_Main");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.v("Message","onStop_Main");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.v("Message","onDestroy_Main");
        super.onDestroy();
    }
}