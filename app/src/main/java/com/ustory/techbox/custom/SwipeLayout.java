package com.ustory.techbox.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/24
 * * 这里定义可滑动的item,先布局
 */
public class SwipeLayout extends FrameLayout{
    private Scroller mScroller;
    private View contentView;
    private View deletetView;
    private int deleteHeight;
    private int deleteWidth;
    private int contentWidth;
    private float downX,downY;
    private Context mContext;
    private VelocityTracker mVelocityTracker;
    private boolean isMoving = false;

    private float delatX;
    private float delatY;
    private float totalDelatx =0f;


    public SwipeLayout(Context context) {
        super(context);
        init(context);
    }

    public SwipeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SwipeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mScroller = new Scroller(context);
        //
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentView = getChildAt(0);
        deletetView = getChildAt(1);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        deleteHeight = deletetView.getMeasuredHeight();
        deleteWidth = deletetView.getMeasuredWidth();
        contentWidth = contentView.getMeasuredWidth();
        Log.i("qiyue","deleteHeight="+deleteHeight+"deleteWidth="+deleteWidth+"contentWidth="+contentWidth);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        contentView.layout(0, 0, contentWidth, deleteHeight);
        deletetView.layout(contentView.getRight(), 0, contentView.getRight() + deleteWidth, deleteHeight);
        Log.i("qiyue","contentWidth="+contentWidth+"deleteHeight="+deleteHeight);
    }



    private int finalx;
    private int finaly;
    private boolean isOpen = false;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //如果当前有打开的，则下面的逻辑不能执行
        //  if(!SwipeLayoutManager.getInstance().isShouldSwipe(this)){
        //     requestDisallowInterceptTouchEvent(true);
        //     return true;
        //  }
        Log.i("qiyue","onTouchEvent");
       // if (mVelocityTracker == null) {
       //     mVelocityTracker = VelocityTracker.obtain();
       // }
      //  mVelocityTracker.addMovement(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i("qiyue","ACTION_DOWN");
                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                //1.获取x和y方向移动的距离
                Log.i("qiyue","ACTION_MOVE");
                float moveX = event.getX();
                float moveY = event.getY();
                delatX = moveX - downX;//x方向移动的距离
                delatY = moveY - downY;//y方向移动的距离
                Log.i("qiyue","moveX="+moveX+"moveY="+moveY+"downX="+downX+"downY"+downY);
                if((Math.abs(delatX)> Math.abs(delatY))&&!isOpen){
                    //表示移动是偏向于水平方向，那么应该SwipeLayout应该处理，请求listview不要拦截
                    requestDisallowInterceptTouchEvent(true);
                    isMoving = true;
                    totalDelatx +=delatX;
                    int y = mScroller.getCurrY();
                    int x = mScroller.getCurrX();
                    finalx = mScroller.getFinalX();
                    finaly = mScroller.getFinalY();
                    Log.i("qiyue","x="+x+"y="+y+"deltX="+delatX);
                    Log.i("qiyue","finalx="+finalx+"finaly="+finaly);
                    scrollBy(x-(int)delatX,y);
                }
                //更新downX，downY
                downX = moveX;
                downY = moveY;
                break;
            case MotionEvent.ACTION_UP:
                if (isMoving){
                    Log.i("qiyue","delatX="+delatX+"(deleteWidth/2)="+(deleteWidth/2));
                    if (totalDelatx<0) {
                        Log.i("qiyue","totalDelatx<0="+totalDelatx);
                        if (Math.abs(totalDelatx) > (deleteWidth / 2)) {
                            mScroller.startScroll(deleteWidth, 0, 0, 0);
                            isOpen = true;
                            //SwipeLayoutManager.getInstance().setSwipeLayout(SwipeLayout.this);
                        } else {
                            close();
                            //SwipeLayoutManager.getInstance().removeSwipeLayout(SwipeLayout.this);
                        }
                        invalidate();
                    }else{
                        Log.i("qiyue","totalDelatx>0="+totalDelatx);
                        //SwipeLayoutManager.getInstance().removeSwipeLayout(SwipeLayout.this);
                        close();
                        invalidate();
                    }
                }

                totalDelatx = 0;
                isMoving = false;
                break;
        }
      ///  mViewDragHelper.processTouchEvent(event);
        return true;
    }

    //调用此方法滚动到目标位置
    public void smoothScrollTo(int fx, int fy) {
        int dx = fx - mScroller.getFinalX();
        int dy = fy - mScroller.getFinalY();
        smoothScrollBy(dx, dy);
    }

    //调用此方法设置滚动的相对偏移
    public void smoothScrollBy(int dx, int dy) {

        //设置mScroller的滚动偏移量
        mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy);
        invalidate();//这里必须调用invalidate()才能保证computeScroll()会被调用，否则不一定会刷新界面，看不到滚动效果
    }

    @Override
    public void computeScroll() {
        //先判断mScroller滚动是否完成
        if (mScroller.computeScrollOffset()) {
               //这里调用View的scrollTo()完成实际的滚动
               scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
              //必须调用该方法，否则不一定能看到滚动效果
               postInvalidate();
           }
        super.computeScroll();
    }


    private OnSwipeStateChangeListener listener;
    public void setOnSwipeStateChangeListener(OnSwipeStateChangeListener listener){
        this.listener = listener;
    }
    public interface OnSwipeStateChangeListener{
        void open(Object tag);
        void close(Object tag);
    }

    public void close(){
        Log.i("qiyue","close");
        mScroller.startScroll(0,0, 0, 0);
        isOpen = false;
        invalidate();
    }
}
