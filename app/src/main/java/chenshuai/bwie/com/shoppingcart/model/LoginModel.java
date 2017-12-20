package chenshuai.bwie.com.shoppingcart.model;


import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;

import chenshuai.bwie.com.shoppingcart.bean.LoginBean;
import chenshuai.bwie.com.shoppingcart.model.IModel.ILoginrModel;
import chenshuai.bwie.com.shoppingcart.net.Api;
import chenshuai.bwie.com.shoppingcart.net.OkHttpUtils;
import chenshuai.bwie.com.shoppingcart.net.OnNetListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 不将就 on 2017/12/11.
 */

public class LoginModel implements ILoginrModel {
    private Handler handler = new Handler(Looper.getMainLooper());


    @Override
    public void getlogin(String mobile, String password, final OnNetListener<LoginBean> onNetListener) {
        String format = String.format(Api.LOGIN, mobile, password);
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
                String s = response.body().string();
                final LoginBean bean = new Gson().fromJson(s, LoginBean.class);
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
