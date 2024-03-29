package fr.wcs.notepad__.View;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import fr.wcs.notepad__.Controler.NotesControler;
import fr.wcs.notepad__.Controler.TitleField;
import fr.wcs.notepad__.Model.BDD.AppDatabase;
import fr.wcs.notepad__.Model.Notes;
import fr.wcs.notepad__.R;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotesView extends AppCompatActivity   {


    private NotesControler notesControler;

    private EditText notes_text, title;

    private ImageButton buttun_back, menu_button;

    private int note_id;

    private int catalogue_id;





    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_activity);

        this.menu_button = findViewById(R.id.id_note_activity_menu);
        this.init();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void init(){
        String noteIdString = this.getIntent().getStringExtra("note_id");
        if (noteIdString != null) {
            this.note_id = Integer.parseInt(noteIdString);
        }
        else {
            this.note_id = -1;
        }

        this.catalogue_id   = this.getIntent().getIntExtra("catalogue_id", 1); // récupérer le putExtra de l'id catalogue (le fichier par défauts le fichier principale)
        this.notes_text     = findViewById(R.id.id_note_activity_note);
        this.title          = findViewById(R.id.id_note_activity_title);
        this.buttun_back    = findViewById(R.id.id_note_activity_back);
        this.notesControler = new NotesControler(this.note_id, this.catalogue_id, this, this.title, this.notes_text);
        this.buttun_back.setOnClickListener(this.notesControler);
        this.menu_button.setOnClickListener(this.notesControler);
    }

}
