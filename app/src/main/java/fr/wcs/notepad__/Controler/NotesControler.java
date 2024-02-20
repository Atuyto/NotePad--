package fr.wcs.notepad__.Controler;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import fr.wcs.notepad__.Model.BDD.AppDatabase;
import fr.wcs.notepad__.Model.Catalogue;
import fr.wcs.notepad__.Model.DateConverter;
import fr.wcs.notepad__.Model.Notes;
import fr.wcs.notepad__.Model.Observable;
import fr.wcs.notepad__.R;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class NotesControler extends Observable implements View.OnClickListener {

    private final AppDatabase appDatabase;
    private int note_id, catalogue_id;

    private ExecutorService executor; // Permet d'excuter des thread en arrière plan
    private AppCompatActivity context;

    private Notes notes;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public NotesControler(int note_id, int catalogue_id, AppCompatActivity context, TextView title, TextView text){
        this.appDatabase    = AppDatabase.getInstance(context);
        this.executor       = Executors.newSingleThreadExecutor();
        this.context        = context;
        this.note_id        = note_id;
        this.catalogue_id   = catalogue_id;

        if(this.note_id == -1){
            this.notes = new Notes();
            this.executor.execute(this::initializaCatalogue);
            this.notes.setCatalogueId(1);
            this.executor.execute(() -> {
                this.notes.setIdNotes(this.appDatabase.notesDao().insertNote(this.notes));
                title.addTextChangedListener(new TitleField(this.context, this.notes, this.appDatabase));
                text.addTextChangedListener(new TextField(this.context, this.notes, this.appDatabase));
            });
        }
        else {
            this.executor.execute(() -> {
                this.notes = this.appDatabase.notesDao().getNoteById(this.note_id);
                this.notes.setLastModif(LocalDate.now());
                this.updateNote(title, text);
                title.addTextChangedListener(new TitleField(this.context, this.notes, this.appDatabase));
                text.addTextChangedListener(new TextField(this.context, this.notes, this.appDatabase));
            });
        }



    }

    private void updateNote(TextView title, TextView text){
        title.setText(this.notes.getTitle());
        text.setText(this.notes.getContainerText());

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
            this.executor.execute(()-> {
                List<Notes> allNotes = appDatabase.notesDao().getAllNotes();
                int nb = appDatabase.notesDao().getNbNote();
                this.context.runOnUiThread(() ->{
                    this.loadNotes(allNotes);
                    this.loadNbNotes(nb);
                });
            });



        }
    }
}
