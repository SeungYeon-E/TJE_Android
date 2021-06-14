package com.example.kakaologin.Activity;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kakaologin.Bean.Friends;
import com.example.kakaologin.NetworkTask.NetworkTask;
import com.example.kakaologin.R;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {

    String urlAddr = null;
    String sId, sName, sPhone, sAddress, sEmail, sImage, macIP, userId;

    EditText eName, ePhone, eAddress, eEmail;
    Button btnInsert;

    androidx.appcompat.widget.Toolbar toolbar;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        toolbar = findViewById(R.id.update_toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);//기본 제목을 없애줍니다.
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        sId = intent.getStringExtra("id");
        sName = intent.getStringExtra("name");
        sPhone = intent.getStringExtra("phone");
        sAddress = intent.getStringExtra("address");
        sEmail = intent.getStringExtra("email");
        sImage = intent.getStringExtra("image");
        macIP = intent.getStringExtra("macIP");
        intent.putExtra("macIP",macIP);
        urlAddr = "http://" + macIP + ":8080/phonebook/phonebookUpdateReturn.jsp?";
        Log.v("message", "ma"+macIP);

        eName = findViewById(R.id.update_name);
        ePhone = findViewById(R.id.update_phone);
        eAddress = findViewById(R.id.update_address);
        eEmail = findViewById(R.id.update_email);

        //입력시 자리수 제한
        eName.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
        ePhone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});

        eName.setText(sName);
        ePhone.setText(sPhone);
        eAddress.setText(sAddress);
        eEmail.setText(sEmail);


    }

    private String connectInsertData(){
        String result = null;
        try {
            NetworkTask networkTask = new NetworkTask(UpdateActivity.this, urlAddr, "update");
            Object obj = networkTask.execute().get();
            result = (String) obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
        //잘끝났으면 1 아니면 에러
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_update, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()){
            case R.id.update_save:
                sName = eName.getText().toString();
                sPhone = ePhone.getText().toString();
                sAddress = eAddress.getText().toString();
                sEmail = eEmail.getText().toString();

                urlAddr = urlAddr + "name=" + sName + "&phone=" + sPhone + "&address=" + sAddress + "&email=" + sEmail + "&id=" + sId;

                Log.v("message", "urlAddr="+urlAddr);

                String result = connectInsertData();

                Log.v("message", "result="+result);

                if(result.equals("1")){
                    Toast.makeText(UpdateActivity.this, "수정되었습니다.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UpdateActivity.this, "입력이 실패 하였습니다.", Toast.LENGTH_SHORT).show();
                }
                finish();
                break;
            case android.R.id.home:
                //select back button
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}