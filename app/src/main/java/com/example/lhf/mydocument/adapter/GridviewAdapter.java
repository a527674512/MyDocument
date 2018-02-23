package com.example.lhf.mydocument.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lhf.mydocument.R;
import com.example.lhf.mydocument.util.AsyncLoadImage;
import com.example.lhf.mydocument.util.CallbackImpl;
import com.example.lhf.mydocument.util.FileType;
import com.example.lhf.mydocument.util.ImageUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2018/1/30.
 */

public class GridviewAdapter extends BaseAdapter {
    private Context context;
    private File[] mCurrentListFiles;
    private List<Picture> pictures=new ArrayList<Picture>();
    private File file;
    private AsyncLoadImage asyncLoadImage;


    public GridviewAdapter(File[] mCurrentListFiles, Context context) {
        super();
        this.context = context;
        this.mCurrentListFiles = mCurrentListFiles;
        asyncLoadImage = new AsyncLoadImage();

//        for (int i = 0; i < images.length; i++) {
//            Picture picture = new Picture(titles[i], images[i]);
//            pictures.add(picture);
//        }

    }

    @Override
    public int getCount() {

        if (null != mCurrentListFiles) {
            return mCurrentListFiles.length;
        } else {
            return 0;
        }
    }


    @Override
    public Object getItem(int position) {
        return null;
        //return pictures.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            // 获得容器
            convertView = LayoutInflater.from(this.context).inflate(R.layout.layout_gridview_item, null);

            // 初始化组件
            viewHolder.ivFileIcon =  convertView.findViewById(R.id.iv_grid_file_icon);
            viewHolder.tvFileName =  convertView.findViewById(R.id.tv_grid_file_name);
            // 给converHolder附加一个对象
            convertView.setTag(viewHolder);
        } else {
            // 取得converHolder附加的对象
            viewHolder = (ViewHolder) convertView.getTag();
        }
        file = mCurrentListFiles[position];
        viewHolder.ivFileIcon.setImageDrawable(context.getResources().getDrawable(R.mipmap.btn_application_drable));
        viewHolder.tvFileName.setText(file.getName());
        if(file.isDirectory()){
//
        }else{
            String path = file.getPath();
            if (FileType.getFileTypes(file.getName()) == FileType.FILE_IMAGE) {
//                Bitmap tempBitmap = BitmapFactory.decodeFile(path);
//                tempBitmap = ImageUtil.getZoomImage(tempBitmap,100);
//                viewHolder.ivFileIcon.setImageBitmap(tempBitmap);//显示图片
                // 异步加载图片并显示
                asyncLoadImage.loadDrawable(path,
                        new CallbackImpl(viewHolder.ivFileIcon));
            }
        }

//        // 给组件设置资源
//        Picture picture = pictures.get(position);
//        viewHolder.tvFileName.setImageResource(picture.getImageId());
//        viewHolder.ivFileIcon.setText(picture.getTitle());

        return convertView;
    }

    class ViewHolder {
        public TextView tvFileName;
        public ImageView ivFileIcon;
    }

    public void updataFiles(File[] mCurrentListFiles){
        this.mCurrentListFiles = mCurrentListFiles;

    }

}
