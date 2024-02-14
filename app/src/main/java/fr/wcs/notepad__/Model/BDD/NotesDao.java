package fr.wcs.notepad__.Model.BDD;

import androidx.room.*;
import fr.wcs.notepad__.Model.Notes;

import java.util.List;

@Dao
public interface NotesDao {

    @Query("select * from notes")
    List<Notes> getAllNotes();

    @Query("select * from notes where notes.idNotes = :noteId")
    Notes getNoteById(long noteId);

    @Delete
    void deleteNotes(Notes notes);

    @Insert
    long insertNote(Notes note);

    @Update
    void updateNotes(Notes notes);





}
