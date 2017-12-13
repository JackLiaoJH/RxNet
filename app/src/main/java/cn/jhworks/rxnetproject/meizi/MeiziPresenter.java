package cn.jhworks.rxnetproject.meizi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

import cn.jhworks.lib_common.base.AbstractMvpPresenter;
import cn.jhworks.lib_common.event.RxBus;
import cn.jhworks.lib_common.event.RxBusHelper;
import cn.jhworks.rxnetproject.module.BasicResult;
import cn.jhworks.rxnetproject.module.MeiZi;
import io.reactivex.observers.DisposableObserver;

/**
 * <p> </p>
 *
 * @author jiahui
 * @date 2017/12/7
 */
public class MeiziPresenter extends AbstractMvpPresenter<MeiziView> {
    private final MeiziMode mMeiziMode;

    public MeiziPresenter() {
        this.mMeiziMode = new MeiziMode();
    }

    public void requestMeizi() {
        if (getMvpView() != null) {
            getMvpView().showLoading();
        }
        mMeiziMode.request(new DisposableObserver<BasicResult<List<MeiZi>>>() {
            @Override
            public void onNext(BasicResult<List<MeiZi>> listBasicResult) {
                if (getMvpView() != null) {
                    getMvpView().resultSuccess(listBasicResult.results);
                }
                RxBusHelper.post(new MeiZiEvent(listBasicResult.results));
            }

            @Override
            public void onError(Throwable e) {
                if (getMvpView() != null) {
                    getMvpView().resultFail(e.getMessage());
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void onCreatePresenter(@Nullable Bundle saveState) {
        super.onCreatePresenter(saveState);
        if (saveState != null) {
            Log.e(getClass().getSimpleName(), "MeiziPresenter onCreatePresenter 测试 = " + saveState.getString("test2"));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(getClass().getSimpleName(), "MeiziPresenter onSaveInstanceState...测试");
        outState.putString("test2", "test2_save测试");
    }

    @Override
    public void onDestroyPresenter() {
        super.onDestroyPresenter();
        mMeiziMode.interruptHttp();
    }
}
