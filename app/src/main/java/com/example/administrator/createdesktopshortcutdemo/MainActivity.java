package com.example.administrator.createdesktopshortcutdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Intent shortcutIntent;
    private Intent addIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ShortcutIcon();
    }

    /**
     * 创建桌面图标
     */
    private void ShortcutIcon() {
        shortcutIntent = new Intent(getApplicationContext(), MainActivity.class);
        shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);                                  // Intent.FLAG_ACTIVITY_NEW_TASK的初衷是在Activity目标taskAffinity的Task中启动, 非Activity启动Activity都必须添加Intent.FLAG_ACTIVITY_NEW_TASK
        shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);                                 // 如果同时使用了FLAG_ACTIVITY_NEW_TASK, 这个时候 目标是Activity自己所属的Task栈, 如果在自己的Task中能找到一个Activity实例, 则将其上面的及自身清理掉, 之后重建

        addIntent = new Intent();
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);                        // 把捷徑要做的intent加入
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, getString(R.string.app_name));           // 設定捷徑名稱
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,                                // 設定圖示
                Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.mipmap.ic_launcher));
        addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");                        // 設定安裝捷徑動作
        getApplicationContext().sendBroadcast(addIntent);                                           // 傳送廣播建立捷徑
    }
}
