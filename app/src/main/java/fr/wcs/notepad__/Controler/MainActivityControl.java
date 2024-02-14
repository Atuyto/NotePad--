package fr.wcs.notepad__.Controler;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import androidx.annotation.RequiresApi;
import fr.wcs.notepad__.Model.BDD.AppDatabase;
import fr.wcs.notepad__.Model.Notes;
import fr.wcs.notepad__.R;
import fr.wcs.notepad__.View.NotesView;

public class MainActivityControl implements View.OnClickListener{

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.id_main_activity_add_notes){
            Intent intent = new Intent(v.getContext(),NotesView.class);
            v.getContext().startActivity(intent);

            // ajouter les différent putExtra (faire l'affichage de toute les notes avant)

        }
    }
}
