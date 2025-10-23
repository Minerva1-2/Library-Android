package com.example.library;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private List<Map<String,Object>> mList;
    private SimpleAdapter mSimpleAdapter;
    private int img=R.drawable.list_library;
    private BottomNavigationView mNav;
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Data_Init();
        createLibrary();
        mNav.setOnNavigationItemSelectedListener(nNavView);
    }
    public void Data_Init(){
        mListView=findViewById(R.id.library_list);
        mNav=findViewById(R.id.NavView);
        mySQLiteOpenHelper=new MySQLiteOpenHelper(this);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener nNavView=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem menuItem) {

            if(menuItem.getItemId()==R.id.add_nav){
                startActivity(new Intent(MainActivity.this, addpage.class));
                finish();
            }else if(menuItem.getItemId()==R.id.delete_nav)
            {
                startActivity(new Intent(MainActivity.this, deletebyname.class));
                finish();
            } else if (menuItem.getItemId()==R.id.change_nav) {
                startActivity(new Intent(MainActivity.this, change.class));
                finish();
            } else if (menuItem.getItemId()==R.id.select_nav) {
                startActivity(new Intent(MainActivity.this, select.class));
                finish();
            }
            return true;
        }
    };
    public void createLibrary() {
        List<Library> libraries=mySQLiteOpenHelper.selectAll();
        mList = new ArrayList<>();

        for (Library lib: libraries) {
            Map<String,Object> map=new HashMap<>();
            map.put("img",img);
            map.put("title", "书名:"+lib.getName());
            map.put("content","编号:"+lib.getNumber()+"\n"+"作者:"+lib.getAuthor()+"\n"+"价格:"+lib.getPrice()+"元/本");
            mList.add(map);
        }
        mSimpleAdapter = new SimpleAdapter(
                this,
                mList,
                R.layout.layout_library,
                new String[]{"img","title","content"},
                new int[]{R.id.list_lib, R.id.name, R.id.number}
                );
        mListView.setAdapter(mSimpleAdapter);
    }
    //退出按钮
    public void logout(View v)
    {
        SharedPreferences spf=getSharedPreferences("spfRecorid",MODE_PRIVATE);
        SharedPreferences.Editor editor=spf.edit();
        editor.putBoolean("isLogin",false);
        editor.apply();
        Intent intent=new Intent(this, login.class);
        startActivity(intent);
        finish();
    }
}