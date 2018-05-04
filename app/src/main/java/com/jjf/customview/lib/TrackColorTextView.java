package com.jjf.customview.lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.jjf.customview.R;

/**
 * Created by jinjiafeng.
 * date:on 18-5-3
 */

public class TrackColorTextView extends AppCompatTextView {

    private int mOriginColor;
    private int mChangeColor;
    private Paint mPaint;
    private Rect mRect;
    private float mOffset;
    private TrackDirection mDirection = TrackDirection.LEFT_TO_RIGHT;


    public TrackColorTextView(Context context) {
        this(context, null);
    }

    public TrackColorTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TrackColorTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TrackColorTextView);
        this.mOriginColor = typedArray.getColor(R.styleable.TrackColorTextView_originColor, Color.BLACK);
        this.mChangeColor = typedArray.getColor(R.styleable.TrackColorTextView_originColor, Color.RED);

        typedArray.recycle();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setTextSize(getTextSize());

        mRect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        final String text = getText().toString();
        mPaint.setColor(mOriginColor);
        mPaint.getTextBounds(text, 0, text.length(), mRect);
        float x = getWidth() / 2 - mRect.width() / 2;
        final Paint.FontMetrics metrics = mPaint.getFontMetrics();
        final float dy = (metrics.bottom - metrics.top) / 2;
        final float y = getHeight() / 2 + dy;
        canvas.drawText(text, x, y, mPaint);

        mPaint.setColor(mChangeColor);
        canvas.save();
        if (this.mDirection == TrackDirection.LEFT_TO_RIGHT) {
            canvas.clipRect(0, 0, getWidth() * mOffset, getHeight());
            canvas.drawText(text, x, y, mPaint);
        } else {
            canvas.clipRect(getWidth() *(1-mOffset), 0, getWidth(), getHeight());
            canvas.drawText(text, x, y, mPaint);
        }
        canvas.restore();
    }

    public void setCurrentOffset(float offset) {
        this.mOffset = offset;
        invalidate();
    }

    public void setDirection(TrackDirection direction) {
        mDirection = direction;
    }
}
