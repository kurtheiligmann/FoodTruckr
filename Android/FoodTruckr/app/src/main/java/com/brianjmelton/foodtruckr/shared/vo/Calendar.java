package com.brianjmelton.foodtruckr.shared.vo;

/**
 * Created by brianmelton on 1/16/15.
 */
public class Calendar {

    private Restaurant monday;

    private Restaurant tuesday;

    private Restaurant wednesday;

    private Restaurant thursday;

    private Restaurant friday;

    public Restaurant getMonday() {
        return monday;
    }

    public void setMonday(Restaurant monday) {
        this.monday = monday;
    }

    public Restaurant getTuesday() {
        return tuesday;
    }

    public void setTuesday(Restaurant tuesday) {
        this.tuesday = tuesday;
    }

    public Restaurant getWednesday() {
        return wednesday;
    }

    public void setWednesday(Restaurant wednesday) {
        this.wednesday = wednesday;
    }

    public Restaurant getThursday() {
        return thursday;
    }

    public void setThursday(Restaurant thursday) {
        this.thursday = thursday;
    }

    public Restaurant getFriday() {
        return friday;
    }

    public void setFriday(Restaurant friday) {
        this.friday = friday;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Calendar{");
        sb.append("monday=").append(monday);
        sb.append(", tuesday=").append(tuesday);
        sb.append(", wednesday=").append(wednesday);
        sb.append(", thursday=").append(thursday);
        sb.append(", friday=").append(friday);
        sb.append('}');
        return sb.toString();
    }
}
