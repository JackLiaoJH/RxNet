package org.net.rxnet.utils;

/**
 * <p>对象为null的判断 </p>
 *
 * @author jiahui
 * date 2017/12/4
 */
public class NullUtils {
    public static void checkNull(Object o) {
        if (o == null) {
            throw new IllegalArgumentException(o + " 不能为null !");
        }
    }

    public static void checkNull(Object o, String errMsg) {
        if (o == null) {
            throw new IllegalArgumentException(errMsg);
        }
    }
}
