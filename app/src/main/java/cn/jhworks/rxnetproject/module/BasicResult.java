package cn.jhworks.rxnetproject.module;

/**
 * <p> 通用结果集</p>
 *
 * @author jiahui
 * @date 2017/12/4
 */
public class BasicResult<T> {
    public int code;
    public String message;
    public T results;
    public boolean error;

    @Override
    public String toString() {
        return "BasicResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + results +
                '}';
    }
}
