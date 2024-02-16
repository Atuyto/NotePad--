package fr.wcs.notepad__.Model;



public abstract class Observable {
    private static Observer observers;

    public static void setObservers(Observer o){
        observers = o;
    }

    public  void loadNotes(){
        observers.loadNotes();
    }

}
