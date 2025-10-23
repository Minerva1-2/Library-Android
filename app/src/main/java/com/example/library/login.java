package com.example.library;

import android.accounts.Account;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class login extends AppCompatActivity {
    public static final int REQUEST_CODE_REGISTER = 1;
    private String Account="闫凯峰";
    private String Pass="2022443985";
    Button login;
    EditText userName;
    EditText userPass;
    String name,pass;
    CheckBox auto_login,remember_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Data_Init();
        checkBox();
        buttonEvent();
    }
    private void Data_Init() {
        login=findViewById(R.id.login_btn);
        userName=findViewById(R.id.user_name);
        userPass=findViewById(R.id.user_password);
        auto_login=findViewById(R.id.auto_login);
        remember_pass=findViewById(R.id.remember_pass);
    }
    public void buttonEvent()
    {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=userName.getText().toString();
                pass=userPass.getText().toString();

                if (TextUtils.isEmpty(Account)){
                    Toast.makeText(login.this, "请先注册账号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.equals(name, Account))
                {
                    if (TextUtils.equals(pass,Pass))
                    {
                        Toast.makeText(login.this, "登录成功", Toast.LENGTH_SHORT).show();
                        if (remember_pass.isChecked())
                        {
                            SharedPreferences spf=getSharedPreferences("spfRecorid",MODE_PRIVATE);
                            SharedPreferences.Editor editor=spf.edit();
                            editor.putString("Account",name);
                            editor.putString("pass",pass);
                            editor.putBoolean("isRemember",true);
                            if (auto_login.isChecked())
                            {
                                editor.putBoolean("isLogin",true);
                            }else {
                                editor.putBoolean("isLogin",false);
                            }
                            editor.apply();
                        }else{
                            SharedPreferences spf=getSharedPreferences("spfRecorid",MODE_PRIVATE);
                            SharedPreferences.Editor editor=spf.edit();
                            editor.putBoolean("isRemember",false);
                            editor.apply();
                        }
                        Intent intent = new Intent(login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else
                    {
                        Toast.makeText(login.this,"密码错误",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(login.this,"用户名错误",Toast.LENGTH_SHORT).show();
                }
            }
        });
        auto_login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    remember_pass.setChecked(true);
                }
            }
        });
        remember_pass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked)
                {
                    auto_login.setChecked(false);
                }
            }
        });
    }
    public void checkBox()
    {
        SharedPreferences spf=getSharedPreferences("spfRecorid",MODE_PRIVATE);
        boolean isRemember=spf.getBoolean("isRemember",false);
        boolean isLogin=spf.getBoolean("isLogin",false);
        String name_remember=spf.getString("Account","");
        String password_remember=spf.getString("pass","");

        if (isLogin)
        {
            Intent intent = new Intent(login.this, MainActivity.class);
            intent.putExtra("Account",name);
            startActivity(intent);
            finish();
        }
        if (isRemember==true){
            userName.setText(name_remember);
            userPass.setText(password_remember);
            remember_pass.setChecked(true);
        }
        Account=name_remember;
        Pass=password_remember;
    }
    public void toRegister(View v)
    {
        Intent intent=new Intent(login.this, register.class);

        startActivityForResult(intent,REQUEST_CODE_REGISTER);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_REGISTER && resultCode==register.RESULT_CODE_REGISTER && data!=null)
        {
            Bundle extras=data.getExtras();

            String account=extras.getString("Account","");
            String password=extras.getString("pass","");

            userName.setText(account);
            userPass.setText(password);

            Account=account;
            Pass=password;
        }
    }
}