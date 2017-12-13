package org.net.rxnet.callback;

/**
 * <p> 网络请求回调</p>
 *
 * @author jiahui
 * date 2017/12/6
 */
public interface CallBack<T> {
    /**
     * 网络请求成功
     *
     * @param data 返回数据
     */
    void onSuccess(T data);

    /**
     * 网络请求失败回调
     *
     * @param errorStr 失败原因
     */
    void onFail(String errorStr);
}
