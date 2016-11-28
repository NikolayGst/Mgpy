package ru.mgpy.Activities.Main.Interactor;

import java.util.List;

public interface MainInteractor {

    void loadFacData();

    void loadChairData(int idFac);

    void loadGroupData(int idChair);

    void loadGroupLesson(String group);

    interface OnLoadResponseListener {

        void OnLoadFac(List<String> facList);

        void OnLoadChair(List<String> chairList);

        void OnLoadGroup(List<String> groupList);

        void OnLoadLesson(String group);

        void OnError(Throwable t);

    }
}
