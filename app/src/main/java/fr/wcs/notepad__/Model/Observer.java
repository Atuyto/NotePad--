package fr.wcs.notepad__.Model;

import java.util.List;

public interface Observer{

    public void loadNotes(List<Notes> notes);

    public void loadNbNotes(int nb);

    List<Notes> getNotes();
}