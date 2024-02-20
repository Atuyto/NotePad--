package fr.wcs.notepad__.Controler;

import android.os.Bundle;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import fr.wcs.notepad__.Model.BDD.AppDatabase;
import fr.wcs.notepad__.Model.Observable;
import fr.wcs.notepad__.R;
import fr.wcs.notepad__.View.MainActivityView;

public class MainActivity extends AppCompatActivity {

    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_main);

        if (savedInstanceState == null) {
            MainActivityView mainActivityView = new MainActivityView();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, mainActivityView, "MainActivityViewTag")
                    .commit();
            Observable.setObservers(mainActivityView);
        } else {
            MainActivityView mainActivityView = (MainActivityView) getSupportFragmentManager().findFragmentByTag("MainActivityViewTag");
            Observable.setObservers(mainActivityView);
        }


    }
}
