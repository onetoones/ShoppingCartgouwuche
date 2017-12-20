package chenshuai.bwie.com.shoppingcart.model;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;

import chenshuai.bwie.com.shoppingcart.bean.ProductDetailBean;
import chenshuai.bwie.com.shoppingcart.model.IModel.IProductDetailModel;
import chenshuai.bwie.com.shoppingcart.net.Api;
import chenshuai.bwie.com.shoppingcart.net.OkHttpUtils;
import chenshuai.bwie.com.shoppingcart.net.OnNetListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 姓名：陈帅
 * 类作用：商品详情也得model
 * 日期：
 */

public class ProductDetailModel implements IProductDetailModel{
private Handler handler = new Handler(Looper.getMainLooper());

public void getPrDetail(String pid, final OnNetListener<ProductDetailBean> onNetListener){
    String url = String.format(Api.PRODUCT_DETAIL, pid);

    OkHttpUtils.getOkHttpUtils().doGet(url, new Callback() {
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
            final ProductDetailBean bean = new Gson().fromJson(s, ProductDetailBean.class);
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
