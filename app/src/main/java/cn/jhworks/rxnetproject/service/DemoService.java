package cn.jhworks.rxnetproject.service;

import java.util.List;

import cn.jhworks.rxnetproject.module.BasicResult;
import cn.jhworks.rxnetproject.module.MeiZi;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * <p>获取数据 </p>
 *
 * @author jiahui
 * @date 2017/12/4
 */
public interface DemoService {

    @Headers("Cache-Control: public, max-age=86400")
    @GET("/api/data/福利/10/1")
    Observable<BasicResult<List<MeiZi>>> getMeiZi();
}
