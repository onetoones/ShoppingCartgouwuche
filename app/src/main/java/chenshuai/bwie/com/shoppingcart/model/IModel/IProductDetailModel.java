package chenshuai.bwie.com.shoppingcart.model.IModel;

import chenshuai.bwie.com.shoppingcart.bean.ProductDetailBean;
import chenshuai.bwie.com.shoppingcart.net.OnNetListener;

/**
 * 姓名：陈帅
 * 类作用：
 * 日期：
 */

public interface IProductDetailModel {
    public void getPrDetail(String pid, final OnNetListener<ProductDetailBean> onNetListener);

}
