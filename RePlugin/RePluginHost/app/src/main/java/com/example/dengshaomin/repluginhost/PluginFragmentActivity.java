package com.example.dengshaomin.repluginhost;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.qihoo360.replugin.RePlugin;

public class PluginFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin_fragment);
        //注册相关Fragment的类
        //注册一个全局Hook用于拦截系统对XX类的寻找定向到Demo1中的XX类主要是用于在xml中可以直接使用插件中的类
        RePlugin.registerHookingClass("com.example.dengshaomin.replugindemo.MyView",
                RePlugin.createComponentName("com.example.dengshaomin.replugindemo", "com.example.dengshaomin" +
                        ".replugindemo.MyView"), null);
        setContentView(R.layout.activity_plugin_fragment);

        //代码使用插件Fragment
        ClassLoader d1ClassLoader = RePlugin.fetchClassLoader("com.example.dengshaomin.replugindemo");//获取插件的ClassLoader
        if (d1ClassLoader == null) return;
        try {
            Class aClass = d1ClassLoader.loadClass("com.example.dengshaomin.replugindemo.ItemFragment");
            String name = aClass.getSimpleName();
            LinearLayout linearLayout = d1ClassLoader.loadClass("com.example.dengshaomin" +
                    ".replugindemo.MyView").asSubclass(LinearLayout.class).newInstance();
//            Fragment fragment = d1ClassLoader.loadClass("com.example.dengshaomin" +
//                    ".replugindemo.ItemFragment").asSubclass(Fragment.class).newInstance();
            //使用插件的Classloader获取指定Fragment实例
//            getSupportFragmentManager().beginTransaction().add(R.id.container2, fragment).commit();//添加Fragment到UI
            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.container2);
            frameLayout.addView(linearLayout, new ViewGroup.LayoutParams(400, 300));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
