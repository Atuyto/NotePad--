package fr.wcs.notepad__.Controler;

import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.PopupMenu;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import fr.wcs.notepad__.Model.BDD.AppDatabase;
import fr.wcs.notepad__.Model.Notes;
import fr.wcs.notepad__.Model.Observable;
import fr.wcs.notepad__.R;
import fr.wcs.notepad__.View.NotesView;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CardNotesControler extends Observable implements View.OnClickListener, View.OnLongClickListener, CompoundButton.OnCheckedChangeListener, PopupMenu.OnMenuItemClickListener {

    private final Notes notes;
    private final CardNoteAddapter cardNoteAddapter;

    public CardNotesControler(Notes notes, CardNoteAddapter cardNoteAddapter) {
        this.notes = notes;
        this.cardNoteAddapter = cardNoteAddapter;

    }

    @Override
    public void onClick(View view) {
        if(!this.cardNoteAddapter.isEditable()){
            if(view.getId() == R.id.id_card_note_view){
                if(this.notes.isDeleted()){
                    PopupMenu popup = new PopupMenu(view.getContext(), view);
                    popup.getMenuInflater().inflate(R.menu.note_menu_handler, popup.getMenu());
                    popup.setOnMenuItemClickListener(this);
                    popup.show();
                    System.out.println("test");
                }
                else {
                    Intent intent = new Intent(view.getContext(), NotesView.class);
                    intent.putExtra("note_id", String.valueOf(notes.getIdNotes()));
                    view.getContext().startActivity(intent);
                }
            }
            if(view.getId() == R.id.id_card_note_sart){
                this.cardNoteAddapter.setFavorit(notes);
            }
        }
    }

    @Override
    public boolean onLongClick(View v) {
        this.onNotesSelected();
        return false;
    }

    @Override
    public boolean onLongClickUseDefaultHapticFeedback(@NonNull View v) {
        return View.OnLongClickListener.super.onLongClickUseDefaultHapticFeedback(v);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            this.addNotesInSelection(this.notes);
        }else this.notesUnSelected(this.notes);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        ExecutorService executors = Executors.newSingleThreadExecutor();
        if (item.getItemId() == R.id.delete){
            executors.execute(()->{
                AppDatabase.getInstance(this.cardNoteAddapter.getContext()).notesDao().deleteNotes(this.notes);
            });
        }
        if(item.getItemId() == R.id.restaurer){
            executors.execute(()->{
                AppDatabase.getInstance(this.cardNoteAddapter.getContext()).notesDao().unSetTarshNote(this.notes.getIdNotes());
            });
        }
        return false;
    }
}
