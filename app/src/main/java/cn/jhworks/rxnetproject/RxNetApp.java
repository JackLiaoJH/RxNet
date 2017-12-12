package cn.jhworks.rxnetproject;

import android.app.Application;

import org.net.rxnet.RxNet;
import org.net.rxnet.model.HttpHeaders;
import org.net.rxnet.model.HttpParams;

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
//        HttpHeaders httpHeaders = new HttpHeaders();
//        for (int i = 0; i < 10; i++) {
//            httpHeaders.put("key" + i, "value" + i);
//        }
        HttpParams httpParams = new HttpParams();
        for (int i = 0; i < 10; i++) {
            httpParams.put("key" + i, "value" + i);
        }
        RxNet.init(this)
                .debug(BuildConfig.DEBUG)
                .setBaseUrl("http://gank.io")
//                .setHttpHeaders(httpHeaders)
                .setHttpParams(httpParams)
                //https
//                .certificates()
                .build()
        ;
    }
}
