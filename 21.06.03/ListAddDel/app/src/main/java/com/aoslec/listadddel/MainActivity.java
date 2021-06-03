package com.aoslec.listadddel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> items;
    ArrayAdapter<String> adapter;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<String>();
        items.add("First");
        items.add("Second");
        items.add("Third");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, items);

        list = findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        list.setOnItemClickListener(mItemClickListener);

        findViewById(R.id.add).setOnClickListener(mClickListener);
        findViewById(R.id.del).setOnClickListener(mClickListener);

    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText editText = findViewById(R.id.newItem);
            switch (v.getId()){
                case R.id.add:
                    String text = editText.getText().toString();
                    if(text.length() != 0){
                        items.add(text);
                        editText.setText("");
                        adapter.notifyDataSetChanged();//새롭게 구성이 된다.
                    }
                    break;
                case R.id.del:
                    int id;
                    id = list.getCheckedItemPosition();//몇번째 눌렀는지 알려줘
                    if(id != ListView.INVALID_POSITION){
                        items.remove(id);
                        list.clearChoices();//전에 클릭했던 번호를 기억하기때문에 지워줘야해
                        adapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    };


    AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String messge;
            messge = "Select Item = " + items.get(position);
            Toast.makeText(MainActivity.this,messge,Toast.LENGTH_SHORT).show();

        }
    };
}