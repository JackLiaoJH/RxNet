package org.net.rxnet.manager;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.net.rxnet.https.HttpsUtils;
import org.net.rxnet.interceptor.HeaderInterceptor;
import org.net.rxnet.interceptor.ParamsInterceptor;
import org.net.rxnet.model.HttpHeaders;
import org.net.rxnet.model.HttpParams;
import org.net.rxnet.utils.RxNetLog;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <p> RxNet 管理类 </p>
 *
 * @author jiahui
 * date 2017/12/4
 */

public final class RxNetManager {
    private static final int DEFAULT_READ_TIME_OUT = 60000;
    private static final int DEFAULT_WRITE_TIME_OUT = 60000;
    private static final int DEFAULT_CONNECT_TIME_OUT = 30000;

    private OkHttpClient mOkHttpClient;
    private OkHttpClient.Builder mBuilder;

    private CacheManager mCacheManager;

    private Retrofit mRetrofit;

    RxNetManager(Context context, String baseUrl, Cache cache, int readTimeout,
                 int writeTimeout, int connectTimeout, HttpHeaders httpHeaders
            , HttpParams httpParams, HttpsUtils.SSLParams sslParams, HostnameVerifier hostnameVerifier) {
        mCacheManager = new CacheManager(context);

        mBuilder = new OkHttpClient.Builder();

        if (RxNetLog.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    try {
                        String text = URLDecoder.decode(message, "utf-8");
                        RxNetLog.d("OKHttp-------%s", text);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        RxNetLog.d("OKHttp-------%s", message);
                    }
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            mBuilder.addInterceptor(loggingInterceptor);
        }

        mBuilder.readTimeout(readTimeout > 0 ? readTimeout : DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout > 0 ? writeTimeout : DEFAULT_WRITE_TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(connectTimeout > 0 ? connectTimeout : DEFAULT_CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(new HeaderInterceptor(httpHeaders))
                .addInterceptor(new ParamsInterceptor(httpParams))
                .addNetworkInterceptor(mCacheManager.getHttpCacheInterceptor())
                .cache(cache != null ? cache : mCacheManager.getCache())
        ;
        //https 支持
        if (sslParams != null) {
            mBuilder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        }
        if (hostnameVerifier != null) {
            mBuilder.hostnameVerifier(hostnameVerifier);
        }
        mOkHttpClient = mBuilder.build();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build()
        ;

    }


    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public static final class Builder {
        private String baseUrl;
        private Context mContext;
        private Cache mCache;

        private int readTimeout;
        private int writeTimeout;
        private int connectTimeout;

        private HttpHeaders mHttpHeaders;
        private HttpParams mHttpParams;
        private HttpsUtils.SSLParams sslParams;
        private HostnameVerifier hostnameVerifier;

        private RxNetManager mRxNetManager;

        public Builder debug(boolean isDebug) {
            RxNetLog.DEBUG = isDebug;
            return this;
        }

        public Builder setContext(Context context) {
            mContext = context;
            return this;
        }

        public Builder setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder setConnectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder setReadTimeout(int readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        public Builder setWriteTimeout(int writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }

        public Builder setCache(Cache cache) {
            mCache = cache;
            return this;
        }

        public Builder setHttpHeaders(HttpHeaders httpHeaders) {
            mHttpHeaders = httpHeaders;
            return this;
        }

        public Builder setHttpParams(HttpParams httpParams) {
            mHttpParams = httpParams;
            return this;
        }

        /**
         * https的全局自签名证书
         *
         * @param certificates
         * @return
         */
        public Builder certificates(InputStream... certificates) {
            this.sslParams = HttpsUtils.getSslSocketFactory(null, null, certificates);
            return this;
        }

        /**
         * https双向认证证书
         *
         * @param bksFile
         * @param password
         * @param certificates
         * @return
         */
        public Builder certificates(InputStream bksFile, String password, InputStream... certificates) {
            this.sslParams = HttpsUtils.getSslSocketFactory(bksFile, password, certificates);
            return this;
        }


        /**
         * https的全局访问规则
         *
         * @param hostnameVerifier
         * @return
         */
        public Builder hostnameVerifier(HostnameVerifier hostnameVerifier) {
            this.hostnameVerifier = hostnameVerifier;
            return this;
        }


        public void build() {
            mRxNetManager = new RxNetManager(mContext, baseUrl, mCache, readTimeout, writeTimeout,
                    connectTimeout, mHttpHeaders, mHttpParams, sslParams, hostnameVerifier);
        }

        public RxNetManager getRxNetManager() {
            return mRxNetManager;
        }
    }
}
