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
}
