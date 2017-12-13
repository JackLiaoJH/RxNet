package org.net.rxnet.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * <p> 网络文件工具类</p>
 *
 * @author jiahui
 * date 2017/12/4
 */

public class RxFileUtils {

    /**
     * 获取缓存目录，有限获取/sdcard/Android/data/package_name/cache，失败才获取/data/data/com.android.framework/cache
     *
     * @param context
     * @return 返回缓存路径
     */
    public static String getCacheDir(Context context) {
        // /data/data/com.android.framework/cache
        File cacheDir = context.getCacheDir();
        // /sdcard/Android/data/package_name/cache
        File externalCacheDir = context.getExternalCacheDir();
        String cacheDirStr;
        if (externalCacheDir == null) {
            cacheDirStr = cacheDir.getAbsolutePath();
        } else {
            cacheDirStr = checkSdCard() ? externalCacheDir.getAbsolutePath() : cacheDir.getAbsolutePath();
        }
        return cacheDirStr;
    }

    public static boolean checkSdCard() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 判断文件是否存在
     *
     * @param file 文件
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public static boolean isFileExists(File file) {
        return file != null && file.exists();
    }

}
