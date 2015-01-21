package com.footprint.androidshell.interfaces;

public interface IShellIntent {
    public String KEY_PACKAGE = "shell_package";
    public String KEY_CLASSNAME = "shell_class_full_name";
    
    /**
     * @return 加载的包名
     */
    public String getPackageName();

    /**
     * @return 需要加载的完整的类名
     */
    public String getClassFullName();
}
