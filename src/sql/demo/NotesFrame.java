package sql.demo;

import sql.demo.model.NoteModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotesFrame {
    private JButton newNotes;
    private JButton edit;
    private JButton remove;
    private JButton viewAll;
    private JButton save;
    private JButton cancel;
    private JButton back;
    private JTextArea areaTitle;
    private JTextArea areaContent;

    private NoteManager noteManager;
    private NoteModel noteModel;

    public JButton createNewNotesButton(){
        newNotes = new JButton("New");
        newNotes();
        return newNotes;
    }
    public JButton createEditButton(){
        edit = new JButton("Edit");
        edit();
        return edit;
    }
    public JButton createRemoveButton(){
        remove = new JButton("Remove");
        remove();
        return remove;
    }

    public JButton creteViewAllButton(){
        viewAll = new JButton("View Note");
        viewAll();
        return viewAll;
    }

    public JButton createSaveButton(){
        save = new JButton("Save");
        return save;
    }

    public JButton createCancelButton(){
        cancel = new JButton("Cancel");
        return cancel;
    }

    public JButton createBackButton(){
        back = new JButton("Back");
        return back;
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

    public void newNotes(){
        newNotes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame("New Note");
                mainPanel.add(Title("Your title"), BorderLayout.NORTH);
                mainPanel.add(Content("Your content"),BorderLayout.CENTER);

                mainPanel.add(Button(),BorderLayout.SOUTH);
                form2.add(mainPanel);

            }
        });
    }
    public void edit(){
        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame("Edit Note");
                mainPanel.add(Title(""), BorderLayout.NORTH);
                mainPanel.add(Content(""), BorderLayout.CENTER);

                mainPanel.add(Button(), BorderLayout.SOUTH);
                form2.add(mainPanel);
            }
        });
    }

    public void remove(){
        remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                noteManager.deleteNote(noteManager.getNotes().get(0));
                noteModel.reload();
            }
        });
    }

    public void viewAll(){
        viewAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame("View Note");
                mainPanel.add(Title(""), BorderLayout.NORTH);
                mainPanel.add(Content(""), BorderLayout.CENTER);

                mainPanel.add(createBackButton(), BorderLayout.SOUTH);
                form2.add(mainPanel);
            }
        });
    }
}
