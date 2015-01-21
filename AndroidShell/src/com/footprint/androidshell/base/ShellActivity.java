package com.footprint.androidshell.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.footprint.androidshell.entity.ShellPlugin;

/**
 * @author liquanmin
 * 
 */
public class ShellActivity extends Activity {
    private String className;
    private ShellPluginManager pluginManager = ShellPluginManager.newInstance();
    private ShellPlugin plugin;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (intent != null) {
            processIntent(intent);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        Log.d("LQM", "Hello : " + layoutResID);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void processIntent(Intent intent) {
        String packageName = intent.getStringExtra(ShellIntent.KEY_PACKAGE);
        if (TextUtils.isEmpty(packageName)) {
            throw new IllegalArgumentException(
                    "The IShellIntent must carry a param with key KEY_PACKAGE");
        }

        className = intent.getStringExtra(ShellIntent.KEY_CLASSNAME);
        // 将classLoader出来
        plugin = pluginManager.getPlugin(packageName);
        if (TextUtils.isEmpty(className))
            className = plugin.firstActivityName;

        try {
            Class<?> activityClass = plugin.classLoader.loadClass(className);
            Constructor<?> localConstructor = activityClass.getConstructor(new Class[] {});
            ShellClientActivity activityInstance = (ShellClientActivity) localConstructor.newInstance(new Object[] {});

            activityInstance.setProxy(this);
            Bundle bundle = new Bundle();
            bundle.putString(ShellIntent.KEY_CLASSNAME, className);
            bundle.putString(ShellIntent.KEY_PACKAGE, packageName);
            Method methodonCreate = activityClass.getDeclaredMethod("onCreate",
                new Class[] { Bundle.class });
            methodonCreate.setAccessible(true);
            methodonCreate.invoke(activityInstance, bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public AssetManager getAssets() {
        return plugin.assetManager == null ? super.getAssets() : plugin.assetManager;
    }

    @Override
    public Resources getResources() {
        return plugin.res == null ? super.getResources() : plugin.res;
    }
}
