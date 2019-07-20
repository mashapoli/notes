package Notes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class NoteManager {

    private QueryManager queryManager;

    public NoteManager(QueryManager queryManager) {
        this.queryManager = queryManager;
    }

    public void addNote(Note n) {
        queryManager.execute(String.format(
                "INSERT INTO NOTES(TITLE, CONTENT, CREATED_ON, UPDATED_ON) " +
                "VALUES('%s', '%s', TS '%s', TS '%s')",
                        n.getTitle(), n.getContent(), dateToTsStr(n.getCreatedOn()), dateToTsStr(n.getUpdateOn())));
    }

    public void updateNote(Note n) {

        if (n.getId() == null) {
            throw new IllegalArgumentException("Note doesn't exists in DB");
        }
        queryManager.execute(
                String.format("UPDATE NOTES SET TITLE = '%s', CONTENT = '%s', UPDATED_ON = TS '%s' WHERE ID = %s",
                        n.getTitle(), n.getContent(), dateToTsStr(n.getUpdateOn()), n.getId()));
    }

    public void deleteNote(Note n) {
        queryManager.execute(
                String.format("DELETE FROM NOTES WHERE ID = %s", n.getId()));
    }

    public List<Note> getNotesByRange(Date dateFrom, Date dateTo) {

        List<Map<String, Object>> rows = queryManager.executeQuery(
                String.format("SELECT * FROM NOTES " +
                                "WHERE UPDATED_ON BETWEEN (TS '%s') AND (TS '%s') OR CREATED_ON BETWEEN (TS '%s') AND (TS '%s')",
                        dateToTsStr(dateFrom), dateToTsStr(dateTo), dateToTsStr(dateFrom), dateToTsStr(dateTo)));

        return mapsToNotes(rows);
    }

    public List<Note> getNotes() {

        List<Map<String, Object>> rows = queryManager.executeQuery("SELECT * FROM NOTES");
        return mapsToNotes(rows);
    }

    private static List<Note> mapsToNotes(List<Map<String, Object>> rows) {
        List<Note> notes = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            notes.add(new Note(row));
        }
        return notes;
    }

    public Note getNote(Integer id) {
        List<Map<String, Object>> rows = queryManager.executeQuery(
                String.format("SELECT * FROM NOTES WHERE ID = %s", id));
        if(rows.isEmpty()) {
            throw new IllegalArgumentException("Note id = " +id + " not found");
        }
        return new Note(rows.get(0));
    }

    private static DateFormat tsDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static String dateToTsStr(Date date) {
        return tsDateFormat.format(date);
    }

}
