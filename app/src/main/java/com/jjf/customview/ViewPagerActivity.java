package com.jjf.customview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.widget.LinearLayout;

import com.jjf.customview.lib.TrackColorTextView;
import com.jjf.customview.lib.TrackDirection;

import java.util.ArrayList;

public class ViewPagerActivity extends AppCompatActivity {

    private static final String TAG = "ViewPagerActivity";
    private String[] titles = {"首页", "热门", "新闻", "图片", "视频", "其他"};
    private ViewPager mViewPager;
    private LinearLayout mLlContainer;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<TrackColorTextView> mTrackColorTextViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        mViewPager = findViewById(R.id.viewPager);
        mLlContainer = findViewById(R.id.ll_title_container);

        for (String title : titles) {
            TrackColorTextView textView = new TrackColorTextView(this);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            textView.setText(title);
            mLlContainer.addView(textView);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) textView.getLayoutParams();
            params.weight = 1;
            mTrackColorTextViews.add(textView);
            mFragments.add(ContentFragment.newInstance(title));
        }

        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG, "onPageScrolled: "+positionOffset);
                TrackColorTextView textView = mTrackColorTextViews.get(position);
                textView.setDirection(TrackDirection.RIGHT_TO_LEFT);
                textView.setCurrentOffset(1-positionOffset);
                if (position == titles.length - 1) {
                    return;
                }
                int nextPosition = position + 1;
                TrackColorTextView nextTextView = mTrackColorTextViews.get(nextPosition);
                nextTextView.setDirection(TrackDirection.LEFT_TO_RIGHT);
                nextTextView.setCurrentOffset(positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return titles.length;
        }
    }
}
