
package ru.mgpy.Model.DB;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Lesson extends RealmObject {

    private RealmList<Red> red = new RealmList<>();

    private RealmList<Green> green = new RealmList<>();

    public RealmList<Red> getRed() {
        return red;
    }

    public void setRed(RealmList<Red> red) {
        this.red = red;
    }

    public RealmList<Green> getGreen() {
        return green;
    }

    public void setGreen(RealmList<Green> green) {
        this.green = green;
    }

}
