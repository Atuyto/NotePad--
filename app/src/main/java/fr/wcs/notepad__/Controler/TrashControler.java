package fr.wcs.notepad__.Controler;

import android.view.View;
import fr.wcs.notepad__.Model.BDD.AppDatabase;
import fr.wcs.notepad__.Model.Notes;
import fr.wcs.notepad__.Model.Observable;
import fr.wcs.notepad__.View.TrashActivity;

import java.util.ArrayList;
import java.util.concurrent.Executors;

public class TrashControler extends Observable implements View.OnClickListener {


    private TrashActivity trashActivity;

    public TrashControler(TrashActivity trashActivity) {
        this.trashActivity = trashActivity;
    }

    @Override
    public void onClick(View v) {

    }
}
