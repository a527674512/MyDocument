package com.example.lhf.mydocument.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lhf.mydocument.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/11/29.
 */

public class PhoneListActivity extends BaseActivity{

    private ListView lvPhoneNumberList;

    private List<String> contactsList = new ArrayList<>();

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_phone_list);
        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView(){
        readContacts();
        lvPhoneNumberList = findViewById(R.id.lv_phone_number_list);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contactsList);
        lvPhoneNumberList.setAdapter(adapter);
    }

    private void readContacts(){
        Cursor cursor = null;

        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        try {
            while (cursor.moveToNext()){
                String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)); 
                contactsList.add(displayName + "\n" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
             cursor.close();
            }
        }
    }
}
