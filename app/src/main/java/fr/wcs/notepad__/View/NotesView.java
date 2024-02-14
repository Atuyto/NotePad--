package fr.wcs.notepad__.View;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import fr.wcs.notepad__.Controler.NotesControler;
import fr.wcs.notepad__.Model.BDD.AppDatabase;
import fr.wcs.notepad__.Model.Notes;
import fr.wcs.notepad__.R;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotesView extends AppCompatActivity   {


    private NotesControler notesControler;

    private EditText notes_text, title;

    private ImageButton buttun_back;

    private int note_id;

    private int catalogue_id;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_activity);
        this.init();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void init(){
        this.note_id        = this.getIntent().getIntExtra("note_id", -1);
        this.catalogue_id   = this.getIntent().getIntExtra("catalogue_id", 1);
        this.notes_text     = findViewById(R.id.id_note_activity_note);
        this.title          = findViewById(R.id.id_note_activity_title);
        this.buttun_back    = findViewById(R.id.id_note_activity_back);
        this.notesControler = new NotesControler(this.note_id, this.catalogue_id, this);


        //this.title.addTextChangedListener(this);
        this.buttun_back.setOnClickListener(this.notesControler);
    }

    /*

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public void afterTextChanged(Editable s) {
        this.notes.setTitle(s.toString());
        this.executor.execute(() -> this.appDatabase.notesDao().updateNotes(this.notes));


        // diviser le text watcher en deux pour voir qui est entrain d'être modifier
    }

     */
}
