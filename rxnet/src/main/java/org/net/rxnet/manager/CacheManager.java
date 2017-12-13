package org.net.rxnet.manager;

import android.content.Context;

import org.net.rxnet.interceptor.HttpCacheInterceptor;
import org.net.rxnet.utils.RxFileUtils;

import java.io.File;

import okhttp3.Cache;

/**
 * <p> 网络请求缓存管理</p>
 *
 * @author jiahui
 * date 2017/12/4
 */

final class CacheManager {
    /** 默认100M */
    private static final int MAX_CACHE = 100 * 1024 * 1024;

    private Cache mCache;
    private HttpCacheInterceptor mHttpCacheInterceptor;

    CacheManager(Context context) {
        File cacheFile = new File(RxFileUtils.getCacheDir(context), "net");
        mCache = new Cache(cacheFile, MAX_CACHE);
        mHttpCacheInterceptor = new HttpCacheInterceptor(context);
    }

    public Cache getCache() {
        return mCache;
    }

    public HttpCacheInterceptor getHttpCacheInterceptor() {
        return mHttpCacheInterceptor;
    }
}
