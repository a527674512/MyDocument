package com.example.lhf.mydocument.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lhf.mydocument.R;
import com.example.lhf.mydocument.activity.BaseActivity;
import com.example.lhf.mydocument.activity.MainActivity;
import com.example.lhf.mydocument.activity.MemoryManager;
import com.example.lhf.mydocument.adapter.GridviewAdapter;
import com.example.lhf.mydocument.adapter.ViewpagerAdapter;

import java.io.File;

/**
 * Created by lenovo on 2018/1/30.
 */

public class AllFilesPage extends LinearLayout {
    private Context mContext;
    private GridView gvAllFileGrid;
    private GridviewAdapter gridviewAdapter;
    private File[] mCurrentListFiles;
    private File mCurrentDir;
    private boolean isSingleChoice = true;

    public AllFilesPage(Context context ,File mCurrentDir) {
        super(context);
        mContext = context;
        this.mCurrentDir = mCurrentDir;
        this.mCurrentListFiles = MemoryManager.hideFileFilter(mCurrentDir);
        LayoutInflater.from(context).inflate(R.layout.layout_viewpager_all_files_page,this);
        gridviewAdapter = new GridviewAdapter(this.mCurrentListFiles ,mContext);
        initView();
        initGridData();
    }

    private void initView(){
        gvAllFileGrid = findViewById(R.id.gv_all_files);
        gvAllFileGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                File dir = mCurrentListFiles[position];
                if (isSingleChoice) {
//                    if (dir.isDirectory()) {
//                        freshDirectory(dir);
//                    } else {
                    freshDirectory(dir);
//                    }
                } else {
//                    // 多选模式
//                    CheckedTextView text = (CheckedTextView) view
//                            .findViewById(R.id.checked_text_file_name);
//                    if (text.isChecked()) {
//                        mMultiModeFiles[position] = null;
//                        text.setChecked(false);
//                    } else {
//                        mMultiModeFiles[position] = dir;
//                        text.setChecked(true);
//                    }
                }
            }
        });

    }
    private void freshDirectory(File operatedFile) {
       mCurrentListFiles = MemoryManager.hideFileFilter(operatedFile);
       mCurrentDir = operatedFile;
       gridviewAdapter.updataFiles(mCurrentListFiles);
       gridviewAdapter.notifyDataSetChanged();
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_VIEW);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        String type = "*/*";
//        String fileName = operatedFile.getName();
//        type = MemoryManager.getFileType(fileName);
//        if ("*/*".equals(type)) {
//            BaseActivity.getInstance().showToast("不支持的文件格式");
//            return;
//        }
//        intent.setDataAndType(Uri.fromFile(operatedFile), type);
//        startActivity(intent);
    }

    public boolean upLevel() {
        if (MainActivity.SD_CARD.equals(mCurrentDir)) {
            Toast.makeText(mContext,"已经是根目录",Toast.LENGTH_SHORT).show();
            return false;
        } else {
            File parentDir = mCurrentDir.getParentFile();
            freshDirectory(parentDir);
            return true;
        }
    }

    private void initGridData(){
        gvAllFileGrid.setAdapter(gridviewAdapter);
    }
}
