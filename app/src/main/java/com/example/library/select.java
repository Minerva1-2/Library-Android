package com.example.library;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class select extends AppCompatActivity {
    private EditText name_sel;
    private EditText number_sel;
    private EditText author_sel;
    private TextView selList;
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        Data_INIT();
    }
    private void Data_INIT() {
        name_sel=findViewById(R.id.sel_name);
        number_sel=findViewById(R.id.sel_number);
        author_sel=findViewById(R.id.sel_author);
        selList=findViewById(R.id.sel_list);
        mySQLiteOpenHelper=new MySQLiteOpenHelper(this);
    }
    public void sel_btn(View v) {
        String sel_name = name_sel.getText().toString().trim();
        String sel_number = number_sel.getText().toString().trim();
        String sel_author = author_sel.getText().toString().trim();

        List<Library> libraries=mySQLiteOpenHelper.selectData(sel_name,sel_number,sel_author);
        String res="";
        for (Library lib:libraries) {
            res+="书名:"+lib.getName()+"\r"+"编号:"+lib.getNumber()+"\n"+"作者:"+lib.getAuthor()
                    +"\r"+"价格:"+lib.getPrice()+"元/本"+"\n"+"\n";
        }
      selList.setText(res);
    }
    public void returnMain(View v)
    {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}