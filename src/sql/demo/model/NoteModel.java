package sql.demo.model;

import sql.demo.Note;
import sql.demo.NoteManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

public class NoteModel extends AbstractListModel<Note> {

    private NoteManager noteManager;
    private List<Note> notes;
    private Date fromDate;
    private Date toDate;

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
        if(isNull(fromDate) || isNull(toDate)) {
            notes = new ArrayList<>(noteManager.getNotes());
        } else {
            notes = new ArrayList<>(noteManager.getNotesByRange(fromDate, toDate));
        }
        fireContentsChanged(this, 0, getSize()-1);
    }

    public void deleteNote(Note note) {
        noteManager.deleteNote(note);
        reload();
    }

    public void addNote(Note note) {
        noteManager.addNote(note);
        reload();
    }

    public void updateNote(Note note) {
        noteManager.updateNote(note);
        reload();
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}

