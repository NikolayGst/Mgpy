package ru.mgpy.Activities.Main.Interactor;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

public class OfflineMainInteractorImpl implements MainInteractor {

    private OnLoadResponseListener mOnLoadResponseListener;

    public OfflineMainInteractorImpl(OnLoadResponseListener onLoadResponseListener) {
        mOnLoadResponseListener = onLoadResponseListener;
    }

    @Override
    public void loadFacData() {
        final List<String> facList = new ArrayList<>();
        facList.add("Оберіть ваш факультет");
        facList.add("Інформатики, математики та економіки");
        facList.add("Природничо - географічний");
        facList.add("Філологічний");
        facList.add("Хіміко-біологічний");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mOnLoadResponseListener.OnLoadFac(facList);
            }
        }, 5000);
    }

    @Override
    public void loadChairData(int idFac) {
        final List<String> chairList = new ArrayList<>();
        switch (idFac) {
            case 1:
                chairList.clear();
                chairList.add("Оберіть вашу кафедру");
                chairList.add("Кафедра педагогіки і педагогічної майстерності");
                chairList.add("Кафедра інформатики і кібернетики");
                chairList.add("Кафедра математики і фізики");
                chairList.add("Кафедра соціології");
                chairList.add("Кафедра економіки");
                chairList.add("Кафедра менеджменту і туристичної індустрії");
                chairList.add("Кафедра прикладної математики та інформаційних технологій");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mOnLoadResponseListener.OnLoadChair(chairList);
                    }
                }, 5000);
                break;
            case 2:
                break;
        }
    }

    @Override
    public void loadGroupData(int idChair) {
        final List<String> groupList = new ArrayList<>();
        switch (idChair) {
            case 2:
                groupList.clear();
                groupList.add("Оберіть вашу групу");
                groupList.add("214");
                groupList.add("314/11i");
                groupList.add("314/12i");
                groupList.add("314/21i");
                groupList.add("314/22i");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mOnLoadResponseListener.OnLoadGroup(groupList);
                    }
                }, 5000);
                break;
            case 3:
                break;
        }

    }

}
