package com.example.lhf.mydocument.activity;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/11/15.
 */

public class ActivityCollector {

    public static List<Activity> activitys = new ArrayList<Activity>();

    public static void addActivity(Activity activity){
        activitys.add(activity);
    }

    public static void removeActivity(Activity activity){
        activitys.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity : activitys){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
