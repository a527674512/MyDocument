package com.example.lhf.mydocument.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by lenovo on 2017/9/21.
 */

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCollector.addActivity(this);

        Log.e("current activity","add"+ "    "+getClass().getSimpleName());
    }

    public static BaseActivity instance;
    public static BaseActivity getInstance(){
        if(instance == null){
            instance = new BaseActivity();
        }
        return instance;
    }

    public Context getContext(){
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("current activity","remove"+ "    "+getClass().getSimpleName());
        ActivityCollector.removeActivity(this);
    }

    public void showToast(String toast){

        Toast.makeText(this,toast,Toast.LENGTH_SHORT).show();

    }

    public void finishAll(){
        ActivityCollector.finishAll();
    }


}
