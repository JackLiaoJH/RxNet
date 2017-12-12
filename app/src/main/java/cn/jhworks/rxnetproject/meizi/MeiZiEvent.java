package cn.jhworks.rxnetproject.meizi;

import java.util.List;

import cn.jhworks.rxnetproject.module.MeiZi;

/**
 * <p> </p>
 *
 * @author jiahui
 * @date 2017/12/13
 */
public class MeiZiEvent {
    public List<MeiZi> mMeiZiList;

    public MeiZiEvent(){}

    public MeiZiEvent(List<MeiZi> meiZiList) {
        mMeiZiList = meiZiList;
    }
}
