package application.androidstury.com.rycalendar.util;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.DrawableRes;

/**
 * Created by zhangruiyu on 16/6/14.
 */

public class BitmapUtils {
    public static BitmapFactory.Options  getBitMapH(Context context, @DrawableRes int id){
        BitmapFactory.decodeResource(context.getResources(), id);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), id, opts);
        opts.inSampleSize = 1;
        opts.inJustDecodeBounds = false;
        BitmapFactory.decodeResource(context.getResources(), id, opts);
        return opts;
    }
}
