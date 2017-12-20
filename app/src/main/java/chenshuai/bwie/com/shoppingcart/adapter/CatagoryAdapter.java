package chenshuai.bwie.com.shoppingcart.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import chenshuai.bwie.com.shoppingcart.R;
import chenshuai.bwie.com.shoppingcart.bean.CatagoryBean;

/**
 * 姓名：陈帅
 * 类作用：
 * 日期：
 */

public class CatagoryAdapter extends BaseAdapter {
    private Context context;
    private List<CatagoryBean.DataBean> list;

    public CatagoryAdapter(Context context, List<CatagoryBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        MyViewHodel hodel;
        if (convertView == null) {
            hodel = new MyViewHodel();
            view = View.inflate(context, R.layout.fragment_catagoryadapter, null);
            hodel.tv = view.findViewById(R.id.adapter_tv);
            hodel.ll = view.findViewById(R.id.lls);
            view.setTag(hodel);
        } else {
            view = convertView;
            hodel = (MyViewHodel) view.getTag();

        }
        CatagoryBean.DataBean dataBean = list.get(position);
        hodel.tv.setText(dataBean.getName());
        //判断他的点击状态
        if (dataBean.isChecked()) {
            hodel.ll.setBackgroundColor(Color.parseColor("#33000000"));

        } else {
            hodel.ll.setBackgroundColor(Color.WHITE);
        }
        return view;
    }

    class MyViewHodel {
        TextView tv;
        LinearLayout ll;
    }

    //判断是否选中
    public void changeItemSelect(int position) {
        //把每一条item的初始值设为未选中
        for (int i = 0; i < list.size(); i++) {
            CatagoryBean.DataBean dataBean = list.get(i);
            dataBean.setChecked(false);
        }
        CatagoryBean.DataBean dataBean = list.get(position);
        dataBean.setChecked(true);
        notifyDataSetChanged();
    }

}
