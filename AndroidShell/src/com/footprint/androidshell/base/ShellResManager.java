package com.footprint.androidshell.base;

public class ShellResManager {
    ShellPluginManager pluginManager;
    
    public ShellPluginManager getPluginManager(){
        return ShellPluginManager.newInstance();
    }
}
