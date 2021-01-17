package com.ssmarc.utils;

/**
 * Created by Administrator on 2018/8/30.
 */
public class FileType {
    public static String fileType(String fileName) {
        if (fileName == null) {
            fileName = "文件名为空！";
            return fileName;
        } else {
            // 获取文件后缀名并转化为写，用于后续比较
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
            // 创建图片类型数组
            String img[] = {"bmp", "jpg", "jpeg", "png", "tiff", "gif", "pcx", "tga", "exif", "fpx", "svg", "psd",
                    "cdr", "pcd", "dxf", "ufo", "eps", "ai", "raw", "wmf"};
            for (int i = 0; i < img.length; i++) {
                if (img[i].equals(fileType)) {
                    return "1";//图片
                }
            }
            // 创建文档类型数组
            String document[] = {"txt", "doc", "docx", "xls", "htm", "html", "jsp", "rtf", "wpd", "pdf", "ppt"};
            for (int i = 0; i < document.length; i++) {
                if (document[i].equals(fileType)) {
                    return "2";//文档
                }
            }
            // 创建视频类型数组
            String video[] = {"mp4", "avi", "mov", "wmv", "asf", "navi", "3gp", "mkv", "f4v", "rmvb", "webm"};
            for (int i = 0; i < video.length; i++) {
                if (video[i].equals(fileType)) {
                    return "3";//视频
                }
            }
            // 创建音乐类型数组
            String music[] = {"mp3", "wma", "wav", "mod", "ra", "cd", "md", "asf", "aac", "vqf", "ape", "mid", "ogg",
                    "m4a", "vqf"};
            for (int i = 0; i < music.length; i++) {
                if (music[i].equals(fileType)) {
                    return "4";//音乐
                }
            }
        }
        return "5";//其他
    }

    public static Long fileSize(String image) {
        String str = image.substring(22); // 1.需要计算文件流大小，首先把头部的data:image/png;base64,（注意有逗号）去掉。
        Integer equalIndex = str.indexOf("=");//2.找到等号，把等号也去掉
        if (str.indexOf("=") > 0) {
            str = str.substring(0, equalIndex);
        }
        Integer strLength = str.length();//3.原来的字符流大小，单位为字节
        Integer size = strLength - (strLength / 8) * 2;//4.计算后得到的文件流大小，单位为字节
        return size.longValue();
    }
}
