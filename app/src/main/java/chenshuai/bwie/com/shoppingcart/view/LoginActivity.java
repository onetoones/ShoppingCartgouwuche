package chenshuai.bwie.com.shoppingcart.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import chenshuai.bwie.com.shoppingcart.R;
import chenshuai.bwie.com.shoppingcart.bean.LoginBean;
import chenshuai.bwie.com.shoppingcart.presenter.LoginPersenter;
import chenshuai.bwie.com.shoppingcart.view.IView.IFragmentmy;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, IFragmentmy {
    /**
     * 登录
     */
    private TextView mTextView;
    /**
     * 请输入手机号
     */
    private EditText mEtPhone;
    /**
     * 请输入密码
     */
    private EditText mEtPwd;
    /**
     * 登录
     */
    private Button mBtDl;
    /**
     * 注册
     */
    private Button mBtZc;
    private LoginPersenter loginPersenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPersenter = new LoginPersenter(this);
        initView();
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.textView);
        mEtPhone = (EditText) findViewById(R.id.et_phone);
        mEtPwd = (EditText) findViewById(R.id.et_pwd);
        mBtDl = (Button) findViewById(R.id.bt_dl);
        mBtDl.setOnClickListener(this);
        mBtZc = (Button) findViewById(R.id.bt_zc);
        mBtZc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_dl:
                loginPersenter.regis();
                break;
            case R.id.bt_zc:
                Intent intent = new Intent(LoginActivity.this, Register.class);
                startActivity(intent);

                break;
        }
    }

    @Override
    public String getName() {
        return mEtPhone.getText().toString().trim();
    }

    @Override
    public String getPwd() {
        return mEtPwd.getText().toString().trim();
    }

    @Override
    public void show(String str) {
        Toast.makeText(LoginActivity.this, str, Toast.LENGTH_LONG).show();
    }

    @Override
    public void tz(LoginBean loginBean) {
        //报存uid
        SharedPreferences sp = getSharedPreferences("user", Context.MODE_PRIVATE);

        SharedPreferences.Editor edit = sp.edit();
        edit.putString("uid", loginBean.getData().getUid() + "");
        edit.commit();
        finish();

    }
}
