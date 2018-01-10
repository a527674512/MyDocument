package com.example.lhf.mydocument.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
        checkPermision();
    }
    private void checkPermision(){
        int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            Activity activty=this;
            ActivityCompat.requestPermissions(activty,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    CODE_FOR_WRITE_PERMISSION);
            return;
        }
    }
    private static int CODE_FOR_WRITE_PERMISSION = 99;
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == CODE_FOR_WRITE_PERMISSION){
            if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    &&grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //用户同意使用write
                showToast(permissions.toString());
            }else{
                //用户不同意，自行处理即可
                finish();
            }
        }
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
////				if(checkLogin()){
////              文本存储
////              MemoryManager.save(LauncherActivity.this, etAccountName.getText().toString() + "");
//
//                //shareprefence 存储
//                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
//                editor.putString("name","tom");
//                editor.putString("password","abc");
////                editor.clear()
//                editor.commit();
//                Intent intent = new Intent().setClass(LauncherActivity.this, MainActivity.class);
//                startActivity(intent);
//
////				}else{
////					showToast("用戶密碼錯誤");
////				}
                call();
            }
        });

        etAccountName = findViewById(R.id.et_account_name);
        etPassword = findViewById(R.id.et_password);
    }

    public void call() {
        //检查权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            //进入到这里代表没有权限.

            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CALL_PHONE)){
                //已经禁止提示了
                Toast.makeText(LauncherActivity.this, "您已禁止该权限，需要重新开启。", Toast.LENGTH_SHORT).show();
            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 100);

            }

        } else {
            showToast("call phone");
        }
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
