package ru.mgpy.Activities.Lesson.Presenter;


import java.util.List;

import ru.mgpy.Activities.Lesson.View.LessonView;
import ru.mgpy.Activities.Lesson.Interactor.LessonInteractor;
import ru.mgpy.Activities.Lesson.Interactor.LessonInteractorImpl;
import ru.mgpy.Model.Schedule;

public class LessonPresenterImpl implements LessonPresenter {

    private LessonView mLessonView;
    private LessonInteractor mLessonInteractor;

    public LessonPresenterImpl(LessonView lessonView) {
        mLessonView = lessonView;
        mLessonInteractor = new LessonInteractorImpl(this);
    }

    @Override
    public void getLesson(int id, int group, String week) {
        mLessonInteractor.loadLessonList(id, group, week);
    }

    @Override
    public void onLoadLesson(List<Schedule> schedules) {
        mLessonView.onLessonLoaded(schedules);
    }

}
