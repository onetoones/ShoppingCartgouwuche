package chenshuai.bwie.com.shoppingcart.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 姓名：陈帅
 * 类作用：
 * 日期：
 */

public class VpDetailsAdapter extends FragmentPagerAdapter{
   List<Fragment> list;
    public VpDetailsAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        this.list = list;
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
