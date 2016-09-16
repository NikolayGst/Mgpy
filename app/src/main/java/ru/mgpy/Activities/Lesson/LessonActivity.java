package ru.mgpy.Activities.Lesson;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.Calendar;

import ru.mgpy.Adapter.LessonAdapter;
import ru.mgpy.R;

@EActivity(R.layout.activity_lesson)
public class LessonActivity extends AppCompatActivity {

    @Extra
    String week;

    @Extra
    Integer group;

    @ViewById
    TabLayout tabs;

    @ViewById
    Toolbar toolbar;

    @ViewById
    ViewPager container;

    private LessonAdapter mLessonAdapter;

    @AfterInject
    void initTheme() {

        if (week.equals("red")) setTheme(R.style.Red);
        else setTheme(R.style.Green);

    }

    @AfterViews
    void init() {

        initView();

        dayOfWeek();

    }

    private void initView() {
        setSupportActionBar(toolbar);
        if (week.equals("red")) setTitle("Красная неделя");
        else  setTitle("Зеленая неделя");
        mLessonAdapter = new LessonAdapter(getSupportFragmentManager(), group, week);
        container.setAdapter(mLessonAdapter);
        tabs.setupWithViewPager(container);
    }

    private void dayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        int day = -1;
        int calDay = calendar.get(Calendar.DAY_OF_WEEK);
        switch (calDay) {
            case Calendar.MONDAY:
                day = 0;
                break;
            case Calendar.THURSDAY:
                day = 1;
                break;
            case Calendar.WEDNESDAY:
                day = 2;
                break;
            case Calendar.TUESDAY:
                day = 3;
                break;
            case Calendar.FRIDAY:
                day = 4;
                break;
        }
        container.setCurrentItem(day);
    }
}
