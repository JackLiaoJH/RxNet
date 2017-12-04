package org.net.rxnet;

import android.content.Context;

import org.net.rxnet.manager.RxNetManager;
import org.net.rxnet.utils.NullUtils;

/**
 * <p> 网络请求入口</p>
 *
 * @author jiahui
 * @date 2017/12/4
 */

public final class RxNet {

    private static RxNet mInstance;

    private static RxNetManager.Builder sBuilder;

    private RxNet(Context context) {
        sBuilder = new RxNetManager.Builder();
        sBuilder.setContext(context);
    }

    public static RxNetManager.Builder init(Context context) {
        mInstance = new RxNet(context);
        return sBuilder;
    }


   /* public static RxNet doGet(String url) {
        checkInstance();

        return mInstance;
    }*/

    public static RxNet get() {
        return mInstance;
    }

    public static  <T> T create(Class<T> service) {
        checkInstance();
        return sBuilder.getRxNetManager().getRetrofit().create(service);
    }

    private static void checkInstance() {
        NullUtils.checkNull(mInstance, "请在项目中先调用RxNet.init()方法初始化!!!");
    }
}