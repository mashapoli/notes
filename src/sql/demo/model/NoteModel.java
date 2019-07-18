package sql.demo.model;

import sql.demo.Note;
import sql.demo.NoteManager;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NoteModel extends AbstractListModel<Note> {

    private NoteManager noteManager;
    private List<Note> notes;

    public NoteModel(NoteManager noteManager) {
        this.noteManager = noteManager;
        reload();
    }

    @Override
    public int getSize() {
        return notes.size();
    }

    @Override
    public Note getElementAt(int index) {
        return notes.get(index);
    }

    public void reload() {
        notes = new ArrayList<>(noteManager.getNotes());
        fireContentsChanged(this, 0, getSize()-1);
    }

}

