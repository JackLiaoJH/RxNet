package org.net.rxnet.interceptor;

import android.content.Context;

import org.net.rxnet.utils.RxNetLog;
import org.net.rxnet.utils.RxNetWorkUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <p> http 缓存拦截器 </p>
 *
 * @author jiahui
 * @date 2017/12/4
 */
public class HttpCacheInterceptor implements Interceptor {

    private Context mContext;

    public HttpCacheInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!RxNetWorkUtils.isAvailable(mContext)) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            RxNetLog.d("没有网络,强制获取缓存!");
        }
        Response originalResponse = chain.proceed(request);
        if (RxNetWorkUtils.isAvailable(mContext)) {
            //有网络的时候读取接口里面的配置，在这里进行统一配置
            String cacheControl = request.cacheControl().toString();
            return originalResponse.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build();
        } else {
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                    .removeHeader("Pragma")
                    .build();
        }
    }
}
