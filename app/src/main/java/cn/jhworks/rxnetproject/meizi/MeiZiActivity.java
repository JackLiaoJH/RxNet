package cn.jhworks.rxnetproject.meizi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import org.net.rxnet.RxNet;
import org.net.rxnet.utils.RxNetLog;

import java.util.List;

import cn.jhworks.rxnetproject.R;
import cn.jhworks.rxnetproject.module.BasicResult;
import cn.jhworks.rxnetproject.module.MeiZi;
import cn.jhworks.rxnetproject.service.DemoService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * <p> </p>
 *
 * @author jiahui
 * @date 2017/12/4
 */
public class MeiZiActivity extends AppCompatActivity {
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


        RxNet.create(DemoService.class).getMeiZi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<BasicResult<List<MeiZi>>>() {
                    @Override
                    public void onNext(BasicResult<List<MeiZi>> listBasicResult) {
                        RxNetLog.d("onNext:%s", listBasicResult.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        RxNetLog.d("onError:%s", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        RxNetLog.d("onComplete.....");
                    }
                });



    }
}
