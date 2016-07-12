package cn.dpocket.moplusand.uinew.ui;

import android.os.Bundle;

import cn.dpocket.moplusand.uinew.R;
import cn.dpocket.moplusand.uinew.ui.core.BaseActivity;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class SplashActivity extends BaseActivity {

    @Override
    public void WndCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_splash);







//        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToLogin.login();
//            }
//        });



//        showCustomCrouton();

        Crouton.makeText(this, "ssss", new Style.Builder().setBackgroundColorValue(Style.holoBlueLight).build()).show();
    }

//    private static final Style INFINITE = new Style.Builder().setBackgroundColorValue(Style.holoBlueLight).build();
//    private static final Configuration CONFIGURATION_INFINITE = new Configuration.Builder()
//            .setDuration(Configuration.DURATION_INFINITE)
//            .build();
//    private void showCustomCrouton() {
//        String croutonDurationString = "1000";
//
////        if (TextUtils.isEmpty(croutonDurationString)) {
////            showCrouton(getString(R.string.warning_duration), Style.ALERT, Configuration.DEFAULT);
////            return;
////        }
//
//        int croutonDuration = Integer.parseInt(croutonDurationString);
//        Style croutonStyle = new Style.Builder().build();
//        Configuration croutonConfiguration = new Configuration.Builder().setDuration(croutonDuration).build();
//
//        String croutonText = "sss";
//
//        showCrouton(croutonText, croutonStyle, croutonConfiguration);
//    }
//    private void showCrouton(String croutonText, Style croutonStyle, Configuration configuration) {
//        final boolean infinite = INFINITE == croutonStyle;
//
//        if (infinite) {
//            croutonText = "222";
//        }
//
//        final Crouton crouton;
////        if (displayOnTop.isChecked()) {
//            crouton = Crouton.makeText(this, croutonText, croutonStyle);
////        } else {
////            crouton = Crouton.makeText(this, croutonText, croutonStyle, R.id.alternate_view_group);
////        }
//        if (infinite) {
////            infiniteCrouton = crouton;
//        }
//        crouton.setConfiguration(infinite ? CONFIGURATION_INFINITE : configuration).show();
//    }


}
