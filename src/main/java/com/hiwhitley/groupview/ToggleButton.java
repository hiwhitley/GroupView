package com.hiwhitley.groupview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by hiwhitley on 2016/3/11.
 */
public class ToggleButton extends View implements View.OnClickListener{

    private Bitmap backgroudBitmap;
    private Bitmap slideBtnBitmap;

    private float slideBtn_left;
    private boolean currentState = false;
    private int firstX = 0;
    private int secondX = 0;
    private static float MAX_LEFT_DISTANCE ;
    private boolean isDrag = false;

    public ToggleButton(Context context) {
        this(context, null);
    }

    public ToggleButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ToggleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ToggleButton);
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {

            int taId = typedArray.getIndex(i);
            switch (taId) {
                case R.styleable.ToggleButton_backgroundBitmap:
                    backgroudBitmap = ((BitmapDrawable)typedArray.getDrawable(taId)).getBitmap();
                    break;
                case R.styleable.ToggleButton_current_state:
                    currentState = typedArray.getBoolean(taId, false);
                    break;
                case R.styleable.ToggleButton_slideButton:
                    slideBtnBitmap = ((BitmapDrawable)typedArray.getDrawable(taId)).getBitmap();
                    break;
                default:
                    break;
            }
        }
        initView();

    }

    private void initView() {
//        backgroudBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.switch_track);
//        slideBtnBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.switch_thumb_disabled_pressed);
        MAX_LEFT_DISTANCE = backgroudBitmap.getWidth()- slideBtnBitmap.getWidth();
        flushState();
        flushView();
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                currentState = !currentState;
                flushState();
                flushView();
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(backgroudBitmap.getWidth(), backgroudBitmap.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        // 打开抗锯齿
        paint.setAntiAlias(true);

        // 画背景
        canvas.drawBitmap(backgroudBitmap, 0, 0, paint);
        // 画滑块
        canvas.drawBitmap(slideBtnBitmap, slideBtn_left, 0, paint);
    }

    protected void flushView(){
        invalidate();
    }

    private void flushState() {
        if (currentState) {
            slideBtn_left = backgroudBitmap.getWidth() - slideBtnBitmap.getWidth();
        }else
            slideBtn_left = 0;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        super.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isDrag = false;
                Log.i("hiwhitley", "isdrag1:" + isDrag);
                firstX = secondX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                isDrag = true;
                Log.i("hiwhitley", "isdrag2:" + isDrag);
                int disX = (int) (event.getX() - secondX);
                secondX = (int) event.getX();

                slideBtn_left = slideBtn_left + disX;
                if (slideBtn_left < 0) {
                    slideBtn_left = 0;
                } else {
                    if (slideBtn_left > MAX_LEFT_DISTANCE) {
                        slideBtn_left = MAX_LEFT_DISTANCE;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (slideBtn_left < MAX_LEFT_DISTANCE / 3) {
                    currentState = false;
                } else if (slideBtn_left >= MAX_LEFT_DISTANCE / 3) {
                    currentState = true;
                }

                flushState();
                break;
            default:
                break;
        }

        flushView();
        return true;
    }


    @Override
    public void onClick(View v) {
        if (!isDrag) {
            Log.i("hiwhitley", "isdrag3:" + isDrag);
            // 如果不是拖动事件，才进行点击事件的响应操作
            currentState = !currentState;
            flushState();
            flushView();
        } else {
            // 如果是拖动事件，则不进行点击事件的响应操作
        }
    }
}


