package com.example.myapplication.AllActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.myapplication.R;

public class screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);

    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            final Background bb=new Background(screen.this);



            if (bb.getName()!="")
            {
                Intent ii=new Intent(screen.this, dashbord.class);
                startActivity(ii);



            }
            else
            {
                Intent i=new Intent(screen.this, MainActivity.class);
                startActivity(i);





            }
        }
    },3000);


    }
}
