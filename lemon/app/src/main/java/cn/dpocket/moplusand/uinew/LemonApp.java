package cn.dpocket.moplusand.uinew;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import java.util.List;

import cn.dpocket.moplusand.uinew.tools.LemonLog;
import cn.dpocket.moplusand.uinew.ui.FavoritesActivity;
import cn.dpocket.moplusand.uinew.ui.LoginActivity;
import cn.dpocket.moplusand.uinew.ui.SplashActivity;
import cn.dpocket.moplusand.uinew.ui.core.JumpActivity;

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
//            boolean defaultProcess = processName.equals(CONSTANTS.REAL_PACKAGE_NAME);
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
        JumpActivity.getSingle().addCache(FavoritesActivity.class);
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
