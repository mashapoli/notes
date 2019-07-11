package sql.demo;

import com.sun.javaws.util.JfxHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotesFrame extends JFrame{
    private JTextField textField;
    private JButton createNote;
    private JButton showAllNotes;
    private JButton showNotesPeriod;
    private  ActionListener actionListener;

    public NotesFrame(){
        super("Notes");

        createGUI();
        CreateNote();
        ShowAllNotes();
        ShowNotesPeriod();
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

        createNote = new JButton("Create Note");
        panel.add(createNote);

        showAllNotes = new JButton("Show all Notes");
        panel.add(showAllNotes);

        showNotesPeriod = new JButton("Show Notes for the period");
        panel.add(showNotesPeriod);

        actionListener = new NotesFrame.TestActionListener();

        getContentPane().add(BorderLayout.CENTER, panel);
        getContentPane().add(BorderLayout.NORTH, mb);

}
    public void CreateNote() {

        createNote.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame form2 = new JFrame("Create Note");
                form2.setSize(600, 600);

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

                form2.getContentPane().add(BorderLayout.NORTH, mb);

                JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout());

                JPanel panelTitle = new JPanel();
                panelTitle.setLayout(new FlowLayout());

                JPanel panelContent = new JPanel();
                panelContent.setLayout(new FlowLayout());

                JButton save = new JButton("Save");
                panel.add(save);

                JButton delete = new JButton("Delete");
                panel.add(delete);

                JLabel title = new JLabel("Title");
                JTextField tf = new JTextField(20);
                panelTitle.add(title);
                panelTitle.add(tf);

                JLabel content = new JLabel("Content");
                JTextArea ta = new JTextArea();
                panelContent.add(content);

                form2.getContentPane().add(BorderLayout.WEST,panelTitle);
                //form2.getContentPane().add(BorderLayout.BEFORE_FIRST_LINE,panelContent);
                form2.getContentPane().add(BorderLayout.CENTER,ta);
                form2. getContentPane().add(BorderLayout.SOUTH, panel);



                NotesFrame frame = new NotesFrame();
                frame.setVisible(false);
                form2.setVisible(true);
            }
        });
    }

   public void ShowAllNotes(){
       showAllNotes.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               JFrame form2 = new JFrame("Show all Notes");
               form2.setSize(600, 600);
               NotesFrame frame = new NotesFrame();
               frame.setVisible(false);
               form2.setVisible(true);
           }
       });
   }

    public void ShowNotesPeriod(){
        showNotesPeriod.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame form2 = new JFrame(" Show Notes for the period");
                form2.setSize(600, 600);
                NotesFrame frame = new NotesFrame();
                frame.setVisible(false);
                form2.setVisible(true);
            }
        });
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