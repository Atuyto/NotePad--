package fr.wcs.notepad__.View;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import fr.wcs.notepad__.Controler.CardNoteAddapter;
import fr.wcs.notepad__.Controler.MainActivityControl;
import fr.wcs.notepad__.Controler.TextSearchControler;
import fr.wcs.notepad__.Manifest;
import fr.wcs.notepad__.Model.BDD.AppDatabase;
import fr.wcs.notepad__.Model.Notes;
import fr.wcs.notepad__.Model.Observable;
import fr.wcs.notepad__.Model.Observer;
import fr.wcs.notepad__.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainActivityView extends AppCompatActivity implements Observer{

    private EditText search_bar;

    private TextView number_pages;

    private FrameLayout button_sort_by_date;

    private ImageView arrow_date;

    private ImageButton burger_menu_button, favorite_button, button_add_note, nav_button_trash;

    private DrawerLayout drawerLayout;

    private Button nav_button_cancel;

    private ScrollView scrollView;

    private AppDatabase appDatabase;
    private RecyclerView recyclerView;
    private CardNoteAddapter cardNoteAddapter;
    private MainActivityControl mainActivityControl;
    private ExecutorService executorService;
    private List<Notes> notes;
    private List<Notes> notesSelected;

    private ConstraintLayout nav_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.main_activity);
        this.number_pages = this.findViewById(R.id.id_main_activity_notes);
        this.recyclerView = this.findViewById(R.id.id_main_activity_recyclerView);
        this.search_bar = this.findViewById(R.id.id_main_activity_search_bar);
        this.button_sort_by_date = this.findViewById(R.id.id_main_activity_date);
        this.burger_menu_button = this.findViewById(R.id.id_main_activity_burger_button);
        this.favorite_button = this.findViewById(R.id.id_main_activity_favorite_button);
        this.button_add_note = this.findViewById(R.id.id_main_activity_add_notes);
        this.burger_menu_button = this.findViewById(R.id.id_main_activity_burger_button);
        this.search_bar         = findViewById(R.id.id_main_activity_search_bar);
        this.favorite_button    = findViewById(R.id.id_main_activity_favorite_button);
        this.drawerLayout = this.findViewById(R.id.drawer2);
        this.executorService = Executors.newSingleThreadExecutor();
        this.arrow_date = findViewById(R.id.id_main_activity_add_date_arrow);
        this.appDatabase = AppDatabase.getInstance(this.getApplicationContext());
        this.mainActivityControl = new MainActivityControl(new DrawerManager(this.drawerLayout));
        this.nav_bar = findViewById(R.id.id_main_activity_nav_bar);
        this.nav_button_cancel = findViewById(R.id.id_bottom_nav_bar_cancel);
        this.nav_button_trash = findViewById(R.id.id_bottom_nav_bar_delete);
        this.scrollView = findViewById(R.id.id_main_activity_add_scroll_view);

        this.init();
    }

    private void init(){
        Observable.setObservers(this);
        this.notesSelected = new ArrayList<>();
        this.button_add_note.setOnClickListener(this.mainActivityControl);
        this.button_sort_by_date.setOnClickListener(this.mainActivityControl);
        this.burger_menu_button.setOnClickListener(this.mainActivityControl);
        this.button_sort_by_date.setOnClickListener(this.mainActivityControl);
        this.search_bar.addTextChangedListener(new TextSearchControler(this));
        this.favorite_button.setOnClickListener(this.mainActivityControl);
        this.nav_button_trash.setOnClickListener(this.mainActivityControl);
        this.nav_button_cancel.setOnClickListener(this.mainActivityControl);



    }


    @Override
    public void loadNbNotes(int nb) {
        number_pages.setText(nb >1 ? String.valueOf(nb).concat(" notes") : String.valueOf(nb).concat(" note") );
    }

    @Override
    public List<Notes> getNotes() {
        return this.notes;
    }

    @Override
    public void setFavorit(boolean favorit) {
        if(favorit){
            this.favorite_button.setColorFilter(Color.rgb(255, 220, 36));
        }else {
            this.favorite_button.setColorFilter(Color.rgb(217, 217, 217));
        }

    }

    @Override
    public void sotedByDate(boolean isSorted) {
        if(isSorted){
            this.arrow_date.setRotation(180F);
        }
        else {
            this.arrow_date.setRotation(0F);
        }

    }

    @Override
    public void loadNotes(List<Notes> notes) {
        this.notes = notes;
        if (this.cardNoteAddapter == null) {
            this.cardNoteAddapter = new CardNoteAddapter(this.getApplicationContext(), notes);
            this.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            this.recyclerView.setHasFixedSize(false);
            this.recyclerView.setAdapter(this.cardNoteAddapter);
        } else {
            this.cardNoteAddapter.setNotesList(notes);
        }

    }

    @Override
    public void onNotesSelected() {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) this.scrollView.getLayoutParams();
        params.setMargins(params.leftMargin, params.topMargin, params.rightMargin, 100);
        this.scrollView.setLayoutParams(params);
        this.cardNoteAddapter.setEditable(true);
        this.button_add_note.setVisibility(View.GONE);
        this.nav_bar.setVisibility(View.VISIBLE);

    }

    @Override
    public void close() {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) this.scrollView.getLayoutParams();
        params.setMargins(params.leftMargin, params.topMargin, params.rightMargin, 0);
        this.scrollView.setLayoutParams(params);
        this.button_add_note.setVisibility(View.VISIBLE);
        this.nav_bar.setVisibility(View.GONE);
        this.cardNoteAddapter.setEditable(false);
        this.notesSelected.clear();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.executorService.execute(()->{
            this.notes =  appDatabase.notesDao().getAllNotes();
            runOnUiThread(()->{
                number_pages.setText(this.notes.size() >1 ? String.valueOf(this.notes.size()).concat(" notes") :
                        String.valueOf(this.notes.size()).concat(" note"));
                loadNotes(this.notes);
            });
        });
    }

    @Override
    public void addNotesInSelection(Notes notes) {
        this.notesSelected.add(notes);
    }

    @Override
    public void notesUnSelected(Notes notes) {
        this.notesSelected.remove(notes);
    }

    @Override
    public List<Notes> getNotesSelected() {
        return this.notesSelected;
    }


}
