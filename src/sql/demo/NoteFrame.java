package sql.demo;

import sql.demo.model.NoteModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.util.Objects.isNull;


public class NoteFrame {

    private NoteModel noteModel;
    private NoteMainFrame noteMainFrame;

    private JList<Note> noteJList;

    public NoteFrame(NoteModel noteModel, JList<Note> noteJList) {
        this.noteModel = noteModel;
        this.noteJList = noteJList;
    }

    public static JButton createEditNoteButton(){
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                Note selectedNote = noteJList.getSelectedValue();
//                if(!isNull(selectedNote)) {
//                    JFrame noteFrame = new NoteFrameX(
//                            "Edit Note", SAVE_CANCEL, selectedNote.getTitle(), selectedNote.getContent());
//                    noteFrame.setVisible(true);
//                }
            }
        });
        return editButton;
    }

    public static JButton createRemoveNoteButton(JList<Note> noteJList){
        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Note selectedNote = noteJList.getSelectedValue();
                if(!isNull(selectedNote)) {
//                    noteModel.deleteNote(selectedNote);
//                    noteModel.reload();
                }
            }
        });
        return removeButton;
    }

    public static JButton createSaveButton(JFrame frame){
        JButton saveButton = new JButton("Save");
        return saveButton;
    }


    public enum ButtonOption {
        NEW_CANCEL, SAVE_CANCEL, BACK
    }

    public static class NoteFrameX extends JFrame{

        private NoteModel noteModel;

        public NoteFrameX(NoteModel noteModel, String frameTitle, ButtonOption buttonOption, Note note) {
            super(frameTitle);
            this.noteModel = noteModel;

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
                case NEW_CANCEL:
                    buttonPanel = new JPanel(new FlowLayout());
                    JButton newButton = new JButton("New");
                    newButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            note.setTitle(titleTextArea.getText());
                            note.setContent(contentTextArea.getText());
                            noteModel
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
                    buttonPanel.add(createSaveButton(this));
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

}
