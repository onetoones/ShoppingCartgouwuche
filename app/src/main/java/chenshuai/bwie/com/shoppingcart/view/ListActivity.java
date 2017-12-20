package chenshuai.bwie.com.shoppingcart.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import chenshuai.bwie.com.shoppingcart.R;
import chenshuai.bwie.com.shoppingcart.adapter.ListAdapter;
import chenshuai.bwie.com.shoppingcart.bean.DetailBean;
import chenshuai.bwie.com.shoppingcart.presenter.ListPersenter;
import chenshuai.bwie.com.shoppingcart.view.IView.IListActivity;

public class ListActivity extends AppCompatActivity implements View.OnClickListener, IListActivity {

    /**
     * 综合
     */
    private TextView mTvZhonghe;
    /**
     * 销量
     */
    private TextView mTvXiaoliang;
    /**
     * 价格
     */
    private TextView mTvPrice;
    /**
     * 筛选
     */
    private TextView mTvShaixuan;
    private RecyclerView rv;
    private RecyclerView mRv;
    private SpringView mSv;
    private String sort = "0";
    private int page = 1;
    private ListPersenter persenter;
    private String pscid;
    List<DetailBean.DataBean> list = new ArrayList<>();
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_adapter);
        initView();
        //得到传过来的数据
        Intent intent = getIntent();
        //绑定P层
        pscid = intent.getStringExtra("pscid");
        persenter = new ListPersenter(this);
        persenter.getDetail(pscid, page + "", "0");
        // rv = findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListAdapter(this, list);
        mRv.setAdapter(adapter);
        mSv.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //当前是什么状态，比如是总和还是销量
                page = 1;
                persenter.getDetail(pscid, page + "", sort);

            }

            @Override
            public void onLoadmore() {
                page++;
                persenter.getDetail(pscid, page + "", sort);
            }
        });

    }
//解绑
    @Override
    protected void onDestroy() {
        super.onDestroy();
        persenter.dettach();
    }
    @Override
    public void show(List<DetailBean.DataBean> list) {
        //判断是否刷新
        if (page == 1) {
            this.list.clear();
            this.list.addAll(list);
        } else {
            this.list.addAll(list);
            if(this.list.size()>=20){
//                mSv.setEnable(false);
            }
        }
        mSv.onFinishFreshAndLoad();
        adapter.notifyDataSetChanged();

    }
    private void initView() {
        mTvZhonghe = (TextView) findViewById(R.id.tvZhonghe);
        mTvZhonghe.setOnClickListener(this);
        mTvXiaoliang = (TextView) findViewById(R.id.tvXiaoliang);
        mTvXiaoliang.setOnClickListener(this);
        mTvPrice = (TextView) findViewById(R.id.tvPrice);
        mTvPrice.setOnClickListener(this);
        mTvShaixuan = (TextView) findViewById(R.id.tvShaixuan);
        mTvShaixuan.setOnClickListener(this);
        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setOnClickListener(this);
        mSv = (SpringView) findViewById(R.id.sv);
        mSv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvZhonghe:
                sort="0";
                persenter.getDetail(pscid,"1",sort);
                break;
            case R.id.tvXiaoliang:
                sort="1";
                persenter.getDetail(pscid,"1",sort);
                break;
            case R.id.tvPrice:
                sort="2";
                persenter.getDetail(pscid,"1",sort);
                break;
            case R.id.tvShaixuan:
                break;
            case R.id.rv:
                break;
            case R.id.sv:
                break;
        }
    }


}
