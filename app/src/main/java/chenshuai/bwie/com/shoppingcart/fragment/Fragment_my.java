package chenshuai.bwie.com.shoppingcart.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import chenshuai.bwie.com.shoppingcart.R;
import chenshuai.bwie.com.shoppingcart.view.LoginActivity;

/**
 * Created by 不将就 on 2017/12/4.
 */

public class Fragment_my extends Fragment implements View.OnClickListener {
    private View view;
    private ImageView mFrIv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my, null);


        initView(view);
        return view;
    }

    private void initView(View view) {
        mFrIv = (ImageView) view.findViewById(R.id.fr_iv);
        mFrIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fr_iv:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
