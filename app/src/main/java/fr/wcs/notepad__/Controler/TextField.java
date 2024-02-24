package fr.wcs.notepad__.Controler;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import fr.wcs.notepad__.Model.BDD.AppDatabase;
import fr.wcs.notepad__.Model.Notes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TextField implements TextWatcher {
    private final Context context;
    private final ExecutorService executorService;
    private final Notes notes;
    private final AppDatabase appDatabase;
    public TextField(Context context, Notes notes, AppDatabase appDatabase){
        this.context = context;
        this.notes = notes;
        this.appDatabase = appDatabase;
        this.executorService = Executors.newSingleThreadExecutor();
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (notes!=null){
            this.executorService.execute(() ->{
                this.notes.setContainerText(s.toString());
                this.appDatabase.notesDao().updateNotes(this.notes);
            });
        }



    }


}

