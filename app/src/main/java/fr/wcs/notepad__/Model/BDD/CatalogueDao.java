package fr.wcs.notepad__.Model.BDD;

import androidx.room.Dao;
import androidx.room.Query;
import fr.wcs.notepad__.Model.Catalogue;

import java.util.List;

@Dao
public interface CatalogueDao {

    @Query("select * from catalogue")
    public List<Catalogue> getAllCatalogue();

    @Query("select * from catalogue where catalogueId = :id")
    public Catalogue getCatalogueFromId(long id);
}
