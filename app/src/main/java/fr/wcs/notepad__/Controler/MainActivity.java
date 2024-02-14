package fr.wcs.notepad__.Controler;

import android.os.Bundle;
import android.view.Window;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import fr.wcs.notepad__.R;
import fr.wcs.notepad__.View.MainActivityView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        new MainActivityView(this);
    }
}
