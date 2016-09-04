package ru.mgpy.Activities.Lesson.View;

import java.util.List;

import ru.mgpy.Model.Schedule;

public interface LessonView {

    void onLessonLoaded(List<Schedule> schedules);

}
