package com.kyrodevs.bottomviewexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BottomView extends View {

    private static final String TAG = "BottomView";
    private List<Bitmap> mIcons = new ArrayList<>();
    private List<Bitmap> mPressedIcons = new ArrayList<>();
    private Paint bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int bgColor = Color.WHITE;

    OnBottomViewClickListener listener;
    private int selected = 0;
    private float containerWidth;
    private int mPaddingLeft = 0;

    public BottomView(Context context) {
        super(context);
        init();
    }

    public BottomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BottomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int width = processMeasureSpec(widthMeasureSpec);
        final int height = processMeasureSpec(heightMeasureSpec);
        if (mIcons.size() == 0) {
            containerWidth = width;
        } else {
            containerWidth = (int) width / mIcons.size();
        }
        setMeasuredDimension(width, height);
    }

    private int processMeasureSpec(int sizeMeasureSpec) {
        final int mode = MeasureSpec.getMode(sizeMeasureSpec);
        final int size = MeasureSpec.getSize(sizeMeasureSpec);
        if (mode == MeasureSpec.EXACTLY || mode == MeasureSpec.AT_MOST) { return size; }
        else { return 200; }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(bgColor);
        for (int i = 0; i < mIcons.size(); i++) {
            if (i == selected) {
                final Bitmap icon = mPressedIcons.get(selected);
                canvas.drawBitmap(icon, (i * containerWidth) + (containerWidth / 2) - (icon.getWidth()/2),
                        (getMeasuredHeight()/2) - (icon.getHeight()/2), bgPaint);
            } else {
                final Bitmap icon = mIcons.get(i);
                canvas.drawBitmap(icon, (i * containerWidth) + (containerWidth / 2) - (icon.getWidth()/2),
                        (getMeasuredHeight()/2) - (icon.getHeight()/2), bgPaint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                for (int i = 0; i < mIcons.size(); i++) {
                    if ((x > i * containerWidth) && x < (i + 1) * containerWidth) {
                        setSelected(i);
                        invalidate();
                        listener.onBottomItemClick(i);
                    }
                }
                break;
            }
            case MotionEvent.ACTION_UP: { break; }
            default:
                break;
        }
        return true;
    }

    private void init() {
        bgPaint.setStyle(Paint.Style.FILL);
        bgPaint.setColor(bgColor);
    }

    public void setBgColor(int color) {
        bgColor = color;
    }

    public void setSelected(int pos) {
        selected = pos;
    }

    public void addBitmapStates(BitmapState state) {
        mIcons.add(state.getM_icon());
        mPressedIcons.add(state.getM_icon_pressed());
    }

    public void addBottomViewClickListener(OnBottomViewClickListener l) {
        this.listener = l;
    }

}
