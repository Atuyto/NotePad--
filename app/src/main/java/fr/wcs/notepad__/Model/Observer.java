package fr.wcs.notepad__.Model;

import java.util.List;

public interface Observer{

    void loadNotes(List<Notes> notes);

    void loadNbNotes(int nb);

    List<Notes> getNotes();

    void setFavorit(boolean favorit);

    void sotedByDate(boolean isSorted);

    void onNotesSelected();

    void addNotesInSelection(Notes notes);

    void notesUnSelected(Notes notes);

    void close();

    List<Notes> getNotesSelected();
}
