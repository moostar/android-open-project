package cn.dpocket.moplusand.uinew.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.Display;
import android.view.WindowManager;

import cn.dpocket.moplusand.uinew.R;
import cn.dpocket.moplusand.uinew.ui.core.BaseActivity;

/**
 * Created by Apple on 16/7/22.
 */
public class StreamActivity extends BaseActivity {

    @Override
    protected void WndCreate() {
        super.WndCreate();

        setContentView(R.layout.activity_stream);
    }

    @Override
    protected void WndNewIntent() {
        super.WndNewIntent();
    }

    @Override
    protected void WndPause() {
        super.WndPause();
    }

    @Override
    protected void WndResume() {
        super.WndResume();
    }

//    private void CreateFragmentWithPreview() {
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager
//                .beginTransaction();
//        fragmentTransaction.replace(android.R.id.fragment_content, fragment1);
//        fragmentTransaction.commit();
//
//    }
}
