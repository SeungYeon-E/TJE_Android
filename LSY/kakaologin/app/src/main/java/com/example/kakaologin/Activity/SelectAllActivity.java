package com.example.kakaologin.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kakaologin.Adapter.FriendsAdapter;
import com.example.kakaologin.Bean.Friends;
import com.example.kakaologin.NetworkTask.NetworkTask;
import com.example.kakaologin.R;
import com.example.kakaologin.Swife.MySwipeHelper;
import com.example.kakaologin.common.CommonInfo;

import java.util.ArrayList;

public class SelectAllActivity extends AppCompatActivity {

    String urlAddr = null;
    ArrayList<Friends> members, members1;
    FriendsAdapter adapter;
//    FavoriteFriendsAdapter adapter1;
//    ListView listView;

    androidx.appcompat.widget.Toolbar toolbar;
    ActionBar actionBar;

    RecyclerView recyclerView, frecyclerView;
    RecyclerView.LayoutManager layoutManager, frelayoutManager;
    //    RecyclerView.Adapter adapter;

    ItemTouchHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_all);

        recyclerView = findViewById(R.id.recycler_students);
//        frecyclerView = findViewById(R.id.recycler_favorite);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);//기본 제목을 없애줍니다.
        actionBar.setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.my);

//        SearchView searchView = (SearchView) findViewById(R.id.search_view);
//        search = (String) searchView.getQuery();
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String a_query) {
//                // to do
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String a_newText) {
//                // to do
//                return false;
//            }
//        });
    }

    @Override
    //수정되면 또 실행함 꼭 필요
    protected void onResume() {
        super.onResume();
        connectGetData();
//        connectGetFavoriteData();
    }

    public void connectGetData(){
        try{
            urlAddr = "http://" + CommonInfo.hostIP + ":8080/phonebook/phonebook_query_all.jsp";
            NetworkTask networkTask = new NetworkTask(SelectAllActivity.this, urlAddr, "select");
            Object obj = networkTask.execute().get();
            members = (ArrayList<Friends>) obj;

//            adapter = new StudentAdapter(SelectAllActivity.this, R.layout.student_layout, members);
//            listView.setAdapter(adapter);
//
//            listView.setOnItemClickListener(onItemClickListener);
//            listView.setOnItemLongClickListener(onItemLongClickListener);

            layoutManager = new LinearLayoutManager(SelectAllActivity.this);
            recyclerView.setLayoutManager(layoutManager);

            adapter = new FriendsAdapter(SelectAllActivity.this, R.layout.card_layout, members);
            recyclerView.setAdapter(adapter);

            //ItemTouchHelper 생성
            helper = new ItemTouchHelper(new MySwipeHelper(adapter));
            //RecyclerView에 ItemTouchHelper 붙이기
            helper.attachToRecyclerView(recyclerView);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
//    public void connectGetFavoriteData(){
//        try{
//            urlAddr = "http://" + CommonInfo.hostIP + ":8080/phonebook/phonebook_favorite_query_all.jsp";
//            NetworkTask networkTask = new NetworkTask(SelectAllActivity.this, urlAddr, "selectFavorite");
//            Object obj = networkTask.execute().get();
//            members1 = (ArrayList<Friends>) obj;
//
//            frelayoutManager = new LinearLayoutManager(SelectAllActivity.this);
//            frecyclerView.setLayoutManager(frelayoutManager);
//
//            adapter1 = new FavoriteFriendsAdapter(SelectAllActivity.this, R.layout.card_layout, members1);
//            frecyclerView.setAdapter(adapter1);
//
//            //ItemTouchHelper 생성
//            helper = new ItemTouchHelper(new MySwipeHelper(adapter1));
//            //RecyclerView에 ItemTouchHelper 붙이기
//            helper.attachToRecyclerView(frecyclerView);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
//        Intent intent = null;
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            intent = new Intent(SelectAllActivity.this, UpdateActivity.class);
//            intent.putExtra("id", members.get(position).getId());
//            intent.putExtra("name", members.get(position).getName());
//            intent.putExtra("phone", members.get(position).getPhone());
//            intent.putExtra("macIP", macIP);
//            startActivity(intent);
//        }
//    };
//
//    AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
//        Intent intent = null;
//        @Override
//        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//            intent = new Intent(SelectAllActivity.this, DeleteActivity.class);
//            intent.putExtra("id", members.get(position).getId());
//            intent.putExtra("name", members.get(position).getName());
//            intent.putExtra("phone", members.get(position).getPhone());
//            intent.putExtra("macIP", macIP);
//            startActivity(intent);
//            return true;
//        }
//    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_selectall, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()){

            case R.id.action_menu_02:
                //select account item
                intent = new Intent(SelectAllActivity.this, InsertActivity.class);
                startActivity(intent);
                break;
            case android.R.id.home:
                //select back button
                intent = new Intent(SelectAllActivity.this, SelectActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}