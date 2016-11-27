package ru.mgpy.Activities.Lesson.Interactor;


import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import ru.mgpy.Activities.Lesson.Presenter.LessonPresenter;
import ru.mgpy.Model.DB.Green;
import ru.mgpy.Model.DB.Group;
import ru.mgpy.Model.DB.Red;
import ru.mgpy.Model.Schedule;

public class LessonInteractorImpl implements LessonInteractor {

    private LessonPresenter mLessonPresenter;
    Realm realm = Realm.getDefaultInstance();

    public LessonInteractorImpl(LessonPresenter lessonPresenter) {
        mLessonPresenter = lessonPresenter;
    }

    @Override
    public void loadLessonList(int id, String group, String week) {
        Group groupDB = realm.where(Group.class).equalTo("group", group).findFirst();
        ru.mgpy.Model.DB.Lesson lesson = groupDB.getLesson();


        switch (week) {
            case "red":
                RealmList<Red> red = lesson.getRed();
                Red redDB = red.get(id);
                if (redDB != null) mLessonPresenter.onLoadLesson(initListRed(redDB));
                break;
            case "green":
                RealmList<Green> green = lesson.getGreen();
                Green greenDB = green.get(id);
                if (greenDB != null) mLessonPresenter.onLoadLesson(initListGreen(greenDB));
                break;
        }

    }

    private List<Schedule> initListRed(Red red) {
        List<Schedule> scheduleList = new ArrayList<>();
        for (ru.mgpy.Model.DB.Schedule scheduleDB : red.getSchedule()) {
            Schedule schedule = new Schedule();
            schedule.setLesson(scheduleDB.getLesson());
            schedule.setTeacher(scheduleDB.getTeacher());
            schedule.setCab(scheduleDB.getCab());
            schedule.setCategory(scheduleDB.getCategory());
            scheduleList.add(schedule);
        }
        return scheduleList;
    }

    private List<Schedule> initListGreen(Green green) {
        List<Schedule> scheduleList = new ArrayList<>();
        for (ru.mgpy.Model.DB.Schedule scheduleDB : green.getSchedule()) {
            Schedule schedule = new Schedule();
            schedule.setLesson(scheduleDB.getLesson());
            schedule.setTeacher(scheduleDB.getTeacher());
            schedule.setCab(scheduleDB.getCab());
            schedule.setCategory(scheduleDB.getCategory());
            scheduleList.add(schedule);
        }
        return scheduleList;
    }
}
