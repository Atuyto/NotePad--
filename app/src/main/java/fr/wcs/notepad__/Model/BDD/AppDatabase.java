package fr.wcs.notepad__.Model.BDD;


import android.content.Context;
import androidx.room.Database;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import fr.wcs.notepad__.Model.AudioNote;
import fr.wcs.notepad__.Model.Catalogue;
import fr.wcs.notepad__.Model.DateConverter;
import fr.wcs.notepad__.Model.Notes;

import java.util.concurrent.Executors;

@Database(entities = {Notes.class, Catalogue.class, AudioNote.class}, version = 3, exportSchema = true)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;
    public abstract NotesDao notesDao();

    public abstract AudioNotesDao audioNotesDao();
    public abstract  CatalogueDao catalogueDao();

    /**
     * <h1>Cette méthode permet de créer le singleton (l'instance de la base de donnée)</h1>
     * @param context c'est le context de l'application
     * @return une instance de AppDatabase
     */
    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "notes_database"
            )
                    .fallbackToDestructiveMigration()
                    .setQueryExecutor(Executors.newSingleThreadExecutor())
                    .build();
        }
        return INSTANCE;
    }
}
