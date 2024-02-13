package fr.wcs.notepad__.Model.BDD;

import androidx.room.Dao;
import androidx.room.Query;
import fr.wcs.notepad__.Model.Trash;

@Dao
public interface TrashDao {

    @Query("select * from trash")
    public Trash getTrash();
}
