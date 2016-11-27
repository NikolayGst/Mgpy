
package ru.mgpy.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Red {

    @SerializedName("schedule")
    @Expose
    private List<Schedule> schedule = new ArrayList<Schedule>();

    /**
     * 
     * @return
     *     The schedule
     */
    public List<Schedule> getSchedule() {
        return schedule;
    }

    /**
     * 
     * @param schedule
     *     The schedule
     */
    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }

}
