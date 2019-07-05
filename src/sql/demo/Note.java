package sql.demo;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Note {

    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String CREATED_ON = "created_on";
    public static final String UPDATE_ON = "update_on";

    private Integer id;
    private String title;
    private String content;
    private Date createdOn;
    private Date updateOn;

    public Note() {
        createdOn = new Date();
        updateOn = new Date();
    }
    public Note(Map<String, Object> row){
        id = (Integer) row.get(ID);
        title = (String) row.get(TITLE);
        content = (String) row.get(CONTENT);
        createdOn = (Date) row.get(CREATED_ON);
        updateOn = (Date) row.get(UPDATE_ON);
    }
    @Override
    public String toString() {
        return getTitle();
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

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
