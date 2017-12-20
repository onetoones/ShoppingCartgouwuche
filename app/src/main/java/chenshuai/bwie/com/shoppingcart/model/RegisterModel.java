package chenshuai.bwie.com.shoppingcart.model;


import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;

import chenshuai.bwie.com.shoppingcart.bean.BaseBean;
import chenshuai.bwie.com.shoppingcart.model.IModel.IRegisterModel;
import chenshuai.bwie.com.shoppingcart.net.Api;
import chenshuai.bwie.com.shoppingcart.net.OkHttpUtils;
import chenshuai.bwie.com.shoppingcart.net.OnNetListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 不将就 on 2017/12/11.
 */

public class RegisterModel implements IRegisterModel {
    private Handler handler = new Handler(Looper.getMainLooper());

    public void getRegis(String mobile, String password, final OnNetListener<BaseBean> onNetListener) {
        String format = String.format(Api.REGIS, mobile, password);

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
                String string = response.body().string();
                final BaseBean b = new Gson().fromJson(string, BaseBean.class);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onSuccess(b);
                    }
                });
            }
        });
    }

}
