package com.footprint.androidshell.interfaces;

import android.content.Context;

import com.footprint.androidshell.entity.ShellPlugin;

public interface IShellResManager {
    /**
     * 扫描apkPath下面的apk包，获取apk信息，使之就绪
     * 
     * @param apkPath
     *            apk路径
     */
    public void addPlugin(String apkPath, Context context);

    /**
     * 根据包名获取相应地插件
     * 
     * @param packageName
     *            包名，例如：com.footprint.androidshell
     */
    public ShellPlugin getPlugin(String packageName);
}
