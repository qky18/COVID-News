package com.java.qiukeyue.utils;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;

import java.util.List;

public class ShareNews {

    public static void shareQQFriend(Context context, String msgTitle, String msgText) {

        shareMsg(context, "com.tencent.mobileqq",
                "com.tencent.mobileqq.activity.JumpActivity", "QQ", msgTitle,
                msgText);
    }

    public static void shareWeChatFriend(Context context, String msgTitle, String msgText) {
        shareMsg(context, "com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI", "微信",
                msgTitle, msgText);
    }


    /**
     * 点击分享的代码
     *
     * @param packageName
     *            (包名,跳转的应用的包名)
     * @param activityName
     *            (类名,跳转的页面名称)
     * @param appname
     *            (应用名,跳转到的应用名称)
     * @param msgTitle
     *            (标题)
     * @param msgText
     *            (内容)
     */
    @SuppressLint("NewApi")
    private static void shareMsg(Context context, String packageName, String activityName,
                          String appname, String msgTitle, String msgText) {
        if (!packageName.isEmpty() && !isAvailable(context, packageName)) {// 判断APP是否存在
            Toast.makeText(context, "请先安装" + appname, Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
        intent.putExtra(Intent.EXTRA_TEXT, msgText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (!packageName.isEmpty()) {
            intent.setComponent(new ComponentName(packageName, activityName));
            context.startActivity(intent);
        } else {
            context.startActivity(Intent.createChooser(intent, msgTitle));
        }

        Toast.makeText(context, "分享成功",
                Toast.LENGTH_LONG).show();
    }

    /**
     * 判断相应的APP是否存在
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAvailable(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();

        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        for (int i = 0; i < pinfo.size(); i++) {
            if (pinfo.get(i).packageName.equalsIgnoreCase(packageName))
                return true;
        }
        return false;
    }
}