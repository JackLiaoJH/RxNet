package cn.jhworks.lib_common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import cn.jhworks.lib_common.base.factory.BaseMvpProxy;
import cn.jhworks.lib_common.base.factory.PresenterMvpFactory;
import cn.jhworks.lib_common.base.factory.PresenterMvpFactoryImpl;
import cn.jhworks.lib_common.base.factory.PresenterProxyInterface;

/**
 * <p> 1. 子类的Presenter必须继承自AbstractMvpPresenter；
 * 2. 子类的View必须继承自IMvpBaseView
 * </p>
 *
 * @author jiahui
 * @date 2017/12/7
 */
public abstract class AbstractMvpActivity<V extends IMvpBaseView, P extends AbstractMvpPresenter<V>> extends AppCompatActivity
        implements PresenterProxyInterface<V, P> {
    protected final String TAG = getClass().getSimpleName();
    private static final String KEY_SAVE_PRESENTER = "key_save_presenter";

    /** 创建代理对象，传入默认的Presenter工厂 */
    private BaseMvpProxy<V, P> mMvpProxy = new BaseMvpProxy<>(PresenterMvpFactoryImpl.<V, P>createFactory(getClass()));
    private final String activityName = getClass().getName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, activityName + " V onCreate...");
        Log.e(TAG, activityName + " V onCreate... mProxy=" + mMvpProxy);
        Log.e(TAG, activityName + " V onCreate... this=" + this.hashCode());
        if (savedInstanceState != null) {
            mMvpProxy.onRestoreInstanceState(savedInstanceState.getBundle(KEY_SAVE_PRESENTER));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, activityName + " V onResume...");
        mMvpProxy.onResume((V) this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, activityName + " V onDestroy...");
        mMvpProxy.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(TAG, activityName + " V onSaveInstanceState...");
        outState.putBundle(KEY_SAVE_PRESENTER, mMvpProxy.onSaveInstanceState());
    }

    @Override
    public void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory) {
        Log.e(TAG, activityName + " V setPresenterFactory...");
        mMvpProxy.setPresenterFactory(presenterFactory);
    }

    @Override
    public PresenterMvpFactory<V, P> getPresenterFactory() {
        Log.e(TAG, activityName + " V getPresenterFactory...");
        return mMvpProxy.getPresenterFactory();
    }

    @Override
    public P getMvpPresenter() {
        Log.e(TAG, activityName + " V getMvpPresenter...");
        return mMvpProxy.getMvpPresenter();
    }
}
