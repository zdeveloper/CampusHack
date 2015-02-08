package mobi.uta.campussynergy.DataModel;

import java.util.Date;

/**
 * Created by zedd on 2/7/15.
 */
public class Event {
    String title, desctiption, fb_author, fb_page, type, img_url, pg_color;
    Date startTime, endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesctiption() {
        return desctiption;
    }

    public void setDesctiption(String desctiption) {
        this.desctiption = desctiption;
    }

    public String getFb_author() {
        return fb_author;
    }

    public void setFb_author(String fb_author) {
        this.fb_author = fb_author;
    }

    public String getFb_page() {
        return fb_page;
    }

    public void setFb_page(String fb_page) {
        this.fb_page = fb_page;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getPg_color() {
        return pg_color;
    }

    public void setPg_color(String pg_color) {
        this.pg_color = pg_color;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
