package cn.dpocket.moplusand.uinew.tools;

import android.widget.Toast;

import cn.dpocket.moplusand.uinew.LemonApp;
import cn.dpocket.moplusand.uinew.ui.core.CoreActivity;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by Apple on 16/7/11.
 */
public class Utils {

    public static void showToast(String msg){
        Toast.makeText(LemonApp.getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    public static void showCrouton(String msg) {
        Crouton.makeText(CoreActivity.getSingle().getTopActivity(), msg , new Style.Builder().setBackgroundColorValue(Style.holoBlueLight).build()).show();
    }
}
