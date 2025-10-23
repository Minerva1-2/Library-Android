package com.example.library;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class init extends AppCompatActivity {
private Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

    }
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            if (!isFinishing()){
                startActivity(new Intent(init.this, login.class));
                finish();
            }
        }
    };
    protected void onResume()
    {
        super.onResume();
        handler.postDelayed(runnable,3000);
    }
    protected void onPause()
    {
        super.onPause();
        handler.removeCallbacks(runnable);
    }
}