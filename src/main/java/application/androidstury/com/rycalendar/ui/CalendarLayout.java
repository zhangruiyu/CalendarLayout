package application.androidstury.com.rycalendar.ui;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import application.androidstury.com.rycalendar.R;
import application.androidstury.com.rycalendar.util.BitmapUtils;
import application.androidstury.com.rycalendar.util.DensityUtil;

/**
 * Created by zhangruiyu on 16/6/14.
 */

public class CalendarLayout extends ViewGroup {

    private CalendarContract.BgViewRule mBgView;
    private int mBgResources = R.mipmap.month_b;
    private BitmapFactory.Options bitMapH1;
    private CalendarContract.View inPicView;
    private CalendarContract.View outPicView;
    private RedCircleView redCircleView;
    private int leftOffset = 11;

    public CalendarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CalendarLayout(Context context) {
        this(context, null);
    }

    public CalendarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        bitMapH1 = BitmapUtils.getBitMapH(context, mBgResources);
        mBgView = new BgView(getContext(), mBgResources);
        inPicView = NumberPicViewIn.createInPicView(context);
        outPicView = NumberPicViewOut.createOutPicView(context);

        redCircleView = RedCircleView.create(context);
        redCircleView.setVisibility(View.INVISIBLE);

        addView(mBgView.getView());
        addView(redCircleView);
        addView(inPicView.getView());
        addView(outPicView.getView());
        mBgView.showView(bitMapH1, new OnsuccessListener() {
            @Override
            public void next() {
                redCircleView.setVisibility(View.VISIBLE);
                redCircleView.animation();
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChild(mBgView.getView(), widthMeasureSpec, heightMeasureSpec);
        measureChild(inPicView.getView(), widthMeasureSpec, heightMeasureSpec);
        measureChild(outPicView.getView(), widthMeasureSpec, heightMeasureSpec);
        measureChild(redCircleView, widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mBgView.getView().getMeasuredWidth()/* + redCircleView.getMeasuredWidth() / 2 - DensityUtil.dip2px(getContext(), leftOffset)*/,
                mBgView.getView().getMeasuredHeight());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int measuredWidth = mBgView.getView().getMeasuredWidth();
        int measuredHeight = mBgView.getView().getMeasuredHeight();
        mBgView.getView().layout(0, -measuredHeight,
                measuredWidth, 0);
        int redCircleViewMeasuredWidth = redCircleView.getMeasuredWidth();
     /*  redCircleView.layout(measuredWidth - redCircleViewMeasuredWidth / 2,
                0,
                measuredWidth + redCircleViewMeasuredWidth / 2,
                redCircleViewMeasuredWidth);  */
        int top = DensityUtil.dip2px(getContext(), 10);
        redCircleView.layout(measuredWidth - redCircleViewMeasuredWidth / 2 - DensityUtil.dip2px(getContext(), leftOffset),
                top,
                measuredWidth + redCircleViewMeasuredWidth / 2 - DensityUtil.dip2px(getContext(), leftOffset),
                redCircleViewMeasuredWidth + top);
        int inPicViewMeasuredHeight = inPicView.getView().getMeasuredHeight();
        int inPicViewMeasuredWidth = inPicView.getView().getMeasuredWidth();
        inPicView.getView().layout(measuredWidth / 2 - inPicViewMeasuredWidth / 2,
                measuredHeight / 2 - inPicViewMeasuredHeight / 2,
                measuredWidth / 2 + inPicViewMeasuredWidth / 2,
                measuredHeight / 2 + inPicViewMeasuredHeight / 2);
        outPicView.getView().layout(measuredWidth,
                measuredHeight / 2 - inPicViewMeasuredHeight / 2,
                measuredWidth + inPicViewMeasuredWidth,
                measuredHeight / 2 + inPicViewMeasuredHeight / 2);
    }

    int[] ids = {R.mipmap.monthone_b, R.mipmap.monthtwo_b, R.mipmap.monththree_b, R.mipmap.monthfive_b,
            R.mipmap.monthsix_b, R.mipmap.monthseven_b};

    public void setNumberResouces(@DrawableRes int mBgResources) {
        int resId = ids[(int) (1 + Math.random() * (ids.length - 1 - 1 + 1))];
        if (inPicView.isIn()) {
            //当前居中的view出去 isin变false
            inPicView.out();
            outPicView.getView().setImageResource(resId);
            outPicView.in(); // 边上的进来 变true
        } else {
            //isin是false  说明当前view是在边上
            inPicView.getView().setImageResource(resId);
            // 边上的进来 变true
            inPicView.in();
            //当前居中的view出去 isin变false
            outPicView.out();
        }
    }

    interface OnsuccessListener {
        void next();
    }
}
