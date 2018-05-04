package com.jjf.customview;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jjf.customview.lib.TrackColorTextView;
import com.jjf.customview.lib.TrackDirection;

public class MainActivity extends AppCompatActivity {

    private TrackColorTextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.TrackColorTextView);


    }

    public void left2Right(View view){
        mTextView.setDirection(TrackDirection.LEFT_TO_RIGHT);
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mTextView.setCurrentOffset(value);
            }
        });
        animator.start();
    }

    public void right2left(View view){
        mTextView.setDirection(TrackDirection.RIGHT_TO_LEFT);
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mTextView.setCurrentOffset(value);
            }
        });
        animator.start();
    }

    public void start(View view){
        startActivity(new Intent(this, ViewPagerActivity.class));
    }
}
