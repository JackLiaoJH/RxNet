package cn.jhworks.lib_common.base.factory;

import cn.jhworks.lib_common.base.AbstractMvpPresenter;
import cn.jhworks.lib_common.base.IMvpBaseView;

/**
 * <p> Presenter 工厂实现类 </p>
 *
 * @author jiahui
 * @date 2017/12/8
 */
public class PresenterMvpFactoryImpl<V extends IMvpBaseView, P extends AbstractMvpPresenter<V>>
        implements PresenterMvpFactory<V, P> {
    private final Class<P> mPresenterClass;

    private PresenterMvpFactoryImpl(Class<P> presenterClass) {
        this.mPresenterClass = presenterClass;
    }

    /**
     * 根据注解创建Presenter的工厂实现方法
     *
     * @param viewClass 需要创建Presenter的V层实现类
     * @param <V>       当前View的实现接口类型
     * @param <P>       当前要创建的Presenter类型
     * @return 工厂实现类
     */
    public static <V extends IMvpBaseView, P extends AbstractMvpPresenter<V>> PresenterMvpFactoryImpl<V, P>
    createFactory(Class<?> viewClass) {
        CreatePresenter annotation = viewClass.getAnnotation(CreatePresenter.class);
        Class<P> pClass = null;
        if (annotation != null) {
            pClass = (Class<P>) annotation.value();
        }
        return pClass == null ? null : new PresenterMvpFactoryImpl<>(pClass);

    }

    @Override
    public P createMvpPresenter() {
        try {
            return mPresenterClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Presenter 创建失败，检查是否声明了@CreatePresenter(xxx.class)注解！！！");
        }
    }
}
