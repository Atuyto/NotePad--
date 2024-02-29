package fr.wcs.notepad__.View;

import android.app.Dialog;
import android.content.Context;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import fr.wcs.notepad__.Controler.AudioNoteControle;
import fr.wcs.notepad__.R;

public class AudioNotePopUp extends Dialog {

    private EditText title;
    private ImageButton play_button;
    public AudioNotePopUp(@NonNull Context context) {
        super(context);
        setContentView(R.layout.audio_notes_popup);
        this.title = findViewById(R.id.id_audio_note_title);
        this.play_button = findViewById(R.id.id_audio_note_play_pause);

        this.play_button.setOnClickListener(new AudioNoteControle());
    }


}
