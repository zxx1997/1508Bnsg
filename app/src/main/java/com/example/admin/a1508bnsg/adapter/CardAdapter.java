package com.example.admin.a1508bnsg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.a1508bnsg.bean.CardBean;

import java.util.List;

/**
 * Created by admin on 2017/10/25/025.
 */

public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<CardBean.DatasBean.CartListBean.GoodsBean> list;

    public CardAdapter(Context context,List<CardBean.DatasBean.CartListBean.GoodsBean> list){
        this.context=context;
        this.list=list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{


        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
