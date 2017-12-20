package chenshuai.bwie.com.shoppingcart.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import chenshuai.bwie.com.shoppingcart.R;
import chenshuai.bwie.com.shoppingcart.bean.ProCatagoryBean;
import chenshuai.bwie.com.shoppingcart.view.ListActivity;

/**
 * 姓名：陈帅
 * 类作用：
 * 日期：
 */

public class ProCatagoryAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<ProCatagoryBean.DataBean> group;
    private List<List<ProCatagoryBean.DataBean.ListBean>> child;

    public ProCatagoryAdapter(Context context, List<ProCatagoryBean.DataBean> group, List<List<ProCatagoryBean.DataBean.ListBean>> child) {
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
        return 1;
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
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder1 holder1;
        View view;
        if (convertView == null) {
            holder1 = new ViewHolder1();
            view = View.inflate(context, R.layout.group_item, null);
            holder1.tv = view.findViewById(R.id.gr_tv);
            view.setTag(holder1);

        } else {
            view = convertView;
            holder1 = (ViewHolder1) view.getTag();
        }
        ProCatagoryBean.DataBean dataBean = group.get(groupPosition);
        holder1.tv.setText(dataBean.getName());
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder2 holder2;
        View view;
        if (convertView == null) {
            holder2 = new ViewHolder2();
            view = View.inflate(context, R.layout.gridviw_item, null);
            holder2.gv = view.findViewById(R.id.gv);
            view.setTag(holder2);

        } else {
            view = convertView;
            holder2 = (ViewHolder2) view.getTag();
        }
        final List<ProCatagoryBean.DataBean.ListBean> listBeen = child.get(groupPosition);
        GVadapter vadapter = new GVadapter(context, listBeen);
        holder2.gv.setAdapter(vadapter);
        holder2.gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "position:" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ListActivity.class);
                //把cid传过去
                intent.putExtra("pscid",listBeen.get(position).getPcid()+"");
                context.startActivity(intent);



            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ViewHolder1 {
        TextView tv;

    }

    class ViewHolder2 {
        GridView gv;

    }

}
