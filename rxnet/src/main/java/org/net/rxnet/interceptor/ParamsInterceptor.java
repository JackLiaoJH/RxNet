package org.net.rxnet.interceptor;

import org.net.rxnet.model.HttpParams;

import java.io.IOException;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <p> 公参拦截器</p>
 *
 * @author jiahui
 * date 2017/12/13
 */
public class ParamsInterceptor implements Interceptor {
    private HttpParams mHttpParams;

    public ParamsInterceptor(HttpParams params) {
        this.mHttpParams = params;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        if (mHttpParams == null || mHttpParams.isEmpty()) {
            return chain.proceed(oldRequest.newBuilder().build());
        }
        HttpUrl.Builder builder = oldRequest.url().newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host());
        for (Map.Entry<String, String> entry : mHttpParams.urlParamsMap.entrySet()) {
            builder.addQueryParameter(entry.getKey(), entry.getValue());
        }

        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(builder.build())
                .build();
        return chain.proceed(newRequest);
    }
}
