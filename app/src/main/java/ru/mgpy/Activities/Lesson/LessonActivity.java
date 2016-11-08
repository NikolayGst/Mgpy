package ru.mgpy.Activities.Lesson;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.joda.time.LocalDate;

import ru.mgpy.Adapter.LessonAdapter;
import ru.mgpy.R;

@EActivity(R.layout.activity_lesson)
public class LessonActivity extends AppCompatActivity {

    @Extra
    String week;

    @Extra
    String group;

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
        toolbar.setNavigationIcon(R.mipmap.ic_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        if (week.equals("red")) setTitle("Червоний тиждень");
        else  setTitle("Зелений тиждень");
        mLessonAdapter = new LessonAdapter(getSupportFragmentManager(), group, week);
        container.setAdapter(mLessonAdapter);
        tabs.setupWithViewPager(container);
    }

    private void dayOfWeek() {
        int day = -1;
        LocalDate date = LocalDate.now();
        switch (date.getDayOfWeek()) {
            case 1:
                day = 0;
                break;
            case 2:
                day = 1;
                break;
            case 3:
                day = 2;
                break;
            case 4:
                day = 3;
                break;
            case 5:
                day = 4;
                break;
        }
        container.setCurrentItem(day);
    }
}
