package org.net.rxnet.request;

import org.net.rxnet.RxNet;
import org.net.rxnet.callback.CallBack;
import org.net.rxnet.utils.NullUtils;
import org.net.rxnet.utils.RxNetLog;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * <p> Get 请求入口</p>
 *
 * @author jiahui
 * @date 2017/12/6
 */
public class GetRequest extends BaseRequest {

    @Override
    public <T> void execute(final CallBack<T> callBack) {
        build().get(url, mParamMap)
                .observeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        NullUtils.checkNull(callBack, "callBack 不能为null！！！");
                        if (responseBody != null) {
                            try {
                                T data = mGson.fromJson(responseBody.string(), ((ParameterizedType) callBack.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
                                RxNetLog.d("GetRequest----response:" + data);
                                callBack.onSuccess(data);
                            } catch (IOException e) {
                                e.printStackTrace();
                                callBack.onFail(e.getMessage());
                            }
                        } else {
                            callBack.onSuccess(null);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public Observable<ResponseBody> createRequest() {
        return null;
    }
}
