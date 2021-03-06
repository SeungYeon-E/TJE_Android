package com.example.kakaologin.Swife;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kakaologin.Activity.DeleteActivity;
import com.example.kakaologin.Interface.MyButtonClickListener;

import org.jetbrains.annotations.NotNull;


public class MySwipeHelper extends ItemTouchHelper.Callback {

    enum ButtonsState{ GONE, LEFT_VISIBLE, RIGHT_VISIBLE }

    private MyButtonClickListener listener;
//    private boolean swipeBack = false;
//    private ButtonsState buttonsShowedState = ButtonsState.GONE;
//    private static final float buttonWidth = 115;
//    private RectF buttonInstance = null;
//    private RecyclerView.ViewHolder currenrtItemViewHolder = null;

    public MySwipeHelper(MyButtonClickListener listener) {
        this.listener = listener;
    }
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
//        int drag_flags = ItemTouchHelper.UP|ItemTouchHelper.DOWN;
        int swipe_flags = ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
        return makeMovementFlags(0,swipe_flags);
    }

    @Override
    public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        if (direction == ItemTouchHelper.LEFT) {
            listener.onLeftSwipe(position);
        } else if (direction == ItemTouchHelper.RIGHT){
            listener.onRightSwipe(position);
        }
    }

    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        Paint p = new Paint();
        Bitmap icon;
        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
            final View itemView = viewHolder.itemView;
            float height = (float) itemView.getBottom() - (float) itemView.getTop();
            float width = height / 6;
            if (dX > 0) {
                //???????????? ????????? ????????????
                p.setColor(Color.parseColor("#70FFEB3B"));
                RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX, (float) itemView.getBottom());
                c.drawRect(background, p);
                icon = BitmapFactory.decodeResource(itemView.getResources(), android.R.drawable.btn_star_big_on);
                RectF icon_dest = new RectF((float) itemView.getLeft() + width, (float) itemView.getTop() + width, (float) itemView.getLeft() + 5 * width, (float) itemView.getBottom() - width);
                c.drawBitmap(icon, null, icon_dest, p);
            } else {
                //??????????????? ?????? ????????????
                p.setColor(Color.parseColor("#70D32F2F"));
                RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
                c.drawRect(background,p);
                icon = BitmapFactory.decodeResource(itemView.getResources(), android.R.drawable.ic_input_delete);
                RectF icon_dest = new RectF((float) itemView.getRight() - 5 * width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
                c.drawBitmap(icon,null,icon_dest,p);
            }

//            View itemView = viewHolder.itemView;
//            float height = (float) itemView.getBottom() - (float) itemView.getTop();
//            float width = height / 3;
//            p.setColor(Color.parseColor("#50D32F2F"));
//            RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
//            c.drawRect(background,p);
//            icon = BitmapFactory.decodeResource(itemView.getResources(), android.R.drawable.ic_menu_delete);
//            RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
//            c.drawBitmap(icon,null,icon_dest,p);
//        }else{
//            View itemView = viewHolder.itemView;
//            float height = (float) itemView.getBottom() - (float) itemView.getTop();
//            float width = height / 3;
//            p.setColor(Color.parseColor("#fffff"));
//            RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
//            c.drawRect(background,p);
//            icon = BitmapFactory.decodeResource(itemView.getResources(), android.R.drawable.ic_menu_delete);
//            RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
//            c.drawBitmap(icon,null,icon_dest,p);
//        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
    //???????????? ??????????????? ????????????????????? ?????? ????????? ???????????? ???????????? ??????
//    @Override
//    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//        //???????????? ???????????? ???????????? ????????? ???????????? ????????? ??????????????? ????????? ??????
//        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
//            if(buttonsShowedState != ButtonsState.GONE){
//                if(buttonsShowedState == ButtonsState.LEFT_VISIBLE) dX = Math.max(dX, buttonWidth);
//                if(buttonsShowedState == ButtonsState.RIGHT_VISIBLE) dX = Math.min(dX, -buttonWidth);
//                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//
//            }else{
//                setTouchListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//            }
//
//            if(buttonsShowedState == ButtonsState.GONE){
//                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//            }
//
//        }
//        currenrtItemViewHolder = viewHolder;
//
//        //????????? ???????????? ??????
//        drawButtons(c, currenrtItemViewHolder);
//    }
//    private void drawButtons(Canvas c, RecyclerView.ViewHolder viewHolder){
//
//        float buttonWidthWithOutPadding = buttonWidth - 10;
//        float corners = 5;
//
//        View itemView = viewHolder.itemView;
//        Paint p = new Paint();
//
//        buttonInstance = null;
//
//
//        //??????????????? ???????????? ????????? (????????? ????????? ???????????? ??? ??????)
//        if(buttonsShowedState == ButtonsState.LEFT_VISIBLE){
//            RectF leftButton = new RectF(itemView.getLeft() + 10, itemView.getTop() + 10, itemView.getLeft() + buttonWidthWithOutPadding,
//                    itemView.getBottom() - 10);
//            p.setColor(Color.BLUE);
//            c.drawRoundRect(leftButton, corners, corners, p);
//            drawText("??????", c, leftButton, p);
//            buttonInstance = leftButton;
//
//            //???????????? ???????????? ????????? (???????????? ????????? ???????????? ??? ??????)
//        }else if(buttonsShowedState == ButtonsState.RIGHT_VISIBLE){
//            RectF rightButton = new RectF(itemView.getRight() - buttonWidthWithOutPadding, itemView.getTop() + 10, itemView.getRight() -10,
//                    itemView.getBottom() - 10);
//            p.setColor(Color.RED);
//            c.drawRoundRect(rightButton, corners, corners, p);
//            drawText("??????", c, rightButton, p);
//
//            buttonInstance = rightButton;
//        }
//
//    }
//
//    //????????? ????????? ????????????
//    private void drawText(String text, Canvas c, RectF button, Paint p){
//        float textSize = 25;
//        p.setColor(Color.WHITE);
//        p.setAntiAlias(true);
//        p.setTextSize(textSize);
//
//        float textWidth = p.measureText(text);
//        c.drawText(text, button.centerX() - (textWidth/2), button.centerY() + (textSize/2), p);
//    }
//
//    @Override
//    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
//        if(swipeBack){
//            swipeBack = false;
//            return 0;
//        }
//        return super.convertToAbsoluteDirection(flags, layoutDirection);
//    }

//
//    private void setTouchListener(final Canvas c, final RecyclerView recyclerView,
//                                  final RecyclerView.ViewHolder viewHolder,
//                                  final float dX, final float dY, final int actionState,
//                                  final boolean isCurrentlyActive){
//        recyclerView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                swipeBack = event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP;
//                if(swipeBack){
//                    if(dX <-buttonWidth) buttonsShowedState = ButtonsState.RIGHT_VISIBLE;
//
//                    else if(dX > buttonWidth) buttonsShowedState = ButtonsState.LEFT_VISIBLE;
//
//
//
//
//                    if(buttonsShowedState != ButtonsState.GONE){
//                        setTouchDownListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//                        setItemsClickable(recyclerView, false);
//                    }
//                }
//                return false;
//            }
//        });
//    }
//
//    private void setTouchDownListener(final Canvas c, final RecyclerView recyclerView
//            , final RecyclerView.ViewHolder viewHolder, final float dX, final float dY
//            , final int actionState, final boolean isCurrentlyActive){
//        recyclerView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction() == MotionEvent.ACTION_DOWN){
//                    setTouchUpListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//                }
//                return false;
//            }
//        });
//    }
//
//    private void setTouchUpListener(final Canvas c, final RecyclerView recyclerView
//            , final RecyclerView.ViewHolder viewHolder, final float dX, final float dY
//            , final int actionState, final boolean isCurrentlyActive){
//
//        recyclerView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                MySwipeHelper.super.onChildDraw(c, recyclerView, viewHolder, 0F, dY, actionState, isCurrentlyActive);
//                recyclerView.setOnTouchListener(new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View v, MotionEvent event) {
//                        return false;
//                    }
//                });
//
//                setItemsClickable(recyclerView, true);
//                swipeBack = false;
//
//                if(listener != null && buttonInstance != null && buttonInstance.contains(event.getX(), event.getY())){
//                    if(buttonsShowedState == ButtonsState.LEFT_VISIBLE){
//                        listener.onLeftClick(viewHolder.getAdapterPosition(), viewHolder);
//                    }else if(buttonsShowedState == ButtonsState.RIGHT_VISIBLE){
//                        listener.onRightClick(viewHolder.getAdapterPosition(), viewHolder);
//                    }
//                }
//
//                buttonsShowedState = ButtonsState.GONE;
//                currenrtItemViewHolder = null;
//                return false;
//            }
//        });
//    }
//
//    private void setItemsClickable(RecyclerView recyclerView, boolean isClickable){
//        for(int i = 0; i < recyclerView.getChildCount(); i++){
//            recyclerView.getChildAt(i).setClickable(isClickable);
//        }
    }
}// MySwipeHelper class..
