package com.example.admin.a1508bnsg.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.admin.a1508bnsg.R;
import com.example.admin.a1508bnsg.adapter.MyAdapter;
import com.example.admin.a1508bnsg.adapter.MyExpandLvAdapter;
import com.example.admin.a1508bnsg.bean.ClassBean;
import com.example.admin.a1508bnsg.bean.RightBean;
import com.example.admin.a1508bnsg.bean.RightChildBean;
import com.example.admin.a1508bnsg.net.Api;
import com.example.admin.a1508bnsg.net.OkHttpUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/10/09/009.
 */

public class FragmentClass extends Fragment {
    private RecyclerView classRv;
    private ExpandableListView classElv;
    private View view;
    private OkHttpUtils httpUtils;
    private Gson gson;
    private List<String> group=new ArrayList<>();
    private List<List<RightChildBean.DatasBean.ClassListBean>> child = new ArrayList<>();
    private int titlesize;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_class, container, false);
        initView();
        getLeft();
        return view;
    }

    //获取分类
    private void getLeft(){
        httpUtils.syncJsonStringByUrl(Api.CLASS, new OkHttpUtils.FuncJsonString() {

            @Override
            public void onResponse(String result) {
                ClassBean bean=gson.fromJson(result,ClassBean.class);
                if(bean.getCode()==200){
                    MyAdapter adapter=new MyAdapter(getActivity(),bean.getDatas().getClass_list());
                    classRv.setAdapter(adapter);
                    getRight(bean.getDatas().getClass_list().get(0).getGc_id());
                    adapter.setOnItemListener(new MyAdapter.OnItemListener() {
                        @Override
                        public void onItemClick(ClassBean.DatasBean.ClassListBean classListBean) {
                            String gc_id=classListBean.getGc_id();
                            getRight(gc_id);
                        }
                    });
                }

            }
        });
    }

    private void getRight(String gcid){
        String url=String.format(Api.RIGHT,gcid);
        httpUtils.syncJsonStringByUrl(url, new OkHttpUtils.FuncJsonString() {

            @Override
            public void onResponse(String result) {
                RightBean bean=gson.fromJson(result,RightBean.class);
                //防止添加重复的数据
                if(bean.getCode()==200){
                    if(group.size()>0){
                        group.clear();
                    }
                    //防止重复的数据
                    if(child.size()>0){
                        child.clear();
                    }
                    titlesize=bean.getDatas().getClass_list().size();
                    for (int i=0;i<titlesize;i++){
                        group.add(bean.getDatas().getClass_list().get(i).getGc_name());
                        String gc_id=
                                bean.getDatas().getClass_list().get(i).getGc_id();
                        getRightItem(gc_id);
                    }
                }
            }
        });
    }

    private void getRightItem(String gc_id){
        String url=String.format(Api.RIGHT,gc_id);
        httpUtils.syncJsonStringByUrl(url, new OkHttpUtils.FuncJsonString() {
            @Override
            public void onResponse(String result) {
                RightChildBean bean=gson.fromJson(result,RightChildBean.class);
                if(bean.getCode()==200){
                    int size=bean.getDatas().getClass_list().size();
                    List<RightChildBean.DatasBean.ClassListBean> l=new ArrayList<RightChildBean.DatasBean.ClassListBean>();
                    for (int i=0;i<size;i++){
                        String gc_name=bean.getDatas().getClass_list().get(i).getGc_name();
                        String gc_id=bean.getDatas().getClass_list().get(i).getGc_id();
                        RightChildBean.DatasBean.ClassListBean classListBean=new RightChildBean.DatasBean.ClassListBean();
                        classListBean.setGc_name(gc_name);
                        classListBean.setGc_id(gc_id);
                        l.add(classListBean);
                    }
                    child.add(l);
                    if(child.size()==titlesize){
                        MyExpandLvAdapter expandLvAdapter=new MyExpandLvAdapter(getActivity(),group,child);
                        classElv.setAdapter(expandLvAdapter);
                    }
                }
            }
        });
    }
    private void initView() {
        gson=new Gson();
        classRv = (RecyclerView) view.findViewById(R.id.class_rv);
        classElv = (ExpandableListView) view.findViewById(R.id.class_elv);
        classRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        classRv.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        httpUtils=OkHttpUtils.getInstance(getActivity());
    }

}
