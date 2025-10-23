package com.example.library;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class change extends AppCompatActivity {
    private EditText nameCha;
    private EditText numberCha;
    private EditText authorCha;
    private EditText priceCha;
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        Data_Init();
    }
    private void Data_Init() {
        nameCha=findViewById(R.id.change_name);
        numberCha=findViewById(R.id.change_number);
        authorCha=findViewById(R.id.change_author);
        priceCha=findViewById(R.id.change_price);
        mySQLiteOpenHelper=new MySQLiteOpenHelper(this);
    }
    public void change_btn(View v)
    {
        String cha_name=nameCha.getText().toString().trim();
        String cha_number=numberCha.getText().toString().trim();
        String cha_author= authorCha.getText().toString().trim();
        String cha_price=priceCha.getText().toString().trim();

        Library library=new Library();
        library.setCha_name(cha_name);
        library.setCha_number(cha_number);
        library.setCha_author(cha_author);
        library.setCha_price(cha_price);

        long rowID = mySQLiteOpenHelper.changeData(library);
        if (rowID>0)
        {
            Toast.makeText(this,"成功更新"+rowID+"条数据",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }else {
            Toast.makeText(this,"更新失败",Toast.LENGTH_LONG).show();
        }
    }
    public void returnMain(View v)
    {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}