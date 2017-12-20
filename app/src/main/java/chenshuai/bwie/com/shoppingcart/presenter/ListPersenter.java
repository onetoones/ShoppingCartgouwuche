package chenshuai.bwie.com.shoppingcart.presenter;

import android.util.ArrayMap;

import java.util.Map;

import chenshuai.bwie.com.shoppingcart.bean.DetailBean;
import chenshuai.bwie.com.shoppingcart.model.IModel.IListModel;
import chenshuai.bwie.com.shoppingcart.model.ListModel;
import chenshuai.bwie.com.shoppingcart.net.OnNetListener;
import chenshuai.bwie.com.shoppingcart.view.IView.IListActivity;

/**
 * 姓名：陈帅
 * 类作用：
 * 日期：
 */

public class ListPersenter {
    private IListActivity listActivity;
    private final IListModel iListModel;

    public ListPersenter(IListActivity listActivity) {
        this.listActivity = listActivity;
        iListModel = new ListModel();
    }
    public void dettach(){
        listActivity=null;

    }

        public void getDetail(String pscid,String page,String sort){
            Map<String,String> params = new ArrayMap<>();
            params.put("pscid",pscid);
            params.put("page",page);
            params.put("sort",sort);
        iListModel.getList(params, new OnNetListener<DetailBean>() {
            @Override
            public void onSuccess(DetailBean detailBean) {
                if (listActivity!=null){
                    listActivity.show(detailBean.getData());

                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
        }
}
