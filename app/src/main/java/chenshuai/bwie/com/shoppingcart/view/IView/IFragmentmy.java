package chenshuai.bwie.com.shoppingcart.view.IView;

import chenshuai.bwie.com.shoppingcart.bean.LoginBean;

/**
 * 姓名：陈帅
 * 类作用：
 * 日期：
 */

public interface IFragmentmy {
    public String getName();
    public String getPwd();
    public void show(String str);

    public void tz(LoginBean loginBean);
}
