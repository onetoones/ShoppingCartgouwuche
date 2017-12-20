package chenshuai.bwie.com.shoppingcart.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import chenshuai.bwie.com.shoppingcart.R;
import chenshuai.bwie.com.shoppingcart.bean.DetailBean;
import chenshuai.bwie.com.shoppingcart.view.ProductDetailsActivity;

/**
 * 姓名：陈帅
 * 类作用：
 * 日期：
 */

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<DetailBean.DataBean> list;

    public ListAdapter(Context context, List<DetailBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.list_adapter, null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
MyViewHolder holders = (MyViewHolder) holder;
        final DetailBean.DataBean dataBean = list.get(position);
        holders.name.setText(dataBean.getSubhead());
        holders.price.setText(dataBean.getPrice()+"");
        String[] split = dataBean.getImages().split("\\|");
        Glide.with(context).load(split[0]).into(holders.iv);
        holders.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("pid",dataBean.getPid()+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView iv;
        private final TextView name;
        private final TextView price;
        private final LinearLayout ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.list_iv);
            name = itemView.findViewById(R.id.tvName);
            price = itemView.findViewById(R.id.tvPrice);
            ll = itemView.findViewById(R.id.list_ll);
        }
    }

}
