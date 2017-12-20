package chenshuai.bwie.com.shoppingcart.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import chenshuai.bwie.com.shoppingcart.R;
import chenshuai.bwie.com.shoppingcart.presenter.RegisterPersenter;
import chenshuai.bwie.com.shoppingcart.view.IView.IActivity;

public class Register extends AppCompatActivity implements IActivity, View.OnClickListener {
    private ImageView mIv;
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
     * 立即注册
     */
    private Button mBtZc;
    private RegisterPersenter registerPersenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerPersenter = new RegisterPersenter(this);
        initView();
    }

    private void initView() {
        mIv = (ImageView) findViewById(R.id.iv);
        mTextView = (TextView) findViewById(R.id.textView);
        mEtPhone = (EditText) findViewById(R.id.et_phone);
        mEtPwd = (EditText) findViewById(R.id.et_pwd);
        mBtZc = (Button) findViewById(R.id.bt_zc);
        mBtZc.setOnClickListener(this);
        mIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_zc:
                registerPersenter.regis();
                break;
            case R.id.iv:
                finish();
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
    public void show(String s) {
        Toast.makeText(Register.this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void tz() {
        finish();

    }


}
