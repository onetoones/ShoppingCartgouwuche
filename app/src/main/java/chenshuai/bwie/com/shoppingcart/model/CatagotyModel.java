package chenshuai.bwie.com.shoppingcart.model;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;

import chenshuai.bwie.com.shoppingcart.bean.CatagoryBean;
import chenshuai.bwie.com.shoppingcart.bean.ProCatagoryBean;
import chenshuai.bwie.com.shoppingcart.model.IModel.ICatagotyModel;
import chenshuai.bwie.com.shoppingcart.net.Api;
import chenshuai.bwie.com.shoppingcart.net.OkHttpUtils;
import chenshuai.bwie.com.shoppingcart.net.OnNetListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 不将就 on 2017/12/12.
 */

public class CatagotyModel implements ICatagotyModel{
    private Handler handler = new Handler(Looper.getMainLooper());

    public void getCata(final OnNetListener<CatagoryBean> onNetList) {

        OkHttpUtils.getOkHttpUtils().doGet(Api.CATAGORY, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetList.onFailure(e);
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String string = response.body().string();
                final CatagoryBean bean = new Gson().fromJson(string, CatagoryBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetList.onSuccess(bean);
                    }
                });
            }
        });

    }

    /**
     * 子分类列表
     * @param cid
     * @param onNetListener
     */
    public void getProductCatagory(String cid, final OnNetListener<ProCatagoryBean> onNetListener){
        String format = String.format(Api.PRODUCTCATAGORY,cid);

        OkHttpUtils.getOkHttpUtils().doGet(format, new Callback() {
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
                String str = response.body().string();
                final ProCatagoryBean bean = new Gson().fromJson(str, ProCatagoryBean.class);
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
