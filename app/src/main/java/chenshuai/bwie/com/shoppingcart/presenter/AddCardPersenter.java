package chenshuai.bwie.com.shoppingcart.presenter;

import java.util.HashMap;
import java.util.Map;

import chenshuai.bwie.com.shoppingcart.bean.BaseBean;
import chenshuai.bwie.com.shoppingcart.model.AddCardModel;
import chenshuai.bwie.com.shoppingcart.model.IModel.IAddCard;
import chenshuai.bwie.com.shoppingcart.net.OnNetListener;
import chenshuai.bwie.com.shoppingcart.view.IView.IProductDetailsActivity;

/**
 * 姓名：陈帅
 * 类作用：
 * 日期：
 */

public class AddCardPersenter {
    private IProductDetailsActivity aactivity;
    private final IAddCard iAddCard;

    public AddCardPersenter(IProductDetailsActivity aactivity) {
        aactivity = aactivity;
        iAddCard = new AddCardModel();
    }

    public void dettach() {
        aactivity = null;

    }

    public void getAddCard(String uid, String pid) {
        Map<String,String> params = new HashMap<>();

        params.put("uid",uid);
        params.put("pid",pid);
        params.put("source", "android");
        iAddCard.addCard(params, new OnNetListener<BaseBean>() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                if (aactivity!=null){
                    aactivity.show(baseBean.getMsg());

                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });

    }


}
