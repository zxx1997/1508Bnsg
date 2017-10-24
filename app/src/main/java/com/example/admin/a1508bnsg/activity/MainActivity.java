package com.example.admin.a1508bnsg.activity;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.example.admin.a1508bnsg.R;
import com.example.admin.a1508bnsg.fragment.FragmentCart;
import com.example.admin.a1508bnsg.fragment.FragmentClass;
import com.example.admin.a1508bnsg.fragment.FragmentIndex;
import com.example.admin.a1508bnsg.fragment.FragmentUser;

public class MainActivity extends FragmentActivity {

    private RadioGroup rg;
    private FragmentManager manager;
    private RadioButton rb1,rb2,rb3,rb4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager=getSupportFragmentManager();

        manager.beginTransaction().replace(R.id.fl,new FragmentIndex()).commit();

        initView();
    }

    private void initView() {
        rg=findViewById(R.id.rg);
        rb1=findViewById(R.id.rb1);
        rb2=findViewById(R.id.rb2);
        rb3=findViewById(R.id.rb3);
        rb4=findViewById(R.id.rb4);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case R.id.rb1:
                        getFragment(new FragmentIndex());
                        break;
                    case R.id.rb2:
                        getFragment(new FragmentClass());
                        break;
                    case R.id.rb3:
                        getFragment(new FragmentCart());
                        break;
                    case R.id.rb4:
                        getFragment(new FragmentUser());
                        break;
                }
            }
        });
    }
    //接受回调传值
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FragmentUser fu = null;
        //使用bundle传递给fragment
        if(data!=null){
            String user=data.getStringExtra("u");
            Bundle bundle=new Bundle();
            bundle.putString("user",user);
            fu=new FragmentUser();
            fu.setArguments(bundle);
            if(requestCode==1&&resultCode==2){
                //fu=new FragmentUser();
                getFragment(new FragmentUser());
                manager.beginTransaction().replace(R.id.fl,fu).commit();
            }
        }
        else{
            if(requestCode==1&&resultCode==2){
                fu=new FragmentUser();
                //getFragment(new FragmentUser());
                manager.beginTransaction().replace(R.id.fl,fu).commit();
            }
        }


    }

    private void getFragment(Fragment fragment){
        manager.beginTransaction().replace(R.id.fl,fragment).commit();
    }
}
