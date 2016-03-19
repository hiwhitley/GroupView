package com.hiwhitley.groupview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hiwhitley on 2016/3/16.
 */
public class MyViewPager extends ViewGroup {

    private GestureDetector detector;
    private int firstX,secondX;
    private int currentId;
    private DistanceProvider distanceProvider;

    public MyViewPager(Context context) {
        this(context, null);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        distanceProvider = new DistanceProvider(context);
        init();
    }

    private void init() {
        detector = new GestureDetector(new GestureDetector.OnGestureListener() {

            /**
             * 按下的时候回调的方法
             */
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            /**
             * 当有手指点击屏幕的时候
             */
            @Override
            public void onShowPress(MotionEvent e) {

            }

            /**
             * 有一个手指抬起的时候回调
             */
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            /**
             * 当有手指在屏幕上滑动的时候回调
             * <p/>
             * e1表示最开始触发本次这一系列Event事件的那个ACTION_DOWN事件，
             * e2表示触发本次Event事件的那个ACTION_MOVE事件，
             * distanceX、distanceY分别表示从上一次调用onScroll调用到这一次onScroll调用在x和y方向上滑动的距离。
             * 这里需要稍微留意的是，distanceX、distanceY的正负并不是像之前ViewGroup里坐标显示的正负那样，而是向左滑动值distanceX为正，向右滑动值为distanceX负。
             */
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                Log.i("hiwhitley", "distanceX:" + distanceX);
                Log.i("hiwhitley", "distanceY:" + distanceY);
                scrollBy((int) distanceX, 0);
                return false;
            }

            /**
             * 当有手指在屏幕上长按的时候回调
             */
            @Override
            public void onLongPress(MotionEvent e) {

            }

            /**
             * 快速滑动的时候回调
             */
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });
    }

    /**
     * 对子view进行布局排列，确定子view的位置
     *
     * @param changed 代表调用onLayout方法时，判断当前布局有没有发生改变，如果发生改变了就为true，否则false
     * @param l,      int t, int r, int b代表当前view也就是我们的myviewpager，相对于它的父view的位置，在这里我们在排列自己的子view的时候
     *                这几个参数基本没什么用
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {

            View view = getChildAt(i);
            view.layout(i * getWidth(), 0, getWidth() * (i + 1), getHeight());
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        super.onTouchEvent(event);

        //detector.onTouchEvent(event);

        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:

                firstX = secondX = (int)event.getX();

                break;
            case MotionEvent.ACTION_MOVE:
                int disX = (int) (event.getX() - secondX);
                secondX = (int) event.getX();
                Log.i("hiwhitley", "distanceX:" + disX);
                scrollBy(-disX, 0);

                break;
            case MotionEvent.ACTION_UP:
                int tmpId = 0;
                if (event.getX() - firstX > getWidth() / 2) {
                    tmpId = currentId - 1;
                }else if (firstX - event.getX() > getWidth() / 2) {
                    tmpId = currentId + 1;
                } else {
                    tmpId = currentId;
                }
                int childCount = getChildCount();
                currentId = tmpId < 0 ? 0
                        : ((tmpId > childCount - 1) ? childCount - 1 : tmpId);

               // scrollTo(currentId*getWidth(), 0);
                moveToNext();

              //invalidate();
                break;
            default:
                break;
        }
        return true;
    }

    private void moveToNext() {
        int distance = currentId*getWidth() -getScrollX();
        distanceProvider.startScroll(getScrollX(), 0, distance, 0);
        invalidate();// invalidate会导致ondraw和computeScroll方法的执行
    }

    @Override
    public void computeScroll() {
        //super.computeScroll();
        // 计算滑动偏移量
        if (!distanceProvider.computeScrollOffset()) {
            int newX = (int) distanceProvider.getCurrentX();
            scrollTo(newX, 0);
            // 再次刷新
            invalidate();
        }
    }
}
