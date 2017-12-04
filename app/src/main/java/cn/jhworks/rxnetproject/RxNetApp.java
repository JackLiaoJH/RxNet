package cn.jhworks.rxnetproject;

import android.app.Application;

import org.net.rxnet.RxNet;

/**
 * <p> </p>
 *
 * @author jiahui
 * @date 2017/12/4
 */
public class RxNetApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RxNet.init(this)
                .debug(BuildConfig.DEBUG)
                .setBaseUrl("http://gank.io")
                .build()
        ;
    }
}
