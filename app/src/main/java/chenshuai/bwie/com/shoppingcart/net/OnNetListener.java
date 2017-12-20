package chenshuai.bwie.com.shoppingcart.net;

/**
 * Created by 不将就 on 2017/12/6.
 */

public interface OnNetListener<T> {

    public void onSuccess(T t);

    public void onFailure(Exception e);
}
