package chenshuai.bwie.com.shoppingcart.presenter;

import java.util.ArrayList;
import java.util.List;

import chenshuai.bwie.com.shoppingcart.bean.CatagoryBean;
import chenshuai.bwie.com.shoppingcart.bean.ProCatagoryBean;
import chenshuai.bwie.com.shoppingcart.fragment.IFrom.Classfrom;
import chenshuai.bwie.com.shoppingcart.model.CatagotyModel;
import chenshuai.bwie.com.shoppingcart.model.IModel.ICatagotyModel;
import chenshuai.bwie.com.shoppingcart.net.OnNetListener;

/**
 * 姓名：陈帅
 * 类作用：分类的P层
 * 日期：
 */

public class CatagoryPresenter {

    private Classfrom classfrom;
    private final ICatagotyModel iCatagotyModel;

    public CatagoryPresenter(Classfrom classfrom) {
        this.classfrom = classfrom;
        iCatagotyModel = new CatagotyModel();
    }

    //分类列表
    public void cata() {

        iCatagotyModel.getCata(new OnNetListener<CatagoryBean>() {
            @Override
            public void onSuccess(CatagoryBean catagoryBean) {
                List<CatagoryBean.DataBean> data = catagoryBean.getData();
                classfrom.show(data);
                //默认加载第一条数据
                procata(catagoryBean.getData().get(0).getCid() + "");
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    public void procata(String cid) {
        iCatagotyModel.getProductCatagory(cid, new OnNetListener<ProCatagoryBean>() {
            @Override
            public void onSuccess(ProCatagoryBean proCatagoryBean) {
                List<ProCatagoryBean.DataBean> data = proCatagoryBean.getData();
                List<List<ProCatagoryBean.DataBean.ListBean>> bean = new ArrayList<List<ProCatagoryBean.DataBean.ListBean>>();
                for (int i = 0; i <data.size() ; i++) {
                    ProCatagoryBean.DataBean dataBean = data.get(i);
                    List<ProCatagoryBean.DataBean.ListBean> list = dataBean.getList();
                    bean.add(list);
                }
                classfrom.showPro(data,bean);


            }

            @Override
            public void onFailure(Exception e) {

            }
        });

    }
}
