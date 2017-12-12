package cn.jhworks.lib_common.event;


import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.subscribers.SerializedSubscriber;

/**
 * <p> 基于RxJava的事件分发封装</p>
 *
 * @author jiahui
 * @date 2017/12/12
 */
public class RxBus {
    private static volatile RxBus sRxBus;
    private final FlowableProcessor<Object> mBus;

    private RxBus() {
        //toSerialized（）保证线程安全
        mBus = PublishProcessor.create().toSerialized();
    }

    public static RxBus get() {
        if (sRxBus == null) {
            synchronized (RxBus.class) {
                if (sRxBus == null) {
                    sRxBus = new RxBus();
                }
            }
        }
        return sRxBus;
    }

    /**
     * 发送消息
     *
     * @param o
     */
    public void post(Object o) {
        new SerializedSubscriber<>(mBus).onNext(o);
    }

    /**
     * 确定接收消息类型
     *
     * @param tClass 消息类型
     * @param <T>
     * @return
     */
    public <T> Flowable<T> toFlowable(Class<T> tClass) {
        return mBus.ofType(tClass);
    }

    /**
     * Returns true if the subject has subscribers.
     * <p>The method is thread-safe.
     *
     * @return true if the subject has subscribers
     */
    public boolean hasSubscribers() {
        return mBus.hasSubscribers();
    }
}
