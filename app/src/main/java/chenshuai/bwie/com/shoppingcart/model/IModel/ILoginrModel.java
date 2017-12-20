package chenshuai.bwie.com.shoppingcart.model.IModel;


import chenshuai.bwie.com.shoppingcart.bean.LoginBean;
import chenshuai.bwie.com.shoppingcart.net.OnNetListener;

/**
 * Created by 不将就 on 2017/12/11.
 */

public interface ILoginrModel {
    public void getlogin(String mobile, String password,OnNetListener<LoginBean> onNetListener);
}
