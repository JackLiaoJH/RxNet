package cn.jhworks.lib_common.base.factory;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import cn.jhworks.lib_common.base.AbstractMvpPresenter;

/**
 * <p> 标注创建Presenter的注解</p>
 *
 * @author jiahui
 * @date 2017/12/8
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatePresenter {
    Class<? extends AbstractMvpPresenter> value();
}
