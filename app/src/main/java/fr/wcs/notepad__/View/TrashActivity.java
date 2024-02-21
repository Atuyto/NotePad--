package fr.wcs.notepad__.View;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import fr.wcs.notepad__.Controler.CardNoteAddapter;
import fr.wcs.notepad__.Controler.NotesControler;
import fr.wcs.notepad__.Controler.TrashControler;
import fr.wcs.notepad__.Model.BDD.AppDatabase;
import fr.wcs.notepad__.Model.Notes;
import fr.wcs.notepad__.R;

import java.util.List;
import java.util.concurrent.Executors;

public class TrashActivity extends AppCompatActivity {

    private RecyclerView recyclerView ;
    private ImageButton menu;
    private List<Notes> notes ;
    private AppDatabase appDatabase;
    private CardNoteAddapter cardNoteAddapter;
    private TrashControler trashControler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.trash_activity);

        this.menu = findViewById(R.id.id_tash_activity_menu);

        this.init();
    }

    private void init(){
        this.recyclerView = findViewById(R.id.id_main_activity_recyclerView);
        this.trashControler = new TrashControler(this);
        this.menu.setOnClickListener(this.trashControler);
        this.appDatabase = AppDatabase.getInstance(this);
        Executors.newSingleThreadExecutor().execute(()->{
            this.notes =  this.appDatabase.notesDao().getAllNotesInTrash();
            loadNotes(this.notes);
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

    public List<Notes> getNotes() {
        return notes;
    }
}
