package com.example.lhf.mydocument.customlayout;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lhf.mydocument.R;

/**
 * Created by lenovo on 2017/11/16.
 */

public class TitleLayout extends LinearLayout {

    public Button btnBack;

    public TextView tvTitle;
    public TitleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.title_layout,this);

        btnBack = findViewById(R.id.title_back);
        btnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)getContext()).finish();
            }
        });

        tvTitle = findViewById(R.id.title_text);

    }

    public void setTitleText(String title){
        tvTitle.setText(title);
    }
    public String getTitleText(){
        return  tvTitle.getText().toString();
    }

    public void setBackText(String backText){
        btnBack.setText(backText);
    }


}
