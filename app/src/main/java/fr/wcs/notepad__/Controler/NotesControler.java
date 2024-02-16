package fr.wcs.notepad__.Controler;

import android.content.Context;
import android.os.Build;
import android.view.View;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import fr.wcs.notepad__.Model.BDD.AppDatabase;
import fr.wcs.notepad__.Model.Catalogue;
import fr.wcs.notepad__.Model.Notes;
import fr.wcs.notepad__.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotesControler implements View.OnClickListener{

    private final AppDatabase appDatabase;
    private int note_id, catalogue_id;

    private ExecutorService executor; // Permet d'excuter des thread en arrière plan
    private AppCompatActivity context;

    private Notes notes;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public NotesControler(int note_id, int catalogue_id, AppCompatActivity context){
        this.appDatabase    = AppDatabase.getInstance(context);
        this.executor       = Executors.newSingleThreadExecutor();
        this.context        = context;
        this.note_id        = note_id;
        this.catalogue_id   = catalogue_id;

        if(this.note_id == -1){
            this.notes = new Notes();
            this.executor.execute(this::initializaCatalogue);
            this.notes.setCatalogueId(1);
            this.executor.execute(() -> this.appDatabase.notesDao().insertNote(this.notes));
        }
        else {
            this.executor.execute(() -> this.notes = this.appDatabase.notesDao().getNoteById(this.note_id));
        }

    }

    /**
     * <h2>Cette méthode est importante pour le démarrage</h2>
     * Elle permet de gérer si dans la base de donné il existe un catalogue ou pas.
     * Si il y en a pas alors il vas en créer un.
     */
    private void initializaCatalogue(){
        if (this.appDatabase.catalogueDao().getAllCatalogue().isEmpty()) {
            this.notes.setCatalogueId(this.appDatabase.catalogueDao().insertCatalogue(new Catalogue()));
        } else {
            this.notes.setCatalogueId(this.catalogue_id);
        }
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.id_note_activity_back){
            this.context.finish(); // ici je reviens à l'activité précédente
        }
    }
}
