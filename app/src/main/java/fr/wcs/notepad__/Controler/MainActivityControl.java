package fr.wcs.notepad__.Controler;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import fr.wcs.notepad__.Model.BDD.AppDatabase;
import fr.wcs.notepad__.Model.Notes;
import fr.wcs.notepad__.Model.Observable;
import fr.wcs.notepad__.R;
import fr.wcs.notepad__.View.DrawerManager;
import fr.wcs.notepad__.View.NotesView;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivityControl extends Observable implements View.OnClickListener{
    private final ExecutorService executor; // Permet d'excuter des thread en arrière plan
    private final DrawerManager drawerManager;
    private boolean DATESELECTED, FAVORITESELECTED;

    public MainActivityControl(DrawerManager drawerManager){
        this.executor         = Executors.newSingleThreadExecutor();
        this.drawerManager     = drawerManager;
        this.DATESELECTED = false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.id_main_activity_add_notes){
            Intent intent = new Intent(v.getContext(),NotesView.class);
            v.getContext().startActivity(intent);
        }
        if(v.getId() == R.id.id_main_activity_burger_button){
            this.drawerManager.openDrawer();
        }
        if(v.getId() == R.id.id_main_activity_favorite_button){
            this.executor.execute(()-> {
                List<Notes> notes;
                FAVORITESELECTED = !FAVORITESELECTED;
                this.setFavorit(FAVORITESELECTED);
                if(FAVORITESELECTED){
                    notes = AppDatabase.getInstance(v.getContext()).notesDao().getFavoriteNotes();
                }
                else {
                    notes = AppDatabase.getInstance(v.getContext()).notesDao().getAllNotes();
                }
                new Handler(Looper.getMainLooper()).post(()->{
                    this.loadNotes(notes);
                    this.loadNbNotes(notes.size());
                });
            });
        }
        if(v.getId() == R.id.id_main_activity_date){
            DATESELECTED = !DATESELECTED;
            this.executor.execute(()-> {
                List<Notes> notes;
                if(DATESELECTED){
                    notes = AppDatabase.getInstance(v.getContext()).notesDao().getNotesSotedByRecentDate();
                }
                else {
                    notes = AppDatabase.getInstance(v.getContext()).notesDao().getNotesSotedByLastDate();
                }

                new Handler(Looper.getMainLooper()).post(()->{
                    this.loadNotes(notes);
                    this.sortedByDate(DATESELECTED);
                });
            });

        }

        if(v.getId() == R.id.id_bottom_nav_bar_cancel){
            this.closeNavBar();
        }
        if(v.getId() == R.id.id_bottom_nav_bar_delete){
            List<Notes> notes = this.getNotesSelected();
            if(!notes.isEmpty()){
                this.executor.execute(()->{
                    for(Notes n : notes){
                        AppDatabase.getInstance(v.getContext()).notesDao().setTarshNote(n.getIdNotes());
                    }
                    List<Notes> allnotes = AppDatabase.getInstance(v.getContext()).notesDao().getAllNotes();
                    new Handler(Looper.getMainLooper()).post(()->{
                        this.loadNotes(allnotes);
                        this.loadNbNotes(allnotes.size());
                        this.closeNavBar();
                    });
                });
            }
        }

        //this.executor.execute(()->System.out.println(AppDatabase.getInstance(v.getContext()).notesDao().getNoteById(1).getTitle()));
    }
}
