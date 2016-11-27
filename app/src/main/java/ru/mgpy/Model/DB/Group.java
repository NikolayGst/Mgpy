package ru.mgpy.Model.DB;

import io.realm.RealmObject;

public class Group extends RealmObject {

    private String group;
   private Lesson lesson;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
