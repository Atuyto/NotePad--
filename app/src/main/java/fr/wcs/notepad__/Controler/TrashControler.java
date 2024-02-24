package fr.wcs.notepad__.Controler;

import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import androidx.lifecycle.Observer;
import fr.wcs.notepad__.Model.BDD.AppDatabase;
import fr.wcs.notepad__.Model.Notes;
import fr.wcs.notepad__.Model.Observable;
import fr.wcs.notepad__.R;
import fr.wcs.notepad__.View.TrashActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class TrashControler extends Observable implements View.OnClickListener, PopupMenu.OnMenuItemClickListener, Observer<List<Notes>> {


    private final TrashActivity trashActivity;

    public TrashControler(TrashActivity trashActivity) {
        this.trashActivity = trashActivity;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.id_trash_activity_back){
            trashActivity.finish();
        }
        if (v.getId() == R.id.id_trash_activity_menu) {
            PopupMenu popup = new PopupMenu(v.getContext(), v);
            popup.getMenuInflater().inflate(R.menu.trash_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(this);
            popup.show();
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.deleteAll) {
            Executors.newSingleThreadExecutor().execute(() -> {
                for (Notes n : this.trashActivity.getNotes()){
                    this.trashActivity.getAppDatabase().notesDao().deleteNotes(n);
                }
                List<Notes> notes = this.trashActivity.getAppDatabase().notesDao().getAllNotesInTrash();
                new Handler(Looper.getMainLooper()).post(()->{
                    this.trashActivity.loadNotes(notes);
                    this.trashActivity.loadNbNote();
                });
            });
            return true;
        }
        if(item.getItemId() == R.id.restaurer){
            Executors.newSingleThreadExecutor().execute(() -> {
                for (Notes n : this.trashActivity.getNotes()){
                    this.trashActivity.getAppDatabase().notesDao().unSetTarshNote(n.getIdNotes());
                }
                List<Notes> notes = this.trashActivity.getAppDatabase().notesDao().getAllNotesInTrash();
                new Handler(Looper.getMainLooper()).post(()->{
                    this.trashActivity.loadNotes(notes);
                    this.trashActivity.loadNbNote();
                });
            });
            return true;
        }
        return false;
    }

    @Override
    public void onChanged(List<Notes> notes) {
        this.trashActivity.runOnUiThread(()->{
            this.trashActivity.loadNotes(notes);
            this.trashActivity.loadNbNote();
        });
    }
}
