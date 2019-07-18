package sql.demo;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;
import java.util.List;

public class NotesModel extends AbstractListModel<Note> {

    private List<Note> notes = new ArrayList<>();

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public Note getElementAt(int index) {
        return null;
    }
}

