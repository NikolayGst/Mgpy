
package ru.mgpy.Model.DB;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Red extends RealmObject {

    private RealmList<Schedule> schedule = new RealmList<Schedule>();

    public RealmList<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(RealmList<Schedule> schedule) {
        this.schedule = schedule;
    }

}
