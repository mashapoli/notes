package com.github.mashapoli.notes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoteFrame extends JFrame {

    public enum ButtonOption {
        CREATE_CANCEL, SAVE_CANCEL, BACK
    }

    public NoteFrame(NoteModel noteModel, String frameTitle, ButtonOption buttonOption, Note note) {
        super(frameTitle);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel titlePanel = new JPanel(new FlowLayout());
        titlePanel.setBorder(BorderFactory.createTitledBorder("Title"));
        JTextArea titleTextArea = new JTextArea(1,50);
        titleTextArea.setText(note.getTitle());
        titleTextArea.setLineWrap(true);
        titleTextArea.setWrapStyleWord(true);
        titlePanel.add(new JScrollPane(titleTextArea));
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new FlowLayout());
        contentPanel.setBorder(BorderFactory.createTitledBorder("Content"));
        JTextArea contentTextArea = new JTextArea(25,50);
        contentTextArea.setText(note.getContent());
        contentTextArea.setLineWrap(true);
        contentTextArea.setWrapStyleWord(true);
        contentPanel.add(new JScrollPane(contentTextArea));
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        JPanel buttonPanel;
        switch (buttonOption) {
            case CREATE_CANCEL:
                buttonPanel = new JPanel(new FlowLayout());
                JButton newButton = new JButton("Create");
                newButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        note.setTitle(titleTextArea.getText());
                        note.setContent(contentTextArea.getText());
                        noteModel.addNote(note);
                        dispose();
                    }
                });
                buttonPanel.add(newButton);
                JButton cancelButton1 = new JButton("Cancel");
                cancelButton1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                buttonPanel.add(cancelButton1);
            break;
            case SAVE_CANCEL:
                buttonPanel = new JPanel(new FlowLayout());
                JButton saveButton = new JButton("Save");
                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        note.setTitle(titleTextArea.getText());
                        note.setContent(contentTextArea.getText());
                        noteModel.updateNote(note);
                        dispose();
                    }
                });
                buttonPanel.add(saveButton);
                JButton cancelButton = new JButton("Cancel");
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                buttonPanel.add(cancelButton);
                break;
            case BACK:
                buttonPanel = new JPanel(new FlowLayout());
                JButton backButton = new JButton("Back");
                backButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                            dispose();
                    }
                });
                buttonPanel.add(backButton);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + buttonOption);
        }

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
        setSize(600, 600);
        pack();
    }
}
