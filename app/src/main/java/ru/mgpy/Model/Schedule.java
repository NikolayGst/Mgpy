
package ru.mgpy.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Schedule {

    @SerializedName("lesson")
    @Expose
    private String lesson;
    @SerializedName("cab")
    @Expose
    private int cab;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("teacher")
    @Expose
    private String teacher;

    /**
     * 
     * @return
     *     The lesson
     */
    public String getLesson() {
        return lesson;
    }

    /**
     * 
     * @param lesson
     *     The lesson
     */
    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    /**
     * 
     * @return
     *     The cab
     */
    public int getCab() {
        return cab;
    }

    /**
     * 
     * @param cab
     *     The cab
     */
    public void setCab(int cab) {
        this.cab = cab;
    }

    /**
     * 
     * @return
     *     The category
     */
    public String getCategory() {
        return category;
    }

    /**
     * 
     * @param category
     *     The category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 
     * @return
     *     The teacher
     */
    public String getTeacher() {
        return teacher;
    }

    /**
     * 
     * @param teacher
     *     The teacher
     */
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

}
