package ru.mgpy.Activities.Lesson.Presenter;

import java.util.List;

import ru.mgpy.Model.Schedule;

public interface LessonPresenter {

    void getLesson(int id, int group, String week);

    void onLoadLesson(List<Schedule> schedules);

}
