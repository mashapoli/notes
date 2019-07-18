package sql.demo;

import sql.demo.model.NoteModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoteFrame extends JFrame{

    private NoteManager noteManager;
    private NoteModel noteModel;

    public NoteFrame(NoteManager noteManager){
        super("Notes");
        this.noteManager = noteManager;
        noteModel = new NoteModel(noteManager);
    }

    public final void initComponents() {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel periodPanel = new JPanel(new FlowLayout());
        periodPanel.setBorder(BorderFactory.createTitledBorder("Period"));

        JLabel from = new JLabel("From");
        from.setLayout(new BorderLayout());
        String sFrom = "ex.2019-06-30 20:18:54.7";
        JTextField fieldF = new JTextField();
        fieldF.setText(sFrom);

        periodPanel.add(from);
        periodPanel.add(fieldF);

        JLabel to = new JLabel("To");
        from.setLayout(new BorderLayout());
        String sTo = "ex.2019-07-30 20:18:54.7";
        JTextField fieldTo = new JTextField();
        fieldTo.setText(sTo);

        periodPanel.add(to);
        periodPanel.add(fieldTo);

        JPanel notesPanel = new JPanel(new BorderLayout());
        notesPanel.setBorder(BorderFactory.createTitledBorder("Notes"));
        notesPanel.add(new JList<>(getNoteModel()), BorderLayout.CENTER);

        JButton newNotes = new JButton("New");
        JButton edit = new JButton("Edit");
        JButton remove = new JButton("Remove");

        newNotes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                noteManager.deleteNote(noteManager.getNotes().get(0));
                noteModel.reload();
            }
        });

        JPanel button = new JPanel(new FlowLayout());
        button.setBorder(BorderFactory.createTitledBorder(""));

        button.add(newNotes);
        button.add(edit);
        button.add(remove);

        mainPanel.add(periodPanel, BorderLayout.NORTH);
        mainPanel.add(notesPanel, BorderLayout.CENTER);
        mainPanel.add(button, BorderLayout.SOUTH);


        getContentPane().add(mainPanel);

        JButton show = new JButton("Show");
        periodPanel.add(show);

        setPreferredSize(new Dimension(600, 600));
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public NoteModel getNoteModel() {
        return noteModel;
    }

}