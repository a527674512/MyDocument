package com.example.lhf.mydocument.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lhf.mydocument.R;
import com.example.lhf.mydocument.adapter.ViewpagerAdapter;
import com.example.lhf.mydocument.view.AllFilesPage;
import com.example.lhf.mydocument.view.HomePage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, View.OnClickListener {

    private TextView tvTotalSize;
    private TextView tvAvailableSize;

    public static final File SD_CARD = Environment
            .getExternalStorageDirectory();
    private static final String TAG = "FileManager";
    private static final int DIALOG_NO = 0;
    private static final int DIALOG_SEARCH = 1;
    private static final int DIALOG_OPERATION_MENU = 2;
    private static final int DIALOG_RENAME = 3;
    private static final int DIALOG_ABOUT = 4;
    private static final int DIALOG_YES_NO = 5;
    private static final int DIALOG_INFO = 6;
    private static final int DIALOG_SEARCHING = 7;
    private static final int DIALOG_MULTI_MANAGE = 8;
    private static final int DIALOG_MULTI_NEW_FILE = 9;

    protected static final int OPEARTION_OPEN = 0;
    protected static final int OPEARTION_COPY = 1;
    protected static final int OPEARTION_MOVE = 2;
    protected static final int OPEARTION_PARSE = 3;
    protected static final int OPEARTION_RENAME = 4;
    protected static final int OPEARTION_DELETE = 5;
    protected static final int OPEARTION_INFO = 6;

    protected static final int MULTI_MANAGE_COPY = 0;
    protected static final int MULTI_MANAGE_MOVE = 1;
    protected static final int MULTI_MANAGE_RENAME = 2;
    protected static final int MULTI_MANAGE_DELETE = 3;

    private int mSelectedDialog = DIALOG_NO;

    private static final int REQUEST_CODE_SETTING = 0;

    private boolean isMultiManage;
    private boolean isSingleChoice;
    private String mTheme;
    private boolean isHiddenFile;
    private String mViewMode;
    private File mHomeDir;
    private String mOrderBy;

    private File mCurrentDir;
    private File[] mCurrentListFiles;
    private File[] mMultiModeFiles;
    private File[] mMultiModeSelectedFiles;
    private List<File> mSearchedFile;
    private File mSelectedFile;
    private File mCoypFile;

    private List<View> views = new ArrayList<View>();

    private LinearLayout llRest;
    private ListView mListFileShow;
    private GridView mGridFileShow;
    private ViewPager vpMyViewpager;
    private HomePage mHomePage;
    private AllFilesPage mAllFilesPage;

    private ViewpagerAdapter mViewpagerAdapter;

    /**
     * 导航栏显示目录
     */
    private TextView mLocalText;

    /**
     * ListView绑定的数据源Adapter
     */
    private FileAdapter2 mAdapter;

    /**
     * 是否可以退出
     */
    private boolean canExit = false;

    private void loadSharedPreferences() {
        isSingleChoice = true;
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        mTheme = preferences.getString("theme", "defaule");
        isHiddenFile = preferences.getBoolean("hiddenfile", false);
        mViewMode = preferences.getString("viewmode", "viewmode_list");
        String homeDir = preferences.getString("homedir",
                SD_CARD.getAbsolutePath());
        mHomeDir = new File(homeDir);
        mOrderBy = preferences.getString("orderby", "name");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            String tempdata = savedInstanceState.getString("data_key");
        }

        loadSharedPreferences();
        initFileList();
        initView();
        showViewPager();
        updateUI();
        initListAndGridView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(vpMyViewpager.getCurrentItem() == 1 ){
            if(!mAllFilesPage.upLevel()){
                finish();
            }
        }else{
            finish();
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add_itm:

//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("http://www.baidu.com"));
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("10086"));
                Intent intent = new Intent().setClass(this, NormalActivity.class);
                startActivity(intent);
//                showToast("add");
                break;
            case R.id.remove_item:
                Intent intent1 = new Intent().setClass(this, DialogActivity.class);
                startActivity(intent1);
                break;
            default:
        }

        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String tempData = "tempdata";
        outState.putString("data_key", tempData);
    }

    private void initView() {
        tvTotalSize = findViewById(R.id.total_memory);
        tvAvailableSize = findViewById(R.id.available_memory);
        mLocalText = findViewById(R.id.addressbar_local_text);
        llRest = findViewById(R.id.ll_rest);
        vpMyViewpager = findViewById(R.id.vp_myviewpager);
        mHomePage = new HomePage(this);
        mAllFilesPage = new AllFilesPage(this ,mCurrentDir);
        views.add(mHomePage);
        views.add(mAllFilesPage);
    }

    private void updateUI() {
        tvTotalSize.setText("共计" + MemoryManager.getTotalMemorySize());
        tvAvailableSize.setText("可用" + MemoryManager.getSDAvailableSize() + "/");
    }

    private void showViewPager(){
        mViewpagerAdapter = new ViewpagerAdapter(views,this);
        vpMyViewpager.setAdapter(mViewpagerAdapter);

    }


    /**
     * 初始化文件列表
     */
    private void initFileList() {

        if (null != mHomeDir) {
            mCurrentDir = mHomeDir;
        } else {
            mCurrentDir = SD_CARD;
        }

        if (isHiddenFile) {
            // 显示隐藏文件
            mCurrentListFiles = mCurrentDir.listFiles();
        } else {
            mCurrentListFiles = MemoryManager.hideFileFilter(mCurrentDir);
        }

        MemoryManager.sort(mCurrentListFiles, mOrderBy);
        mMultiModeFiles = new File[mCurrentListFiles.length];

    }


    /**
     * 初始化ListView
     */
    private void initListAndGridView() {
        mListFileShow = (ListView) findViewById(R.id.listShow);
        mGridFileShow = (GridView) findViewById(R.id.gridShow);
        mListFileShow.setOnItemClickListener(this);
        mListFileShow.setOnItemLongClickListener(this);
        mGridFileShow.setOnItemClickListener(this);
        mGridFileShow.setOnItemLongClickListener(this);
        showViewMode(isSingleChoice);
        // registerForContextMenu(mListFileShow);
    }

    private void changeChoiceMode() {
        isSingleChoice = !isSingleChoice;
        showViewMode(isSingleChoice);
    }


    private void showViewMode(boolean mode) {
        if ("viewmode_list".equals(mViewMode)) {
            mListFileShow.setVisibility(View.VISIBLE);
            if (mode) {
                int[] resid = new int[] { R.id.image_file_pic,
                        R.id.text_file_name };
                mAdapter = new FileAdapter2(this, mCurrentListFiles,
                        R.layout.list_show_item, resid);
            } else {
                int[] resid = new int[] { R.id.image_file_pic,
                        R.id.text_file_name, R.id.checked_text_file_name };
                mAdapter = new FileAdapter2(this, mCurrentListFiles,
                        R.layout.list_show_checked_item, resid);
            }
            mListFileShow.setAdapter(mAdapter);
            mGridFileShow.setVisibility(View.GONE);
        } else {
            mGridFileShow.setVisibility(View.VISIBLE);
            if (mode) {
                int[] resid = new int[] { R.id.image_file_pic,
                        R.id.text_file_name };
                mAdapter = new FileAdapter2(this, mCurrentListFiles,
                        R.layout.grid_show_item, resid);
            } else {
                int[] resid = new int[] { R.id.image_file_pic,
                        R.id.text_file_name, R.id.checked_text_file_name };
                mAdapter = new FileAdapter2(this, mCurrentListFiles,
                        R.layout.grid_show_checked_item, resid);
            }
            mGridFileShow.setAdapter(mAdapter);
            mListFileShow.setVisibility(View.GONE);
        }
    }

    /**
     * 刷新目录，将传入的目录显示到ListView中
     *
     * @param dir 刷新的目录
     */
    private void freshDirectory(File dir) {
        mCurrentDir = dir;
        setCurrentPath();
        File[] files;
        if (isHiddenFile) {
            // 显示隐藏文件
            files = mCurrentDir.listFiles();
        } else {
            files = MemoryManager.hideFileFilter(mCurrentDir);
        }

        if (null == files || files.length == 0) {
            mCurrentListFiles = new File[]{new File(dir.getParentFile(), ".")};
        } else {
            mCurrentListFiles = files;
            MemoryManager.sort(mCurrentListFiles, mOrderBy);
        }
        mMultiModeFiles = new File[mCurrentListFiles.length];
        mAdapter.changeListFiles(mCurrentListFiles);
        canExit = false;
    }

    private void setCurrentPath() {
        try {
            // 导航条显示当前目录
            mLocalText.setText(mCurrentDir.getCanonicalPath());
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    // *************************长按菜单*************************
    private void open(File operatedFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        String type = "*/*";
        String fileName = operatedFile.getName();
        type = MemoryManager.getFileType(fileName);
        if ("*/*".equals(type)) {
            Toast.makeText(this, "不支持的文件格式", Toast.LENGTH_SHORT).show();
            return;
        }
        intent.setDataAndType(Uri.fromFile(operatedFile), type);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        File dir = mCurrentListFiles[position];
        if (isSingleChoice) {
            if (dir.isDirectory()) {
                freshDirectory(dir);
            } else {
                open(dir);
            }
        } else {
            // 多选模式
            CheckedTextView text = (CheckedTextView) view
                    .findViewById(R.id.checked_text_file_name);
            if (text.isChecked()) {
                mMultiModeFiles[position] = null;
                text.setChecked(false);
            } else {
                mMultiModeFiles[position] = dir;
                text.setChecked(true);
            }
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                break;
            case R.id.btn_2:
                break;
            case R.id.btn_3:
                break;
            case R.id.btn_4:
                break;
            case R.id.btn_5:
                break;
            case R.id.btn_6:
                break;
            case R.id.btn_7:
                break;
            case R.id.btn_8:
                break;
            case R.id.btn_9:
                break;
            case R.id.btn_10:
                break;
            case R.id.btn_11:
                break;
            case R.id.btn_12:
                break;
            case R.id.btn_13:
                break;
            case R.id.btn_14:
                break;
            case R.id.btn_15:
                break;
            case R.id.btn_16:
                break;
            case R.id.btn_17:
                break;
            case R.id.btn_18:
                break;
            case R.id.tv_open_close:

                break;

            default:
                break;
        }


    }

    // ***************InnerClass***************************

    private class FileAdapter2 extends MyBaseAdapter {
        private File[] mListFiles;

        public FileAdapter2(Context context, File[] listFiles, int resource,
                            int[] resIds) {
            super(context, resource, resIds);
            this.mListFiles = listFiles;
        }

        public void changeListFiles(File[] listFiles) {
            this.mListFiles = listFiles;
            this.notifyDataSetChanged();
        }

        public int getCount() {
            return mListFiles.length;
        }

        @Override
        public void bindView(int position, View view) {
            File file = mListFiles[position];
            for (int i = 0; i < this.mResIds.length; i++) {
                switch (this.mResIds[i]) {
                    case R.id.image_file_pic:
                        ImageView imageView = (ImageView) view
                                .getTag(this.mResIds[i]);
                        if (file.isFile()) {
                            String fileName = file.getName();
                            setFileIcon(imageView, fileName);
                        } else {
                            String[] list = file.list();
                            if (list == null || list.length == 0) {
                                imageView.setImageResource(R.drawable.folder);
                            } else {
                                imageView.setImageResource(R.drawable.folder_);
                            }
                        }
                        break;
                    case R.id.text_file_name:
                        TextView textView = (TextView) view.getTag(this.mResIds[i]);
                        textView.setText(file.getName());
                        break;
                    case R.id.checked_text_file_name:
                        CheckedTextView checkedTextView = (CheckedTextView) view
                                .getTag(this.mResIds[i]);
                        if (!isSingleChoice) {
                            if (null != mMultiModeFiles[position]) {
                                checkedTextView.setChecked(true);
                            } else {
                                checkedTextView.setChecked(false);
                            }
                        }
                        break;
                }
            }
        }

        private void setFileIcon(ImageView imageView, String fileName) {
            String fileType = MemoryManager.getFileType(fileName);
            if ("image/*".equalsIgnoreCase(fileType)) {
                // 图片
                imageView.setImageResource(R.drawable.format_picture);
            } else if ("audio/*".equalsIgnoreCase(fileType)) {
                // 音频
                imageView.setImageResource(R.drawable.format_music);
            } else if ("video/*".equalsIgnoreCase(fileType)) {
                // 视频
                imageView.setImageResource(R.drawable.format_media);
            } else {
                // 未知
                imageView.setImageResource(R.drawable.file);
            }
        }
    }

}
