package fr.wcs.notepad__.Controler;

import android.content.Context;
import android.media.MediaRecorder;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import fr.wcs.notepad__.Model.AudioNote;
import fr.wcs.notepad__.Model.BDD.AppDatabase;
import fr.wcs.notepad__.Model.BDD.AudioNotesDao;
import fr.wcs.notepad__.Model.Catalogue;
import fr.wcs.notepad__.R;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class AudioNoteControle implements View.OnClickListener {


    private MediaRecorder mediaRecorder;
    private String audioFilePath;

    private boolean isRecording = false;
    private ExecutorService executorService;

    private String title;
    private AudioNote audioNote;
    public AudioNoteControle(String title) {
        this.title = title;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.id_audio_note_play_pause){
            if(!isRecording){
                try {
                    startRecording(view);
                    Toast.makeText(view.getContext(), "Lancer", Toast.LENGTH_LONG).show();
                    isRecording = true;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                stopRecording(view);
                Toast.makeText(view.getContext(), "Stoper", Toast.LENGTH_LONG).show();
            }
        }


    }

    public void startRecording(View view) throws IOException {
        if (this.mediaRecorder == null) {
            this.executorService.execute(()->this.initializaCatalogue(view.getContext()));
            this.audioNote = new AudioNote(this.title);
            this.audioNote.setCatalogueId(1);

            this.mediaRecorder = new MediaRecorder();
            this.audioFilePath = view.getContext().getExternalCacheDir().getAbsolutePath().concat("/").concat(this.audioNote.createPath());
            System.out.println(audioFilePath);

            // Configurez le MediaRecorder
            this.mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            this.mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            this.mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            this.mediaRecorder.setOutputFile(this.audioFilePath);

            this.mediaRecorder.prepare();
            this.mediaRecorder.start();







        }
    }

    public void stopRecording(View view) {
        if (this.mediaRecorder != null) {
            this.mediaRecorder.stop();
            this.mediaRecorder.release();
            this.mediaRecorder = null;

            this.isRecording = false;
            this.audioNote.setPath(this.audioFilePath);

            this.executorService.execute(() -> {
                this.audioNote.setId(AppDatabase.getInstance(view.getContext()).audioNotesDao().insert(this.audioNote));
            });

            // Enregistrez le chemin du fichier audio dans votre base de données Room
            // Code pour ajouter la note vocale à la base de données Room ici
        }
    }

    private void initializaCatalogue(Context context){
        if (AppDatabase.getInstance(context).catalogueDao().getAllCatalogue().isEmpty()) {
            AppDatabase.getInstance(context).catalogueDao().insertCatalogue(new Catalogue());
        }
    }


}
