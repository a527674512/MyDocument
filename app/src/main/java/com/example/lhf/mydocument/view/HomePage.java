package com.example.lhf.mydocument.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lhf.mydocument.R;

/**
 * Created by lenovo on 2018/1/30.
 */

public class HomePage extends LinearLayout implements View.OnClickListener{

    private Context mContext;
    private Button mBtnLog;

    public HomePage(Context context) {
        super(context);
        mContext = context;
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_viewpager_home_page, this);
        initView();
    }

    private void initView(){
        mBtnLog = findViewById(R.id.btn_1);
        mBtnLog.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        Toast.makeText(mContext,"dsd ",Toast.LENGTH_SHORT).show();
        switch (view.getId()) {
            case R.id.btn_1:
                break;
            case R.id.btn_2:
                break;
            case R.id.btn_3:
                break;
            case R.id.btn_4:
                break;
            case R.id.btn_5:
                break;
            case R.id.btn_6:
                break;
            case R.id.btn_7:
                break;
            case R.id.btn_8:
                break;
            case R.id.btn_9:
                break;
            case R.id.btn_10:
                break;
            case R.id.btn_11:
                break;
            case R.id.btn_12:
                break;
            case R.id.btn_13:
                break;
            case R.id.btn_14:
                break;
            case R.id.btn_15:
                break;
            case R.id.btn_16:
                break;
            case R.id.btn_17:
                break;
            case R.id.btn_18:
                break;
            case R.id.tv_open_close:

                break;

            default:
                break;
        }
    }
}
