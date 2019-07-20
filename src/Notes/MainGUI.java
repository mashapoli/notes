package Notes;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainGUI {

    public static String DB_URL = "jdbc:h2:~/notesDB";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void main(String[] args) {

        QueryManager queryManager = new QueryManager(getConnection());
        queryManager.execute(
                "CREATE TABLE IF NOT EXISTS NOTES(ID BIGINT AUTO_INCREMENT PRIMARY KEY," +
                        "TITLE VARCHAR(255), CONTENT VARCHAR(255), CREATED_ON TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                        "UPDATED_ON TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP)"
        );
        NoteManager noteManager = new NoteManager(queryManager);


        JFrame.setDefaultLookAndFeelDecorated(true);
        NoteMainFrame noteMainFrame = new NoteMainFrame(noteManager);

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                noteMainFrame.initComponents();
                noteMainFrame.setVisible(true);
            }
        });
    }
}
