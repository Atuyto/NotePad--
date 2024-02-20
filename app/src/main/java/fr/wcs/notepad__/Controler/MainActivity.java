package fr.wcs.notepad__.Controler;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.drawerlayout.widget.DrawerLayout;
import fr.wcs.notepad__.Model.BDD.AppDatabase;
import fr.wcs.notepad__.Model.Observable;
import fr.wcs.notepad__.R;
import fr.wcs.notepad__.View.MainActivityView;


public class MainActivity extends AppCompatActivity {

    private AppDatabase appDatabase;


    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, MainActivityView.class));



    }

}
