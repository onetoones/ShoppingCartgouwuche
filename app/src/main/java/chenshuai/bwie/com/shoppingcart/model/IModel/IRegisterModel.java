package chenshuai.bwie.com.shoppingcart.model.IModel;


import chenshuai.bwie.com.shoppingcart.bean.BaseBean;
import chenshuai.bwie.com.shoppingcart.net.OnNetListener;

/**
 * Created by 不将就 on 2017/12/11.
 */

public interface IRegisterModel {
    public void getRegis(String mobile, String password, OnNetListener<BaseBean> onNetListener);
}
