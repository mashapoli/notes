package sql.demo;

import sun.java2d.pipe.SpanShapeRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotesFrame extends JFrame{
    private JTextField textField;

    public NotesFrame(){
        super("Notes");

        createGUI();
    }

    public void createGUI(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);

        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("File");
        mb.add(m1);
        JMenuItem m11 = new JMenuItem("Create new Note");
        JMenuItem m22 = new JMenuItem("Save");
        JMenuItem m33 = new JMenuItem("Remove note");
        JMenuItem m44 = new JMenuItem("Show all notes");
        m1.add(m11);
        m1.add(m22);
        m1.add(m33);
        m1.add(m44);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton createNote = new JButton("Create Note");
        panel.add(createNote);

        JButton showAllNotes = new JButton("Show all Notes");
        panel.add(showAllNotes);

        JButton showNotesPeriod = new JButton("Show Notes for the period");
        panel.add(showNotesPeriod);

        ActionListener actionListener = new NotesFrame.TestActionListener();

        createNote.addActionListener(actionListener);
        showAllNotes.addActionListener(actionListener);
        showNotesPeriod.addActionListener(actionListener);

        getContentPane().add(BorderLayout.CENTER, panel);
        getContentPane().add(BorderLayout.NORTH, mb);

    }

    public class TestActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            textField.setText(e.getActionCommand());
        }
    }

    public static void main(String args[]){
                NotesFrame frame = new NotesFrame();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
    }

}