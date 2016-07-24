/*
 * Copyright (c) 2016.
 * Create or modify by Zhao.
 * Email:laozhao1005@gmail.com
 */

package cn.dpocket.moplusand.uinew.ui.anim.gift;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class GiftAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
    //    private static List<GiftInfo> GIFT_LIST = null;
//
//
//    private LayoutInflater mInflater;
//
//    private int page;
//
//    private int pageTotal;
//
//    private OnGiftChecked onGiftChecked;
//    private Context context;
//
//    public static void setGiftList(List<GiftInfo> list) {
//        if (GIFT_LIST == null) {
//            GIFT_LIST = list;
//
////            for (int i = 0; i < GIFT_LIST.size(); i++) {
////                if (i < 4) {
////                    GIFT_LIST.get(i).setCombo("0");
////                } else
////                    GIFT_LIST.get(i).setCombo("1");
////            }
//        }
//    }
//
//    public static List<GiftInfo> getGiftList() {
//        return GIFT_LIST;
//    }
//
//
//    public GiftAdapter(Context context, int page, int pageTotal, OnGiftChecked onGiftChecked) {
//        this.mInflater = LayoutInflater.from(context);
//
//        this.context = context;
//        this.page = page;
//        this.pageTotal = pageTotal;
//        this.onGiftChecked = onGiftChecked;
//
//        getData();
//    }
//
//    private List<GiftInfo> getData() {
//        int start = page * pageTotal;
//
//        List<GiftInfo> list = ListUtils.subList(GIFT_LIST, start, pageTotal);
//        return list;
//    }
//
//    @Override
//    public int getCount() {
//        // TODO Auto-generated method stub
//        List<GiftInfo> list = getData();
//        if (list == null) {
//            return 0;
//        } else {
//            return list.size();
//        }
//    }
//
//    @Override
//    public GiftInfo getItem(int position) {
//        // TODO Auto-generated method stub
//        int real = getRealGiftIndex(position);
//
//        if (ListUtils.isNullOrEmpty(GIFT_LIST) || real >= GIFT_LIST.size()) {
//            return null;
//        }
//        return GIFT_LIST.get(real);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        final GiftInfo giftInfo = getItem(position);
//
//
//        if (convertView == null) {
//            convertView = mInflater.inflate(R.layout.item_gift_gridview, null);
//        }
//        ImageView giftImage = ViewHolder.get(convertView, R.id.iv_gift_img);
//        TextView giftPrice = ViewHolder.get(convertView, R.id.txt_gift_point);
//        TextView giftCharm = ViewHolder.get(convertView, R.id.txt_gift_charm);
//        final ImageView ivDribble = ViewHolder.get(convertView, R.id.ivDribble);
//        final ImageView checkBox = ViewHolder.get(convertView, R.id.checkbox);
//
//        giftImage.setBackgroundResource(0);
//        giftImage.setImageBitmap(null);
//
//        // 设置
//        if (giftInfo != null) {
//
//            LogicHttpImageMgr.getSingleton().appendImage(
//                    giftImage,
//                    LogicHttpImageMgr
//                            .convertImageIdToHttpUrl(
//                                    Constants.RESTYPE_IMAGE_GIFT,
//                                    giftInfo.getResourceid() + ""), 0,
//                    null, 0, LogicFileCacheMgr.TYPE_FILECACHE_GIFT);
//
//            String price = giftInfo.getPointValue() + "";
//            giftPrice.setText(price);
//
//            String charm = context.getResources().getString(R.string.space_glamour)
//                    + "+"
//                    + Integer.toString(giftInfo.getGlamour());
//            giftCharm.setText(charm);
//
//            if ("1".equals(giftInfo.getCombo())) {
//                ivDribble.setVisibility(View.VISIBLE);
//            } else {
//                ivDribble.setVisibility(View.GONE);
//            }
//
//            if (giftInfo.isChecked()) {
//                checkBox.setVisibility(View.VISIBLE);
//            } else {
//                checkBox.setVisibility(View.GONE);
//            }
//
//            checkBox.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    setChecked(checkBox, ivDribble, position, !giftInfo.isChecked());
//
//                }
//            });
//            convertView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    setChecked(checkBox, ivDribble, position, !giftInfo.isChecked());
//                }
//            });
//        }
//
//
//        return convertView;
//    }
//
//    private int getRealGiftIndex(int position) {
//        int realGiftIndex = position + (page * pageTotal);
//        return realGiftIndex;
//    }
//
//
//    private void setChecked(ImageView checkBox, ImageView ivDribble, int position, boolean isChecked) {
//
//        int realGiftPosition = getRealGiftIndex(position);
//
//        ULog.log("before realGiftIndex:" + realGiftPosition + " GIFT_LIST.get(realGiftPosition):" + GIFT_LIST.get(realGiftPosition).isChecked());
//
//        if (GIFT_LIST != null && realGiftPosition < GIFT_LIST.size()) {
//
//            GIFT_LIST.get(realGiftPosition).setChecked(isChecked);
//
//            for (int i = 0; i < GIFT_LIST.size(); i++) {
//                if (realGiftPosition == i)
//                    GIFT_LIST.get(realGiftPosition).setChecked(isChecked);
//                else
//                    GIFT_LIST.get(i).setChecked(false);
//            }
//
//
//            if (!isChecked)
//                onGiftChecked.onNoneChecked();
//
//            if (isChecked) {
//                onGiftChecked.onChecked(position, page);
//                ivDribble.setVisibility(View.GONE);
//                checkBox.setVisibility(View.VISIBLE);
//            } else {
//                checkBox.setVisibility(View.GONE);
//                ivDribble.setVisibility(View.VISIBLE);
//            }
//
//            onGiftChecked.onCheckChanged(position, page, isChecked);
//
//
//        }
//
//        ULog.log("after realGiftIndex:" + realGiftPosition + " GIFT_LIST.get(realGiftPosition):" + GIFT_LIST.get(realGiftPosition).isChecked());
//
//        notifyDataSetChanged();
//
//    }
//
//
//    @Override
//    public void notifyDataSetChanged() {
//        getData();
//        super.notifyDataSetChanged();
//    }
//
//    public interface OnGiftChecked {
//
//
//        void onChecked(int position, int page);
//
//        void onCheckChanged(int position, int page, boolean isChecked);
//
//        void onNoneChecked();
//
//    }
//
//
//    public static Vector toVector(List list) {
//
//        Vector v = new Vector();
//
//        for (int i = 0; i < list.size(); i++) {
//            v.add(list.get(i));
//        }
//        return v;
//    }

}