package cn.jhworks.lib_common.base.factory;

import cn.jhworks.lib_common.base.AbstractMvpPresenter;
import cn.jhworks.lib_common.base.IMvpBaseView;

/**
 * <p> 提供设置工厂，获取工厂，获取Presenter的方法，由V层实现这个接口</p>
 *
 * @author jiahui
 * @date 2017/12/8
 */
public interface PresenterProxyInterface<V extends IMvpBaseView, P extends AbstractMvpPresenter<V>> {

    /**
     * 设置Presenter的工厂类，这个方法只能在getMvpPresenter()之前调用，如果Presenter已经创建了则不能再更改
     *
     * @param presenterFactory 要设置的Presenter的工厂类型
     */
    void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory);

    /**
     * 获取Presenter的工厂类
     *
     * @return 返回的是PresenterMvpFactory类型实例
     */
    PresenterMvpFactory<V, P> getPresenterFactory();

    /**
     * 获取创建的Presenter
     *
     * @return 指定的目标Presenter
     */
    P getMvpPresenter();
}
