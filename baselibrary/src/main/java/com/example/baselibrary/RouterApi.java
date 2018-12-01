package com.example.baselibrary;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;


import java.util.Enumeration;
import java.util.HashMap;

import dalvik.system.DexFile;

public class RouterApi {
    private static RouterApi routerApi;
    private Context mContext;

    private HashMap<String, Class<? extends Activity>> activityMap = new HashMap<>();

    public static RouterApi getInstance() {
        if (routerApi == null) {
            synchronized (RouterApi.class) {
                if (routerApi == null) {
                    routerApi = new RouterApi();
                }


            }
        }
        return routerApi;
    }

    public void load(Application application) {
        mContext = application;

        try {
            ApplicationInfo applicationInfo = application.getPackageManager().getApplicationInfo(application.getPackageName(), 0);
            //获取apk路径
            String apkPath = applicationInfo.sourceDir;

            DexFile dexFile = new DexFile("com.example.router");

            Log.e("xxxxxx", apkPath);
            Enumeration<String> entries = dexFile.entries();
            while (entries.hasMoreElements()) {
                String className = entries.nextElement();
                Log.e("xxxxxx", className);
                if (className.startsWith("com.example.router")) {
                    Class<?> aClass = Class.forName(className);
                    IRouter iRouter = (IRouter) aClass.newInstance();
                    iRouter.load(activityMap);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
