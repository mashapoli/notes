package sql.demo;

import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Note {

    private int oSupId;
    private static AtomicInteger count = new AtomicInteger(0);
    private String oTitle;
    private String oContent;
    private Date oCreatedOn;
    private Date oUpdateOn;

    public static final String SUP_ID = "sup_id";
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String CREATED_ON = "created_on";
    public static final String UPDATE_ON = "update_on";

    public Note() {
        oSupId = count.incrementAndGet();
        oCreatedOn = new Date();
        oUpdateOn = new Date();
    }

    @Override
    public String toString() {
        return getTitle();
    }

    public Map<String, Object> toNotes(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int colums = md.getColumnCount();
        Map<String, Object> row = new HashMap<String, Object>(colums);
        row.put(SUP_ID, oSupId);
        row.put(TITLE, oTitle);
        row.put(CONTENT, oContent);
        row.put(CREATED_ON, oCreatedOn);
        row.put(UPDATE_ON, oUpdateOn);
        return row;
    }

    public Date getDateCreated() {
        return oCreatedOn;
    }

    public Date getDateUpdate() {
        return oUpdateOn;
    }

    public void setDateCreated(Date date) {
        oCreatedOn = date;
    }

    public void setDateUpdate(Date date) {
        oUpdateOn = date;
    }

    public int getSupId() {
        return oSupId;
    }

    public String getTitle() {
        return oTitle;
    }

    public void setTitle(String title) {
        oTitle = title;
    }

    public String getContent() {
        return oContent;
    }

    public void setContent(String content) {
        oContent = content;
    }
}
