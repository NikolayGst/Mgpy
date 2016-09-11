package ru.mgpy.Activities.Lesson.Interactor;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import ru.mgpy.Activities.Lesson.Presenter.LessonPresenter;
import ru.mgpy.Model.Lesson;

public class LessonInteractorImpl implements LessonInteractor {

    private LessonPresenter mLessonPresenter;
    private Gson mGson;
    private List<Lesson> mLessonList;

    public LessonInteractorImpl(LessonPresenter lessonPresenter) {
        mLessonPresenter = lessonPresenter;
    }

    @Override
    public void loadLessonList(int id) {
        mGson = new Gson();
        mLessonList = new ArrayList<>();
        initList();
        Lesson lesson = mLessonList.get(id);
        if (lesson != null) mLessonPresenter.onLoadLesson(lesson.getSchedule());
    }

    private void initList() {
        String json = "[{\"schedule\":[{\"lesson\":\"Бази даних та інформаційні системи\",\"cab\":41},{\"lesson\":\"Медіаосвіта та медіаграмотність\",\"cab\":41}]},{\"schedule\":[{\"lesson\":\"Математичний аналіз\",\"cab\":41},{\"lesson\":\"Програмування та підтримка веб-програмувань\",\"cab\":41}]},{\"schedule\":[{\"lesson\":\"Програмування\",\"cab\":41},{\"lesson\":\"Математичний аналіз\",\"cab\":41}]},{\"schedule\":[{\"lesson\":\"Програмування та підтримка веб-програмувань\",\"cab\":41},{\"lesson\":\"Програмування\",\"cab\":41},{\"lesson\":\"Перелік-1\",\"cab\":41}]},{\"schedule\":[{\"lesson\":\"Бази даних та інформаційні системи\",\"cab\":41},{\"lesson\":\"Перелік-2\",\"cab\":41}]}]";
        JsonReader reader = new JsonReader(new StringReader(json));
        reader.setLenient(true);
        mLessonList = mGson.fromJson(reader, new TypeToken<List<Lesson>>() {
        }.getType());
    }
}
