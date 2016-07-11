package cn.dpocket.moplusand.uinew.tools;

import android.widget.Toast;

import cn.dpocket.moplusand.uinew.LemonApp;

/**
 * Created by Apple on 16/7/11.
 */
public class Utils {

    public static void ShowToast(String msg){
        Toast.makeText(LemonApp.getContext(),msg,Toast.LENGTH_SHORT).show();
    }
}
