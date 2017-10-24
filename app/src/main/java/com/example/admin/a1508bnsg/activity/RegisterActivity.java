package com.example.admin.a1508bnsg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.admin.a1508bnsg.R;
import com.example.admin.a1508bnsg.bean.RegBean;
import com.example.admin.a1508bnsg.net.Api;
import com.example.admin.a1508bnsg.net.OkHttpUtils;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends BaseActivity {

    private ImageView regBack;
    private EditText regUsername;
    private EditText regPass;
    private EditText regPasstrue;
    private EditText regEmail;
    private Button regreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

        regBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        regreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=regUsername.getText().toString().trim();
                String pass=regPass.getText().toString().trim();
                String passtrue=regPasstrue.getText().toString().trim();
                String email=regEmail.getText().toString().trim();
                if(checkInfo(name,pass,passtrue,email)){
                    showPd();
                    Map<String,String> map=new HashMap<String, String>();
                    map.put("username",name);
                    map.put("password",pass);
                    map.put("password_confirm",passtrue);
                    map.put("email",email);
                    map.put("client","android");
                    httpUtil.sendDataForClicent(Api.REGISTER,map, new OkHttpUtils.FuncJsonString() {

                        @Override
                        public void onResponse(String result) {
                            dismissPd();
                            RegBean bean=gson.fromJson(result,RegBean.class);
                            int code=bean.getCode();
                            if(code==200){
                                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                                startActivity(intent);
                                RegisterActivity.this.finish();
                            }else{
                                String error=bean.getDatas().getError();
                                //toast("注册失败");
                                toast(error);
                            }

                        }
                    });
                }

            }
        });
    }

    private boolean checkInfo(String name,String pass,String passtrue,String email){
        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(pass)||TextUtils.isEmpty(passtrue)||TextUtils.isEmpty(email)){
            toast("请输入内容");
            return false;
        }
        return true;
    }
    private void initView() {
        regBack = (ImageView) findViewById(R.id.reg_back);
        regUsername = (EditText) findViewById(R.id.reg_username);
        regPass = (EditText) findViewById(R.id.reg_pass);
        regPasstrue = (EditText) findViewById(R.id.reg_passtrue);
        regEmail = (EditText) findViewById(R.id.reg_email);
        regreg= (Button) findViewById(R.id.reg_reg);
    }
}
