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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kakaologin.Adapter.FriendsAdapter;
import com.example.kakaologin.Bean.Friends;
import com.example.kakaologin.NetworkTask.NetworkTask;
import com.example.kakaologin.R;
import com.example.kakaologin.Swife.MySwipeHelper;
import com.example.kakaologin.common.CommonInfo;
import com.example.kakaologin.login.SessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

import java.util.ArrayList;

public class SelectActivity extends AppCompatActivity {

    String urlAddr = null;
    private ArrayList<Friends> data = null;
    String sName, sPhone, sAddress, sEmail, sImage, macIP, userId;

    EditText eName, ePhone, eAddress, eEmail;

    WebView webView = null;

    androidx.appcompat.widget.Toolbar toolbar;
    ActionBar actionBar;
    CommonInfo commonInfo = new CommonInfo();
    Button logout;
    private MainActivity.SessionCallback sessionCallback;
    Session session;

    Intent intent;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        toolbar = findViewById(R.id.select_toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);//기본 제목을 없애줍니다.
        actionBar.setDisplayHomeAsUpEnabled(true);

        intent = getIntent();
        userId = intent.getStringExtra("userid");
        macIP = commonInfo.hostIP;

        urlAddr = "http://" + macIP + ":8080/phonebook/phonebookSelectReturn.jsp?";

        String result = connectInsertData(userId);

        eName = findViewById(R.id.select_name);
        ePhone = findViewById(R.id.select_phone);
        eAddress = findViewById(R.id.select_address);
        eEmail = findViewById(R.id.select_email);
        webView = findViewById(R.id.selectWebView);
        logout = findViewById(R.id.logout);

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

        logout.setOnClickListener(v -> {
            Log.d(TAG, "onCreate:click ");
            UserManagement.getInstance()
                    .requestLogout(new LogoutResponseCallback() {
                        @Override
                        public void onSessionClosed(ErrorResult errorResult) {
                            super.onSessionClosed(errorResult);
                            Log.d(TAG, "onSessionClosed: "+errorResult.getErrorMessage());

                        }
                        @Override
                        public void onCompleteLogout() {
                            if (sessionCallback != null) {
                                Session.getCurrentSession().removeCallback(sessionCallback);

                            }
                            Log.d(TAG, "onCompleteLogout:logout ");

                            finishAffinity();
                            intent = new Intent(SelectActivity.this, MainActivity.class);
                            startActivity(intent);
                            System.exit(0);

                        }


                    });

        });

    }

    private String connectInsertData(String id){
        String result = null;
        String tempAddr = urlAddr + "userid=" + id;
        try {
            NetworkTask networkTask = new NetworkTask(SelectActivity.this, tempAddr, "select");
            Object obj = networkTask.execute().get();
            result = (String) obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
        //잘끝났으면 1 아니면 에러
    }

    private void htmlData(String image){
        String content =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"+
                        "<html><head>"+
                        "<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />"+
                        "<head><body style='margin:0; padding:0; text-align:center;'>"+
                        "<img src=\"http://172.30.1.8:8080/phonebook/img/";
        content += image + "\" alt=\"이미지\" height=\"100%\"></body></html>";
        webView.loadData(content, "text/html; charset=utf-8", "UTF-8");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_select, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()){
            case R.id.select_save:
                intent = new Intent(SelectActivity.this, UpdateActivity.class);
                intent.putExtra("name", sName);
                intent.putExtra("phone", sPhone);
                intent.putExtra("address", sAddress);
                intent.putExtra("email", sEmail);
                intent.putExtra("image", sImage);
                intent.putExtra("macIP", macIP);
                startActivity(intent);

//                sName = eName.getText().toString();
//                sPhone = ePhone.getText().toString();
//                sAddress = eAddress.getText().toString();
//                sEmail = eEmail.getText().toString();
//
//                urlAddr = urlAddr + "name=" + sName + "&phone=" + sPhone + "&address=" + sAddress + "&email=" + sEmail + "&id=" + sId;
//
//                Log.v("message", "urlAddr="+urlAddr);
//
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