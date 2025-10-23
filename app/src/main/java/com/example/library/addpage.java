package com.example.library;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class addpage extends AppCompatActivity {
    private EditText nameAdd;
    private EditText numberAdd;
    private EditText authorAdd;
    private EditText priceAdd;
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpage);

        Data_init();
        mySQLiteOpenHelper=new MySQLiteOpenHelper(this);
    }
    private void Data_init() {
        nameAdd=findViewById(R.id.add_name);
        numberAdd=findViewById(R.id.add_number);
        authorAdd=findViewById(R.id.add_author);
        priceAdd=findViewById(R.id.add_price);
    }
    public void add(View v)
    {
        String name2=nameAdd.getText().toString().trim();
        String number2=numberAdd.getText().toString().trim();
        String author2= authorAdd.getText().toString().trim();
        String price2=priceAdd.getText().toString().trim();

        Library library=new Library();
        library.setNameAdd(name2);
        library.setNumberAdd(number2);
        library.setAuthorAdd(author2);
        library.setPriceAdd(price2);

        long rowID = mySQLiteOpenHelper.insertData(library);
        if (rowID!=-1)
        {
            Toast.makeText(this,"添加成功",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }else {
            Toast.makeText(this,"添加失败",Toast.LENGTH_LONG).show();
        }
    }
    public void returnMain(View v)
    {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}