package chenshuai.bwie.com.shoppingcart.net;

import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 *
 */

public class OkHttpUtils {
    private static OkHttpUtils okHttpUtils;
    private final OkHttpClient client;

    private OkHttpUtils() {
        client = new OkHttpClient.Builder().build();

    }

    public static OkHttpUtils getOkHttpUtils() {
        if (okHttpUtils == null) {
            synchronized (OkHttpUtils.class) {
                if (okHttpUtils == null) {
                    okHttpUtils = new OkHttpUtils();
                }
            }

        }
        return okHttpUtils;
    }

    //get请求
    public void doGet(String url, Callback callback) {

        Request build = new Request.Builder().url(url).build();
        client.newCall(build).enqueue(callback);

    }

    //POST请求
    public void doPost(String url, Map<String, String> params, Callback callback) {

        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());

        }
        FormBody build = builder.build();

        Request request = new Request.Builder().url(url).post(build).build();
        client.newCall(request).enqueue(callback);

    }
}
