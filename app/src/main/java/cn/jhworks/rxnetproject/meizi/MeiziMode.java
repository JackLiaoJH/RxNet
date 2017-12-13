package cn.jhworks.rxnetproject.meizi;

import org.net.rxnet.RxNet;

import java.util.List;

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
 * @date 2017/12/7
 */
public class MeiziMode {

    public void request(DisposableObserver<BasicResult<List<MeiZi>>> observer) {
        RxNet.create(DemoService.class).getMeiZi(1, "测试")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void interruptHttp() {
        //停止网络请求
    }
}
