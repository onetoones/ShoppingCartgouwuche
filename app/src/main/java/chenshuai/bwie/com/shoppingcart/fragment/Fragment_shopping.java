package chenshuai.bwie.com.shoppingcart.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import chenshuai.bwie.com.shoppingcart.R;

/**
 * Created by 不将就 on 2017/12/4.
 */

public class Fragment_shopping extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shopping,null);


        return view;
    }
}
