package org.net.rxnet.model;

import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.MediaType;

/**
 * <p> http请求参数 实体封装</p>
 *
 * @author jiahui
 * date 2017/12/7
 */
public class HttpParams {
    /**
     * 普通的键值对参数
     */
    public LinkedHashMap<String, String> urlParamsMap;

    public HttpParams() {
        init();
    }

    public HttpParams(String key, String value) {
        init();
        put(key, value);
    }

    private void init() {
        urlParamsMap = new LinkedHashMap<>();
    }

    public void put(HttpParams params) {
        if (params != null) {
            if (params.urlParamsMap != null && !params.urlParamsMap.isEmpty()) {
                urlParamsMap.putAll(params.urlParamsMap);
            }
        }
    }

    public void put(Map<String, String> params) {
        if (params == null || params.isEmpty()) {
            return;
        }
        urlParamsMap.putAll(params);
    }

    public void put(String key, String value) {
        urlParamsMap.put(key, value);
    }


    public void removeUrl(String key) {
        urlParamsMap.remove(key);
    }


    public void remove(String key) {
        removeUrl(key);
    }

    public void clear() {
        urlParamsMap.clear();
    }

    public boolean isEmpty() {
        return urlParamsMap.isEmpty();
    }

    private MediaType guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        //解决文件名中含有#号异常的问题
        path = path.replace("#", "");
        String contentType = fileNameMap.getContentTypeFor(path);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return MediaType.parse(contentType);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (ConcurrentHashMap.Entry<String, String> entry : urlParamsMap.entrySet()) {
            if (result.length() > 0) {
                result.append("&");
            }
            result.append(entry.getKey()).append("=").append(entry.getValue());
        }
        return result.toString();
    }
}