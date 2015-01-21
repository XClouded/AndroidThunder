package com.footprint.androidshell.base;

import android.content.Intent;

import com.footprint.androidshell.interfaces.IShellIntent;

public class ShellIntent extends Intent implements IShellIntent {

    @Override
    public String getClassFullName() {
        return this.getStringExtra(KEY_CLASSNAME);
    }

    @Override
    public String getPackageName() {
        return this.getStringExtra(KEY_PACKAGE);
    }
}
