package org.net.rxnet.utils;

import android.util.Log;

/**
 * <p> 网络日志</p>
 *
 * @author jiahui
 * @date 2017/12/4
 */
public class RxNetLog {
    public static final String TAG = "RxNet_LOG";
    public static boolean DEBUG = false;

    public static void i(String format, Object... args) {
        if (DEBUG) {
            Log.i(TAG, String.format(format, args));
        }
    }

    public static void d(String format, Object... args) {
        if (DEBUG) {
            Log.d(TAG, String.format(format, args));
        }
    }

    public static void w(String format, Object... args) {
        if (DEBUG) {
            Log.w(TAG, String.format(format, args));
        }
    }

    public static void e(String format, Object... args) {
        if (DEBUG) {
            Log.e(TAG, String.format(format, args));
        }
    }
}
