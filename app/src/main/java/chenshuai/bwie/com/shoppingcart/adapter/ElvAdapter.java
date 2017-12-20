package chenshuai.bwie.com.shoppingcart.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import chenshuai.bwie.com.shoppingcart.R;
import chenshuai.bwie.com.shoppingcart.bean.GoodsCardBean;
import chenshuai.bwie.com.shoppingcart.bean.PriceAndCount;
import chenshuai.bwie.com.shoppingcart.view.GoodsCardActivity;
import chenshuai.bwie.com.shoppingcart.view.IDelCard;

/**
 * 姓名：陈帅
 * 类作用：
 * 日期：
 */

public class ElvAdapter extends BaseExpandableListAdapter implements IDelCard {
    private Context context;
    private List<GoodsCardBean.DataBean> group;
    private List<List<GoodsCardBean.DataBean.ListBean>> child;
    private int gPosition;
    private int cPosition;

    public ElvAdapter(Context context, List<GoodsCardBean.DataBean> group, List<List<GoodsCardBean.DataBean.ListBean>> child) {
        this.context = context;
        this.group = group;
        this.child = child;
    }

    @Override
    public int getGroupCount() {
        return group.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return child.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view;
        final GroupViewHolder holder;
        if (convertView == null) {
            holder = new GroupViewHolder();
            view = View.inflate(context, R.layout.elv_group, null);
            holder.cbGroup = view.findViewById(R.id.cbGroup);
            holder.tv = view.findViewById(R.id.tvGroup);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (GroupViewHolder) view.getTag();
        }
        final GoodsCardBean.DataBean bean = group.get(groupPosition);
        holder.tv.setText(bean.getSellerName());
        holder.cbGroup.setChecked(bean.isChecked());
        //一级列表的点击事件
        holder.cbGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bean.setChecked(holder.cbGroup.isChecked());
                setChildsChecked(groupPosition, holder.cbGroup.isChecked());
                //如果某个一级列表的二级列表全部选中，则要判断其它的一级列表是否都选中，去改变“全选”状态
                ((GoodsCardActivity) context).setAllSelect(isGroupCbChecked());

                setPriceAndCount();
                //改变后刷新
                notifyDataSetChanged();
            }
        });


        return view;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view;
        final ChildViewHolder holder;
        if (convertView == null) {
            holder = new ChildViewHolder();
            view = View.inflate(context, R.layout.elv_childe, null);
            holder.iv = view.findViewById(R.id.iv);
            holder.tvTitle = view.findViewById(R.id.tvTitle);
            holder.tvSubhead = view.findViewById(R.id.tvSubhead);
            holder.tvPrice = view.findViewById(R.id.tvPrice);
            holder.cbChild = view.findViewById(R.id.cbChild);
            holder.btDel = view.findViewById(R.id.btDel);
            holder.tvNum = view.findViewById(R.id.tvNum);
            holder.ivDel = view.findViewById(R.id.ivDel);
            holder.ivAdd = view.findViewById(R.id.ivAdd);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ChildViewHolder) view.getTag();
        }
        final GoodsCardBean.DataBean.ListBean bean = child.get(groupPosition).get(childPosition);
        String[] split = bean.getImages().split("\\|");
        Glide.with(context).load(split[0]).into(holder.iv);
        holder.tvTitle.setText(bean.getTitle());
        holder.cbChild.setChecked(bean.isCheckeds());
        holder.tvSubhead.setText(bean.getSubhead());
        holder.tvPrice.setText(bean.getPrice() + "元");
        holder.tvNum.setText(bean.getCount() + "");

      /*  holder.btDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gPosition = groupPosition;
                cPosition = childPosition;
                SharedPreferences sp = context.getSharedPreferences("user", Context.MODE_PRIVATE);
                String uid = sp.getString("uid", "");
                String pid = sp.getString("pid", "");



            }
        });*/
        //给减号设置点击事件
        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int c = bean.getCount();
                if (c <= 1) {
                    c = 1;
                } else {
                    c--;
                }
                setNum(bean, holder, c);
            }
        });
        //给加号设置点击事件
        holder.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int c = bean.getCount();
                c++;
                setNum(bean, holder, c);
            }
        });

        //给二级列表的复选框添加点击事件
        holder.cbChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击的时候给checkbox设置对应的值
                bean.setCheckeds(holder.cbChild.isChecked());
                group.get(groupPosition).setChecked(isChildsCbChecked(groupPosition));
                ((GoodsCardActivity) context).setAllSelect(isGroupCbChecked());
                setPriceAndCount();
                notifyDataSetChanged();
            }
        });


/////////////////////////////////////////////////
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    //继承的接口
    @Override
    public void Onsuccess() {
        Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
        List<GoodsCardBean.DataBean.ListBean> listBeens = child.get(gPosition);
        if (listBeens != null && listBeens.size() > 0) {
            listBeens.remove(cPosition);
        }
        if (listBeens != null && listBeens.size() == 0) {
            child.remove(gPosition);
            group.remove(gPosition);
        }
        //计算钱和数量
        setPriceAndCount();
        //如果某个一级列表下的二级列表全部选中时，则要判断其它的一级列表是否都选中，去改变“全选”状态
        ((GoodsCardActivity) context).setAllSelect(isGroupCbChecked());
        notifyDataSetChanged();

    }

    //判断钱和数量

    private void setPriceAndCount() {
        PriceAndCount priceAndCount = computePrice();
        ((GoodsCardActivity) context).setMoney(priceAndCount.getPrice());
        ((GoodsCardActivity) context).setCount(priceAndCount.getCount());

    }

    //计算钱
    private PriceAndCount computePrice() {
        double sum = 0;
        int count = 0;
        for (int i = 0; i < child.size(); i++) {
            List<GoodsCardBean.DataBean.ListBean> listBeens = child.get(i);
            for (int j = 0; j < listBeens.size(); j++) {
                GoodsCardBean.DataBean.ListBean listBean = listBeens.get(j);
                //表示是否选中
                if (listBean.isCheckeds()) {
                    count += listBean.getCount();
                    sum += listBean.getPrice() * listBean.getCount();
                }
            }

        }
        return new PriceAndCount(sum, count);
    }

    //判断同一商家的自列表是否都选中
    private boolean isChildsCbChecked(int groupPosition) {
        List<GoodsCardBean.DataBean.ListBean> listBeens = child.get(groupPosition);
        for (int i = 0; i < listBeens.size(); i++) {
            GoodsCardBean.DataBean.ListBean listBean = listBeens.get(i);
            if (!listBean.isCheckeds()) {
                return false;
            }

        }
        return true;
    }


    //改变子列表中的所有checkbox的状态
    private void setChildsChecked(int groupPosition, boolean bool) {
        List<GoodsCardBean.DataBean.ListBean> listBeens = child.get(groupPosition);
        for (int i = 0; i < listBeens.size(); i++) {
            GoodsCardBean.DataBean.ListBean listBean = listBeens.get(i);
            listBean.setCheckeds(bool);


        }

    }

    //点击全选的操作过程
    public void setAllchecked(boolean bool) {
        for (int i = 0; i < group.size(); i++) {
            GoodsCardBean.DataBean dataBean = group.get(i);
            dataBean.setChecked(bool);
            //这是改变二季列表的选中
            setChildsChecked(i, bool);
        }
        setPriceAndCount();
        notifyDataSetChanged();
    }

    //判断一级列表是否全部选中
    private boolean isGroupCbChecked() {
        if (group.size() == 0) {
            return false;
        }
        for (int i = 0; i < group.size(); i++) {
            GoodsCardBean.DataBean dataBean = group.get(i);
            if (!dataBean.isChecked()) {
                return false;
            }
        }
        return true;
    }

    private void setNum(GoodsCardBean.DataBean.ListBean listBean, ChildViewHolder holder, int c) {
        //改变bean里的那个值
        listBean.setCount(c);
        holder.tvNum.setText(c + "");
        //计算钱和数量，并显示
        setPriceAndCount();
        //刷新页面
        notifyDataSetChanged();
    }

    ////////////////////////////////////////////////
    //一级列表
    class GroupViewHolder {
        CheckBox cbGroup;
        TextView tv;
    }

    //二级列表
    class ChildViewHolder {

        CheckBox cbChild;
        //商品图片
        ImageView iv;
        //标题
        TextView tvTitle;
        //内容
        TextView tvSubhead;
        //价格
        TextView tvPrice;
        //删除键
        Button btDel;
        //数量
        TextView tvNum;
        //减号
        ImageView ivDel;
        //加号
        ImageView ivAdd;

    }
}
