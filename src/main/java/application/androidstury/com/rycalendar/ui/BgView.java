package application.androidstury.com.rycalendar.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.support.annotation.DrawableRes;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by zhangruiyu on 16/6/15.
 */

public class BgView extends ImageView implements CalendarContract.BgViewRule {

    private int durationMillis = 300;

    public BgView(Context context, @DrawableRes int mBgResources) {
        super(context);
        setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setImageResource(mBgResources);
        //   setBackgroundColor(Color.parseColor("#0000ff"));
    }

    @Override
    public void showView(BitmapFactory.Options bitMapH1, final CalendarLayout.OnsuccessListener onsuccessListener) {
        animate().translationY(bitMapH1.outHeight).setDuration(500).setListener(new AnimatorListenerAdapter() {
                                                                                     @Override
                                                                                     public void onAnimationEnd(Animator animation) {
                                                                                         Rotata rotata = new Rotata();
                                                                                         rotata.setDuration(durationMillis);
                                                                                         rotata.setFillAfter(true);
                                                                                         setTag(true);
                                                                                         startAnimation(rotata);
                                                                                         getHandler().postDelayed(new Runnable() {
                                                                                             @Override
                                                                                             public void run() {
                                                                                                 setTag(false);
                                                                                                 Rotata rotata = new Rotata();
                                                                                                 rotata.setDuration(durationMillis);
                                                                                                 startAnimation(rotata);
                                                                                                 getHandler().postDelayed(new Runnable() {
                                                                                                     @Override
                                                                                                     public void run() {
                                                                                                         onsuccessListener.next();
                                                                                                     }
                                                                                                 }, durationMillis);
                                                                                             }
                                                                                         }, durationMillis);
                                                                                     }
                                                                                 }

        ).start();
    }

    @Override
    public ImageView getView() {
        return this;
    }


    class Rotata extends Animation {
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            Matrix matrix = t.getMatrix();
            Camera camera = new Camera();
            camera.save();
            camera.rotateX(-30 * ((Boolean) getTag() ? interpolatedTime : 1 - interpolatedTime));
            camera.getMatrix(matrix);
            camera.restore();
            matrix.preTranslate(-getMeasuredWidth()/2, -getMeasuredHeight()/2);
            matrix.postTranslate(getMeasuredWidth()/2, getMeasuredHeight()/2);
            invalidate();
        }
    }


}

