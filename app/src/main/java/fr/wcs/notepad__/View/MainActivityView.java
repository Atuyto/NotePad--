package fr.wcs.notepad__.View;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import fr.wcs.notepad__.Controler.MainActivity;
import fr.wcs.notepad__.Controler.MainActivityControl;
import fr.wcs.notepad__.R;

public class MainActivityView extends AppCompatActivity{

    private EditText search_bar;

    private TextView number_pages;

    private Button button_sort_by_date;

    private ImageButton burger_menu_button, favorite_button, button_add_note;

    private MainActivity mainActivity;

    private AppBarConfiguration appBarConfiguration;

    public  MainActivityView(MainActivity mainActivity){
        this.mainActivity        = mainActivity;
        this.mainActivity.setContentView(R.layout.main_activity);
        this.number_pages        = this.mainActivity.findViewById(R.id.id_main_activity_notes);
        this.search_bar          = this.mainActivity.findViewById(R.id.id_main_activity_search_bar);
        this.button_sort_by_date = this.mainActivity.findViewById(R.id.id_main_activity_date);
        this.burger_menu_button  = this.mainActivity.findViewById(R.id.id_main_activity_burger_button);
        this.favorite_button     = this.mainActivity.findViewById(R.id.id_main_activity_favorite_button);
        this.button_add_note     = this.mainActivity.findViewById(R.id.id_main_activity_add_notes);
        this.init();
    }

    private void init(){
        this.button_add_note.setOnClickListener(new MainActivityControl());


//        burger_menu_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        DrawerLayout drawerLayout = mainActivity.findViewById(R.id.drawer_layout);
        NavigationView navigationView = mainActivity.findViewById(R.id.navigation_view_leteral);

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_item_one, R.id.nav_item_two).setOpenableLayout(drawerLayout).build();

        NavController navController = Navigation.findNavController(mainActivity,R.id.fragement);
        NavigationUI.setupActionBarWithNavController(mainActivity, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }



}
