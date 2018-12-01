package com.example.homepage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.router_annotion.Path;

@Path("/homepage/MainActivity")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
