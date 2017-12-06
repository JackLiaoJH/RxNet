package org.net.rxnet.manager;


import org.net.rxnet.utils.NullUtils;

/**
 * <p> 网络请求管理</p>
 *
 * @author jiahui
 * @date 2017/12/6
 */
public final class RequestManager {

    public void dispenseRequest(int method) {
        if (method == 1) {

        }
    }

    public <T> T createRequest(Class<T> clazz) {
        NullUtils.checkNull(clazz);
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
