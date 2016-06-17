package application.androidstury.com.rycalendar.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import application.androidstury.com.rycalendar.R;

/**
 * Created by zhangruiyu on 16/6/15.
 */

public class NumberPicViewIn extends ImageView implements CalendarContract.View {
    private boolean isIn;

    public NumberPicViewIn(Context context, boolean isIn, int mResouce) {
        super(context);
        this.isIn = isIn;
        setImageResource(mResouce);
    }


    @Override
    public void in() {
        if (!isIn) {
            setVisibility(View.VISIBLE);
            animate().rotation(0).alpha(1f).translationX(0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    isIn = true;
                    animate().setListener(null);
                }
            }).setDuration(500).start();
        }
    }

    @Override
    public void out() {
        if (isIn) {
            setVisibility(View.VISIBLE);
            animate().rotation(360).alpha(0f).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    isIn = false;
                    animate().setListener(null);
                    animate().translationX(200).start();


                }
            }).setDuration(500).start();
        }
    }

    @Override
    public ImageView getView() {
        return this;
    }


    public static CalendarContract.View createInPicView(Context context) {
        return new NumberPicViewIn(context, true, R.mipmap.monthone_b);
    }


    @Override
    public boolean isIn() {
        return isIn;
    }


}
