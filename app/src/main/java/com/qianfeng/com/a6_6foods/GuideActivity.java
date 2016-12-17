package com.qianfeng.com.a6_6foods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qianfeng.com.a6_6foods.adapter.MyGuidePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity {

    private ViewPager guideViewPager;
    private int prePosition;
    private int currentPosition;

    private int preState = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        //跳转成功后，设置isFirstLogin值为false
        getSharedPreferences("xiaomo",MODE_PRIVATE).edit().putBoolean("isFirstLogin",false).commit();
        initView();
    }

    private void initView() {
        prePosition = 0;
        guideViewPager = (ViewPager) findViewById(R.id.guide_viewpager);
        final LinearLayout dotLayout = (LinearLayout) findViewById(R.id.dot_layout);
        List<ImageView> imageViews = new ArrayList<>();
        int[] imagIds = new int[]{R.drawable.p96,R.drawable.p97,R.drawable.p98 ,R.drawable.p99};
        for (int image : imagIds){
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(image);
            imageViews.add(imageView);

            View view = new View(this);
            view.setBackgroundResource(R.drawable.dot_selector);
            view.setEnabled(false);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()),
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));
            layoutParams.leftMargin = 20;
            view.setLayoutParams(layoutParams);
            dotLayout.addView(view);
        }
        dotLayout.getChildAt(0).setEnabled(true);

        MyGuidePagerAdapter adapter = new MyGuidePagerAdapter(imageViews);
        guideViewPager.setAdapter(adapter);
        guideViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                currentPosition = position;
            }


            @Override
            public void onPageSelected(int position) {
                dotLayout.getChildAt(prePosition).setEnabled(false);
                dotLayout.getChildAt(position).setEnabled(true);
                prePosition = position;
            }

            //状态共分为3种，
            //0表示静止
            //1表示手指拖动
            //2表示自由滚动
            //只有在首页向右滑和尾页向左滑动时，状态的变换为1->0,如果再加上条件position=4，就可以唯一确定在最后一个页面向左滑动
            //其他滑动时，状态的变化为1—>2—>0
            @Override
            public void onPageScrollStateChanged(int state) {
                if (preState ==1  && state == 0 && currentPosition == 3) {
                    startActivity(new Intent(GuideActivity.this, MainActivity.class));
                    GuideActivity.this.finish();
                }
                preState = state;
            }
        });
    }
}
