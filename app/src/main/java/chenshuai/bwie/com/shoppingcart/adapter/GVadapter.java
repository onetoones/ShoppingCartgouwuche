package chenshuai.bwie.com.shoppingcart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import chenshuai.bwie.com.shoppingcart.R;
import chenshuai.bwie.com.shoppingcart.bean.ProCatagoryBean;

//import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 姓名：陈帅
 * 类作用：
 * 日期：
 */

public class GVadapter extends BaseAdapter {
    private Context context;
    private List<ProCatagoryBean.DataBean.ListBean> list;
    private final LayoutInflater inflater;

    public GVadapter(Context context, List<ProCatagoryBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
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
        ViewHolder holder;
        View view;
        if (convertView == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.gv_item, null);
            holder.sv = view.findViewById(R.id.iv);
            holder.tv = view.findViewById(R.id.tv);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        ProCatagoryBean.DataBean.ListBean listBean = list.get(position);
        String[] split = list.get(position).getIcon().split("\\|");
        Glide.with(context).load(split[0]).into(holder.sv);
        holder.tv.setText(listBean.getName());

        return view;
    }

    class ViewHolder {
        ImageView sv;
        TextView tv;
    }
}
