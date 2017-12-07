package cn.jhworks.rxnetproject.meizi;

import java.util.List;

import cn.jhworks.lib_common.base.IMvpBaseView;
import cn.jhworks.rxnetproject.module.MeiZi;

/**
 * <p> </p>
 *
 * @author jiahui
 * @date 2017/12/7
 */
public interface MeiziView extends IMvpBaseView{
    void request();

    void showLoading();

    void resultSuccess(List<MeiZi> meiZiList);

    void resultFail(String errorStr);
}
