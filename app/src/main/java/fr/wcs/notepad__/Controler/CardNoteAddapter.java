package fr.wcs.notepad__.Controler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import fr.wcs.notepad__.Model.BDD.AppDatabase;
import fr.wcs.notepad__.Model.DateConverter;
import fr.wcs.notepad__.Model.Notes;
import fr.wcs.notepad__.R;
import org.jetbrains.annotations.NotNull;

import java.util.List;

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
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Notes note = notes.get(position);
        holder.bind(note);

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    static class CardViewHolder extends RecyclerView.ViewHolder {

        private CardView preview;
        private TextView title, date;
        private CardView cardView;
        public CardViewHolder(View itemView) {
            super(itemView);
            this.cardView = itemView.findViewById(R.id.id_card_note_view);
            this.preview = itemView.findViewById(R.id.id_card_note_preview);
            this.title   = itemView.findViewById(R.id.id_card_note_title);
            this.date    = itemView.findViewById(R.id.id_card_note_date);


        }

        public void bind(Notes notes){;
            this.title.setText(notes.getTitle());
            this.date.setText(DateConverter.fromLocalDate(notes.getLastModif()));
            this.cardView.setOnClickListener(new CardNotesControler(notes));
        }

    }
}


