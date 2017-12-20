package chenshuai.bwie.com.shoppingcart.presenter;

import chenshuai.bwie.com.shoppingcart.bean.ProductDetailBean;
import chenshuai.bwie.com.shoppingcart.model.IModel.IProductDetailModel;
import chenshuai.bwie.com.shoppingcart.model.ProductDetailModel;
import chenshuai.bwie.com.shoppingcart.net.OnNetListener;
import chenshuai.bwie.com.shoppingcart.view.fragments.IGoods;

/**
 * 姓名：陈帅
 * 类作用：
 * 日期：
 */

public class PrDetailPresenter {
    private IGoods iGoods;
    private final IProductDetailModel detailModel;

    public PrDetailPresenter(IGoods iGoods) {
        this.iGoods = iGoods;
        detailModel = new ProductDetailModel();
    }

    public void onDetach() {
        iGoods = null;

    }

    public void getDetail(String pid) {
        detailModel.getPrDetail(pid, new OnNetListener<ProductDetailBean>() {
            @Override
            public void onSuccess(ProductDetailBean productDetailBean) {
                iGoods.show(productDetailBean);

            }

            @Override
            public void onFailure(Exception e) {

            }
        });

    }

}
