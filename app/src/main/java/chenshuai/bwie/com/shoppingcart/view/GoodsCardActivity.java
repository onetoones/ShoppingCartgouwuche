package chenshuai.bwie.com.shoppingcart.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.List;

import chenshuai.bwie.com.shoppingcart.R;
import chenshuai.bwie.com.shoppingcart.adapter.ElvAdapter;
import chenshuai.bwie.com.shoppingcart.bean.GoodsCardBean;
import chenshuai.bwie.com.shoppingcart.presenter.GootsPresenter;
import chenshuai.bwie.com.shoppingcart.view.IView.IGoodsActivity;

public class GoodsCardActivity extends AppCompatActivity implements IGoodsActivity {

    private ExpandableListView mElv;
    /**
     * 全选
     */
    private CheckBox mCb;
    /**
     * 合计:
     */
    private TextView mTvTotal;
    /**
     * 去结算(0)
     */
    private TextView mTvCount;
    private GootsPresenter presenter;
    private ElvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_card);
        presenter = new GootsPresenter(this);
        presenter.getGoods(getSharedPreferences("user", Context.MODE_PRIVATE).getString("uid", ""));
        initView();
        mCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter != null) {
                    adapter.setAllchecked(mCb.isChecked());
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dettach();
    }

    private void initView() {
        mElv = (ExpandableListView) findViewById(R.id.elv);
        mCb = (CheckBox) findViewById(R.id.cb);
        mTvTotal = (TextView) findViewById(R.id.tvTotal);
        mTvCount = (TextView) findViewById(R.id.tvCount);


    }


    @Override
    public void show(List<GoodsCardBean.DataBean> group, List<List<GoodsCardBean.DataBean.ListBean>> child) {
//设置适配器
        adapter = new ElvAdapter(this, group, child);
        mElv.setAdapter(adapter);
        for (int i = 0; i < group.size(); i++) {
            mElv.expandGroup(i);
        }

    }

    public void setAllSelect(boolean bool) {

        mCb.setChecked(bool);
    }

    public void setMoney(double price) {
        mTvTotal.setText("合计：" + price);

    }
    public  void setCount(int count){
        mTvCount.setText("去结算：（" + count + "）");


    }
}