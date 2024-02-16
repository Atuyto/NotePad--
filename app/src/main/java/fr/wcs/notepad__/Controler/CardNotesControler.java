package fr.wcs.notepad__.Controler;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import fr.wcs.notepad__.Model.BDD.AppDatabase;
import fr.wcs.notepad__.Model.Notes;
import fr.wcs.notepad__.Model.Observable;
import fr.wcs.notepad__.R;
import fr.wcs.notepad__.View.NotesView;

import java.util.List;
import java.util.concurrent.Executors;

public class CardNotesControler extends Observable implements View.OnClickListener {

    private Notes notes;
    private AppCompatActivity compatActivity;

    public CardNotesControler(Notes notes, AppCompatActivity activity) {
        this.notes = notes;
        this.compatActivity = activity;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.id_card_note_view){
            Intent intent = new Intent(view.getContext(), NotesView.class);
            intent.putExtra("note_id", String.valueOf(notes.getIdNotes()));
            view.getContext().startActivity(intent);
        }
        if(view.getId() == R.id.id_card_note_sart){
            this.notes.setFavorite(!this.notes.isFavorite());
            compatActivity.runOnUiThread(() -> this.loadNotes(this.getNotes()));
        }

    }
}
