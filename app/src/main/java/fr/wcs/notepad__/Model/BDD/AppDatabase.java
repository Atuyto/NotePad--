package fr.wcs.notepad__.Model.BDD;


import android.content.Context;
import androidx.room.Database;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import fr.wcs.notepad__.Model.Catalogue;
import fr.wcs.notepad__.Model.DateConverter;
import fr.wcs.notepad__.Model.Notes;

@Database(entities = {Notes.class, Catalogue.class}, version = 2, exportSchema = true)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;
    public abstract NotesDao notesDao();

    public abstract  CatalogueDao catalogueDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "notes_database"
            ).fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }
}
