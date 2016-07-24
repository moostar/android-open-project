package cn.dpocket.moplusand.uinew.ui.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.dpocket.moplusand.logic.LogicFileCacheMgr;
import cn.dpocket.moplusand.logic.LogicHttpImageMgr;
import cn.dpocket.moplusand.uinew.R;
import cn.dpocket.moplusand.uinew.ui.widget.CircleImageView;
import cn.dpocket.moplusand.uinew.view.MarqueeTextView;

/**
 * Created by joy on 16/6/13.
 */
public class MasterView extends RelativeLayout {

    private CircleImageView ivHeader;
    private TextView live_tvUNumber;
    private TextView live_master_nickName;
    private ImageView live_master_online_status;
    private MarqueeTextView live_chatroom_subject;
    private TextView tvAction;
    private View topicLayout;
    private EditText etTopic;
    private ImageView ivEditTopicIcon;

    public MasterView(Context context) {
        super(context);
        init();
    }

    public MasterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MasterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.include_shower_head, null);
        ivHeader = (CircleImageView) view.findViewById(R.id.live_master_header_img);
        live_tvUNumber = (TextView) view.findViewById(R.id.live_tvUNumber);
        live_master_nickName = (TextView) view.findViewById(R.id.live_master_nickName);
        live_master_online_status = (ImageView) view.findViewById(R.id.live_master_online_status);
        live_chatroom_subject = (MarqueeTextView) view.findViewById(R.id.live_chatroom_subject);
        tvAction = (TextView) view.findViewById(R.id.live_btn_masterinfo_attention);
        topicLayout = view.findViewById(R.id.header_row_2);
        etTopic = (EditText) view.findViewById(R.id.live_et_chatroom_subject);
        ivEditTopicIcon = (ImageView) view.findViewById(R.id.live_img_edit_subject);
        addView(view);
    }

    /**
     * 设置黄色区域文字:如友加号
     */
    public void setYellowAreaText(String text) {
        live_tvUNumber.setText(text);
    }


    public void setNickName(String nickName) {
        live_master_nickName.setText(nickName);
    }

    public void setHeaderUrl(String url, int defRes) {
        LogicHttpImageMgr.getSingleton().appendImage(
                ivHeader, url,
                defRes, null, 0,
                LogicFileCacheMgr.TYPE_FILECACHE_IMAGE);
    }

    public void setActionButtonEnable(boolean enable) {
        tvAction.setVisibility(enable ? VISIBLE : GONE);
    }

    public void setOnActionButtonClick(OnClickListener onSpaceClick) {
        tvAction.setOnClickListener(onSpaceClick);
    }

    public void setActionButtonText(CharSequence text) {
        tvAction.setText(text);
    }


    public TextView getActionButton() {
        return tvAction;
    }

    public void setOnHeaderClick(OnClickListener onHeaderClick) {
        ivHeader.setOnClickListener(onHeaderClick);
    }

    public void setOnlineDrawable(int res) {
        live_master_online_status.setImageResource(res);
    }

    public void setSecondName(String text) {
        live_chatroom_subject.setText(text);
    }

    public void setSecondNameMarqueeEnalbe(boolean enable) {
        if (!enable)
            live_chatroom_subject.setMarqueeEnalbe(enable);
    }

    public void setSecondNameEnable(boolean enable) {
        topicLayout.setVisibility(enable ? VISIBLE : GONE);
    }

    public void setStatusEnalbe(boolean enable) {
        live_master_online_status.setVisibility(enable ? VISIBLE : GONE);
    }

    public void setOnSubjectClick(OnClickListener onSubjectClick) {
        topicLayout.setOnClickListener(onSubjectClick);
    }

    public void setSubjectEditIconEnable(boolean enable) {
        ivEditTopicIcon.setVisibility(enable ? VISIBLE : GONE);
    }

}
