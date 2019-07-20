package sql.demo;

import sql.demo.model.NoteModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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

    private NoteManager noteManager;
    private NoteModel noteModel;
    private NoteMainFrame noteMainFrame;

    public JButton createNewNotesButton(){
        newNotesButton = new JButton("New");
        newNotesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame("New Note");
                mainPanel.add(Title("Your title"), BorderLayout.NORTH);
                mainPanel.add(Content("Your content"),BorderLayout.CENTER);

                mainPanel.add(Button(),BorderLayout.SOUTH);
                form2.add(mainPanel);

            }
        });
        return newNotesButton;
    }
    public JButton createEditButton(){
        editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame("Edit Note");
                mainPanel.add(Title(""), BorderLayout.NORTH);
                mainPanel.add(Content(""), BorderLayout.CENTER);

                mainPanel.add(Button(), BorderLayout.SOUTH);
                form2.add(mainPanel);
            }
        });
        return editButton;
    }
    public JButton createRemoveButton(){
        removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              // noteManager.deleteNote(noteManager.getNotes().get(0));
                //noteModel.reload();
               //noteMainFrame.list.getSelectedValue();

            }
        });
        return removeButton;
    }

    public JButton creteViewAllButton(){
        viewButton = new JButton("View");
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                frame("View Note");
                mainPanel.add(Title(""), BorderLayout.NORTH);
                mainPanel.add(Content(""), BorderLayout.CENTER);

                mainPanel.add(createBackButton(), BorderLayout.SOUTH);
                form2.add(mainPanel);
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

    public JPanel Content(String s){
        JPanel content = new JPanel(new FlowLayout());
        content.setBorder(BorderFactory.createTitledBorder("Content"));
        areaContent = new JTextArea(25,50);
        areaContent.setText(s);
        areaContent.setLineWrap(true);
        areaContent.setWrapStyleWord(true);
        content.add(new JScrollPane(areaContent));
        return content;
    }


    public JPanel Title(String s){
        JPanel title = new JPanel(new FlowLayout());
        title.setBorder(BorderFactory.createTitledBorder("Title"));
        areaTitle = new JTextArea(1,50);
        areaTitle.setText(s);
        areaTitle.setLineWrap(true);
        areaTitle.setWrapStyleWord(true);
        title.add(new JScrollPane(areaTitle));
        return title;
    }

    public JPanel Button(){
        JPanel button = new JPanel(new FlowLayout());
        button.add(createSaveButton());
        button.add(createCancelButton());
        return button;
    }
    private JPanel mainPanel;
    private JFrame form2;
    public void frame(String t){
        form2 = new JFrame(t);
        form2.setSize(600, 600);
        form2.setVisible(true);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

}
