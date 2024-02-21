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

    @Query("select count(*) from notes")
    int getNbNote();

    @Query("Select * from notes order by lastModif desc")
    List<Notes> getNotesSotedByRecentDate();
    @Query("Select * from notes order by  lastModif ")
    List<Notes> getNotesSotedByLastDate();

    @Query("select * from notes where title like '%' || :word || '%'")
    List<Notes> getNoteBySearch(String word);

    @Query("select * from notes where favorite = 1")
    List<Notes> getFavoriteNotes();

    @Query("Select * from notes where isDeleted = 1")
    List<Notes> getNotesInTrash();


    @Delete
    void deleteNotes(Notes notes);

    @Insert
    long insertNote(Notes note);

    @Update
    void updateNotes(Notes notes);





}
