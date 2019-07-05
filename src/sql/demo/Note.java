package sql.demo;

import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Note {

    private int supId;
    private static AtomicInteger count = new AtomicInteger(0);
    private String title;
    private String content;
    private Date createdOn;
    private Date updateOn;

    public static final String SUP_ID = "sup_id";
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String CREATED_ON = "created_on";
    public static final String UPDATE_ON = "update_on";

    public Note() {
        supId = count.incrementAndGet();
        createdOn = new Date();
        updateOn = new Date();
    }

    @Override
    public String toString() {
        return getTitle();
    }

    public Map<String, Object> toNotes(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int colums = md.getColumnCount();
        Map<String, Object> row = new HashMap<String, Object>(colums);
        row.put(SUP_ID, supId);
        row.put(TITLE, title);
        row.put(CONTENT, content);
        row.put(CREATED_ON, createdOn);
        row.put(UPDATE_ON, updateOn);
        return row;
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

    public int getSupId() {
        return supId;
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
