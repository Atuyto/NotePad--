package fr.wcs.notepad__.Model.BDD;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import fr.wcs.notepad__.Model.Catalogue;
import fr.wcs.notepad__.Model.DateConverter;
import fr.wcs.notepad__.Model.Notes;
import fr.wcs.notepad__.Model.Trash;

@Database(entities = {Notes.class, Catalogue.class, Trash.class}, version = 1)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract NotesDao notesDao();

    public abstract  CatalogueDao catalogueDao();

    public abstract TrashDao trashDao();

}
