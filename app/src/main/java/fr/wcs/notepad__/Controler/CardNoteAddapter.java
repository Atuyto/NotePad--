package fr.wcs.notepad__.Controler;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import fr.wcs.notepad__.Model.BDD.AppDatabase;
import fr.wcs.notepad__.Model.DateConverter;
import fr.wcs.notepad__.Model.Notes;
import fr.wcs.notepad__.R;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.Executors;

public class CardNoteAddapter extends RecyclerView.Adapter<CardNoteAddapter.CardViewHolder>  {

    private List<Notes> notes;
    private Context context;



    public CardNoteAddapter(Context context, List<Notes> notes){
        this.notes = notes;
        this.context = context;
    }
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_note, parent, false);
        return new CardViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Notes note = notes.get(position);
        holder.bind(note);

    }

    private int getPositionNote(Notes notes){
        int cpt =0;
        for(Notes n : this.notes){
            if(n.equals(notes)){
                return cpt;
            }
            else {
                cpt++;
            }
        }
        return -1;
    }

    public void setFavorit(Notes n){
        n.setFavorite(!n.isFavorite());
        Executors.newSingleThreadExecutor().execute(()-> AppDatabase.getInstance(this.context).notesDao().updateNotes(n));
        this.notifyItemChanged(this.getPositionNote(n));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    static class CardViewHolder extends RecyclerView.ViewHolder {

        private TextView title, date, preview;
        private CardView cardView;
        private ImageView ic_stars;
        private CardNoteAddapter cardNoteAddapter;
        public CardViewHolder(View itemView, CardNoteAddapter cardNoteAddapter) {
            super(itemView);
            this.cardNoteAddapter = cardNoteAddapter;
            this.cardView = itemView.findViewById(R.id.id_card_note_view);
            this.preview = itemView.findViewById(R.id.id_card_note_preview);
            this.title   = itemView.findViewById(R.id.id_card_note_title);
            this.date    = itemView.findViewById(R.id.id_card_note_date);
            this.ic_stars= itemView.findViewById(R.id.id_card_note_sart);

        }

        public void bind(Notes notes){;
            this.title.setText(notes.getTitle());
            this.date.setText(DateConverter.fromLocalDate(notes.getLastModif()));
            this.preview.setText(notes.getContainerText());
            CardNotesControler cardNotesControler = new CardNotesControler(notes,this.cardNoteAddapter );
            this.cardView.setOnClickListener(cardNotesControler);
            this.ic_stars.setOnClickListener(cardNotesControler);
            if(notes.isFavorite()){
                this.ic_stars.setColorFilter(Color.rgb(255, 220, 36));
            }else {
                this.ic_stars.setColorFilter(Color.rgb(217, 217, 217));
            }

        }

    }
}


