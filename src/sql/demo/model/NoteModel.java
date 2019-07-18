package sql.demo.model;

import sql.demo.Note;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NoteModel extends AbstractListModel<Note> {

    private List<Note> getTestNotes() {
        Note note1 = new Note();
        note1.setTitle("Note 1");
        note1.setContent("Content 1");

        Note note2 = new Note();
        note2.setTitle("Note 2");
        note2.setContent("Content 2");

        Note note3 = new Note();
        note3.setTitle("Note 3");
        note3.setContent("Content 3");

        return Arrays.asList(note1, note2, note3);
    }

    private List<Note> notes = new ArrayList<>(getTestNotes());

    @Override
    public int getSize() {
        return notes.size();
    }

    @Override
    public Note getElementAt(int index) {
        return notes.get(index);
    }
}

