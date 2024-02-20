package fr.wcs.notepad__.Controler;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import androidx.annotation.RequiresApi;
import fr.wcs.notepad__.Model.BDD.AppDatabase;
import fr.wcs.notepad__.Model.Notes;
import fr.wcs.notepad__.R;
import fr.wcs.notepad__.View.NotesView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivityControl implements View.OnClickListener{
    private ExecutorService executor; // Permet d'excuter des thread en arrière plan

    private CardNoteAddapter cardNoteAddapter;
    public MainActivityControl(CardNoteAddapter cardNoteAddapter, Context context){
        this.cardNoteAddapter = cardNoteAddapter;
        this.executor         = Executors.newSingleThreadExecutor();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.id_main_activity_add_notes){
            Intent intent = new Intent(v.getContext(),NotesView.class);
            v.getContext().startActivity(intent);

            // ajouter les différent putExtra (faire l'affichage de toute les notes avant
        }
        //this.executor.execute(()->System.out.println(AppDatabase.getInstance(v.getContext()).notesDao().getNoteById(1).getTitle()));

    }
}
