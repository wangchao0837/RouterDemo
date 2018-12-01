package com.example.shopping;

import android.app.Person;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.router_annotion.Path;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Path("/shopping/MainActivity")
public class MainActivity extends AppCompatActivity implements person {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public String getName() {
//        Log.e("AAA",)
        return null;
    }

    @Override
    public int getAge() {
        return 0;
    }


}
