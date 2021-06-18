package com.example.kakaologin.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.InputFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.kakaologin.NetworkTask.ImageUploader;
import com.example.kakaologin.NetworkTask.NetworkTask;
import com.example.kakaologin.R;
import com.example.kakaologin.common.CommonInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertActivity extends AppCompatActivity {

    final static String TAG = "MainActivity";
    String urlAddr = null;

    String sName, sPhone, sAddress, sEmail, sImage;

    EditText eName, ePhone, eAddress, eEmail;
    ImageView imageView;

    androidx.appcompat.widget.Toolbar toolbar;
    ActionBar actionBar;

    String devicePath = Environment.getDataDirectory().getAbsolutePath() + "/data/com.example.kakaologin/";

    private final int REQ_CODE_SELECT_IMAGE = 300; // Gallery Return Code
    private String img_path = null; // 최종 file name
    private String f_ext = null;    // 최종 file extension
    File tempSelectFile;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        ActivityCompat.requestPermissions(InsertActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        toolbar = findViewById(R.id.insert_toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);//기본 제목을 없애줍니다.
        actionBar.setDisplayHomeAsUpEnabled(true);

        urlAddr = "http://" + CommonInfo.hostIP + ":8080/phonebook/phonebookInsertReturn.jsp?";

        eName = findViewById(R.id.insert_name);
        ePhone = findViewById(R.id.insert_phone);
        eAddress = findViewById(R.id.insert_address);
        eEmail = findViewById(R.id.insert_email);
        imageView = findViewById(R.id.insert_image);

        //입력시 자리수 제한
        eName.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
        ePhone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});

//        btnInsert = findViewById(R.id.insert_btn);
        imageView.setOnClickListener(onClickListener);

    }

    private String connectInsertData(){
        String result = null;
        try {
            NetworkTask networkTask = new NetworkTask(InsertActivity.this, urlAddr, "insert");
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
        menuInflater.inflate(R.menu.menu_insert, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.insert_save:
                sName = eName.getText().toString();
                sPhone = ePhone.getText().toString();
                sAddress = eAddress.getText().toString();
                sEmail = eEmail.getText().toString();

                urlAddr = urlAddr + "name=" + sName + "&phone=" + sPhone + "&address=" + sAddress + "&email=" + sEmail + "&userid=" + MainActivity.userid;

                Log.v("message", "urlAddr="+urlAddr);

                String result = connectInsertData();

                Log.v("message", "result="+result);

                if(result.equals("1")){
                    Toast.makeText(InsertActivity.this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(InsertActivity.this, "입력이 실패 하였습니다.", Toast.LENGTH_SHORT).show();
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
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            urlAddr = "http://"+CommonInfo.hostIP+":8080/phonebook/multipartRequest.jsp";



            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
            intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);

            ImageUploader imageUploader = new ImageUploader(InsertActivity.this, imageView, img_path, urlAddr);
            try {
                Integer result = imageUploader.execute(100).get();
                switch (result) {
                    case 1:
                        Toast.makeText(InsertActivity.this, "Success!", Toast.LENGTH_SHORT).show();

                        File file = new File(img_path);
                        file.delete();
                        break;
                    case 0:
                        Toast.makeText(InsertActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.v(TAG, "Data :" + String.valueOf(data));
        urlAddr = "http://"+CommonInfo.hostIP+":8080/phonebook/multipartRequest.jsp";
        if (requestCode == REQ_CODE_SELECT_IMAGE && resultCode == Activity.RESULT_OK) {
            try {
                //이미지의 URI를 얻어 경로값으로 반환.
                img_path = getImagePathToUri(data.getData());
                Log.v(TAG, "image path :" + img_path);
                Log.v(TAG, "Data :" +String.valueOf(data.getData()));

                //이미지를 비트맵형식으로 반환
                Bitmap image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());

                //image_bitmap 으로 받아온 이미지의 사이즈를 임의적으로 조절함. width: 400 , height: 300
                Bitmap image_bitmap_copy = Bitmap.createScaledBitmap(image_bitmap, 400, 300, true);
                imageView.setImageBitmap(image_bitmap_copy);

                // 파일 이름 및 경로 바꾸기(임시 저장, 경로는 임의로 지정 가능)
                String date = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());
                String imageName = date + "." + f_ext;
                tempSelectFile = new File(devicePath , imageName);
                OutputStream out = new FileOutputStream(tempSelectFile);
                image_bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

                // 임시 파일 경로로 위의 img_path 재정의
                img_path = devicePath + imageName;
                Log.v(TAG,"fileName :" + img_path);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
        ImageUploader imageUploader = new ImageUploader(InsertActivity.this, imageView, img_path, urlAddr);
        try {
            Integer result = imageUploader.execute(100).get();
            switch (result) {
                case 1:
                    Toast.makeText(InsertActivity.this, "Success!", Toast.LENGTH_SHORT).show();

                    File file = new File(img_path);
                    file.delete();
                    break;
                case 0:
                    Toast.makeText(InsertActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getImagePathToUri(Uri data) {

        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();

        //이미지의 경로 값
        String imgPath = cursor.getString(column_index);
        Log.v(TAG, "Image Path :" + imgPath);

        //이미지의 이름 값
        String imgName = imgPath.substring(imgPath.lastIndexOf("/") + 1);

        // 확장자 명 저장
        f_ext = imgPath.substring(imgPath.length()-3, imgPath.length());

        return imgPath;
    }
}