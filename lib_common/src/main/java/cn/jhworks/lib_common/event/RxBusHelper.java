package cn.jhworks.lib_common.event;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <p> {@link RxBus}简单封装，支持在主线程接收，子线程接收 </p>
 *
 * @author jiahui
 * @date 2017/12/12
 */
public class RxBusHelper {
    /**
     * 发送消息
     *
     * @param o
     */
    public static void post(Object o) {
        RxBus.get().post(o);
    }

    private static <T> void doReceiveEvent(Class<T> tClass, CompositeDisposable disposable,
                                           Consumer<T> onNext, Consumer<Throwable> onError, boolean isMainThread) {
        if (disposable != null && onError != null) {
            disposable.add(RxBus.get().toFlowable(tClass)
                    .observeOn(isMainThread ? AndroidSchedulers.mainThread() : Schedulers.newThread())
                    .subscribe(onNext, onError)
            );
        } else {
            if (disposable != null) {
                disposable.add(RxBus.get().toFlowable(tClass)
                        .observeOn(isMainThread ? AndroidSchedulers.mainThread() : Schedulers.newThread())
                        .subscribe(onNext)
                );
            } else {
                RxBus.get().toFlowable(tClass)
                        .observeOn(isMainThread ? AndroidSchedulers.mainThread() : Schedulers.newThread())
                        .subscribe(onNext);
            }
        }
    }

    /**
     * 接收消息，且在主线程处理
     *
     * @param tClass     接收消息类型
     * @param disposable 存放消息
     * @param onNext     处理成功结果回调
     * @param onError    处理错误结果回调
     * @param <T>
     */
    public static <T> void doOnMainThread(Class<T> tClass, CompositeDisposable disposable,
                                          Consumer<T> onNext, Consumer<Throwable> onError) {
        doReceiveEvent(tClass, disposable, onNext, onError, true);
    }

    /**
     * 接收消息，且在主线程处理
     *
     * @param tClass     接收消息类型
     * @param disposable 存放消息
     * @param onNext     处理成功结果回调
     * @param <T>
     */
    public static <T> void doOnMainThread(Class<T> tClass, CompositeDisposable disposable, Consumer<T> onNext) {
        doOnMainThread(tClass, disposable, onNext, null);
    }

    /**
     * 接收消息，且在主线程处理
     *
     * @param tClass 接收消息类型
     * @param onNext 处理成功结果回调
     * @param <T>
     */
    public static <T> void doOnMainThread(Class<T> tClass, Consumer<T> onNext) {
        doOnMainThread(tClass, null, onNext, null);
    }

    /**
     * 接收消息，且在子线程处理
     *
     * @param tClass     接收消息类型
     * @param disposable 存放消息
     * @param onNext     处理成功结果回调
     * @param onError    处理错误结果回调
     * @param <T>
     */
    public static <T> void doOnChildThread(Class<T> tClass, CompositeDisposable disposable,
                                           Consumer<T> onNext, Consumer<Throwable> onError) {
        doReceiveEvent(tClass, disposable, onNext, onError, false);
    }

    /**
     * 接收消息，且在子线程处理
     *
     * @param tClass     接收消息类型
     * @param disposable 存放消息
     * @param onNext     处理成功结果回调
     * @param <T>
     */
    public static <T> void doOnChildThread(Class<T> tClass, CompositeDisposable disposable, Consumer<T> onNext) {
        doOnChildThread(tClass, disposable, onNext, null);
    }

    /**
     * 接收消息，且在子线程处理
     *
     * @param tClass 接收消息类型
     * @param onNext 处理成功结果回调
     * @param <T>
     */
    public static <T> void doOnChildThread(Class<T> tClass, Consumer<T> onNext) {
        doOnChildThread(tClass, null, onNext, null);
    }

}
