package com.example.library;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class login extends AppCompatActivity {
    Button login;
    EditText username;
    EditText userPass;
    TextView forget_pass;
    TextView no_count;
    String name,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Data_Init();
    }
    private void Data_Init() {
        login=findViewById(R.id.login_btn);
        username=findViewById(R.id.user_name);
        userPass=findViewById(R.id.user_password);
        forget_pass=findViewById(R.id.forget_pass);
        no_count=findViewById(R.id.no_count);

        name=username.getText().toString();
        pass=userPass.getText().toString();
    }
    public void login(View view)
    {
        if( name.isEmpty()&& pass.isEmpty())
        {
            Toast.makeText(this,"登陆成功",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this, MainActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this,"登陆失败",Toast.LENGTH_SHORT).show();
        }
    }
    public void user_register()
    {

    }
    public void change_pass()
    {

    }
}