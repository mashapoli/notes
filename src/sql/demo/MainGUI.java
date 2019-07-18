package sql.demo;

import javax.swing.*;

public class MainGUI {
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                NotesFrame notesFrame = new NotesFrame();
                notesFrame.setVisible(true);
            }
        });
        System.out.println("hello outside");
    }
}
