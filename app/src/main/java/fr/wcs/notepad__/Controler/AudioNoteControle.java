package fr.wcs.notepad__.Controler;

import android.media.MediaRecorder;
import android.view.View;
import android.widget.ImageButton;
import fr.wcs.notepad__.R;

import java.io.IOException;


public class AudioNoteControle implements View.OnClickListener {


    private MediaRecorder mediaRecorder;
    private String audioFilePath;

    private boolean isRecording = false;

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.id_audio_note_play_pause){
            if(!isRecording){
                try {
                    startRecording(view);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                stopRecording(view);
            }
        }


    }

    public void startRecording(View view) throws IOException {
        if (mediaRecorder == null) {
            mediaRecorder = new MediaRecorder();
            audioFilePath = view.getContext().getExternalCacheDir().getAbsolutePath() + "/audio_note.3gp";

            // Configurez le MediaRecorder
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.setOutputFile(audioFilePath);

            mediaRecorder.prepare();
            mediaRecorder.start();



        }
    }

    public void stopRecording(View view) {
        if (mediaRecorder != null) {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;

            // Enregistrez le chemin du fichier audio dans votre base de données Room
            // Code pour ajouter la note vocale à la base de données Room ici
        }
    }


}
