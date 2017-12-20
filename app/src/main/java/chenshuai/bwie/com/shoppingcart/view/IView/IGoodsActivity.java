package chenshuai.bwie.com.shoppingcart.view.IView;

import java.util.List;

import chenshuai.bwie.com.shoppingcart.bean.GoodsCardBean;

/**
 * 姓名：陈帅
 * 类作用：
 * 日期：
 */

public interface IGoodsActivity {
    void show(List<GoodsCardBean.DataBean> group, List<List<GoodsCardBean.DataBean.ListBean>> child);
}
