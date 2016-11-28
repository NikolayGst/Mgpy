package ru.mgpy.Activities.Main.View;

import java.util.List;

public interface MainView {

    void showProgressDialog(String text);

    void hideProgressDialog();

    void onLoadFac(List<String> facList);

    void onLoadChair(List<String> chairList);

    void onLoadGroup(List<String> groupList);

    void onLoadLesson(String group);

    void onErrorLoaded(Throwable t);



}
