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
    private String red22group = "[{\"schedule\":[{\"lesson\":\"Медіаосвіта та медіаграмотність\",\"cab\":32,\"category\":\"практ.\"},{\"lesson\":\"Програмування\",\"cab\":32,\"category\":\"лекцiя\"}]},{\"schedule\":[{\"lesson\":\"Програмування та підтримка веб-програмувань\",\"cab\":29,\"category\":\"практ.\"},{\"lesson\":\"Бази даних та інформаційні системи\",\"cab\":32,\"category\":\"практ.\"},{\"lesson\":\"Організаційно-виховна година\",\"cab\":41,\"category\":\"\"}]},{\"schedule\":[{\"lesson\":\"Перелік-1\",\"cab\":32,\"category\":\"практ.\"},{\"lesson\":\"Програмування та підтримка веб-програмувань\",\"cab\":57,\"category\":\"лекція\"}]},{\"schedule\":[{\"lesson\":\"Немає пари\",\"cab\":1,\"category\":\"\"},{\"lesson\":\"Математичний аналіз\",\"cab\":41,\"category\":\"лекція\"},{\"lesson\":\"Програмування\",\"cab\":74,\"category\":\"практ.\"}]},{\"schedule\":[{\"lesson\":\"Перелік-2\",\"cab\":29,\"category\":\"практ.\"},{\"lesson\":\"Бази даних та інформаційні системи\",\"cab\":57,\"category\":\"лекція\"},{\"lesson\":\"Математичний аналіз\",\"cab\":62,\"category\":\"практ.\"}]}]";
    private String red21group = "[{\"schedule\":[{\"lesson\":\"Програмування\",\"cab\":74,\"category\":\"практ.\"},{\"lesson\":\"Програмування\",\"cab\":32,\"category\":\"лекцiя\"}]},{\"schedule\":[{\"lesson\":\"Бази даних та інформаційні системи\",\"cab\":32,\"category\":\"практ.\"},{\"lesson\":\"Програмування та підтримка веб-програмувань\",\"cab\":29,\"category\":\"практ.\"},{\"lesson\":\"Організаційно-виховна година\",\"cab\":41,\"category\":\"\"}]},{\"schedule\":[{\"lesson\":\"Медіаосвіта та медіаграмотність\",\"cab\":75,\"category\":\"практ.\"},{\"lesson\":\"Програмування та підтримка веб-програмувань\",\"cab\":57,\"category\":\"лекція\"},{\"lesson\":\"Перелік-1\",\"cab\":75,\"category\":\"практ.\"}]},{\"schedule\":[{\"lesson\":\"Немає пари\",\"cab\":1,\"category\":\"\"},{\"lesson\":\"Математичний аналіз\",\"cab\":41,\"category\":\"лекція\"},{\"lesson\":\"Перелік-2\",\"cab\":32,\"category\":\"практ.\"}]},{\"schedule\":[{\"lesson\":\"Немає пари\",\"cab\":1,\"category\":\"\"},{\"lesson\":\"Бази даних та інформаційні системи\",\"cab\":57,\"category\":\"лекція\"},{\"lesson\":\"Математичний аналіз\",\"cab\":62,\"category\":\"практ.\"}]}]";


    public LessonInteractorImpl(LessonPresenter lessonPresenter) {
        mLessonPresenter = lessonPresenter;
    }

    @Override
    public void loadLessonList(int id, int group, String week) {
        mGson = new Gson();
        mLessonList = new ArrayList<>();
        initList(group, week);
        Lesson lesson = mLessonList.get(id);
        if (lesson != null) mLessonPresenter.onLoadLesson(lesson.getSchedule());
    }

    private void initList(int group, String week) {
        String json = "";
        switch (week){
            case "red":
                json = group == 1 ? red22group : red21group;
                break;
            case "green":
                json = group == 1 ? red22group : red21group;
                break;
        }
       //   String json = "[{\"schedule\":[{\"lesson\":\"Бази даних та інформаційні системи\",\"cab\":41},{\"lesson\":\"Медіаосвіта та медіаграмотність\",\"cab\":41}]},{\"schedule\":[{\"lesson\":\"Математичний аналіз\",\"cab\":41},{\"lesson\":\"Програмування та підтримка веб-програмувань\",\"cab\":41}]},{\"schedule\":[{\"lesson\":\"Програмування\",\"cab\":41},{\"lesson\":\"Математичний аналіз\",\"cab\":41}]},{\"schedule\":[{\"lesson\":\"Програмування та підтримка веб-програмувань\",\"cab\":41},{\"lesson\":\"Програмування\",\"cab\":41},{\"lesson\":\"Перелік-1\",\"cab\":41}]},{\"schedule\":[{\"lesson\":\"Бази даних та інформаційні системи\",\"cab\":41},{\"lesson\":\"Перелік-2\",\"cab\":41}]}]";
        JsonReader reader = new JsonReader(new StringReader(json));
        reader.setLenient(true);
        mLessonList = mGson.fromJson(reader, new TypeToken<List<Lesson>>() {
        }.getType());
    }
}
