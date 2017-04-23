package com.io.wisp.io_android.app.launch.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.io.wisp.io_android.R;
import com.io.wisp.io_android.app.launch.ui.utils.Utils;
import com.io.wisp.io_android.global.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunever on 2017/4/23.
 */

public class LaunchActivity extends Activity implements View.OnClickListener {

    private ViewPager vp;
    private List<View> views;
    private Button startBtn;

    private static final int[] pics = {
            R.layout.launch_page1,
            R.layout.launch_page2,
            R.layout.launch_page3,
            R.layout.launch_page4
    };

    private ImageView[] dots;

    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        views = new ArrayList<View>();

        for (int i=0; i<pics.length; i++) {
            View view = LayoutInflater.from(this).inflate(pics[i], null);
            if (i == pics.length - 1) {
                startBtn = (Button) view.findViewById(R.id.btn_enter);
                startBtn.setTag("enter");
                startBtn.setOnClickListener(this);
            }
            views.add(view);
        }

        vp = (ViewPager) findViewById(R.id.vp_launch);
        vp.setAdapter(new LaunchViewPagerAdapter(views));
        vp.addOnPageChangeListener(new PageChangeListener());

        initDots();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Utils.putBoolean(LaunchActivity.this, Constants.FIRST_OPEN, true);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if (v.getTag().equals("enter")) {
            enterMainActivity();
            return;
        }

        int position = (Integer) v.getTag();
        setCurDot(position);
        setCurView(position);
    }

    private void initDots() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        dots = new ImageView[pics.length];

        for(int i=0; i<pics.length; i++) {
            dots[i] = (ImageView) ll.getChildAt(i);
            dots[i].setBackgroundResource(R.drawable.dot_normal);
            dots[i].setOnClickListener(this);
            dots[i].setTag(i);
        }

        currentIndex = 0;
        dots[currentIndex].setBackgroundResource(R.drawable.dot_focused);
    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int position) {

        }

        @Override
        public void onPageScrolled(int position, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            // 设置底部小点选中状态
            setCurDot(position);
        }
    }

    private void setCurView(int position) {
        if (position < 0 || position >= pics.length) {
            return;
        }
        vp.setCurrentItem(position);
    }

    private void setCurDot(int position) {
        if (position < 0 || position > pics.length || currentIndex == position) {
            return;
        }
        dots[position].setBackgroundResource(R.drawable.dot_focused);
        dots[currentIndex].setBackgroundResource(R.drawable.dot_normal);
        currentIndex = position;
    }

    private void enterMainActivity() {
        Intent intent = new Intent(LaunchActivity.this, SplashActivity.class);
        startActivity(intent);
        Utils.putBoolean(LaunchActivity.this, Constants.FIRST_OPEN, true);
        finish();
    }
}
