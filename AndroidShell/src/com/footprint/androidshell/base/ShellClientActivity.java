package com.footprint.androidshell.base;

import android.app.Activity;
import android.content.Context;

import com.footprint.androidshell.interfaces.IShellClientActivity;

public class ShellClientActivity extends Activity implements IShellClientActivity {
    protected Activity proxyActivity;
    protected Activity pointer = this;

    @Override
    public void setProxy(Context context) {
        proxyActivity = (Activity) context;
        pointer = proxyActivity;
    }

    @Override
    public void setContentView(int layoutResID) {
        pointer.setContentView(layoutResID);
    }
}
