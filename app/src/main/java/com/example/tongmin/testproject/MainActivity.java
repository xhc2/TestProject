package com.example.tongmin.testproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button bt ;
    MyView myView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        bt = (Button)findViewById(R.id.text);
        myView = (MyView)findViewById(R.id.tv);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 int where = (int)(Math.random() *1000) % 9;
//                myView.scrollToChild(where);
            }
        });
    }

}
