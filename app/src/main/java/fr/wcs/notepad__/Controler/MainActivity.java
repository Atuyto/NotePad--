package fr.wcs.notepad__.Controler;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import fr.wcs.notepad__.Model.BDD.AppDatabase;
import fr.wcs.notepad__.Model.Fragment.MainFragment;
import fr.wcs.notepad__.Model.Observable;
import fr.wcs.notepad__.R;
import fr.wcs.notepad__.View.MainActivityView;
import fr.wcs.notepad__.databinding.FragmentMainBinding;
import fr.wcs.notepad__.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity {

    private AppDatabase appDatabase;

    private AppBarConfiguration appBarConfiguration;

    private FragmentMainBinding binding;

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ImageButton imageButton = findViewById(R.id.id_main_activity_burger_button);
        drawerLayout = findViewById(R.id.drawer2);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ouvrir le menu latéral
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });



        /*
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_main);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view_leteral);

        appBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_item_one,R.id.nav_item_two)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        if (savedInstanceState == null) {
            MainActivityView mainActivityView = new MainActivityView();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, mainActivityView)
                    .commit();
            Observable.setObservers(mainActivityView);
        }
        */

    }
/*
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }*/

}
