package com.brianjmelton.foodtruckr.shared.vo;

/**
 * Created by brianmelton on 1/16/15.
 */
public class Restaurant {

    private String name;

    private long id;

    private String logo;

    private String background;

    private String link;

    private String cuisine;

    private String payment;

    private String shortDescription;

    private String longDescription;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Restaurant{");
        sb.append("name='").append(name).append('\'');
        sb.append(", id=").append(id);
        sb.append(", logo='").append(logo).append('\'');
        sb.append(", background='").append(background).append('\'');
        sb.append(", link='").append(link).append('\'');
        sb.append(", cuisine='").append(cuisine).append('\'');
        sb.append(", payment='").append(payment).append('\'');
        sb.append(", shortDescription='").append(shortDescription).append('\'');
        sb.append(", longDescription='").append(longDescription).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
