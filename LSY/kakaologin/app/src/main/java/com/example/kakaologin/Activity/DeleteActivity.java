package com.example.kakaologin.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kakaologin.NetworkTask.NetworkTask;
import com.example.kakaologin.R;

public class DeleteActivity extends AppCompatActivity {

    String urlAddr = null;
    String id, sname, sdept, sphone, macIP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        macIP = intent.getStringExtra("macIP");
        urlAddr = "http://" + macIP + ":8080/phonebook/phonebookDeleteReturn.jsp?";

        urlAddr = urlAddr + "id=" + id;

        String result = connectInsertData();

        if(result.equals("1")){
            Toast.makeText(DeleteActivity.this, "연락처가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(DeleteActivity.this, "삭제이 실패 하였습니다.", Toast.LENGTH_SHORT).show();
        }
        finish();

    }

    private String connectInsertData(){
        String result = null;
        try {
            NetworkTask networkTask = new NetworkTask(DeleteActivity.this, urlAddr, "delete");
            Object obj = networkTask.execute().get();
            result = (String) obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
        //잘끝났으면 1 아니면 에러
    }
}