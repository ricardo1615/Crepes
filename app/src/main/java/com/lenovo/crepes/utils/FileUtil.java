package com.lenovo.crepes.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/3/18.
 */
public class FileUtil {

    public static long getFileSize(File file) {
        long size = 0;
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return size;
        }
        for (int i = 0; i < listFiles.length; i++) {
            File f = listFiles[i];
            if (f.isDirectory()) {
                size = size + getFileSize(f);
            } else {
                size = size + f.length();
            }
        }
        return size;
    }

    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte(s)";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "K";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

    public static String getFinalFileSize(Context context) {
        File comicFiles = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File cacheFiles = context.getCacheDir();
        File novelFiles = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()+"/CrepesNovel");
        String s = "0M";
        long size = 0;
        if (comicFiles != null) {
            size += getFileSize(comicFiles);
        }

        if (cacheFiles != null) {
            size += getFileSize(cacheFiles);
        }

        if (novelFiles != null) {
            size += getFileSize(novelFiles);
        }

        if (size!=0){
            s = getFormatSize(0.0+size);
        }

        return s;
    }

    public static void deleteCacheFile(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }

        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                file.delete();
                return;
            }

            for (int i = 0; i < childFiles.length; i++) {
                deleteCacheFile(childFiles[i]);
            }
            file.delete();
        }
    }

    public static void deleteAllCacheFile(Context context){
        File comicFiles = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File cacheFiles = context.getCacheDir();
        File novelFiles = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()+"/CrepesNovel");
        if (comicFiles != null) {
            deleteCacheFile(comicFiles);
        }

        if (cacheFiles != null) {
            deleteCacheFile(cacheFiles);
        }

        if (novelFiles != null) {
            deleteCacheFile(novelFiles);
        }
    }
}
