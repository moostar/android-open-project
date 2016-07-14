package cn.dpocket.moplusand.uinew.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.dpocket.moplusand.uinew.R;
import cn.dpocket.moplusand.uinew.ui.core.HomeActivity;
import cn.dpocket.moplusand.uinew.ui.view.ViewPagerFragment;
import cn.dpocket.moplusand.uinew.ui.view.pager.TabPageIndicator;
import cn.dpocket.moplusand.uinew.ui.view.refreshview.PtrClassicFrameLayout;
import cn.dpocket.moplusand.uinew.ui.view.refreshview.PtrFrameLayout;
import cn.dpocket.moplusand.uinew.ui.view.refreshview.PtrHandler;

/**
 * Created by Apple on 16/7/14.
 */
public class FavoritesActivity extends HomeActivity {
    private TabPageIndicator mCatTabPageIndicator;
    private ViewPager mFragmentViewPager;
    private FragmentViewPagerAdapter mPagerAdapter;
    private PtrFrameLayout mPtrFrame;


    @Override
    protected void WndPause() {
        super.WndPause();
    }

    @Override
    protected void WndResume() {
        super.WndResume();
    }

    @Override
    protected void WndNewIntent() {
        super.WndNewIntent();
    }

    @Override
    protected void WndCreate() {
        super.WndCreate();
        setContentView(R.layout.activity_view_pager);
        initCateViews();
    }

//    @Override
//    protected void MenuTabSelected(int menuItemId) {
//        super.MenuTabSelected(menuItemId);
//    }

    private void initCateViews() {

        int startIndex = 0;

        mCatTabPageIndicator = (TabPageIndicator) findViewById(R.id.view_pager_tab_indicator);
        mFragmentViewPager = (ViewPager) this.findViewById(R.id.view_pager_view_pager);
        ArrayList<ViewPagerFragment> list = new ArrayList<ViewPagerFragment>();

        for (int i = 1; i <= 8; i++) {
            list.add(ViewPagerFragment.create(i));
        }
        mPagerAdapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), list);
        mFragmentViewPager.setAdapter(mPagerAdapter);

        mCatTabPageIndicator.setViewHolderCreator(new TabPageIndicator.ViewHolderCreator() {
            @Override
            public TabPageIndicator.ViewHolderBase createViewHolder() {
                return new HomeCatItemViewHolder();
            }
        });
        mCatTabPageIndicator.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int i) {
                switchTo(i);
            }
        });
        mCatTabPageIndicator.setViewPager(mFragmentViewPager);

        mPtrFrame = (PtrClassicFrameLayout) findViewById(R.id.view_pager_ptr_frame);
        mPtrFrame.disableWhenHorizontalMove(true);

//        mPtrFrame.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void loadMore() {
//
//            }
//        });
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return mPagerAdapter.checkCanDoRefresh();
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPagerAdapter.updateData();
            }
        });
        mFragmentViewPager.setCurrentItem(startIndex);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCatTabPageIndicator.moveToItem(mFragmentViewPager.getCurrentItem());
    }

    private void switchTo(int position) {
        mPagerAdapter.switchTO(position);
    }

    private class FragmentViewPagerAdapter extends FragmentStatePagerAdapter {

        private final ArrayList<ViewPagerFragment> mViewPagerFragments;
        private ViewPagerFragment mCurrentFragment;

        public FragmentViewPagerAdapter(FragmentManager fm, ArrayList<ViewPagerFragment> list) {
            super(fm);
            mViewPagerFragments = list;
        }

        @Override
        public Fragment getItem(int position) {
            return mViewPagerFragments.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
        }

        protected void updateData() {
            final ViewPagerFragment fragment = mCurrentFragment;
            mPtrFrame.refreshComplete();
//            DemoRequestData.getImageList(new RequestFinishHandler<JsonData>() {
//                @Override
//                public void onRequestFinish(final JsonData data) {
//                    if (fragment == mCurrentFragment) {
//                        fragment.update(data);
//                        mPtrFrame.refreshComplete();
//                    }
//                }
//            });
        }

        @Override
        public int getCount() {
            return mViewPagerFragments.size();
        }

        public void switchTO(final int position) {
            int len = mViewPagerFragments.size();
            for (int i = 0; i < len; i++) {
                ViewPagerFragment fragment = mViewPagerFragments.get(i);
                if (i == position) {
                    mCurrentFragment = fragment;
                    fragment.show();
                } else {
                    fragment.hide();
                }
            }
        }

        public boolean checkCanDoRefresh() {
            if (mCurrentFragment == null) {
                return true;
            }
            return mCurrentFragment.checkCanDoRefresh();
        }
    }

    private class HomeCatItemViewHolder extends TabPageIndicator.ViewHolderBase {

        private TextView mNameView;
        private View mTagView;

        @Override
        public View createView(LayoutInflater layoutInflater, int position) {
            View view = layoutInflater.inflate(R.layout.view_pager_indicator_item, null);
            view.setLayoutParams(new AbsListView.LayoutParams(100, -1));
            mNameView = (TextView) view.findViewById(R.id.view_pager_indicator_name);
            mTagView = view.findViewById(R.id.view_pager_indicator_tab_current);
            return view;
        }

        @Override
        public void updateView(int position, boolean isCurrent) {
            mNameView.setText(position + "");
            if (isCurrent) {
                mTagView.setVisibility(View.VISIBLE);
            } else {
                mTagView.setVisibility(View.INVISIBLE);
            }
        }
    }


}
