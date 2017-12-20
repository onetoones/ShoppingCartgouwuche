package chenshuai.bwie.com.shoppingcart.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youth.banner.Banner;

import java.util.Arrays;

import chenshuai.bwie.com.shoppingcart.R;
import chenshuai.bwie.com.shoppingcart.bean.ProductDetailBean;
import chenshuai.bwie.com.shoppingcart.presenter.PrDetailPresenter;
import chenshuai.bwie.com.shoppingcart.utils.GlideImageLoader;

/**
 * 姓名：陈帅
 * 类作用：
 * 日期：
 */

public class Goodsfragment extends Fragment implements IGoods {

    private PrDetailPresenter prDetailPresenter;
    private ProductDetailBean productDetailBean;
    private View view;
    private Banner mBanner;
    private TextView mTvTilde;
    private TextView mTvSubhead;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String pid = bundle.getString("pid");
        prDetailPresenter = new PrDetailPresenter(this);
        View view = View.inflate(getActivity(), R.layout.goods_fragment, null);
        prDetailPresenter.getDetail(pid);
        initView(view);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        prDetailPresenter.onDetach();
    }

    @Override
    public void show(ProductDetailBean productDetailBean) {
        this.productDetailBean = productDetailBean;
        String images = productDetailBean.getData().getImages();
        String[] split = images.split("\\|");
        mBanner.setImages(Arrays.asList(split));
        mBanner.start();
        mTvTilde.setText(productDetailBean.getData().getTitle());
        mTvSubhead.setText(productDetailBean.getData().getSubhead());
    }

    private void initView(View view) {
        mBanner = (Banner) view.findViewById(R.id.banner);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        mTvTilde = (TextView) view.findViewById(R.id.tvTilde);
        mTvSubhead = (TextView) view.findViewById(R.id.tvSubhead);
    }
}
