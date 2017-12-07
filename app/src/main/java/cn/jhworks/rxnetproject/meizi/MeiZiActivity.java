package cn.jhworks.rxnetproject.meizi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.net.rxnet.utils.RxNetLog;

import java.util.List;

import cn.jhworks.lib_common.base.AbstractMvpActivity;
import cn.jhworks.lib_common.base.factory.CreatePresenter;
import cn.jhworks.rxnetproject.R;
import cn.jhworks.rxnetproject.module.MeiZi;

/**
 * <p> </p>
 *
 * @author jiahui
 * @date 2017/12/4
 */
@CreatePresenter(MeiziPresenter.class)
public class MeiZiActivity extends AbstractMvpActivity<MeiziView, MeiziPresenter>
        implements MeiziView {
    private RecyclerView mRecyclerView;

    public static void start(Context context) {
        context.startActivity(new Intent(context, MeiZiActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meizi);
        mRecyclerView = findViewById(R.id.recycler_view);

      /*  RxNet.create(DemoService.class).getMeiZi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<BasicResult<List<MeiZi>>>() {
                    @Override
                    public void onNext(BasicResult<List<MeiZi>> listBasicResult) {
                        RxNetLog.d("onNext:%s", listBasicResult.toString());
                    }

                    @Override
                    public void onError(Throwable t) {
                        RxNetLog.d("onError:%s", t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        RxNetLog.d("onComplete.....");
                    }
                });*/

 /*       RxNet.doGet("/api/data/福利/10/1")
                .param("", "")
                .headers("Cache-Control", "public, max-age=86400")
                .execute(new CallBack<BasicResult<List<MeiZi>>>() {

                    @Override
                    public void onSuccess(BasicResult<List<MeiZi>> data) {
                        Log.e("liao", data.toString());
                    }

                    @Override
                    public void onFail(String errorStr) {
                        Log.e("liao", "错误:" + errorStr);
                    }
                });*/

//
//        RxNet.create(DemoService.class).getMeiZi()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DisposableObserver<BasicResult<List<MeiZi>>>() {
//                    @Override
//                    public void onNext(BasicResult<List<MeiZi>> listBasicResult) {
//                        RxNetLog.d("onNext:%s", listBasicResult.toString());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        RxNetLog.d("onError:%s", e.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        RxNetLog.d("onComplete.....");
//                    }
//                });


        //设置自己的Presentre工厂，如果你想要自定义的话
        //        setPresenterFactory(xxxx);
        if (savedInstanceState != null) {
            Log.e(TAG, "MainActivity onCreate 测试 = " + savedInstanceState.getString("test"));
        }

        request();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("test", "测试保存");
    }

    @Override
    public void request() {
        getMvpPresenter().requestMeizi();
    }

    @Override
    public void showLoading() {
        RxNetLog.i("请求中，请稍后...");
    }

    @Override
    public void resultSuccess(List<MeiZi> meiZiList) {
        RxNetLog.i("请求结果:" + meiZiList.toString());
    }

    @Override
    public void resultFail(String errorStr) {
        RxNetLog.i("请求结果失败..." + errorStr);
    }
}
