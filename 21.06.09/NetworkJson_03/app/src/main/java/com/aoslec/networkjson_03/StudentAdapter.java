package com.aoslec.networkjson_03;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private Context mContext = null;
    private int layout = 0;
    private ArrayList<JsonStudent> data = null;


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_code;
        public TextView tv_name;
        public TextView tv_dept;
        public TextView tv_phone;
        public WebView webView;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_code = itemView.findViewById(R.id.tv_code);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_dept = itemView.findViewById(R.id.tv_dept);
            tv_phone = itemView.findViewById(R.id.tv_phone);
            webView = itemView.findViewById(R.id.webview);

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
    public StudentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }
    //뷰홀더 쓰는거
    @Override
    public void onBindViewHolder(StudentAdapter.ViewHolder holder, int position) {
        holder.tv_code.setText("Code : " + data.get(position).getCode());
        holder.tv_name.setText("Name : " + data.get(position).getName());
        holder.tv_dept.setText("Dept : " + data.get(position).getDept());
        holder.tv_phone.setText("Phone : " + data.get(position).getPhone());
        holder.webView.loadData(htmlData(data.get(position).getImage()),"text/html; charset=utf-8", "UTF-8");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public StudentAdapter(Context mContext, int layout, ArrayList<JsonStudent> data) {
        this.mContext = mContext;
        this.layout = layout;
        this.data = data;
    }

    private String htmlData(String image){
        String content =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"+
                        "<html><head>"+
                        "<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />"+
                        "<head><body>"+
                        "<img src=\"http://192.168.2.9:8080/test/";
        content += image + "\" alt=\"멍멍이\" width=\"100%\" height=\"100%\"></body></html>";

        return content;
    }

}
