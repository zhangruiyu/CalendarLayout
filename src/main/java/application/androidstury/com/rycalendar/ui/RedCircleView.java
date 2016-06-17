package application.androidstury.com.rycalendar.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;

import application.androidstury.com.rycalendar.util.DensityUtil;

/**
 * Created by zhangruiyu on 16/6/16.
 */

public class RedCircleView extends View {
    private Paint paint = new Paint();
    private int circleViewColor;
    private int whiteViewColor;
    private float whiteCircleRadius = .5f;
    private int measuredWidth;
    private int measuredHeight;

    /*
      android:layout_width="6dp"
            android:id="@+id/circle"
            android:layout_marginLeft="40dp"
            android:layout_marginTop="40dp"
            android:layout_height="6dp" />
    * */
    public RedCircleView(Context context) {
        super(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(DensityUtil.dip2px(context, 6), DensityUtil.dip2px(context, 6f));
        setLayoutParams(layoutParams);
        init();
    }

    private void init() {
        paint.setAntiAlias(true);
        circleViewColor = Color.parseColor("#ff0000");
        whiteViewColor = Color.parseColor("#ffffff");
        //  setPivotX(measuredWidth / 2);
        // setPivotY(measuredHeight / 2);
    }

    public RedCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public RedCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        measuredWidth = getMeasuredWidth();
        measuredHeight = getMeasuredHeight();
        paint.setColor(whiteViewColor);
        int pxWhiteRadius = DensityUtil.dip2px(getContext(), whiteCircleRadius);
        int redRadius = measuredWidth / 2 - pxWhiteRadius;
        canvas.drawCircle(measuredWidth / 2, measuredHeight / 2, measuredWidth / 2, paint);
        paint.setColor(circleViewColor);
        canvas.drawCircle(measuredWidth / 2, measuredHeight / 2, redRadius, paint);
    }

    public void animation() {
        animate().scaleX(3f).scaleY(3f).setDuration(200).setListener(new AnimatorListenerAdapter() {
                                                                         @Override
                                                                         public void onAnimationEnd(Animator animation) {
                                                                             super.onAnimationEnd(animation);
                                                                             animate().scaleX(2.0f).scaleY(2.0f).setListener(new AnimatorListenerAdapter() {
                                                                                 @Override
                                                                                 public void onAnimationEnd(Animator animation) {
                                                                                     animate().scaleX(2.5f).scaleY(2.5f).setDuration(200).start();
                                                                                 }
                                                                             }).setDuration(200).start();
                                                                         }
                                                                     }
        ).setInterpolator(new OvershootInterpolator(8.0f)).start();

    }

    public void reset() {
        animate().scaleX(1f).scaleY(1f).setDuration(1).start();
    }

    public static RedCircleView create(Context context) {
        return new RedCircleView(context);
    }

}
