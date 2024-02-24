package fr.wcs.notepad__.Controler;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import fr.wcs.notepad__.Model.BDD.AppDatabase;
import fr.wcs.notepad__.Model.Notes;
import fr.wcs.notepad__.Model.Observable;

import java.util.List;
import java.util.concurrent.Executors;

public class TextSearchControler extends Observable implements TextWatcher {

    private final Context context;

    public TextSearchControler(Context context) {
        this.context = context;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        Executors.newSingleThreadExecutor().execute(()->{
            List<Notes> notes = AppDatabase.getInstance(this.context).notesDao().getNoteBySearch(s.toString());
            new Handler(Looper.getMainLooper()).post(()->{
                this.loadNotes(notes);
                this.loadNbNotes(notes.size());
            });
        });
    }
}
