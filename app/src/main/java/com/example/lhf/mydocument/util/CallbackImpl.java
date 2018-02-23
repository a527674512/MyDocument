package com.example.lhf.mydocument.util;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by lenovo on 2018/2/23.
 */

public class CallbackImpl implements AsyncLoadImage.ImageCallback {
    private ImageView imageView;

    public CallbackImpl(ImageView imageView) {
        super();
        this.imageView = imageView;
    }

    @Override
    public void imageLoaded(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
