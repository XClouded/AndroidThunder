package com.example.androidshelltest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.footprint.androidshell.base.ShellClientActivity;

public class MainActivity extends ShellClientActivity {
    Button button;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        button = (Button) pointer.findViewById(R.id.testButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(pointer, "哎呀被点了！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
