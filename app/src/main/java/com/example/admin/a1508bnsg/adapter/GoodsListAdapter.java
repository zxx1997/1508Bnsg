package com.example.admin.a1508bnsg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.a1508bnsg.R;
import com.example.admin.a1508bnsg.bean.GoodlistBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by admin on 2017/10/23/023.
 */

public class GoodsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<GoodlistBean.DatasBean.GoodsListBean> list;
    private OnItemListener onItemListener;

    public GoodsListAdapter(Context context,List<GoodlistBean.DatasBean.GoodsListBean> list){
        this.context=context;
        this.list=list;
    }
    public interface OnItemListener{
        void onItemClick(GoodlistBean.DatasBean.GoodsListBean bean);
    }

    public void setOnItemListener(OnItemListener onItemListener){
        this.onItemListener=onItemListener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.goods_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final GoodlistBean.DatasBean.GoodsListBean bean=list.get(position);
        MyViewHolder viewHolder= (MyViewHolder) holder;
        ImageLoader.getInstance().displayImage(bean.getGoods_image_url(),viewHolder.iv);
        viewHolder.tv_goodsName.setText(bean.getGoods_name());
        viewHolder.tv_price.setText(bean.getGoods_price());
        viewHolder.tv_saleNum.setText(bean.getGoods_salenum());
        viewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemListener.onItemClick(bean);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }
    private class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv;
        private TextView tv_goodsName,tv_price,tv_saleNum;
        private LinearLayout ll;
        public MyViewHolder(View itemView) {
            super(itemView);
            ll=itemView.findViewById(R.id.goods_ll);
            iv=itemView.findViewById(R.id.goods_iv_watch);
            tv_goodsName=itemView.findViewById(R.id.goods_tv_goodsName);
            tv_price=itemView.findViewById(R.id.goods_tv_price);
            tv_saleNum=itemView.findViewById(R.id.goods_tv_saleNum);
        }
    }

    //刷新数据
    public void refreshData(List<GoodlistBean.DatasBean.GoodsListBean > list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    //加载更多
    public void loadMore(List<GoodlistBean.DatasBean.GoodsListBean> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

}
