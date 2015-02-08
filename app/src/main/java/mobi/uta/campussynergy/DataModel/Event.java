package mobi.uta.campussynergy.DataModel;

import android.content.res.Resources;

import com.parse.ParseGeoPoint;

import java.util.Calendar;

import mobi.uta.campussynergy.R;

/**
 * Created by zedd on 2/7/15.
 */
public class Event {
    String title, desctiption, fb_author, fb_page, img_url, pg_color, header, objectId;
    ParseGeoPoint location;
    int type;

    public static final int TYPE_UNIVERSITY = 0;
    public static final int TYPE_GREEK_LIFE = 1;
    public static final int TYPE_STUDY_GROUPS = 2;
    public static final int TYPE_RELIGION = 3;
    public static final int TYPE_DISABILITIES = 4;
    public static final int TYPE_TECHNOLOGY = 5;
    public static final int TYPE_MUSIC = 6;
    public static final int TYPE_ARTS = 7;
    public static final int TYPE_SPORTS = 8;
    public static final int TYPE_WORKSHOPS = 9;
    public static final int TYPE_MEETUPS = 10;
    public static final int TYPE_DIVERSITY = 11;

    public int getCategoryDrawableId(int type) {
        switch (type) {
            case TYPE_UNIVERSITY:
                return R.drawable.ic_school_white_48dp;

            case TYPE_GREEK_LIFE:
                return R.drawable.ic_bank_white_48dp;

            case TYPE_STUDY_GROUPS:
                return R.drawable.ic_account_multiple_white_48dp;

            case TYPE_RELIGION:
                return R.drawable.ic_library_white_48dp;

            case TYPE_DISABILITIES:
                return R.drawable.ic_wheelchair_accessibility_white_48dp;

            case TYPE_TECHNOLOGY:
                return R.drawable.ic_cellphone_link_white_48dp;

            case TYPE_MUSIC:
                return R.drawable.ic_music_box_white_48dp;

            case TYPE_ARTS:
                return R.drawable.ic_brush_white_48dp;

            case TYPE_SPORTS:
                return R.drawable.ic_dribbble_white_48dp;

            case TYPE_WORKSHOPS:
                return R.drawable.ic_presentation_white_48dp;

            case TYPE_MEETUPS:
                return R.drawable.ic_human_male_female_white_48dp;

            case TYPE_DIVERSITY:
                return R.drawable.ic_duck_white_48dp;

            default:
                return R.drawable.ic_school_white_48dp;
        }
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public ParseGeoPoint getLocation() {
        return location;
    }

    public void setLocation(ParseGeoPoint location) {
        this.location = location;
    }

    long headerId;
    Calendar startCal, endCal;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Calendar getEndCal() {
        return endCal;
    }

    public void setEndCal(Calendar endCal) {
        this.endCal = endCal;
    }

    public Calendar getStartCal() {
        return startCal;
    }

    public void setStartCal(Calendar startCal) {
        this.startCal = startCal;
    }

    public long getHeaderId() {
        return headerId;
    }

    public void setHeaderId(long headerid) {
        this.headerId = headerId;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
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


}
