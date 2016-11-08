package ru.mgpy.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ru.mgpy.Activities.Lesson.LessonListFragment;


public class LessonAdapter extends FragmentPagerAdapter {

    private String group;
    private String week;

    public LessonAdapter(FragmentManager fm, String group, String week) {
        super(fm);
        this.group = group;
        this.week = week;
    }

    @Override
    public Fragment getItem(int position) {
        return LessonListFragment.newInstance(position, group, week);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Пн";
            case 1:
                return "Вт";
            case 2:
                return "Ср";
            case 3:
                return "Чт";
            case 4:
                return "Пт";
        }
        return null;
    }
}
