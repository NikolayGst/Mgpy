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
    private String red22group = "[{\"schedule\":[{\"lesson\":\"Медіаосвіта та медіаграмотність\",\"cab\":32,\"category\":\"практ.\",\"teacher\":\"Наумук І.М.\"},{\"lesson\":\"Програмування\",\"cab\":41,\"category\":\"лекція\",\"teacher\":\"Еремєєв В.С.\"}]},{\"schedule\":[{\"lesson\":\"Програмування та підтримка веб-програмувань\",\"cab\":29,\"category\":\"практ.\",\"teacher\":\"Осадчий В.В.\"},{\"lesson\":\"База даних та інформаційні системи\",\"cab\":32,\"category\":\"практ.\",\"teacher\":\"Шаров С.В.\"},{\"lesson\":\"Організаційно-виховна година\",\"cab\":41,\"category\":\"\",\"teacher\":\"\"}]},{\"schedule\":[{\"lesson\":\"Перелік 1\",\"cab\":32,\"category\":\"практ.\",\"teacher\":\"Чемерис Г.Ю.\"},{\"lesson\":\"Програмування та підтримка веб-програмувань\",\"cab\":57,\"category\":\"лекція\",\"teacher\":\"Осадчий В.В.\"}]},{\"schedule\":[{\"lesson\":\"Програмування\",\"cab\":32,\"category\":\"практ.\",\"teacher\":\"Еремєєв В.С.\"},{\"lesson\":\"Математичний аналіз\",\"cab\":41,\"category\":\"лекція\",\"teacher\":\"Рубцов М.О.\"}]},{\"schedule\":[{\"lesson\":\"Перелік 2\",\"cab\":29,\"category\":\"практ.\",\"teacher\":\"Круглик В.С.\"},{\"lesson\":\"База даних та інформаційні системи\",\"cab\":57,\"category\":\"лекція\",\"teacher\":\"Шаров С.В.\"},{\"lesson\":\"Математичний аналіз\",\"cab\":62,\"category\":\"практ.\",\"teacher\":\"Бурцева О.Г.\"}]}]";
    private String red21group = "[{\"schedule\":[{\"lesson\":\"Програмування\",\"cab\":74,\"category\":\"практ.\",\"teacher\":\"Чемерис Г.Ю.\"},{\"lesson\":\"Програмування\",\"cab\":41,\"category\":\"лекція\",\"teacher\":\"Еремєєв В.С.\"}]},{\"schedule\":[{\"lesson\":\"База даних та інформаційні системи\",\"cab\":32,\"category\":\"практ.\",\"teacher\":\"Шаров С.В.\"},{\"lesson\":\"Програмування та підтримка веб-програмувань\",\"cab\":29,\"category\":\"практ.\",\"teacher\":\"Осадчий В.В.\"},{\"lesson\":\"Організаційно-виховна година\",\"cab\":41,\"category\":\"\",\"teacher\":\"\"}]},{\"schedule\":[{\"lesson\":\"Медіаосвіта та медіаграмотність\",\"cab\":75,\"category\":\"практ.\",\"teacher\":\"Наумук І.М.\"},{\"lesson\":\"Програмування та підтримка веб-програмувань\",\"cab\":57,\"category\":\"лекція\",\"teacher\":\"Осадчий В.В.\"},{\"lesson\":\"Перелік 1\",\"cab\":75,\"category\":\"практ.\",\"teacher\":\"Чемерис Г.Ю.\"}]},{\"schedule\":[{\"lesson\":\"Немає пари\",\"cab\":-1,\"category\":\"\",\"teacher\":\"\"},{\"lesson\":\"Математичний аналіз\",\"cab\":41,\"category\":\"лекція\",\"teacher\":\"Рубцов М.О.\"},{\"lesson\":\"Перелік 2\",\"cab\":32,\"category\":\"практ.\",\"teacher\":\"Круглик В.С.\"}]},{\"schedule\":[{\"lesson\":\"Немає пари\",\"cab\":-1,\"category\":\"\",\"teacher\":\"\"},{\"lesson\":\"База даних та інформаційні системи\",\"cab\":57,\"category\":\"лекція\",\"teacher\":\"Шаров С.В.\"},{\"lesson\":\"Математичний аналіз\",\"cab\":62,\"category\":\"практ.\",\"teacher\":\"Бурцева О.Г.\"}]}]";
    private String green22group = "[{\"schedule\":[{\"lesson\":\"Медіаосвіта та медіаграмотність\",\"cab\":29,\"category\":\"практ.\",\"teacher\":\"Наумук І.М.\"},{\"lesson\":\"Математичний аналіз\",\"cab\":74,\"category\":\"практ.\",\"teacher\":\"Бурцева О.Г.\"}]},{\"schedule\":[{\"lesson\":\"Програмування та підтримка веб-програмувань\",\"cab\":29,\"category\":\"практ.\",\"teacher\":\"Осадчий В.В.\"},{\"lesson\":\"Программування\",\"cab\":31,\"category\":\"практ.\",\"teacher\":\"Єремєєв В.С.\"},{\"lesson\":\"Організаційно-виховна година\",\"cab\":41,\"category\":\"\",\"teacher\":\"\"}]},{\"schedule\":[{\"lesson\":\"Медіаосвіта та медіаграмотність\",\"cab\":32,\"category\":\"лекція\",\"teacher\":\"Наумук І.М.\"},{\"lesson\":\"Перелік 1\",\"cab\":32,\"category\":\"лекція\",\"teacher\":\"Наумук О.В.\"},{\"lesson\":\"Програмування\",\"cab\":41,\"category\":\"лекція\",\"teacher\":\"Еремєєв В.С.\"}]},{\"schedule\":[{\"lesson\":\"Перелік 2\",\"cab\":41,\"category\":\"лекція\",\"teacher\":\"Круглик В.С.\"},{\"lesson\":\"Перелік 1\",\"cab\":32,\"category\":\"практ.\",\"teacher\":\"Чемерис Г.Ю.\"}]},{\"schedule\":[{\"lesson\":\"Перелік 2\",\"cab\":31,\"category\":\"практ.\",\"teacher\":\"Круглик В.С.\"},{\"lesson\":\"База даних та інформаційні системи\",\"cab\":41,\"category\":\"лекція\",\"teacher\":\"Шаров С.В.\"}]}]";
    private String green21group = "[{\"schedule\":[{\"lesson\":\"Программування\",\"cab\":32,\"category\":\"практ.\",\"teacher\":\"Чемерис Г.Ю.\"},{\"lesson\":\"Математичний аналіз\",\"cab\":74,\"category\":\"практ.\",\"teacher\":\"Бурцева О.Г.\"}]},{\"schedule\":[{\"lesson\":\"Медіаосвіта та медіаграмотність\",\"cab\":30,\"category\":\"практ.\",\"teacher\":\"Наумук І.М.\"},{\"lesson\":\"Програмування та підтримка веб-програмувань\",\"cab\":29,\"category\":\"практ.\",\"teacher\":\"Осадчий В.В.\"},{\"lesson\":\"Організаційно-виховна година\",\"cab\":41,\"category\":\"\",\"teacher\":\"\"}]},{\"schedule\":[{\"lesson\":\"Медіаосвіта та медіаграмотність\",\"cab\":32,\"category\":\"лекція\",\"teacher\":\"Наумук І.М.\"},{\"lesson\":\"Перелік 1\",\"cab\":32,\"category\":\"лекція\",\"teacher\":\"Наумук О.В.\"},{\"lesson\":\"Програмування\",\"cab\":41,\"category\":\"лекція\",\"teacher\":\"Еремєєв В.С.\"}]},{\"schedule\":[{\"lesson\":\"Перелік 2\",\"cab\":41,\"category\":\"лекція\",\"teacher\":\"Круглик В.С.\"},{\"lesson\":\"Перелік 2\",\"cab\":30,\"category\":\"практ.\",\"teacher\":\"Круглик В.С.\"}]},{\"schedule\":[{\"lesson\":\"Перелік 1\",\"cab\":75,\"category\":\"практ.\",\"teacher\":\"Чемерис Г.Ю.\"},{\"lesson\":\"База даних та інформаційні системи\",\"cab\":41,\"category\":\"лекція\",\"teacher\":\"Шаров С.В.\"}]}]";

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
        switch (week) {
            case "red":
                json = group == 1 ? red22group : red21group;
                break;
            case "green":
                json = group == 1 ? green22group : green21group;
                break;
        }
        JsonReader reader = new JsonReader(new StringReader(json));
        reader.setLenient(true);
        mLessonList = mGson.fromJson(reader, new TypeToken<List<Lesson>>() {}.getType());
    }
}
