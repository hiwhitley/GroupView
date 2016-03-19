package com.hiwhitley.groupview;

import android.content.Context;
import android.os.SystemClock;

/**
 * Created by hiwhitley on 2016/3/17.
 */
public class DistanceProvider {

    private int startX;
    private int startY;
    private int distanceX;
    private int distanceY;
    /**
     * 开始执行动画的时间
     */
    private long startTime;
    /**
     * 判断是否还在执行动画，true为已经停止，false表示还在运行
     */
    private boolean isFinish;
    /**
     * 默认的运行时间，毫秒值，300毫秒
     */
    private long duration;
    /**
     * 当前的x值
     */
    private long currentX;
    private long currentY;

    public long getCurrentX() {
        return currentX;
    }

    public void setCurrentX(long currentX) {
        this.currentX = currentX;
    }

    public DistanceProvider(Context context) {
        isFinish = false;
    }

    /**
     * 开始移动
     *
     * @param startX
     *            x的起始坐标
     * @param startY
     *            y的起始坐标
     * @param distanceX
     *            x方向要移动的距离
     * @param distanceY
     *            y方向要移动的距离
     */
    public void startScroll(int startX, int startY, int distanceX, int distanceY) {
        this.startX = startX;
        this.startY = startY;
        this.distanceX = distanceX;
        this.distanceY = distanceY;
        this.startTime = SystemClock.uptimeMillis();
        //我们这里将滑动的持续时间设为300毫秒
        this.duration = 300;
        this.isFinish = false;

    }

    /**
     * 计算一下当前的运行状态
     *
     * @return true：表示运行结束 false：表示还在运行
     */
    public boolean computeScrollOffset() {
        if (isFinish) {
            return isFinish;
        }
        // 计算一下滑动运行了多久时间
        long passTime = SystemClock.uptimeMillis() - startTime;

        if (passTime < duration) {
            currentX = startX + distanceX * passTime / duration;
            currentY = startY + distanceX * passTime / duration;
        } else {
            currentX = startX + distanceX;
            currentY = startY + distanceY;
            isFinish = true;
        }
        return false;
    }
}
