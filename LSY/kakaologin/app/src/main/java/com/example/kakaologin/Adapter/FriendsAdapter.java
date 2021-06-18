package com.example.kakaologin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kakaologin.Activity.ContentActivity;
import com.example.kakaologin.Activity.DeleteActivity;
import com.example.kakaologin.Activity.InsertActivity;
import com.example.kakaologin.Activity.SelectAllActivity;
import com.example.kakaologin.Bean.Friends;
import com.example.kakaologin.Interface.MyButtonClickListener;
import com.example.kakaologin.NetworkTask.NetworkTask;
import com.example.kakaologin.R;
import com.example.kakaologin.Swife.MySwipeHelper;
import com.example.kakaologin.common.CommonInfo;

import java.util.ArrayList;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder>
        implements MyButtonClickListener {

    private Context mContext = null;
    private int layout = 0;
    private ArrayList<Friends> data = null;
    Intent intent = null;
    String urlAddr;
    String result;

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tv_name;
        public TextView tv_phone;
        public WebView webView;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_phone = itemView.findViewById(R.id.tv_phone);
            webView = itemView.findViewById(R.id.webview);

            webView.setBackgroundColor(0);

//            RecyclerView.Recycler view Click Event -> detail view
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        intent = new Intent(v.getContext(), ContentActivity.class);
                        intent.putExtra("id", data.get(position).getId());
                        intent.putExtra("name", data.get(position).getName());
                        intent.putExtra("phone", data.get(position).getPhone());
                        intent.putExtra("address", data.get(position).getAddress());
                        intent.putExtra("email", data.get(position).getEmail());
                        intent.putExtra("image", data.get(position).getImage());
                        v.getContext().startActivity(intent);
                    }
                }
            });
           /* itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Snackbar.make(v, details[position], Snackbar.LENGTH_LONG).setAction("",null).show();
                }
            });*/
        }
    }

    @NonNull
    @Override
    // 뷰홀더 만드는거
    public FriendsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }
    //뷰홀더 쓰는거
    @Override
    public void onBindViewHolder(FriendsAdapter.ViewHolder holder, int position) {
        holder.tv_name.setText(data.get(position).getName());
        holder.tv_phone.setText(data.get(position).getPhone());
        holder.webView.loadData(htmlData(data.get(position).getImage()),"text/html; charset=utf-8", "UTF-8");
        Log.v("message", data.get(position).getPhone());
    }

    private String htmlData(String image){
        String content =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"+
                        "<html><head>"+
                        "<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />"+
                        "<head><body>"+
                        "<img src=\"http://"+CommonInfo.hostIP+":8080/phonebook/img/";
        content += image + "\" alt=\"이미지\" width=\"100%\" height=\"100%\"></body></html>";

        return content;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public FriendsAdapter(Context mContext, int layout, ArrayList<Friends> data) {
        this.mContext = mContext;
        this.layout = layout;
        this.data = data;
    }



    public boolean onItemMove(int from_position, int to_position) {
        //이동할 객체 저장
        Friends person = data.get(from_position);
        //이동할 객체 삭제
        data.remove(from_position);
        //이동하고 싶은 position에 추가
        data.add(to_position,person);
        //Adapter에 데이터 이동알림
        notifyItemMoved(from_position,to_position);
        return true;
    }
    @Override
    public void onLeftSwipe(int position) {
        data.remove(position);
        urlAddr = "http://" + CommonInfo.hostIP + ":8080/phonebook/phonebookDeleteReturn.jsp?";
        result = connectInsertData(data.get(position).getId());

        if(result.equals("1")){
            notifyItemRemoved(position);
            Toast.makeText(mContext, "연락처가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(mContext, "삭제이 실패 하였습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRightSwipe(int position) {
//        data.remove(position);
        Log.v("message", "position"+position);
        urlAddr = "http://" + CommonInfo.hostIP + ":8080/phonebook/phonebookFavoriteReturn.jsp?";
        result = connectFavoriteData(data.get(position).getId());

        if(result.equals("1")){
            notifyDataSetChanged();
            Toast.makeText(mContext, "연락처가 즐겨찾기되었습니다.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(mContext, "즐겨찾기 실패 하였습니다.", Toast.LENGTH_SHORT).show();
        }
    }
//    @Override
//    public void onItemSwipe(int position) {
//        data.remove(position);
//
//        String result = connectInsertData(data.get(position).getId());
//
//        if(result.equals("1")){
//            notifyItemRemoved(position);
//            Toast.makeText(mContext, "연락처가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(mContext, "삭제이 실패 하였습니다.", Toast.LENGTH_SHORT).show();
//        }
//
//    }
//
//    @Override
//    public void onLeftClick(int position, RecyclerView.ViewHolder viewHolder) {
//
//    }
//
//    @Override
//    public void onRightClick(int position, RecyclerView.ViewHolder viewHolder) {
//
//    }
    private String connectFavoriteData(String id){
        String result = null;
        String tempAddr = urlAddr + "id=" + id;
        try {
            NetworkTask networkTask = new NetworkTask(mContext, tempAddr, "favorite");
            Object obj = networkTask.execute().get();
            result = (String) obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
        //잘끝났으면 1 아니면 에러
    }
    private String connectInsertData(String id){
        String result = null;
        String tempAddr = urlAddr + "id=" + id;
        try {
            NetworkTask networkTask = new NetworkTask(mContext, tempAddr, "delete");
            Object obj = networkTask.execute().get();
            result = (String) obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
        //잘끝났으면 1 아니면 에러
    }

}
