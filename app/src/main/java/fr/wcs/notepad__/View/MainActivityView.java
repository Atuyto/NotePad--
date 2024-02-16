package fr.wcs.notepad__.View;

import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
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

public class MainActivityView implements Observer {

    private EditText search_bar;

    private TextView number_pages;

    private Button button_sort_by_date;

    private ImageButton burger_menu_button, favorite_button, button_add_note;

    private MainActivity mainActivity;

    private AppDatabase appDatabase;
    private RecyclerView recyclerView;
    private CardNoteAddapter cardNoteAddapter;
    private MainActivityControl mainActivityControl;
    private ExecutorService executorService;
    private List<Notes> notes;
    public  MainActivityView(MainActivity mainActivity){
        this.executorService     = Executors.newSingleThreadExecutor();
        this.appDatabase         = AppDatabase.getInstance(mainActivity);
        this.mainActivity        = mainActivity;
        this.mainActivityControl = new MainActivityControl(this.cardNoteAddapter, this.mainActivity);
        this.mainActivity.setContentView(R.layout.main_activity);
        this.number_pages        = this.mainActivity.findViewById(R.id.id_main_activity_notes);
        this.recyclerView        = this.mainActivity.findViewById(R.id.id_main_activity_recyclerView);
        this.search_bar          = this.mainActivity.findViewById(R.id.id_main_activity_search_bar);
        this.button_sort_by_date = this.mainActivity.findViewById(R.id.id_main_activity_date);
        this.burger_menu_button  = this.mainActivity.findViewById(R.id.id_main_activity_burger_button);
        this.favorite_button     = this.mainActivity.findViewById(R.id.id_main_activity_favorite_button);
        this.button_add_note     = this.mainActivity.findViewById(R.id.id_main_activity_add_notes);
        this.init();
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
        cardNoteAddapter = new CardNoteAddapter(mainActivity, notes);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(cardNoteAddapter);
    }






}
