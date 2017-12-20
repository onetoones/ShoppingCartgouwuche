package chenshuai.bwie.com.shoppingcart.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;

import com.youth.banner.Banner;

import java.util.List;

import chenshuai.bwie.com.shoppingcart.R;
import chenshuai.bwie.com.shoppingcart.adapter.CatagoryAdapter;
import chenshuai.bwie.com.shoppingcart.adapter.ProCatagoryAdapter;
import chenshuai.bwie.com.shoppingcart.bean.CatagoryBean;
import chenshuai.bwie.com.shoppingcart.bean.ProCatagoryBean;
import chenshuai.bwie.com.shoppingcart.fragment.IFrom.Classfrom;
import chenshuai.bwie.com.shoppingcart.presenter.CatagoryPresenter;
import chenshuai.bwie.com.shoppingcart.widget.MyExpandableListView;

/**
 *
 *
 */

public class Fragment_classification extends Fragment implements Classfrom {
    private View view;
    private ListView mLv;
    private MyExpandableListView mElv;
    private Banner mBanner;
    private ScrollView mSv;
    private CatagoryAdapter adapter;
    private CatagoryPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_classification, null);
        initView(view);
        presenter = new CatagoryPresenter(this);
        presenter.cata();
        return view;
    }

    private void initView(View view) {
        mLv = (ListView) view.findViewById(R.id.lv);
        mElv = (MyExpandableListView) view.findViewById(R.id.elv);
        mBanner = (Banner) view.findViewById(R.id.banner);
        mSv = (ScrollView) view.findViewById(R.id.sv);

        //左侧listview条目的点击事件
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CatagoryBean.DataBean bean = (CatagoryBean.DataBean) parent.getItemAtPosition(position);
                adapter.changeItemSelect(position);
                int cid = bean.getCid();
                presenter.procata(cid + "");
            }
        });

    }

    @Override
    public void show(List<CatagoryBean.DataBean> list) {
        adapter = new CatagoryAdapter(getActivity(), list);
        mLv.setAdapter(adapter);
        //默认加载第一条数据
        adapter.changeItemSelect(0);

    }

    @Override
    public void showPro(List<ProCatagoryBean.DataBean> group, List<List<ProCatagoryBean.DataBean.ListBean>> child) {
        ProCatagoryAdapter proCatagoryAdapter = new ProCatagoryAdapter(getActivity(), group, child);

        mElv.setAdapter(proCatagoryAdapter);
        for (int i = 0; i < group.size(); i++) {
            mElv.expandGroup(i);
        }
    }
}
