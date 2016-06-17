package application.androidstury.com.rycalendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import application.androidstury.com.rycalendar.ui.CalendarLayout;

public class MainActivity extends AppCompatActivity {

    private CalendarLayout calendarlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final CalendarLayout calendarlayout = (CalendarLayout) findViewById(R.id.calendarlayout);
        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarlayout.setNumberResouces(R.mipmap.monthtwo_b);
            }
        });

    }

}
