package fr.wcs.notepad__.View;

import android.os.Bundle;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import fr.wcs.notepad__.Controler.CardNoteAddapter;
import fr.wcs.notepad__.Controler.TrashControler;
import fr.wcs.notepad__.Model.BDD.AppDatabase;
import fr.wcs.notepad__.Model.Notes;
import fr.wcs.notepad__.R;

import java.util.List;
import java.util.concurrent.Executors;

public class TrashActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageButton menu, back;
    private List<Notes> notes;
    private AppDatabase appDatabase;
    private CardNoteAddapter cardNoteAddapter;
    private TrashControler trashControler;
    private TextView nb_notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.trash_activity);

        this.back = findViewById(R.id.id_trash_activity_back);
        this.menu = findViewById(R.id.id_trash_activity_menu);
        this.nb_notes = findViewById(R.id.id_trash_activity_nb_notes);

        this.init();
    }

    private void init() {
        this.recyclerView = findViewById(R.id.id_main_activity_recyclerView);
        this.trashControler = new TrashControler(this);
        this.menu.setOnClickListener(this.trashControler);
        this.back.setOnClickListener(this.trashControler);
        this.appDatabase = AppDatabase.getInstance(this);
        AppDatabase.getInstance(this).notesDao().getNotesLiveData().observe(this, this.trashControler); // permet de mettre à jours des qu'il détecte un changement dans la base de donné
        Executors.newSingleThreadExecutor().execute(() -> {
            this.notes = this.appDatabase.notesDao().getAllNotesInTrash();
            this.runOnUiThread(() -> {
                this.loadNotes(this.notes);
                this.loadNbNote();
            });
        });
    }


    public void loadNotes(List<Notes> notes) {
        this.notes = notes;
        if (this.cardNoteAddapter == null) {
            this.cardNoteAddapter = new CardNoteAddapter(this.getApplicationContext(), notes);
            this.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            this.recyclerView.setHasFixedSize(false);
            this.recyclerView.setAdapter(this.cardNoteAddapter);
        } else {
            this.cardNoteAddapter.setNotesList(notes);
        }
    }


    public void loadNbNote(){
        this.nb_notes.setText(this.notes.size()>1 ? String.valueOf(this.notes.size()).concat(" notes") :
                String.valueOf(this.notes.size()).concat(" note"));
    }

    public List<Notes> getNotes() {
        return notes;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
