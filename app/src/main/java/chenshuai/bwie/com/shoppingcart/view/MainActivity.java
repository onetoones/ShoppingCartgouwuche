package chenshuai.bwie.com.shoppingcart.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import chenshuai.bwie.com.shoppingcart.R;
import chenshuai.bwie.com.shoppingcart.fragment.CustomViewPager;
import chenshuai.bwie.com.shoppingcart.fragment.Fragment_HomePage;
import chenshuai.bwie.com.shoppingcart.fragment.Fragment_classification;
import chenshuai.bwie.com.shoppingcart.fragment.Fragment_find;
import chenshuai.bwie.com.shoppingcart.fragment.Fragment_my;
import chenshuai.bwie.com.shoppingcart.fragment.Fragment_shopping;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CustomViewPager mVp;
    private ImageView mHomepage;
    private ImageView mClassification;
    private ImageView mFind;
    private ImageView mShopping;
    private ImageView mMy;
    private List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //绑定适配器
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        mVp.setAdapter(adapter);
    }

    private void initView() {
        //1.初始化控件
        mVp = (CustomViewPager) findViewById(R.id.vp);
        //禁止fragment滑动
        mVp.setScanScroll(false);
        mHomepage = (ImageView) findViewById(R.id.homepage);
        mClassification = (ImageView) findViewById(R.id.classification);
        mFind = (ImageView) findViewById(R.id.find);
        mShopping = (ImageView) findViewById(R.id.shopping);
        mMy = (ImageView) findViewById(R.id.my);
        //2.给viewpa添加数据
        list = new ArrayList<>();
        list.add(new Fragment_HomePage());
        list.add(new Fragment_classification());
        list.add(new Fragment_find());
        list.add(new Fragment_shopping());
        list.add(new Fragment_my());
        //按钮的滑动时间
        mHomepage.setOnClickListener(this);
        mClassification.setOnClickListener(this);
        mFind.setOnClickListener(this);
        mShopping.setOnClickListener(this);
        mMy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homepage:
                mVp.setCurrentItem(0);
                mHomepage.setImageResource(R.drawable.ac1);
                mClassification.setImageResource(R.drawable.abw);
                mFind.setImageResource(R.drawable.aby);
                mShopping.setImageResource(R.drawable.abu);
                mMy.setImageResource(R.drawable.ac2);

                break;
            case R.id.classification:
                mVp.setCurrentItem(1);
                mHomepage.setImageResource(R.drawable.ac0);
                mClassification.setImageResource(R.drawable.abx);
                mFind.setImageResource(R.drawable.aby);
                mShopping.setImageResource(R.drawable.abu);
                mMy.setImageResource(R.drawable.ac2);
                break;
            case R.id.find:
                mVp.setCurrentItem(2);
                mHomepage.setImageResource(R.drawable.ac0);
                mClassification.setImageResource(R.drawable.abw);
                mFind.setImageResource(R.drawable.abz);
                mShopping.setImageResource(R.drawable.abu);
                mMy.setImageResource(R.drawable.ac2);
                break;
            case R.id.shopping:
                mVp.setCurrentItem(3);
                mHomepage.setImageResource(R.drawable.ac0);
                mClassification.setImageResource(R.drawable.abw);
                mFind.setImageResource(R.drawable.aby);
                mShopping.setImageResource(R.drawable.abv);
                mMy.setImageResource(R.drawable.ac2);
                break;
            case R.id.my:
                mVp.setCurrentItem(4);
                mHomepage.setImageResource(R.drawable.ac0);
                mClassification.setImageResource(R.drawable.abw);
                mFind.setImageResource(R.drawable.aby);
                mShopping.setImageResource(R.drawable.abu);
                mMy.setImageResource(R.drawable.ac3);
                break;
        }
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

}
