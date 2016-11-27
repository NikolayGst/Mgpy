package ru.mgpy.Activities.Main.View;

import java.util.List;

public interface MainView {

    void showProgressDialog(String text);

    void hideProgressDialog();

    void OnLoadFac(List<String> facList);

    void OnLoadChair(List<String> chairList);

    void OnLoadGroup(List<String> groupList);

    void OnLoadLesson(String group);

    void OnErrorLoaded(Exception ex);



}
