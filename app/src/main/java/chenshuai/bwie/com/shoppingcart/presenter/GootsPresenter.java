package chenshuai.bwie.com.shoppingcart.presenter;

import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import chenshuai.bwie.com.shoppingcart.bean.GoodsCardBean;
import chenshuai.bwie.com.shoppingcart.model.GoodsCartModel;
import chenshuai.bwie.com.shoppingcart.model.IModel.IGoodsCartModel;
import chenshuai.bwie.com.shoppingcart.net.OnNetListener;
import chenshuai.bwie.com.shoppingcart.view.IView.IGoodsActivity;

/**
 * 姓名：陈帅
 * 类作用：
 * 日期：
 */

public class GootsPresenter {
    private IGoodsActivity iGoodsActivity;
    private final IGoodsCartModel iGoodsCartModel;

    public GootsPresenter(IGoodsActivity iGoodsActivity) {
        this.iGoodsActivity = iGoodsActivity;
        iGoodsCartModel = new GoodsCartModel();

    }

    public void dettach() {
        iGoodsActivity = null;
    }

    public void getGoods(String uid) {
        Map<String, String> params = new ArrayMap<>();
        params.put("uid", uid);
        params.put("source", "android");
        iGoodsCartModel.getCarts(params, new OnNetListener<GoodsCardBean>() {
            @Override
            public void onSuccess(GoodsCardBean goodsCardBean) {
                List<List<GoodsCardBean.DataBean.ListBean>> child = new ArrayList<>();
                for (int i = 0; i < goodsCardBean.getData().size(); i++) {
                    child.add(goodsCardBean.getData().get(i).getList());
                }
                if (iGoodsActivity != null) {
                    iGoodsActivity.show(goodsCardBean.getData(), child);

                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });

    }

}
