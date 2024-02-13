package fr.wcs.notepad__.Model.BDD;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import fr.wcs.notepad__.Model.Notes;

import java.util.List;

@Dao
public interface NotesDao {

    @Query("select * from notes")
    List<Notes> getAllNotes();

    @Query("select * from notes where notes.idNotes = :id")
    List<Notes> getAllNotes(long id);

    @Delete
    void deleteNotes(Notes notes);

    @Insert
    void insertNotes(Notes notes);

}
