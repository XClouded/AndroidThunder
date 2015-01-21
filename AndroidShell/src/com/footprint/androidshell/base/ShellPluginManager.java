package com.footprint.androidshell.base;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import com.footprint.androidshell.entity.ShellPlugin;
import com.footprint.androidshell.interfaces.IShellResManager;

import dalvik.system.DexClassLoader;

/**
 * @author liquanmin
 *         资源管理:扫描指定路径的apk文件
 */
public class ShellPluginManager implements IShellResManager {
    private HashMap<String, ShellPlugin> pluginsMap = new HashMap<String, ShellPlugin>();

    private static ShellPluginManager manager = new ShellPluginManager();

    public static ShellPluginManager newInstance() {
        return manager;
    }

    private ShellPluginManager() {
    };

    private DexClassLoader createDexClassLoader(String dexPath, Context context) {
        File dexOutputDir = context.getDir("shelldex", Context.MODE_PRIVATE);
        final String dexOutputPath = dexOutputDir.getAbsolutePath();
        DexClassLoader loader = new DexClassLoader(dexPath, dexOutputPath, null,
                context.getClassLoader());
        return loader;
    }

    @Override
    public void addPlugin(String apkPath, Context context) {
        File file = new File(apkPath);
        if (!file.exists())
            return;

        // 读取PackageName
        PackageInfo packageInfo = context.getPackageManager().getPackageArchiveInfo(apkPath,
            PackageManager.GET_ACTIVITIES);
        if (packageInfo == null)
            return;

        String packageName = packageInfo.packageName;
        if (pluginsMap.containsKey(packageName)) {
            Log.w("AndroidShell", "The package [" + packageName + "] has existed!");
        }

        String firstActivityName = "";
        if (packageInfo.activities != null && packageInfo.activities.length > 0)
            firstActivityName = packageInfo.activities[0].name;

        // 添加资源
        AssetManager assetManager = null;
        try {
            assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, apkPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Resources superRes = context.getResources();
        Resources mResources = new Resources(assetManager, superRes.getDisplayMetrics(),
                superRes.getConfiguration());

        ShellPlugin plugin = new ShellPlugin(mResources, firstActivityName, packageName,
                createDexClassLoader(apkPath, context), assetManager);
        pluginsMap.put(packageName, plugin);
    }

    @Override
    public ShellPlugin getPlugin(String packageName) {
        return pluginsMap.get(packageName);
    }

}
