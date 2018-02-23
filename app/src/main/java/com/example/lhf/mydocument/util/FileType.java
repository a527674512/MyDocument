package com.example.lhf.mydocument.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by lenovo on 2018/2/7.
 */

public class FileType {
    public static final HashMap<String, String> mFileTypes = new HashMap<String, String>();

    static {
        //images
        mFileTypes.put("FFD8FF", "jpg");
        mFileTypes.put("89504E47", "png");
        mFileTypes.put("47494638", "gif");
        mFileTypes.put("49492A00", "tif");
        mFileTypes.put("424D", "bmp");
        //
        mFileTypes.put("41433130", "dwg"); //CAD
        mFileTypes.put("38425053", "psd");
        mFileTypes.put("7B5C727466", "rtf"); //日记本
        mFileTypes.put("3C3F786D6C", "xml");
        mFileTypes.put("68746D6C3E", "html");
        mFileTypes.put("44656C69766572792D646174653A", "eml"); //邮件
        mFileTypes.put("D0CF11E0", "doc");
        mFileTypes.put("5374616E64617264204A", "mdb");
        mFileTypes.put("252150532D41646F6265", "ps");
        mFileTypes.put("255044462D312E", "pdf");
        mFileTypes.put("504B0304", "zip");
        mFileTypes.put("52617221", "rar");
        mFileTypes.put("57415645", "wav");
        mFileTypes.put("41564920", "avi");
        mFileTypes.put("2E524D46", "rm");
        mFileTypes.put("000001BA", "mpg");
        mFileTypes.put("000001B3", "mpg");
        mFileTypes.put("6D6F6F76", "mov");
        mFileTypes.put("3026B2758E66CF11", "asf");
        mFileTypes.put("4D546864", "mid");
        mFileTypes.put("1F8B08", "gz");
        mFileTypes.put("", "");
        mFileTypes.put("", "");
    }

    public static int FILE_IMAGE = 0;
    public static int FILE_TEXT = 1;
    public static int FILE_VIDEO = 2;
    public static int FILE_MUSIC = 3;

    //    public static int getFileTypes(String path) {
//
//        String fileType = getFileType(path);
//        int types = -1;
//
//        switch (fileType) {
//            case "jpg":
//                types = FILE_IMAGE;
//                break;
//            case "png":
//                types = FILE_IMAGE;
//                break;
//            case "gif":
//                types = FILE_IMAGE;
//                break;
//            case "tif":
//                types = FILE_IMAGE;
//                break;
//            case "bmp":
//                types = FILE_IMAGE;
//                break;
//            case "dwg":
//                break;
//            case "psd":
//                break;
//            case "rtf":
//                break;
//            case "xml":
//                types = FILE_TEXT;
//                break;
//            case "html":
//                types = FILE_TEXT;
//                break;
//            case "eml":
//                break;
//            case "mdb":
//                break;
//            case "ps":
//                break;
//            case "pdf":
//                break;
//            case "zip":
//                break;
//            case "rar":
//                break;
//            case "wav":
//                break;
//            case "avi":
//                types = FILE_VIDEO;
//                break;
//            case "mpg":
//                types = FILE_VIDEO;
//                break;
//            case "mov":
//                types = FILE_VIDEO;
//                break;
//            case "asf":
//                break;
//            case "mid":
//                break;
//            case "gz":
//                break;
//            default:
//                break;
//        }
//
//        return types;
//
//    }
    public static int getFileTypes(String fileName) {
        String type = "*/*";
        int types = -1;
        if (null != fileName && fileName.contains(".")) {
            int lastIndexOf = fileName.lastIndexOf(".");
            String end = fileName.substring(lastIndexOf + 1);
            if ("jpg".equalsIgnoreCase(end) || "png".equalsIgnoreCase(end)
                    || "gif".equalsIgnoreCase(end) || "bmp".equalsIgnoreCase(end)) {
                type = "image/*";
                types = 0;
            } else if ("mp3".equalsIgnoreCase(end)) {
                type = "audio/*";
            } else if ("mp4".equalsIgnoreCase(end) || "3gp".equalsIgnoreCase(end)) {
                type = "video/*";
            }
        }
        return types;
    }

    public static String getFileType(String filePath) {
        return mFileTypes.get(getFileHeader(filePath));
    }

    //获取文件头信息
    public static String getFileHeader(String filePath) {
        FileInputStream is = null;
        String value = null;
        try {
            is = new FileInputStream(filePath);
            byte[] b = new byte[3];
            is.read(b, 0, b.length);
            value = bytesToHexString(b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
        return value;
    }

    private static String bytesToHexString(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;
        for (int i = 0; i < src.length; i++) {
            hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        return builder.toString();
    }
//    public static void main(String[] args) throws Exception {
//        final String fileType = getFileType("D:/apache-tomcat-6.0.35.tar.gz");
//        System.out.println(fileType);
//    }
}
