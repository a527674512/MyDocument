package com.example.lhf.mydocument.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;

import com.example.lhf.mydocument.R;

/**
 * Created by lenovo on 2017/11/15.
 */

public class DialogActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_layout);
    }
}
