package com.example.kakaologin.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kakaologin.Adapter.FriendsAdapter;
import com.example.kakaologin.Bean.Friends;
import com.example.kakaologin.NetworkTask.NetworkTask;
import com.example.kakaologin.R;
import com.example.kakaologin.Swife.MySwipeHelper;
import com.example.kakaologin.common.CommonInfo;

import java.util.ArrayList;

public class ContentActivity extends AppCompatActivity {

    String urlAddr = null;
    ArrayList<Friends> user;
    String sId, sName, sPhone, sAddress, sEmail, sImage, macIP;

    EditText eName, ePhone, eAddress, eEmail;

    WebView webView = null;

    androidx.appcompat.widget.Toolbar toolbar;
    ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        toolbar = findViewById(R.id.content_toolbar);
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

        eName = findViewById(R.id.content_name);
        ePhone = findViewById(R.id.content_phone);
        eAddress = findViewById(R.id.content_address);
        eEmail = findViewById(R.id.content_email);
        webView = findViewById(R.id.contentWebView);

        webView.setBackgroundColor(0);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

        htmlData(sImage);

        eName.setText(sName);
        ePhone.setText(sPhone);
        eAddress.setText(sAddress);
        eEmail.setText(sEmail);

        ePhone.setOnTouchListener(mTouchListener);

    }
    private void htmlData(String image){
        String content =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"+
                        "<html><head>"+
                        "<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />"+
                        "<head><body style='margin:0; padding:0; text-align:center;'>"+
                        "<img src=\"http://"+ CommonInfo.hostIP +":8080/phonebook/img/";
        content += image + "\" alt=\"이미지\" height=\"100%\"></body></html>";
        webView.loadData(content, "text/html; charset=utf-8", "UTF-8");
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_content, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()){
            case R.id.content_save:
                intent = new Intent(ContentActivity.this, UpdateActivity.class);
                intent.putExtra("id", sId);
                intent.putExtra("name", sName);
                intent.putExtra("phone", sPhone);
                intent.putExtra("address", sAddress);
                intent.putExtra("email", sEmail);
                intent.putExtra("image", sImage);
                intent.putExtra("macIP", macIP);
                startActivity(intent);

                finish();
                break;
            case android.R.id.home:
                //select back button
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Intent intent;
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + String.valueOf(ePhone.getText())));
                    startActivity(intent);
                    break;
            }
            return false;
        }
    };

}