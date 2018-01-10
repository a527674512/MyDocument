package com.example.lhf.mydocument.activity;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

import static com.example.lhf.mydocument.R.drawable.file;

/**
 * Created by lenovo on 2017/9/21.
 */

public class MemoryManager {

    public  static String getTotalMemorySize(){
        File path = Environment.getExternalStorageDirectory();
        StatFs statFs = new StatFs(path.getPath());
        long blockSize = statFs.getBlockSizeLong();
        long totalBlocks = statFs.getBlockCount();
        return Formatter.formatFileSize(Myapplication.getContext(), blockSize * totalBlocks);
    }

    /**
     * 获得sd卡剩余容量，即可用大小
     *
     * @return
     */
    public static String getSDAvailableSize() {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return Formatter.formatFileSize(Myapplication.getContext(), blockSize * availableBlocks);
    }

    /**
     * 获得机身内存总大小
     *
     * @return
     */
    public static String getRomTotalSize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return Formatter.formatFileSize(Myapplication.getContext(), blockSize * totalBlocks);
    }

    /**
     * 获得机身可用内存
     *
     * @return
     */
    public static String getRomAvailableSize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return Formatter.formatFileSize(Myapplication.getContext(), blockSize * availableBlocks);
    }


    /**
     * 过滤隐藏文件，获取指定目录的非隐藏文件
     *
     * @param dir
     *            指定目录
     * @return 返回非隐藏文件数组
     */
    public static File[] hideFileFilter(File dir) {
        File[] list = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return !file.isHidden();
            }
        });
        return list;
    }

    /**
     * 文件数组排序
     *
     * @param files
     *            文件数组
     * @param what
     *            排序方式
     */
    public static void sort(File[] files, String what) {
        Comparator<File> comparator = null;
        if ("type".equals(what)) {
            comparator = new Comparator<File>() {

                public int compare(File object1, File object2) {
                    return 1;
                }
            };
        } else if ("size".equals(what)) {
            comparator = new Comparator<File>() {

                public int compare(File object1, File object2) {
                    if (object1.isDirectory() && object2.isFile()) {
                        return -1;
                    } else if (object2.isDirectory() && object1.isFile()) {
                        return 1;
                    } else if (object1.isDirectory() && object2.isDirectory()) {
                        return object1.getPath().compareToIgnoreCase(object2.getPath());
                    }

                    long file1 = object1.length();
                    long file2 = object2.length();
                    long max = Math.max(file1, file2);
                    if (file1 == max) {
                        return 1;
                    } else if (file2 == max) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            };
        } else if ("time".equals(what)) {
            comparator = new Comparator<File>() {

                public int compare(File object1, File object2) {
                    long file1 = object1.lastModified();
                    long file2 = object2.lastModified();
                    long max = Math.max(file1, file2);
                    if (file1 == max) {
                        return 1;
                    } else if (file2 == max) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            };
        } else {
            comparator = new Comparator<File>() {

                public int compare(File object1, File object2) {
                    if (object1.isDirectory() && object2.isFile()) {
                        return -1;
                    } else {
                        if (object2.isDirectory() && object1.isFile()) {
                            return 1;
                        }
                    }
                    return object1.getPath().compareToIgnoreCase(object2.getPath());
                }
            };
        }
        Arrays.sort(files, comparator);
    }

    public static String getFileType(String fileName) {
        String type = "*/*";
        if (null != fileName && fileName.contains(".")) {
            int lastIndexOf = fileName.lastIndexOf(".");
            String end = fileName.substring(lastIndexOf + 1);
            if ("jpg".equalsIgnoreCase(end) || "png".equalsIgnoreCase(end)
                    || "gif".equalsIgnoreCase(end) || "bmp".equalsIgnoreCase(end)) {
                type = "image/*";
            } else if ("mp3".equalsIgnoreCase(end)) {
                type = "audio/*";
            } else if ("mp4".equalsIgnoreCase(end) || "3gp".equalsIgnoreCase(end)) {
                type = "video/*";
            }
        }
        return type;
    }

    public static void save(Context context,String accountName){
        FileOutputStream out = null;
        BufferedWriter writer = null;

        try {
            out = context.openFileOutput("data",Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(accountName);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String load(Context context){
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = context.openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null){
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }
}
