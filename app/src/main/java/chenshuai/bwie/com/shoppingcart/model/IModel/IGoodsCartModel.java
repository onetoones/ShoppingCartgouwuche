package chenshuai.bwie.com.shoppingcart.model.IModel;

import java.util.Map;

import chenshuai.bwie.com.shoppingcart.bean.GoodsCardBean;
import chenshuai.bwie.com.shoppingcart.net.OnNetListener;

/**
 * 姓名：陈帅
 * 类作用：
 * 日期：
 */

public interface IGoodsCartModel {

void getCarts(Map<String,String> params, OnNetListener<GoodsCardBean> onNetListener);

}
