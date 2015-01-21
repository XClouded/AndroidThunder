package com.footprint.androidshell.test;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import com.footprint.androidshell.R;
import com.footprint.androidshell.base.ShellActivity;
import com.footprint.androidshell.base.ShellIntent;
import com.footprint.androidshell.base.ShellPluginManager;

public class DemoActivity extends Activity {
    Button shellButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        
        ShellPluginManager.newInstance().addPlugin(Environment.getExternalStorageDirectory() + "/androidshell/AndroidShellTest.apk", this);
        
        shellButton = (Button)findViewById(R.id.shellButton);
        shellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShellIntent intent = new ShellIntent();
                intent.setClass(DemoActivity.this, ShellActivity.class);
                intent.putExtra(ShellIntent.KEY_PACKAGE, "com.example.androidshelltest");
                intent.putExtra(ShellIntent.KEY_CLASSNAME, "com.example.androidshelltest.MainActivity");
                startActivity(intent);
            }
        });
    }
}
