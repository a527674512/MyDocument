package com.example.lhf.mydocument.util;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2018/2/23.
 */

public class AsyncLoadImage {
    // 图片缓存
    // string是图片的imagePath，值是一个SoftReference对象，该对象指向一个Bitmap的对象
    private Map<String, SoftReference<Bitmap>> imageCache =
            new HashMap<String, SoftReference<Bitmap>>();

    @SuppressLint("HandlerLeak")
    public void loadDrawable(final String imagePath,
                             final ImageCallback callback) {

        if (imageCache.containsKey(imagePath)) {
            //根据路径得到Softreference对象
            SoftReference<Bitmap> softReference = imageCache.get(imagePath);
            if (softReference.get() != null) {
                //若有该缓存，则回调显示该图片
                callback.imageLoaded(softReference.get());
            }
        }
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                //回调显示图片
                callback.imageLoaded((Bitmap) msg.obj);
            }
        };
        //开启子线程加载图片
        new Thread() {
            public void run() {
                Bitmap bm = Bitmap.createScaledBitmap(
                        BitmapFactory.decodeFile(imagePath), 120, 160, true);
                bm = ImageUtil.getZoomImage(bm,10);
                bm = ImageUtil.getZoomImage(bm,100,100);
                Message msg = handler.obtainMessage(0, bm);
                handler.sendMessage(msg);
            };
        }.start();
    }
    //回调接口
    public interface ImageCallback {
        public void imageLoaded(Bitmap bitmap);
    }
}
