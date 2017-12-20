package chenshuai.bwie.com.shoppingcart.model.IModel;

import java.util.Map;

import chenshuai.bwie.com.shoppingcart.bean.BaseBean;
import chenshuai.bwie.com.shoppingcart.net.OnNetListener;

/**
 * 姓名：陈帅
 * 类作用：
 * 日期：
 */

public interface IAddCard {
     void addCard(Map<String, String> params, final OnNetListener<BaseBean> onNetListener);
}
