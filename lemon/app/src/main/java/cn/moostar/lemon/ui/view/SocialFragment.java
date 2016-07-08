package cn.moostar.lemon.ui.view;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import cn.moostar.lemon.R;
import cn.moostar.lemon.tools.LemonLog;

/**
 * Created by xiong on 7/7/16.
 */
public class SocialFragment extends Fragment {

    public interface ISSOLoginFragment {

        public static int WEIXIN = 0;
        public static int QQ = WEIXIN + 1;
        public static int WEIBO = QQ + 1;

        public void onLoginFramentClick(int type);
    }

    private ISSOLoginFragment mCallback;


    public int ssoArray[][]= {
            { ISSOLoginFragment.WEIXIN  , R.drawable.login_account_weixin  , 0  },
            { ISSOLoginFragment.QQ      , R.drawable.login_account_qq  ,   1},
            { ISSOLoginFragment.WEIBO   , R.drawable.login_account_weibo  , 2  }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_social, container, false);
        ImageButton param1 = (ImageButton) view.findViewById(R.id.param1);
        ImageButton param2 = (ImageButton) view.findViewById(R.id.param2);
        ImageButton param3 = (ImageButton) view.findViewById(R.id.param3);


        final ImageButton []arrayIB = new ImageButton[]{param1,param2,param3};

        if(arrayIB.length != ssoArray.length){
            LemonLog.error(this,"length error");
        }

        for(int i = 0;i < arrayIB.length;i ++){

            final int index = i;
            arrayIB[i].setBackgroundResource(ssoArray[index][1]);
            arrayIB[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mCallback != null){
                        mCallback.onLoginFramentClick(ssoArray[index][0]);
                    }
                }
            });

        }
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity != null){

            mCallback = (ISSOLoginFragment) activity;
        }

    }
}
