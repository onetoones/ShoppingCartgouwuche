package chenshuai.bwie.com.shoppingcart.model.IModel;

import chenshuai.bwie.com.shoppingcart.bean.CatagoryBean;
import chenshuai.bwie.com.shoppingcart.bean.ProCatagoryBean;
import chenshuai.bwie.com.shoppingcart.net.OnNetListener;

/**
 * Created by 分类列表
 */

public interface ICatagotyModel {
    public void getCata(final OnNetListener<CatagoryBean> onNetListener);

    public void getProductCatagory(String cid, final OnNetListener<ProCatagoryBean> onNetList);
}
