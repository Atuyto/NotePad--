package fr.wcs.notepad__.View;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RotateDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.google.android.material.navigation.NavigationView;
import fr.wcs.notepad__.Controler.CardNoteAddapter;
import fr.wcs.notepad__.Controler.MainActivity;
import fr.wcs.notepad__.Controler.MainActivityControl;
import fr.wcs.notepad__.Controler.TextSearchControler;
import fr.wcs.notepad__.Model.BDD.AppDatabase;
import fr.wcs.notepad__.Model.Notes;
import fr.wcs.notepad__.Model.Observable;
import fr.wcs.notepad__.Model.Observer;
import fr.wcs.notepad__.R;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivityView extends AppCompatActivity implements Observer {

    private EditText search_bar;

    private TextView number_pages;

    private FrameLayout button_sort_by_date;

    private ImageView arrow_date;

    private ImageButton burger_menu_button, favorite_button, button_add_note;

    private DrawerLayout drawerLayout;

    private AppDatabase appDatabase;
    private RecyclerView recyclerView;
    private CardNoteAddapter cardNoteAddapter;
    private MainActivityControl mainActivityControl;
    private ExecutorService executorService;
    private List<Notes> notes;

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

        this.init(); // Appeler la méthode d'initialisation
    }

    private void init(){
        Observable.setObservers(this);
        this.button_add_note.setOnClickListener(this.mainActivityControl);
        this.button_sort_by_date.setOnClickListener(this.mainActivityControl);
        this.burger_menu_button.setOnClickListener(this.mainActivityControl);
        this.button_sort_by_date.setOnClickListener(this.mainActivityControl);
        this.search_bar.addTextChangedListener(new TextSearchControler(this));
        this.favorite_button.setOnClickListener(this.mainActivityControl);

        this.executorService.execute(()->{
            this.notes =  appDatabase.notesDao().getAllNotes();
            number_pages.setText(String.valueOf(appDatabase.notesDao().getNbNote()).concat(" notes"));
            loadNotes(this.notes);
        });
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






}
