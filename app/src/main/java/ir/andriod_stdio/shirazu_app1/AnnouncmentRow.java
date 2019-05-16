package ir.andriod_stdio.shirazu_app1;

import android.widget.TextView;

public class AnnouncmentRow {
    private String announcmnetTitle ;
    private String announcmnetTime ;

    public AnnouncmentRow(String announcmnetTitle, String announcmnetTime) {
        this.announcmnetTitle = announcmnetTitle;
        this.announcmnetTime = announcmnetTime;
    }

    public String getAnnouncmnetTitle() {
        return announcmnetTitle;
    }

    public void setAnnouncmnetTitle(String announcmnetTitle) {
        this.announcmnetTitle = announcmnetTitle;
    }

    public String getAnnouncmnetTime() {
        return announcmnetTime;
    }

    public void setAnnouncmnetTime (String announcmnetTime) {
        this.announcmnetTime = announcmnetTime;
    }
}
