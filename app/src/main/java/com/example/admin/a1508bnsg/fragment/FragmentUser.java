package com.example.admin.a1508bnsg.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.a1508bnsg.R;
import com.example.admin.a1508bnsg.activity.LoginActivity;

/**
 * Created by admin on 2017/10/09/009.
 */

public class FragmentUser extends Fragment{
    private LinearLayout ll;
    private TextView user_name;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_user,container,false);
        ll=view.findViewById(R.id.login);
        user_name=view.findViewById(R.id.user_name);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivityForResult(intent,1);
            }
        });
        //接受传值
        Bundle bundle=getArguments();
        if(bundle!=null){
            String user=bundle.getString("user");
            user_name.setText(user);
        }
        return view;
    }
}
