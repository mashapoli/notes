package sql.demo;

import sql.demo.model.NoteModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.util.Objects.isNull;


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
                createNoteFrame("New Note");
                mainPanel.add(createTitlePanel("Your title"), BorderLayout.NORTH);
                mainPanel.add(contentPanel("Your content"),BorderLayout.CENTER);

                mainPanel.add(createButtonPanel(),BorderLayout.SOUTH);
                form2.add(mainPanel);

            }

        });
        return newNotesButton;
    }
    public JButton createEditButton(){
        editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createNoteFrame("Edit Note");
                mainPanel.add(createTitlePanel(""), BorderLayout.NORTH);
                mainPanel.add(contentPanel(""), BorderLayout.CENTER);

                mainPanel.add(createButtonPanel(), BorderLayout.SOUTH);
                form2.add(mainPanel);
            }
        });
        return editButton;
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

    public JButton createViewButton(){
        viewButton = new JButton("View");
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Note selectedNote = noteJList.getSelectedValue();
                if(!isNull(selectedNote)) {

                    createNoteFrame("View Note");
                    mainPanel.add(createTitlePanel(""), BorderLayout.NORTH);
                    mainPanel.add(contentPanel(""), BorderLayout.CENTER);
                    JPanel backPanel = new JPanel(new FlowLayout());
                    backPanel.add(createBackButton());
                    mainPanel.add(backPanel, BorderLayout.SOUTH);
                    form2.add(mainPanel);

                }
            }
        });
        return viewButton;
    }

    public JButton createSaveButton(){
        saveButton = new JButton("Save");
        return saveButton;
    }

    public JButton createCancelButton(){
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[] options = { "Yes", "No" };
                int n = JOptionPane
                        .showOptionDialog(form2, "Do not save changes??",
                                "Confirmation", JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE, null, options,
                                options[0]);
                if (n == 0) {
                    form2.setVisible(false);
                    form2.dispose();
                }
            }
        });
        return cancelButton;
    }

    public JButton createBackButton(){
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    form2.dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        return backButton;
    }

    public JPanel contentPanel(String s){
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

    public JPanel createButtonPanel(){
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(createSaveButton());
        buttonPanel.add(createCancelButton());
        return buttonPanel;
    }

    private JPanel mainPanel;
    private JFrame form2;

    public void createNoteFrame(String frameTitle){
        form2 = new JFrame(frameTitle);
        form2.setSize(600, 600);
        form2.setVisible(true);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

}
