package com.aoslec.fragmenttest;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class ToolBarFragment extends Fragment {

    EditText editText = null;
    Button button = null;
    SeekBar seekBar = null;

    int seekValue = 10;

    ToolbarListener activityCallback;

    //-------------------------
    //MainActivity와의 통신을 위해 interfase를 사용
    //MainActivity에서는 implements로 사용
    //-------------------------

    public interface ToolbarListener{
        public void onButtonClick(int position, String text);
    }

    //-------------------------
    //fragment에서는 onAttach()가 제일 처음으로 실행
    //-------------------------
    @Override
    public void onAttach(@NonNull Context context) {
        //데이터 보낼때 필요해
        Log.v("Message","onAttach_toolbar");
        super.onAttach(context);
        try{
            activityCallback = (ToolbarListener) context;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v("Message","onCreateView_toolbar");
        View view = inflater.inflate(R.layout.fragment_toolbar, container, false);
        editText = view.findViewById(R.id.f1_edit);
        button = view.findViewById(R.id.f1_button);
        seekBar = view.findViewById(R.id.f1_seek);

        button.setOnClickListener(mClickListener);
        seekBar.setOnSeekBarChangeListener(mSeekBarChangedListener);

        return view;
    }

    SeekBar.OnSeekBarChangeListener mSeekBarChangedListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            Log.v("Message","onProgressChaged_toolbar");
            seekValue = progress;
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            Log.v("Message","onStartTrackingTouch_toolbar");

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            Log.v("Message","onStopTrackingTouch_toolbar");

        }
    };


    //----
    // MainActivity의 onButtonClick()에서 실행
    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.v("Message","onClick_toolbar");
            activityCallback.onButtonClick(seekValue, editText.getText().toString());
        }
    };




}