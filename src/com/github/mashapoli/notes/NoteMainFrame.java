package com.github.mashapoli.notes;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static java.util.Objects.isNull;
import static com.github.mashapoli.notes.NoteFrame.ButtonOption.*;

public class NoteMainFrame extends JFrame {

    private NoteManager noteManager;
    private NoteModel noteModel;
    public JList list;


    public NoteMainFrame(NoteManager noteManager){
        super("com/github/mashapoli/notes");
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
        String sFrom = "YYYY-MM-DD";
        JFormattedTextField fieldFrom = new JFormattedTextField(getMaskFormatter());
        fieldFrom.setColumns(6);
        fieldFrom.setText(sFrom);

        periodPanel.add(from);
        periodPanel.add(fieldFrom);

        JLabel to = new JLabel("To");
        from.setLayout(new BorderLayout());
        String sTo = "YYYY-MM-DD";
        JFormattedTextField fieldTo = new JFormattedTextField(getMaskFormatter());
        fieldTo.setColumns(6);
        fieldTo.setText(sTo);

        periodPanel.add(to);
        periodPanel.add(fieldTo);

        {
            JButton applyButton = new JButton("Apply");
            applyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        String fieldFromValue = (String) fieldFrom.getValue();
                        String fieldToValue = (String) fieldTo.getValue();
                        if(!isNull(fieldFromValue) && !isNull(fieldToValue)) {
                            noteModel.setFromDate(format.parse(fieldFromValue));
                            noteModel.setToDate(format.parse(fieldToValue));
                            noteModel.reload();
                        }
                    } catch (ParseException ex) {
                        throw new IllegalArgumentException(ex);
                    }
                }
            });
            periodPanel.add(applyButton);
        }

        {
            JButton resetRangeButton = new JButton("Reset");
            resetRangeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fieldFrom.setText("");
                    fieldTo.setText("");
                    noteModel.setFromDate(null);
                    noteModel.setToDate(null);
                    noteModel.reload();
                }
            });
            periodPanel.add(resetRangeButton);
        }

        JPanel notesPanel = new JPanel(new BorderLayout());
        notesPanel.setBorder(BorderFactory.createTitledBorder("Notes"));
        list = new JList(getNoteModel());
        notesPanel.add(list, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBorder(BorderFactory.createTitledBorder(""));

        {
            JButton newNoteButton = new JButton("New");
            newNoteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame noteFrame1 = new NoteFrame(noteModel,
                            "New Note", CREATE_CANCEL, new Note());
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
                        JFrame noteFrame1 = new NoteFrame(
                                noteModel, "View Note", BACK, selectedNote);
                        noteFrame1.setVisible(true);
                    }
                }
            });
            buttonPanel.add(viewButton);
        }
        {
            JButton editButton = new JButton("Edit");
            editButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Note selectedNote = ((JList<Note>) list).getSelectedValue();
                    if (!isNull(selectedNote)) {
                        JFrame noteFrame1 = new NoteFrame(
                                noteModel, "Edit Note", SAVE_CANCEL, selectedNote);
                        noteFrame1.setVisible(true);
                    }
                }
            });
            buttonPanel.add(editButton);
        }
        {
            JButton removeButton = new JButton("Remove");
            removeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Note selectedNote = ((JList<Note>) list).getSelectedValue();
                    if (!isNull(selectedNote)) {
                        noteModel.deleteNote(selectedNote);
                        noteModel.reload();
                    }
                }
            });
            buttonPanel.add(removeButton);
        }

        mainPanel.add(periodPanel, BorderLayout.NORTH);
        mainPanel.add(notesPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);


        getContentPane().add(mainPanel);


        setPreferredSize(new Dimension(600, 600));
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public NoteModel getNoteModel() {
        return noteModel;
    }

    private MaskFormatter getMaskFormatter() {
        try {
            return new MaskFormatter("####-##-##");
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }

    }

}