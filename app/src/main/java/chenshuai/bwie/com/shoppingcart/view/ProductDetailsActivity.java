package chenshuai.bwie.com.shoppingcart.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import chenshuai.bwie.com.shoppingcart.R;
import chenshuai.bwie.com.shoppingcart.adapter.VpDetailsAdapter;
import chenshuai.bwie.com.shoppingcart.presenter.AddCardPersenter;
import chenshuai.bwie.com.shoppingcart.view.IView.IProductDetailsActivity;
import chenshuai.bwie.com.shoppingcart.view.fragments.Detailssfragment;
import chenshuai.bwie.com.shoppingcart.view.fragments.Evaluatefragment;
import chenshuai.bwie.com.shoppingcart.view.fragments.Goodsfragment;

public class ProductDetailsActivity extends AppCompatActivity implements View.OnClickListener, IProductDetailsActivity {

    private ImageView mIvBack;
    /**
     * 商品
     */
    private RadioButton mRbGoods;
    /**
     * 详情
     */
    private RadioButton mRbDetails;
    /**
     * 评价
     */
    private RadioButton mRbAppraise;
    private RadioGroup mRg;
    private ImageView mIvShare;
    private ImageView mIvMsg;
    private ViewPager mVp;
    private LinearLayout mLlSupplier;
    private LinearLayout mLlShop;
    private LinearLayout mLlAttention;
    private LinearLayout mLlCard;
    private ShareListener shareListener;
    /**
     * 加入购物车
     */
    private TextView mTvAddCard;
    private String pid;
    private AddCardPersenter cardPersenter;

    ////////////////////////////////////////////
    public void setShareListener(ShareListener shareListener) {
        this.shareListener = shareListener;

    }

    @Override
    public void show(String str) {
        Toast.makeText(ProductDetailsActivity.this, str, Toast.LENGTH_SHORT).show();
    }

    public interface ShareListener {
        void share();
    }


    ////////////////////////////////////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Intent intent = getIntent();
        pid = intent.getStringExtra("pid");
        //和P层交互
        cardPersenter = new AddCardPersenter(this);

        initView();
        //
        Goodsfragment goodsfragment = new Goodsfragment();
        List<Fragment> list = new ArrayList<>();
        list.add(goodsfragment);
        list.add(new Detailssfragment());
        list.add(new Evaluatefragment());

        VpDetailsAdapter adapter = new VpDetailsAdapter(getSupportFragmentManager(), list);
        mVp.setAdapter(adapter);
        //bundle传给fragment值
        Bundle bundle = new Bundle();
        bundle.putString("pid", pid);
        goodsfragment.setArguments(bundle);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cardPersenter.dettach();

    }

    private void initView() {
        mIvBack = (ImageView) findViewById(R.id.ivBack);
        mRbGoods = (RadioButton) findViewById(R.id.rbGoods);
        mRbDetails = (RadioButton) findViewById(R.id.rbDetails);
        mRbAppraise = (RadioButton) findViewById(R.id.rbAppraise);
        mRg = (RadioGroup) findViewById(R.id.rg);
        mIvShare = (ImageView) findViewById(R.id.ivShare);
        mIvMsg = (ImageView) findViewById(R.id.ivMsg);
        mVp = (ViewPager) findViewById(R.id.vp);
        mLlSupplier = (LinearLayout) findViewById(R.id.llSupplier);
        mLlShop = (LinearLayout) findViewById(R.id.llShop);
        mLlAttention = (LinearLayout) findViewById(R.id.llAttention);
        mLlCard = (LinearLayout) findViewById(R.id.llCard);
        mTvAddCard = (TextView) findViewById(R.id.tvAddCard);
        mLlCard.setOnClickListener(this);
        mTvAddCard.setOnClickListener(this);
        mIvShare.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llCard:
                //跳转购物车
                Intent intent = new Intent(ProductDetailsActivity.this, GoodsCardActivity.class);
                startActivity(intent);
                break;
            case R.id.tvAddCard:
                //添加购物车
                SharedPreferences sp = getSharedPreferences("user", Context.MODE_PRIVATE);
                cardPersenter.getAddCard(sp.getString("uid", ""), pid);

                break;
            case R.id.ivShare:

                if (shareListener != null) {
                    shareListener.share();
                }
                break;
        }
    }
}
