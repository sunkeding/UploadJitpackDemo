package study.sunkeding.com.lib;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * Created by skd .
 * <p>
 * 获取app版本，包信息等跟app相关的一些方法
 */

public class AppUtils {

    /**
     * @return
     * @author ygh 获取包信息
     */
    public static final PackageInfo getPackageInfo(Context context) {
        PackageManager manager = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(context
                    .getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return info;
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static String getVersion(Context context) {
        try {
            PackageInfo info = getPackageInfo(context);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取 versionCode
     *
     * @return
     */
    public static int getVersionCode(Context context) {
        try {
            PackageInfo pi = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 获取应用名。
     *
     * @return
     */
    public static String getAppName(Context context) {
        CharSequence appName = context.getPackageManager()
                .getApplicationLabel(context.getApplicationInfo());
        if (appName == null) {
            appName = "";
        }
        return appName.toString();
    }

    public static String getPackageName(Context context) {
        return context.getPackageName();
    }

    /**
     * 获取进程名称
     */
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {

                return appProcess.processName;
            }
        }
        return "";
    }

    /**
     * App 当前是否处于前台。
     */
    public static boolean isForeground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }


    /**
     * 判断是否已经安装了该包名应用
     */
    public static boolean isInstalled(Context context, String packageName) {
        PackageInfo packageInfo = null;

        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException var3) {
            packageInfo = null;
            return false;
        }

        return packageInfo != null;
    }
}
