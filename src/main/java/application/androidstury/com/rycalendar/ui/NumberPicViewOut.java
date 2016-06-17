package application.androidstury.com.rycalendar.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.widget.ImageView;

import application.androidstury.com.rycalendar.R;

/**
 * Created by zhangruiyu on 16/6/16.
 */

public class NumberPicViewOut extends ImageView implements CalendarContract.View {
    private boolean isIn;

    public NumberPicViewOut(Context context, boolean isIn, int mResouce) {
        super(context);
        this.isIn = isIn;
        setImageResource(mResouce);
    }


    @Override
    public void in() {
        animate().rotation(0).alpha(1f).translationX(-200).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                isIn = true;
                animate().setListener(null);
            }
        }).setDuration(500).start();
    }

    @Override
    public void out() {

        animate().rotation(360).alpha(0f).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                isIn = false;
                animate().setListener(null);
                animate().translationX(200).start();
            }
        }).setDuration(500).start();
    }

    @Override
    public ImageView getView() {
        return this;
    }

    public static NumberPicViewOut createOutPicView(Context context) {
        return new NumberPicViewOut(context, false, R.mipmap.monthtwo_b);
    }

    @Override
    public boolean isIn() {
        return isIn;
    }


}
