package cn.dpocket.moplusand.uinew.ui.core;

import android.app.Activity;

import java.lang.ref.WeakReference;
import java.util.Stack;

/**
 * Created by Apple on 16/7/7.
 */
public class CoreActivity {

    public static final int CREATE = 0;
    public static final int NEWINTENT = CREATE + 1;
    public static final int ONRESUME = NEWINTENT + 1;
    public static final int ONPAUSE = ONRESUME + 1;


    private static CoreActivity single = new CoreActivity();

    private CoreActivity(){}

    public static CoreActivity getSingle(){
        return single;
    }
    class ActivityInfo{
        public int status;
        public WeakReference<Activity> weak;
    }

    private static Stack<ActivityInfo> activityStack = null;


    public void onCreate(Activity c){

        if(activityStack == null){
            activityStack = new Stack<ActivityInfo>();
        }
        ActivityInfo info = new ActivityInfo();
        info.status = CREATE;
        info.weak = new WeakReference<Activity>(c);

        activityStack.push(info);
    }
    public void onDestory(Activity c){
        WeakReference<Activity> activity = null;
        if (activityStack != null && activityStack.size() > 0) {
            for (int i = 0; i < activityStack.size(); i++) {
                ActivityInfo info = activityStack.get(i);

                activity = info.weak;
                if (activity != null && activity.get() != null) {
                    if (activity.get().getClass().equals(c)) {
                        activity.get().finish();
                        activityStack.remove(i);
                    }
                }
            }
        }
    }
    public void onNewIntent(Activity c){
        setStatus(c,NEWINTENT);
    }
    public void onPause(Activity c){
        setStatus(c,ONPAUSE);
    }
    public void onResume(Activity c){
        setStatus(c,ONRESUME);
    }
    private void setStatus(Activity c,int status){
        WeakReference<Activity> activity = null;
        if (activityStack != null && activityStack.size() > 0) {
            for (int i = 0; i < activityStack.size(); i++) {
                ActivityInfo info = activityStack.get(i);

                activity = info.weak;
                if (activity != null && activity.get() != null) {
                    if (activity.get().getClass().equals(c)) {
                        info.status = status;
                    }
                }
            }
        }
    }


    public void popAllActivity() {
        WeakReference<Activity> activity = null;
        while (activityStack != null && activityStack.size() > 0) {
            ActivityInfo info = activityStack.pop();

            activity = info.weak;
            if (activity == null || activity.get() == null)
                continue;

            activity.get().finish();
        }
    }

    public Activity getTopActivity(){
        ActivityInfo info = activityStack.peek();

        if(info.weak != null)
            return info.weak.get();

        return null;
    }

}
