package fr.wcs.notepad__.Controler;

import android.view.View;
import fr.wcs.notepad__.Model.Notes;

public class CardNotesControler implements View.OnClickListener {

    private Notes notes;

    public CardNotesControler(Notes notes) {
        this.notes = notes;
    }

    @Override
    public void onClick(View view) {
        System.out.println(this.notes.getTitle());
    }
}
