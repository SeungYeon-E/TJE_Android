package com.aoslec.hybrid02;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    WebView webView = null;
    Button btnHello, btnImage1, btnImage2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webview);
        btnHello = findViewById(R.id.btn_hello);
        btnImage1 = findViewById(R.id.btn_image1);
        btnImage2 = findViewById(R.id.btn_image2);

        btnHello.setOnClickListener(onClickListener);
        btnImage1.setOnClickListener(onClickListener);
        btnImage2.setOnClickListener(onClickListener);

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

    }
    //뒤로 가기 눌렀을때 꺼지지 않고 전실행 하는 것
    public void onBackPressed() {
//        super.onBackPressed();
        if(webView.canGoBack()){
            webView.goBack();
        }else {
            finish();
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        String content = "";
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_hello:
                    content = "<H1>Hello world!</H1>";
                    btnPage1Click(content);
//                    webView.loadUrl("http://172.30.1.7:8080/test/Arithmetic.jsp");
                    break;
                case R.id.btn_image1:
                    content = "<img src=\"https://mblogthumb-phinf.pstatic.net/MjAxOTAzMjlfMzcg/MDAxNTUzODI3OTU3NTg1.geQ7whX1F1DWXBeARbVmBfuqZWp_Db152hko56ZQneIg.5fjMBtfCJeBPcwr1CIndT4qQGbepVcgI9skIDJkEDq8g.JPEG.petianbooks/%EA%B0%95%EC%95%84%EC%A7%80_%EA%B0%B8%EC%9A%B0%EB%9A%B1.jpg?type=w800\" alt=\"멍멍이\">";
                    btnPage1Click(content);
//                    webView.loadUrl("http://172.30.1.7:8080/test/Arithmetic.jsp");
                    break;
                case R.id.btn_image2:
                    content = "<img src=\"http://172.30.1.7:8080/test/dog.PNG\" alt=\"멍멍이\" width=\"100%\">";
                    btnPage1Click(content);
//                    webView.loadUrl("http://172.30.1.7:8080/test/Arithmetic.jsp");
                    break;
                default:
                    break;
            }

        }
    };
    public void btnPage1Click(String result){
        String content =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"+
                        "<html><head>"+
                        "<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />"+
                        "<head><body>";
        content += result + "</body></html>";
        webView.loadData(content, "text/html; charset=utf-8", "UTF-8");
    }
}