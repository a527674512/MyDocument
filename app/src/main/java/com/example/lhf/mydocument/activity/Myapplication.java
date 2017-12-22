package com.example.lhf.mydocument.activity;

import android.app.Application;
import android.content.Context;

/**
 * Created by lenovo on 2017/9/21.
 */

public class Myapplication extends Application {

        private static Context context;
        @Override
        public void onCreate() {
            context = getApplicationContext();
        }
        public static Context getContext() {
            return context;
    }
}
