package fr.wcs.notepad__.Model;


import java.util.List;

public abstract class Observable {
    private static Observer observers;

    public static void setObservers(Observer o){
        observers = o;
    }

    public  void loadNotes(List<Notes> notes){
        observers.loadNotes(notes);
    }

    public void loadNbNotes(int nb){
        observers.loadNbNotes(nb);
    }

    public List<Notes> getNotes(){
        return observers.getNotes();
    }

    public void setFavorit(boolean favorit){
        observers.setFavorit(favorit);
    }

    public void sortedByDate(boolean isSorted){
        observers.sotedByDate(isSorted);
    }

    public void onNotesSelected(){
        observers.onNotesSelected();
    }

    public void addNotesInSelection(Notes notes){
        observers.addNotesInSelection(notes);
    }

    public void notesUnSelected(Notes notes){
        observers.notesUnSelected(notes);
    }

    public void closeNavBar(){
        observers.close();
    }

    public List<Notes> getNotesSelected(){
        return observers.getNotesSelected();
    }
}
