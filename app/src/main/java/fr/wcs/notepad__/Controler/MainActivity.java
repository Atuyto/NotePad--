package fr.wcs.notepad__.Controler;

import android.os.Bundle;
import android.view.Window;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.room.Room;
import fr.wcs.notepad__.Model.BDD.AppDatabase;
import fr.wcs.notepad__.R;
import fr.wcs.notepad__.View.MainActivityView;

public class MainActivity extends AppCompatActivity {

    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        new MainActivityView(this);


    }

}
