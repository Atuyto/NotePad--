package fr.wcs.notepad__.Model.BDD;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import fr.wcs.notepad__.Model.Notes;

import java.util.List;

@Dao
public interface NotesDao {

    @Query("select * from notes where isDeleted = 0")
    List<Notes> getAllNotes();

    @Query("select * from notes where isDeleted = 1")
    List<Notes> getAllNotesInTrash();

    @Query("select * from notes where notes.idNotes = :noteId")
    Notes getNoteById(long noteId);

    @Query("select count(*) from notes where isDeleted = 0")
    int getNbNote();

    @Query("Select * from notes where isDeleted = 0 order by lastModif desc ")
    List<Notes> getNotesSotedByRecentDate();
    @Query("Select * from notes where isDeleted = 0 order by  lastModif ")
    List<Notes> getNotesSotedByLastDate();

    @Query("select * from notes where  isDeleted = 0 and title like '%' || :word || '%'")
    List<Notes> getNoteBySearch(String word);

    @Query("select * from notes where favorite = 1 and isDeleted = 0")
    List<Notes> getFavoriteNotes();

    @Query("Select * from notes where isDeleted = 1")
    List<Notes> getNotesInTrash();

    @Query("update notes set isDeleted = 1 where idNotes = :id")
    void setTarshNote(long id);

    @Query("update notes set isDeleted = 0 where idNotes = :id")
    void unSetTarshNote(long id);

    @Query("SELECT * FROM notes WHERE isDeleted = 1")
    LiveData<List<Notes>> getNotesLiveData();

    @Delete
    void deleteNotes(Notes notes);

    @Insert
    long insertNote(Notes note);

    @Update
    void updateNotes(Notes notes);





}
