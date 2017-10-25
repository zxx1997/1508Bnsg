package com.example.admin.a1508bnsg.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.admin.a1508bnsg.R;
import com.example.admin.a1508bnsg.bean.CardBean;
import com.example.admin.a1508bnsg.net.Api;
import com.example.admin.a1508bnsg.net.OkHttpUtils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/10/09/009.
 */

public class FragmentCart extends Fragment {
    private RecyclerView cardRv;
    private CheckBox cardCb;
    private TextView cardTvContent;
    private Button cardBt;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart, container, false);
        initView();

        EventBus.getDefault().register(getActivity());


        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(getActivity());
    }

    private void getInfo(){
        Map<String,String> map=new HashMap<>();
        map.put("key",getActivity().getSharedPreferences("nsg", Context.MODE_PRIVATE).getString("key",""));
        OkHttpUtils.getInstance(getActivity()).sendDataForClicent(Api.CARD, map, new OkHttpUtils.FuncJsonString() {
            @Override
            public void onResponse(String result) {
                CardBean bean=new Gson().fromJson(result,CardBean.class);

            }
        });
    }
    private void initView() {
        cardRv = (RecyclerView)view.findViewById(R.id.card_rv);
        cardCb = (CheckBox) view.findViewById(R.id.card_cb);
        cardTvContent = (TextView) view.findViewById(R.id.card_tvContent);
        cardBt = (Button) view.findViewById(R.id.card_bt);

    }
}
