package sql.demo;
import java.sql.*;
import java.util.*;

public class Main {

    public static String DB_URL = "jdbc:h2:/D:/JavaPrj/SQLDemo/db/notesDB";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void main(String[] args) throws SQLException {

        QueryManager queryManager = new QueryManager(getConnection());
        queryManager.execute("DROP TABLE IF EXISTS NOTES");
        queryManager.execute(
                "CREATE TABLE NOTES(ID BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "TITLE VARCHAR(255), CONTENT VARCHAR(255), CREATED_ON TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                "UPDATED_ON TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP)"
        );

        NoteManager noteManager = new NoteManager(queryManager);
        Note note1 = new Note();
        note1.setTitle("Note 1");
        note1.setContent("Content 1");
        noteManager.addNote(note1);

        Note note2 = new Note();
        note2.setTitle("Note 2");
        note2.setContent("Content 2");
        noteManager.addNote(note2);

        Note note3 = new Note();
        note3.setTitle("Note 3");
        note3.setContent("Content 3");
        noteManager.addNote(note3);

        List<Note> notes = noteManager.getNotes();
        System.out.println("notes = " + notes);

        noteManager.deleteNote(noteManager.getNotes().get(0));

        notes = noteManager.getNotes();
        System.out.println("notes = " + notes);
    }
}

