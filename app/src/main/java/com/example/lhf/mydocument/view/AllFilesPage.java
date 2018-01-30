package com.example.lhf.mydocument.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.example.lhf.mydocument.R;
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
    public AllFilesPage(Context context ,File[] mCurrentListFiles) {
        super(context);
        mContext = context;
        this.mCurrentListFiles = mCurrentListFiles;
        LayoutInflater.from(context).inflate(R.layout.layout_viewpager_all_files_page,this);
        gridviewAdapter = new GridviewAdapter(mCurrentListFiles ,mContext);
        initView();
        initGridData();
    }

    private void initView(){
        gvAllFileGrid = findViewById(R.id.gv_all_files);
        gvAllFileGrid.setNumColumns(4);

    }

    private void initGridData(){
        gvAllFileGrid.setAdapter(gridviewAdapter);
    }
}
