package com.example.kakaologin.Interface;

import androidx.recyclerview.widget.RecyclerView;

public interface MyButtonClickListener {
    boolean onItemMove(int from_position, int to_position);
    void onItemSwipe(int position);
    void onLeftClick(int position, RecyclerView.ViewHolder viewHolder);
    void onRightClick(int position, RecyclerView.ViewHolder viewHolder);

}
