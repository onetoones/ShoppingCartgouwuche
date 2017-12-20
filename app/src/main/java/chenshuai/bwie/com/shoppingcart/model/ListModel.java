package chenshuai.bwie.com.shoppingcart.model;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import chenshuai.bwie.com.shoppingcart.bean.DetailBean;
import chenshuai.bwie.com.shoppingcart.model.IModel.IListModel;
import chenshuai.bwie.com.shoppingcart.net.Api;
import chenshuai.bwie.com.shoppingcart.net.OkHttpUtils;
import chenshuai.bwie.com.shoppingcart.net.OnNetListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 姓名：陈帅
 * 类作用：
 * 日期：
 */

public class ListModel implements IListModel{
private Handler handler = new Handler(Looper.getMainLooper());
    public void getList(Map<String,String> params, final OnNetListener<DetailBean> onNetListener){



        OkHttpUtils.getOkHttpUtils().doPost(Api.DETAIL, params, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onFailure(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String string = response.body().string();
                final DetailBean bean = new Gson().fromJson(string, DetailBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onSuccess(bean);
                    }
                });

            }
        });

    }


}
