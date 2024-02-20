package fr.wcs.notepad__.Model.Fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import fr.wcs.notepad__.Model.Observable;
import fr.wcs.notepad__.R;
import fr.wcs.notepad__.View.MainActivityView;

public class MainFragment extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        if (savedInstanceState == null) {
            MainActivityView mainActivityView = new MainActivityView();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, mainActivityView)
                    .commit();
            Observable.setObservers(mainActivityView);
        }
    }
}
