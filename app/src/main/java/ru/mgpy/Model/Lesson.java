
package ru.mgpy.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Lesson {

    @SerializedName("red")
    @Expose
    private List<Red> red = new ArrayList<Red>();
    @SerializedName("green")
    @Expose
    private List<Green> green = new ArrayList<Green>();

    /**
     * 
     * @return
     *     The red
     */
    public List<Red> getRed() {
        return red;
    }

    /**
     * 
     * @param red
     *     The red
     */
    public void setRed(List<Red> red) {
        this.red = red;
    }

    /**
     * 
     * @return
     *     The green
     */
    public List<Green> getGreen() {
        return green;
    }

    /**
     * 
     * @param green
     *     The green
     */
    public void setGreen(List<Green> green) {
        this.green = green;
    }

}
