package fr.wcs.notepad__.View;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import fr.wcs.notepad__.Controler.CardNoteAddapter;
import fr.wcs.notepad__.Controler.MainActivity;
import fr.wcs.notepad__.Controler.MainActivityControl;
import fr.wcs.notepad__.Model.BDD.AppDatabase;
import fr.wcs.notepad__.Model.Notes;
import fr.wcs.notepad__.Model.Observer;
import fr.wcs.notepad__.R;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivityView extends Fragment implements Observer {

    private EditText search_bar;

    private TextView number_pages;

    private Button button_sort_by_date;

    private ImageButton burger_menu_button, favorite_button, button_add_note;


    private AppDatabase appDatabase;
    private RecyclerView recyclerView;
    private CardNoteAddapter cardNoteAddapter;
    private MainActivityControl mainActivityControl;
    private ExecutorService executorService;
    private List<Notes> notes;

    public  MainActivityView(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_activity, container, false);

        // Initialiser les vues ici
        this.number_pages = rootView.findViewById(R.id.id_main_activity_notes);
        this.recyclerView = rootView.findViewById(R.id.id_main_activity_recyclerView);
        this.search_bar = rootView.findViewById(R.id.id_main_activity_search_bar);
        this.button_sort_by_date = rootView.findViewById(R.id.id_main_activity_date);
        this.burger_menu_button = rootView.findViewById(R.id.id_main_activity_burger_button);
        this.favorite_button = rootView.findViewById(R.id.id_main_activity_favorite_button);
        this.button_add_note = rootView.findViewById(R.id.id_main_activity_add_notes);

        this.executorService = Executors.newSingleThreadExecutor();
        this.appDatabase = AppDatabase.getInstance(this.getContext());
        this.mainActivityControl = new MainActivityControl(this.getContext());

        this.init(); // Appeler la méthode d'initialisation

        return rootView;
    }


    private void init(){
        this.button_add_note.setOnClickListener(this.mainActivityControl);
        this.button_sort_by_date.setOnClickListener(this.mainActivityControl);
        this.executorService.execute(()->{
            this.notes =  appDatabase.notesDao().getAllNotes();
            number_pages.setText(String.valueOf(appDatabase.notesDao().getNbNote()).concat(" notes"));
            loadNotes(this.notes);
        });

    }

    @Override
    public void loadNbNotes(int nb) {
        number_pages.setText(String.valueOf(nb));
    }

    @Override
    public List<Notes> getNotes() {
        return this.notes;
    }

    @Override
    public void loadNotes(List<Notes> notes) {
        this.notes = notes;
        if (this.cardNoteAddapter == null) {
            this.cardNoteAddapter = new CardNoteAddapter(getContext(), notes);
            this.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            this.recyclerView.setHasFixedSize(false);
            this.recyclerView.setAdapter(this.cardNoteAddapter);
        } else {
            this.cardNoteAddapter.setNotesList(notes);
        }

    }






}
