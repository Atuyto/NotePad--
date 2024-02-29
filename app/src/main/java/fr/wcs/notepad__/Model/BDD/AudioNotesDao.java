package fr.wcs.notepad__.Model.BDD;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import fr.wcs.notepad__.Model.AudioNote;

import java.util.List;

@Dao
public interface AudioNotesDao {

    @Insert
    public Long insert(AudioNote audioNote);

    @Query("select * from audionote where isDeleted = 0")
    List<AudioNote> getAllNotes();

    @Query("select * from audionote where isDeleted = 0 and id = :id")
    AudioNote getAudioNoteById(Long id);

}
