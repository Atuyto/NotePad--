package fr.wcs.notepad__;


import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.room.Room;
import fr.wcs.notepad__.Model.BDD.AppDatabase;
import fr.wcs.notepad__.Model.BDD.CatalogueDao;
import fr.wcs.notepad__.Model.BDD.NotesDao;
import fr.wcs.notepad__.Model.Catalogue;
import fr.wcs.notepad__.Model.Notes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class NotesDatabaseTest {

    private AppDatabase appDatabase;
    private NotesDao notesDao;
    private CatalogueDao catalogueDao;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        notesDao = appDatabase.notesDao();
        catalogueDao = appDatabase.catalogueDao();


    }

    @After
    public void closeDb() {
        if(appDatabase != null){
            appDatabase.close();
        }
    }

    @Test
    public void insertAndRetrieveNotes() {
        // Insertion d'un catalogue
        Catalogue catalogue = new Catalogue();
        long catalogueId = catalogueDao.insertCatalogue(catalogue);

        // Insertion d'une note liée à un catalogue
        Notes note = new Notes("Test Title", "Test Container Text");
        note.setCatalogueId(catalogueId);
        long noteId = notesDao.insertNote(note);

        // Récupération de la note à partir de la base de données
        Notes retrievedNote = notesDao.getNoteById(noteId);

        // Vérification des données
        assertEquals("Test Title", retrievedNote.getTitle());
        assertEquals("Test Container Text", retrievedNote.getContainerText());
        assertEquals(catalogueId, retrievedNote.getCatalogueId());
    }

}
