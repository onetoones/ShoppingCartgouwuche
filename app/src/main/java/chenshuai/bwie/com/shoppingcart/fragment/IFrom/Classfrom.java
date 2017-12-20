package chenshuai.bwie.com.shoppingcart.fragment.IFrom;

import java.util.List;

import chenshuai.bwie.com.shoppingcart.bean.CatagoryBean;
import chenshuai.bwie.com.shoppingcart.bean.ProCatagoryBean;

/**
 * 姓名：陈帅
 * 类作用：
 * 日期：
 */

public interface Classfrom {

    public void show(List<CatagoryBean.DataBean> data);
    public void showPro(List<ProCatagoryBean.DataBean> group,List<List<ProCatagoryBean.DataBean.ListBean>> child);
}
