package chenshuai.bwie.com.shoppingcart.model.IModel;

import java.util.Map;

import chenshuai.bwie.com.shoppingcart.bean.DetailBean;
import chenshuai.bwie.com.shoppingcart.net.OnNetListener;

/**
 * 姓名：陈帅
 * 类作用：
 * 日期：
 */

public interface IListModel {
    public void getList(Map<String,String> params, final OnNetListener<DetailBean> onNetListener);
}
