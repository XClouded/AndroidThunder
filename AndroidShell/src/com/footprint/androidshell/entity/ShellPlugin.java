package com.footprint.androidshell.entity;

import android.content.res.AssetManager;
import android.content.res.Resources;
import dalvik.system.DexClassLoader;

public class ShellPlugin {
    public Resources res;
    public String firstActivityName;// 第一个Activity的具体包名+类名
    public String packageName;
    public DexClassLoader classLoader;
    public AssetManager assetManager;
    
    public ShellPlugin(Resources res, String firstActivityName, String packageName,
            DexClassLoader classLoader, AssetManager assetManager) {
        super();
        this.res = res;
        this.firstActivityName = firstActivityName;
        this.packageName = packageName;
        this.classLoader = classLoader;
        this.assetManager = assetManager;
    }
}
