package com.example.admin.a1508bnsg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;

import com.example.admin.a1508bnsg.R;
import com.example.admin.a1508bnsg.adapter.GoodsListAdapter;
import com.example.admin.a1508bnsg.bean.GoodlistBean;
import com.example.admin.a1508bnsg.net.Api;
import com.example.admin.a1508bnsg.net.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

/*
* 商品列表
*/
public class GoodsListActivity extends BaseActivity {

    private SwipeRefreshLayout goodsSrl;
    private RecyclerView goodsRv;
    private GoodsListAdapter adapter;
    private List<GoodlistBean.DatasBean.GoodsListBean> list=new ArrayList<>();
    private String gcid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);

        initView();
        //获取gc_id
        Intent intent=getIntent();
        gcid=intent.getStringExtra("gcid");
        getInfo(true);
    }

    private void getInfo(final boolean isRerfesh){
        httpUtil.syncJsonStringByUrl(Api.GOODS_LIST, new OkHttpUtils.FuncJsonString() {
            @Override
            public void onResponse(String result) {
                GoodlistBean bean=gson.fromJson(result,GoodlistBean.class);
                if(isRerfesh){
                    adapter.refreshData(bean.getDatas().getGoods_list());
                    goodsSrl.setRefreshing(false);
                }else{
                    adapter.loadMore(bean.getDatas().getGoods_list());
                }
            }
        });
    }

    private void initView() {
        goodsSrl = (SwipeRefreshLayout) findViewById(R.id.goods_srl);
        goodsRv = (RecyclerView) findViewById(R.id.goods_rv);
        adapter=new GoodsListAdapter(GoodsListActivity.this,list);
        goodsRv.setLayoutManager(new LinearLayoutManager(this));
        goodsRv.setAdapter(adapter);
        adapter.setOnItemListener(new GoodsListAdapter.OnItemListener() {
            @Override
            public void onItemClick(GoodlistBean.DatasBean.GoodsListBean bean) {
                Intent intent=new Intent(GoodsListActivity.this,GoodsDetailActivity.class);
                intent.putExtra("goods_id",bean.getGoods_id());
                startActivity(intent);
            }
        });

        goodsSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getInfo(true);
            }
        });
        //加载更多
        goodsRv.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) goodsRv.getLayoutManager();
                int childCount = layoutManager.getItemCount();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                /**
                 * 判断是否滑动到了底部
                 */
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    if (lastVisibleItemPosition == childCount - 1) {
                        getInfo(false);
                    }

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
}
