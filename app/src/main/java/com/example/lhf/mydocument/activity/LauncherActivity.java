package com.example.lhf.mydocument.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lhf.mydocument.R;

import java.io.File;
import java.util.List;

public class LauncherActivity extends BaseActivity {

    private EditText etAccountName;

    private EditText etPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher);

        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
//        String content = MemoryManager.load(this);
//        if (!TextUtils.isEmpty(content)) {
//            etAccountName.setText(content);
//        }

        SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
        String name = preferences.getString("name","");
        String password = preferences.getString("password","");
        etAccountName.setText(name+"");
        etPassword.setText(password+"");

    }

    private void initView() {
        Button button = findViewById(R.id.jump);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//				if(checkLogin()){
//              文本存储
//              MemoryManager.save(LauncherActivity.this, etAccountName.getText().toString() + "");

                //shareprefence 存储
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("name","tom");
                editor.putString("password","abc");
//                editor.clear()
                editor.commit();
                Intent intent = new Intent().setClass(LauncherActivity.this, MainActivity.class);
                startActivity(intent);

//				}else{
//					showToast("用戶密碼錯誤");
//				}

            }
        });

        etAccountName = findViewById(R.id.et_account_name);
        etPassword = findViewById(R.id.et_password);
    }

    private boolean checkLogin() {

        boolean allowLogin = false;
        String accountName;
        String passWord;
        accountName = etAccountName.getText().toString();
        passWord = etPassword.getText().toString();

        if (accountName.equals("a") && passWord.equals("a")) {
            allowLogin = true;
        }
        return allowLogin;
    }

}
