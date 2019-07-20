package sql.demo;

import java.util.Date;
import java.util.Map;

public class Note {

    public static final String ID = "ID";
    public static final String TITLE = "TITLE";
    public static final String CONTENT = "CONTENT";
    public static final String CREATED_ON = "CREATED_ON";
    public static final String UPDATED_ON = "UPDATED_ON";

    private Long id;
    private String title;
    private String content;
    private Date createdOn;
    private Date updateOn;

    public Note() {
        Date now = new Date();
        createdOn = now;
        updateOn = now;
    }
    public Note(Map<String, Object> row){
        id = (Long) row.get(ID);
        title = (String) row.get(TITLE);
        content = (String) row.get(CONTENT);
        createdOn = (Date) row.get(CREATED_ON);
        updateOn = (Date) row.get(UPDATED_ON);
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Date getUpdateOn() {
        return updateOn;
    }

    public void setCreatedOn(Date date) {
        createdOn = date;
    }

    public void setUpdateOn(Date date) {
        updateOn = date;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        setUpdatedOnToNow();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        setUpdatedOnToNow();
    }

    private void setUpdatedOnToNow() {
       this.updateOn = new Date();
    }
    @Override
    public String toString() {
        return getTitle();
    }
}
