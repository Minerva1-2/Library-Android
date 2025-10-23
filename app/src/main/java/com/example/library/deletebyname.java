package com.example.library;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class deletebyname extends AppCompatActivity {
    private EditText name_del;
    private EditText number_del;
    private EditText author_del;
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletebyname);

        Data_Init();
    }
    private void Data_Init() {
        name_del=findViewById(R.id.delete_name);
        number_del=findViewById(R.id.delete_number);
        author_del=findViewById(R.id.delete_author);
        mySQLiteOpenHelper=new MySQLiteOpenHelper(this);
    }
    public void delete_btn(View v)
    {
        String delName=name_del.getText().toString().trim();
        String delNumber=number_del.getText().toString().trim();
        String delAuthor=author_del.getText().toString().trim();

        int rowID = mySQLiteOpenHelper.deleteData(delName,delNumber,delAuthor);
        if (rowID>0)
        {
            Toast.makeText(this,"成功删除"+rowID+"条数据",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }else {
            Toast.makeText(this,"删除失败",Toast.LENGTH_LONG).show();
        }
    }
    public void del_returnMain(View v)
    {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}