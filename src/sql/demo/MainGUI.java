package sql.demo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainGUI {

    public static String DB_URL = "jdbc:h2:/D:/JavaPrj/SQLDemo/db/notesDB";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void main(String[] args) {

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

        JFrame.setDefaultLookAndFeelDecorated(true);
        NoteFrame noteFrame = new NoteFrame(noteManager);

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                noteFrame.initComponents();
                noteFrame.setVisible(true);
            }
        });
        System.out.println("hello outside");
    }
}
