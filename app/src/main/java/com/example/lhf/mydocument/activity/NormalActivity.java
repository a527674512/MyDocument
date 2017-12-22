package com.example.lhf.mydocument.activity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

import com.example.lhf.mydocument.R;
import com.example.lhf.mydocument.adapter.ListviewAdapter;
import com.example.lhf.mydocument.bean.Fruit;
import com.example.lhf.mydocument.customlayout.TitleLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/11/15.
 */

public class NormalActivity extends BaseActivity {

    private String[] data = {"aaa", "bbb", "ccc", "ddd", "eee", "fff", "ggg", "hhh", "iii", "jjj", "kkk"};

    private List<Fruit> fruits = new ArrayList<Fruit>();


    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.normal_layout);

        Button showAlertDialog = findViewById(R.id.show_alertdialog);
        showAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });

        Button showProgressDialog = findViewById(R.id.show_progressdialog);
        showProgressDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressdialog();
            }
        });

        Button startChat = findViewById(R.id.btn_start_chat_activity);
        startChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent().setClass(NormalActivity.this,ChatActivity.class);
                startActivity(intent);
            }
        });

        Button startFragment = findViewById(R.id.btn_start_fragment_activity);
        startFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent().setClass(NormalActivity.this,FragmentActivity.class);
                startActivity(intent);
            }
        });
        Button startBraodcast = findViewById(R.id.btn_start_broadcast_activity);
        startBraodcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent().setClass(NormalActivity.this,BroadCastActivity.class);
                startActivity(intent);
            }
        });

        Button startPhoneList = findViewById(R.id.btn_start_phone_list);
        startPhoneList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent().setClass(NormalActivity.this,PhoneListActivity.class);
                startActivity(intent);
            }
        });
        Button startVolley = findViewById(R.id.btn_start_volley);
        startVolley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent().setClass(NormalActivity.this,VolleyTest.class);
                startActivity(intent);
            }
        });

        TitleLayout titleLayout = findViewById(R.id.title_layout);
        titleLayout.setTitleText("这是一个title");
        titleLayout.setBackText("返回");

        listView = findViewById(R.id.listview);
        initFruit();
        showListView();

    }

    private void initFruit() {
        Fruit a = new Fruit("111", R.drawable.toolbar_home);
        fruits.add(a);
        Fruit b = new Fruit("222", R.drawable.toolbar_home);
        fruits.add(b);
        Fruit c = new Fruit("333", R.drawable.toolbar_home);
        fruits.add(c);
        Fruit d = new Fruit("444", R.drawable.toolbar_home);
        fruits.add(d);
        Fruit e = new Fruit("555", R.drawable.toolbar_home);
        fruits.add(e);
        Fruit f = new Fruit("666", R.drawable.toolbar_home);
        fruits.add(f);
        Fruit g = new Fruit("777", R.drawable.toolbar_home);
        fruits.add(g);
        Fruit h = new Fruit("888", R.drawable.toolbar_home);
        fruits.add(h);
        Fruit i = new Fruit("999", R.drawable.toolbar_home);
        fruits.add(i);
        Fruit j = new Fruit("000", R.drawable.toolbar_home);
        fruits.add(j);

    }

    private void showListView() {
        ListviewAdapter listAdapter = new ListviewAdapter(this, R.layout.list_view_item, fruits);
        listView.setAdapter(listAdapter);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
//        listView.setAdapter(adapter);
    }

    private void showAlertDialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("this is a dialog ");
        dialog.setMessage("important alert");
        dialog.setCancelable(false);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        dialog.show();
    }

    private void showProgressdialog() {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("this is progress dialog");
        progressDialog.setMessage("loading");
        progressDialog.setCancelable(true);
        progressDialog.show();

    }
}
