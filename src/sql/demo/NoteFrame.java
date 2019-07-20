package sql.demo;

import sql.demo.model.NoteModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.util.Objects.isNull;
import static sql.demo.NoteFrame.ButtonOption.BACK;
import static sql.demo.NoteFrame.ButtonOption.SAVE_CANCEL;


public class NoteFrame {

    private JButton newNotesButton;
    private JButton editButton;
    private JButton removeButton;
    private JButton viewButton;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton backButton;
    private JTextArea areaTitle;
    private JTextArea areaContent;

    private NoteModel noteModel;
    private NoteMainFrame noteMainFrame;

    private JList<Note> noteJList;

    public NoteFrame(NoteModel noteModel, JList<Note> noteJList) {
        this.noteModel = noteModel;
        this.noteJList = noteJList;
    }

    public JButton createNewNotesButton(){
        newNotesButton = new JButton("New");
        newNotesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame noteFrame = createNoteFrame(
                        "New Note", SAVE_CANCEL, "Your title", "Your content");
                noteFrame.setVisible(true);
            }

        });
        return newNotesButton;
    }
    public JButton createEditButton(){
        editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Note selectedNote = noteJList.getSelectedValue();
                if(!isNull(selectedNote)) {
                    JFrame noteFrame = createNoteFrame(
                            "Edit Note", SAVE_CANCEL, selectedNote.getTitle(), selectedNote.getContent());
                    noteFrame.setVisible(true);
                }
            }
        });
        return editButton;
    }

    public JButton createViewButton(){
        viewButton = new JButton("View");
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Note selectedNote = noteJList.getSelectedValue();
                if(!isNull(selectedNote)) {
                    JFrame noteFrame = createNoteFrame(
                            "View Note", BACK, selectedNote.getTitle(), selectedNote.getContent());
                    noteFrame.setVisible(true);
                }
            }
        });
        return viewButton;
    }

    public JButton createRemoveButton(){
        removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Note selectedNote = noteJList.getSelectedValue();
                if(!isNull(selectedNote)) {
                    noteModel.deleteNote(selectedNote);
                    noteModel.reload();
                }
            }
        });
        return removeButton;
    }


    public JButton createSaveButton(){
        saveButton = new JButton("Save");
        return saveButton;
    }

    public JButton createCancelButton(JFrame frame){
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[] options = { "Yes", "No" };
                int n = JOptionPane
                        .showOptionDialog(frame, "Do not save changes??",
                                "Confirmation", JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE, null, options,
                                options[0]);
                if (n == 0) {
                    frame.setVisible(false);
                    frame.dispose();
                }
            }
        });
        return cancelButton;
    }

    public JButton createBackButton(JFrame frame){
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    frame.dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        return backButton;
    }

    public JPanel createContentPanel(String s){
        JPanel contentPanel = new JPanel(new FlowLayout());
        contentPanel.setBorder(BorderFactory.createTitledBorder("Content"));
        areaContent = new JTextArea(25,50);
        areaContent.setText(s);
        areaContent.setLineWrap(true);
        areaContent.setWrapStyleWord(true);
        contentPanel.add(new JScrollPane(areaContent));
        return contentPanel;
    }


    public JPanel createTitlePanel(String s){
        JPanel titlePanel = new JPanel(new FlowLayout());
        titlePanel.setBorder(BorderFactory.createTitledBorder("Title"));
        areaTitle = new JTextArea(1,50);
        areaTitle.setText(s);
        areaTitle.setLineWrap(true);
        areaTitle.setWrapStyleWord(true);
        titlePanel.add(new JScrollPane(areaTitle));
        return titlePanel;
    }

    public JPanel createButtonPanel(JFrame frame){
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(createSaveButton());
        buttonPanel.add(createCancelButton(frame));
        return buttonPanel;
    }

    private JPanel mainPanel;


    public enum ButtonOption {
        SAVE_CANCEL, BACK
    }
    public JFrame createNoteFrame(String frameTitle, ButtonOption buttonOption, String noteTitle, String noteContent) {
        JFrame frame = new JFrame(frameTitle);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        mainPanel.add(createTitlePanel(noteTitle), BorderLayout.NORTH);
        mainPanel.add(createContentPanel(noteContent), BorderLayout.CENTER);

        JPanel buttonPanel;
        switch (buttonOption) {
            case SAVE_CANCEL:
                buttonPanel = createButtonPanel(frame);
                break;
            case BACK:
                buttonPanel = new JPanel(new FlowLayout());
                buttonPanel.add(createBackButton(frame));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + buttonOption);
        }


        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(mainPanel);
        frame.setSize(600, 600);
        frame.pack();
        return frame;
    }

}
