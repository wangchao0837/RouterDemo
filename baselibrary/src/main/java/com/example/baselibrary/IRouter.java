package com.example.baselibrary;

import android.app.Activity;


import java.util.HashMap;
public interface IRouter {
    public void load(HashMap<String, Class<? extends Activity>> activityMap);
}
