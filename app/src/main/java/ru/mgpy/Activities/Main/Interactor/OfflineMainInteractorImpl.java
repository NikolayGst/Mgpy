package ru.mgpy.Activities.Main.Interactor;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.mgpy.Model.DB.Green;
import ru.mgpy.Model.DB.Group;
import ru.mgpy.Model.DB.Red;
import ru.mgpy.Model.Lesson;
import ru.mgpy.Model.Schedule;
import ru.mgpy.Network.Request;

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
        }, 1000);
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
                }, 1000);
                break;
            default:
                mOnLoadResponseListener.OnLoadChair(new ArrayList<String>());
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
                }, 1000);
                break;
            default:
                mOnLoadResponseListener.OnLoadGroup(new ArrayList<String>());
        }

    }

    @Override
    public void loadGroupLesson(final String group) {
        Request.getAPI().getLessonList(group).enqueue(new Callback<Lesson>() {
            @Override
            public void onResponse(Call<Lesson> call, Response<Lesson> response) {
                if (response.body() != null) {
                    checkDB(group, response.body());
                    mOnLoadResponseListener.OnLoadLesson(group);
                }
            }

            @Override
            public void onFailure(Call<Lesson> call, Throwable t) {
                mOnLoadResponseListener.OnError((Exception) t);
            }
        });
    }

    private void checkDB(String group, Lesson lesson){
        Realm realm = Realm.getDefaultInstance();
        Group groupDb = realm.where(Group.class).equalTo("group", group).findFirst();
        if (groupDb == null) {
            createDB(group, lesson, realm);
        } else {
            realm.beginTransaction();
            groupDb.deleteFromRealm();
            realm.commitTransaction();
            createDB(group, lesson, realm);
        }
    }

    private void createDB(String group, Lesson lesson, Realm realm){
        realm.beginTransaction();
        Group createGroup = realm.createObject(Group.class);
        createGroup.setGroup(group);

        ru.mgpy.Model.DB.Lesson lessonDB = realm.createObject(ru.mgpy.Model.DB.Lesson.class);
        RealmList<Red> redRealmList = new RealmList<>();
        for (ru.mgpy.Model.Red red : lesson.getRed()) {
            Red redDB = realm.createObject(Red.class);
            RealmList<ru.mgpy.Model.DB.Schedule> scheduleRealmList = new RealmList<>();
            for (Schedule schedule : red.getSchedule()) {
                ru.mgpy.Model.DB.Schedule scheduleDB = realm.createObject(ru.mgpy.Model.DB.Schedule.class);
                scheduleDB.setCab(schedule.getCab());
                scheduleDB.setCategory(schedule.getCategory());
                scheduleDB.setLesson(schedule.getLesson());
                scheduleDB.setTeacher(schedule.getTeacher());
                scheduleRealmList.add(scheduleDB);
            }
            redDB.setSchedule(scheduleRealmList);
            redRealmList.add(redDB);
        }
        lessonDB.setRed(redRealmList);

        RealmList<Green> greenRealmList = new RealmList<>();
        for (ru.mgpy.Model.Green green : lesson.getGreen()) {
            Green greenDB = realm.createObject(Green.class);
            RealmList<ru.mgpy.Model.DB.Schedule> scheduleRealmList = new RealmList<>();
            for (Schedule schedule : green.getSchedule()) {
                ru.mgpy.Model.DB.Schedule scheduleDB =realm.createObject(ru.mgpy.Model.DB.Schedule.class);
                scheduleDB.setCab(schedule.getCab());
                scheduleDB.setCategory(schedule.getCategory());
                scheduleDB.setLesson(schedule.getLesson());
                scheduleDB.setTeacher(schedule.getTeacher());
                scheduleRealmList.add(scheduleDB);
            }
            greenDB.setSchedule(scheduleRealmList);
            greenRealmList.add(greenDB);
        }
        lessonDB.setGreen(greenRealmList);

        createGroup.setLesson(lessonDB);
        realm.commitTransaction();
    }

}
