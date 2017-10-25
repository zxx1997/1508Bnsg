package com.example.admin.a1508bnsg.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.a1508bnsg.R;
import com.example.admin.a1508bnsg.bean.GoodsDetailBean;
import com.example.admin.a1508bnsg.net.Api;
import com.example.admin.a1508bnsg.net.OkHttpUtils;
import com.example.admin.a1508bnsg.widget.AddDelView;
import com.nostra13.universalimageloader.core.ImageLoader;

public class GoodsDetailActivity extends BaseActivity {

    private RelativeLayout goodsR1;
    private ImageView goodsIv;
    private TextView goodsTvGoodsMsg;
    private ImageView goodsImageView;
    private LinearLayout goodsLlToCard;
    private LinearLayout goodsLlAddCard;
    private ImageView goodsImageView2;
    private String goods_id;
    private GoodsDetailBean bean;
    private PopupWindow popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        initView();
        Intent intent=getIntent();
        goods_id=intent.getStringExtra("goods_id");

        getInfo();
    }

    private void getInfo(){
        String url=String.format(Api.GOODS_DETAILS,goods_id);
        httpUtil.syncJsonStringByUrl(url, new OkHttpUtils.FuncJsonString() {
            @Override
            public void onResponse(String result) {
                bean=gson.fromJson(result,GoodsDetailBean.class);
                ImageLoader.getInstance().displayImage(bean.getDatas().getSpec_image().get(0),goodsIv);
                goodsTvGoodsMsg.setText(bean.getDatas().getGoods_info().getGoods_name());

            }
        });
    }
    private void initView() {
        goodsR1 = (RelativeLayout) findViewById(R.id.goods_r1);
        goodsIv = (ImageView) findViewById(R.id.goods_iv);
        goodsTvGoodsMsg = (TextView) findViewById(R.id.goods_tv_goodsMsg);
        goodsImageView = (ImageView) findViewById(R.id.goods_imageView);
        goodsLlToCard = (LinearLayout) findViewById(R.id.goods_ll_toCard);
        goodsLlAddCard = (LinearLayout) findViewById(R.id.goods_ll_addCard);
        goodsImageView2 = (ImageView) findViewById(R.id.goods_imageView2);

        goodsLlAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }
    private void showPop(){
        View view=View.inflate(this,R.layout.goods_pop_item,null);
        View bt_add=view.findViewById(R.id.goods_bt_add);
        AddDelView adv=view.findViewById(R.id.goods_adv);
        popupWindow=new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,60);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(goodsR1, Gravity.BOTTOM,0,0);
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    /*
    添加到购物车
     */

}
