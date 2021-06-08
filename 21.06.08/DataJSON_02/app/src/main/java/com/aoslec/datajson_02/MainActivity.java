package com.aoslec.datajson_02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "Status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parser();

    }

    private void parser() {
        Log.v(TAG, "parser()");
        /*파일에서 불러오기위함,  서버에서 불러올땐 필요없어*/
        InputStream inputStream = getResources().openRawResource(R.raw.students);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        /*파일에서 불러오기위함,  서버에서 불러올땐 필요없어*/

        StringBuffer stringBuffer = new StringBuffer();
        String line = null;
        //한줄씩 불러오기위함
        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            Log.v(TAG, "StringBuffer" + stringBuffer.toString());

            JSONObject jsonObject = new JSONObject(stringBuffer.toString());


            JSONArray jsonArray = new JSONArray(jsonObject.getString("students_info"));
            for(int i = 0; i<jsonArray.length(); i++){
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);

                String code = jsonObject1.getString("code");
                Log.v(TAG, "code :"+code);
                String name = jsonObject1.getString("name");
                Log.v(TAG, "name :"+name);
                String dept = jsonObject1.getString("dept");
                Log.v(TAG, "dept :"+dept);
                String phone = jsonObject1.getString("phone");
                Log.v(TAG, "phone :"+phone);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) inputStream.close();
                if (inputStreamReader != null) inputStreamReader.close();
                if (bufferedReader != null) bufferedReader.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}