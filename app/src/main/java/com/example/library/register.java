package com.example.library;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class register extends AppCompatActivity {
    public static final int RESULT_CODE_REGISTER = 0;
    EditText user_name;
    EditText user_pass;
    EditText true_pass;
    String name;
    String pass;
    String truePass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init_Data();
    }
    private void init_Data() {
        user_name=findViewById(R.id.user_name);
        user_pass=findViewById(R.id.user_password);
        true_pass=findViewById(R.id.true_password);
    }//注册
    public void register(View v)
    {
        name=user_name.getText().toString();
        pass=user_pass.getText().toString();
        truePass=true_pass.getText().toString();

        if(!name.isEmpty() && !pass.isEmpty() && !truePass.isEmpty() && TextUtils.equals(pass,truePass))
        {
            Toast.makeText(register.this, "注册成功", Toast.LENGTH_SHORT).show();
            SharedPreferences spf=getSharedPreferences("spfRecorid",MODE_PRIVATE);
            SharedPreferences.Editor editor=spf.edit();
            editor.putString("Account",name);
            editor.putString("pass",truePass);

            Intent intent = new Intent();

            Bundle bundle=new Bundle();
            bundle.putString("Account",name);
            bundle.putString("pass",truePass);

            intent.putExtras(bundle);
            setResult(RESULT_CODE_REGISTER,intent);

            finish();
        }else {
            Toast.makeText(register.this,"注册失败",Toast.LENGTH_SHORT).show();
        }
    }
    //返回
    public void return_login(View v)
    {
        finish();
    }
}