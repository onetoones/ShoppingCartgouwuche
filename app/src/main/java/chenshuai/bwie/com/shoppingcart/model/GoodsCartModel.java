package chenshuai.bwie.com.shoppingcart.model;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import chenshuai.bwie.com.shoppingcart.bean.GoodsCardBean;
import chenshuai.bwie.com.shoppingcart.model.IModel.IGoodsCartModel;
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

public class GoodsCartModel implements IGoodsCartModel{
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void getCarts(Map<String, String> params, final OnNetListener<GoodsCardBean> onNetListener) {
        OkHttpUtils.getOkHttpUtils().doPost(Api.SELECT_CARD, params, new Callback() {
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
                String s = response.body().string();
                final GoodsCardBean bean = new Gson().fromJson(s, GoodsCardBean.class);
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
