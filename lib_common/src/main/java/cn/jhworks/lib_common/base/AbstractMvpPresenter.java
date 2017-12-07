package cn.jhworks.lib_common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * <p>MVP模式Present 基类 ,跟Activity，Fragment等生命周期绑定</p>
 *
 * @author jiahui
 * @date 2017/12/7
 */
public abstract class AbstractMvpPresenter<V extends IMvpBaseView> {
    private static final String TAG = "super-mvp";
    private V mMvpView;

    /** 绑定View */
    public void attachMvpView(V mvpView) {
        this.mMvpView = mvpView;
        Log.e(TAG, "Presenter attachMvpView...");
    }

    /** 解除绑定的View */
    public void detachMvpView() {
        mMvpView = null;
        Log.e(TAG, "Presenter detachMvpView...");
    }

    /**
     * 获取V层的接口View
     *
     * @return 当前的接口View
     */
    public V getMvpView() {
        return mMvpView;
    }

    /**
     * Presenter 被创建后调用
     *
     * @param saveState 被意外销毁后的Bundle
     */
    public void onCreatePresenter(@Nullable Bundle saveState) {
        Log.e(TAG, "Presenter onCreatePresenter...");
    }

    /** Presenter被销毁的时候调用，可以在此释放资源等 */
    public void onDestroyPresenter() {
        Log.e(TAG, "Presenter onDestroyPresenter...");
    }

    /**
     * 在Presenter被意外销毁时调用，它的调用时机和Activity，Fragment，View中的onSaveInstanceState()方法调用时机相同
     *
     * @param outState 保存消息的Bundle
     */
    public void onSaveInstanceState(Bundle outState) {
        Log.e(TAG, "Presenter onSaveInstanceState...");
    }

}
