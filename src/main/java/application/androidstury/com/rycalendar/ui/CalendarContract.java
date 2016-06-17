package application.androidstury.com.rycalendar.ui;

import android.graphics.BitmapFactory;
import android.widget.ImageView;

/**
 * Created by zhangruiyu on 16/6/15.
 */

public interface CalendarContract {
    interface View extends BaseView<View> {
        void in();

        void out();

        ImageView getView();

        boolean isIn();
    }

    interface BgViewRule {
        void showView(BitmapFactory.Options bitMapH1, CalendarLayout.OnsuccessListener onsuccessListener);

        ImageView getView();
    }
}
