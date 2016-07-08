package cn.moostar.lemon;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import java.util.List;

import cn.moostar.lemon.tools.LemonLog;
import cn.moostar.lemon.ui.LoginActivity;
import cn.moostar.lemon.ui.SplashActivity;
import cn.moostar.lemon.ui.core.JumpActivity;

/**
 * Created by Apple on 16/7/7.
 */
public class LemonApp extends Application {

    private static Context context;
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        String processName = getProcessName(this, android.os.Process.myPid());
        if (processName != null) {
//            boolean defaultProcess = processName.equals(Constants.REAL_PACKAGE_NAME);
//            if (defaultProcess) {
//
//            } else if (processName.contains(":webbrowser")) {
//
//            }

            context = this;
            initLemon();
            LemonLog.info(this,"onCreate");
        }
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }


    public static Context getContext(){
        return context;
    }

    private void initLemon(){
        JumpActivity.getSingle().addCache(SplashActivity.class);
        JumpActivity.getSingle().addCache(LoginActivity.class);
    }

    /**
     *
     * @return null may be returned if the specified process not found
     */
    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }


}
