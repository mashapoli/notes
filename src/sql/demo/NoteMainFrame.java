package sql.demo;

import sql.demo.model.NoteModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.util.Objects.isNull;
import static sql.demo.NoteFrame.ButtonOption.BACK;
import static sql.demo.NoteFrame.ButtonOption.SAVE_CANCEL;

public class NoteMainFrame extends JFrame {  // TODO: rename

    private NoteManager noteManager;
    private NoteModel noteModel;
    public JList list;


    public NoteMainFrame(NoteManager noteManager){
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
        list = new JList(getNoteModel());
        notesPanel.add(list, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBorder(BorderFactory.createTitledBorder(""));

        NoteFrame noteFrame = new NoteFrame(noteModel, list);

        {
            JButton newNoteButton = new JButton("New");
            newNoteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame noteFrame1 = new NoteFrame.NoteFrameX(noteModel,
                            "New Note", SAVE_CANCEL, new Note());
                    noteFrame1.setVisible(true);
                }

            });
            buttonPanel.add(newNoteButton);
        }

        {
            JButton viewButton = new JButton("View");
            viewButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Note selectedNote = ((JList<Note>) list).getSelectedValue();
                    if (!isNull(selectedNote)) {
                        JFrame noteFrame1 = new NoteFrame.NoteFrameX(
                                noteModel, "View Note", BACK, selectedNote);
                        noteFrame1.setVisible(true);
                    }
                }
            });
            buttonPanel.add(viewButton);
        }
//        buttonPanel.add(noteFrame.createEditNoteButton(list));
//        buttonPanel.add(noteFrame.createRemoveNoteButton());

        mainPanel.add(periodPanel, BorderLayout.NORTH);
        mainPanel.add(notesPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);


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