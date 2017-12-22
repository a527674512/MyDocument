package com.example.lhf.mydocument.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.lhf.mydocument.R;
import com.example.lhf.mydocument.adapter.ChatMsgAdapter;
import com.example.lhf.mydocument.bean.Msg;
import com.example.lhf.mydocument.customlayout.TitleLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/11/15.
 */

public class ChatActivity extends BaseActivity {

    private ChatMsgAdapter chatMsgAdapter;

    private List<Msg> msgs = new ArrayList<Msg>();

    private ListView listView;

    private Button btnSend;

    private EditText etInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_chat);
        initMsg();
        initView();
        showListView();

    }

    private void initView(){
        TitleLayout titleLayout = findViewById(R.id.title_layout);
        titleLayout.setTitleText("这是一个title");
        titleLayout.setBackText("返回");
        etInput = findViewById(R.id.et_input);
        btnSend = findViewById(R.id.btn_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(etInput.getText().toString())){
                    sendMsg();
                }
            }
        });
        listView = findViewById(R.id.lv_chat_content);
    }

    private void sendMsg(){
        Msg msg = new Msg(etInput.getText().toString(),Msg.TYPE_SEND);
        msgs.add(msg);
        chatMsgAdapter.notifyDataSetChanged();
        listView.setSelection(msgs.size());
        etInput.setText("");

    }

    private void showListView() {
        chatMsgAdapter = new ChatMsgAdapter(this, R.layout.item_chat_msg, msgs);
        listView.setAdapter(chatMsgAdapter);
    }

    private void initMsg(){
        Msg msg1 = new Msg("你好",Msg.TYPE_RECEIVED);
        msgs.add(msg1);
        Msg msg2 = new Msg("恩 你好",Msg.TYPE_SEND);
        msgs.add(msg2);
        Msg msg3 = new Msg("嘎哈呢",Msg.TYPE_RECEIVED);
        msgs.add(msg3);
        Msg msg4 = new Msg("不嘎哈",Msg.TYPE_SEND);
        msgs.add(msg4);
        Msg msg5 = new Msg("你瞅啥",Msg.TYPE_RECEIVED);
        msgs.add(msg5);

    }

}
