package ru.mgpy.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Lesson {

    @SerializedName("schedule")
    @Expose
    private List<Schedule> schedule = new ArrayList<Schedule>();

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }
}
