package com.example.admin.a1508bnsg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.a1508bnsg.R;
import com.example.admin.a1508bnsg.bean.RegBean;
import com.example.admin.a1508bnsg.net.Api;
import com.example.admin.a1508bnsg.net.OkHttpUtils;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseActivity {


    private ImageView loginBack;
    private EditText loginUser;
    private EditText loginPass;
    private Button loginLogin;
    private TextView loginZc;
    private TextView loginZh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        loginZc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //通过回调传值回去
                String name=loginUser.getText().toString().trim();
                String pass=loginPass.getText().toString().trim();
                if(checkInfo(name,pass)){
                    showPd();
                    Map<String,String> map=new HashMap<String, String>();
                    map.put("username",name);
                    map.put("password",pass);
                    map.put("client","android");
                    httpUtil.sendDataForClicent(Api.LOGIN,map, new OkHttpUtils.FuncJsonString() {

                        @Override
                        public void onResponse(String result) {
                            dismissPd();
                            RegBean bean=gson.fromJson(result,RegBean.class);
                            int code=bean.getCode();
                            if(code==200){
                                String user=bean.getDatas().getUsername();
                                Intent intent = new Intent();
                                intent.putExtra("u", user);
                                setResult(2, intent);
                                finish();
                            }else{
                                toast("登录失败");
                            }

                        }
                    });
                }

            }
        });
        loginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setResult(2);
                finish();
            }
        });

    }
    private boolean checkInfo(String name,String pass){
        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(pass)){
            toast("请输入内容");
            return false;
        }
        return true;
    }
    private void initView() {
        loginBack = (ImageView) findViewById(R.id.login_back);
        loginUser = (EditText) findViewById(R.id.login_user);
        loginPass = (EditText) findViewById(R.id.login_pass);
        loginLogin = (Button) findViewById(R.id.login_login);
        loginZc = (TextView) findViewById(R.id.login_zc);
        loginZh = (TextView) findViewById(R.id.login_zh);
    }
}
