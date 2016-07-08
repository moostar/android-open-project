package cn.moostar.lemon.ui.core;

import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;

import cn.moostar.lemon.LemonApp;
import cn.moostar.lemon.tools.LemonLog;

/**
 * Created by Apple on 16/7/7.
 */
public class JumpActivity {

    public static class JumpInfo{
        public String classname;
        public HashMap<String , String > map;
    }

    private static class ActInfo {
        public boolean isNeedReorderToFront = false;
        public Class<?> cls;

        public ActInfo(Class<?> cls, boolean isNeedReorderToFront) {
            this.cls = cls;
            this.isNeedReorderToFront = isNeedReorderToFront;
        }
    }

    private static JumpActivity jumpActivity = new JumpActivity();
    private HashMap<String, ActInfo > actMap = new HashMap<String ,ActInfo>();


    private JumpActivity(){};

    public static JumpActivity getSingle(){
        return jumpActivity;
    }

    public void addCache(Class<?> cls ){
        if(!actMap.containsKey(cls.getSimpleName() + ""))
            actMap.put(cls.getSimpleName() + "", new ActInfo(cls,false));
        LemonLog.info(this,"addCache : " + cls.getSimpleName() + "");
    }

    public void toJump(JumpInfo info){

        LemonLog.info(this,"toJump :" + info.classname);
        if(actMap.containsKey(info.classname)){
            ActInfo actInfo = actMap.get(info.classname);

            if(actInfo != null){

                Intent intent = new Intent();

                Bundle bundle = new Bundle();
                intent.setClass(LemonApp.getContext(), actInfo.cls);
                intent.putExtras(bundle);

                if(CoreActivity.getSingle().getTopActivity() != null)
                    CoreActivity.getSingle().getTopActivity().startActivity(intent);
            }

        }
    }




}
