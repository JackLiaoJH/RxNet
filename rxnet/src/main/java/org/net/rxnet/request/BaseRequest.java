package org.net.rxnet.request;

import android.text.TextUtils;

import com.google.gson.Gson;

import org.net.rxnet.RxNet;
import org.net.rxnet.callback.CallBack;
import org.net.rxnet.manager.RxNetManager;
import org.net.rxnet.model.HttpHeaders;
import org.net.rxnet.service.ApiService;
import org.net.rxnet.utils.NullUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * <p> 网络请求基类 </p>
 *
 * @author jiahui
 * @date 2017/12/6
 */
public abstract class BaseRequest {
    protected String url;
    protected Map<String, String> mParamMap;
    private HttpHeaders mHttpHeaders;

    private RxNetManager mRxNetManager;
    protected Gson mGson;


    public BaseRequest() {
        mRxNetManager = RxNet.get().getRxNetManager();

        mGson = new Gson();

        mParamMap = new HashMap<>();
        mHttpHeaders = new HttpHeaders();
        //默认添加 Accept-Language
        String acceptLanguage = HttpHeaders.getAcceptLanguage();
        if (!TextUtils.isEmpty(acceptLanguage)) {
            headers(HttpHeaders.HEAD_KEY_ACCEPT_LANGUAGE, acceptLanguage);
        }
        //默认添加 User-Agent
        String userAgent = HttpHeaders.getUserAgent();
        if (!TextUtils.isEmpty(userAgent)) {
            headers(HttpHeaders.HEAD_KEY_USER_AGENT, userAgent);
        }

    }

    protected ApiService build() {

        return mRxNetManager.getRetrofit().create(ApiService.class);
    }

    public BaseRequest setUrl(String url) {
        this.url = url;
        return this;
    }

    public BaseRequest param(String key, String value) {
        if (TextUtils.isEmpty(key)) {
            return this;
        }
        mParamMap.put(key, value);
        return this;
    }

    public BaseRequest paramMap(Map<String, String> paramMap) {
        if (paramMap == null || paramMap.size() == 0) {
            return this;
        }
        mParamMap.putAll(paramMap);
        return this;
    }

    /**
     * 添加头信息
     */
    public BaseRequest headers(HttpHeaders headers) {
        this.mHttpHeaders.put(headers);
        return this;
    }

    /**
     * 添加头信息
     */
    public BaseRequest headers(String key, String value) {
        mHttpHeaders.put(key, value);
        return this;
    }

    /**
     * 移除头信息
     */
    public BaseRequest removeHeader(String key) {
        mHttpHeaders.remove(key);
        return this;
    }

    /**
     * 移除所有头信息
     */
    public BaseRequest removeAllHeaders() {
        mHttpHeaders.clear();
        return this;
    }

    /**
     * 执行网络请求
     *
     * @param callBack 请求回调接口
     * @param <T>      请求回调实体泛型
     */
    public abstract <T> void execute(CallBack<T> callBack);

    /**
     * 创建请求
     *
     * @return 返回请求结果Observable<ResponseBody>
     */
    public abstract Observable<ResponseBody> createRequest();


}
