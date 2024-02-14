package fr.wcs.notepad__.View;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import fr.wcs.notepad__.Controler.MainActivity;
import fr.wcs.notepad__.R;

public class MainActivityView{

    private EditText search_bar;

    private TextView number_pages;

    private Button button_sort_by_date;

    private ImageButton burger_menu_button, favorite_button;

    private MainActivity mainActivity;

    public  MainActivityView(MainActivity mainActivity){
        this.mainActivity        = mainActivity;
        this.mainActivity.setContentView(R.layout.main_activity);
        this.number_pages        = this.mainActivity.findViewById(R.id.id_main_activity_notes);
        this.search_bar          = this.mainActivity.findViewById(R.id.id_main_activity_search_bar);
        this.button_sort_by_date = this.mainActivity.findViewById(R.id.id_main_activity_date);
        this.burger_menu_button  = this.mainActivity.findViewById(R.id.id_main_activity_burger_button);
        this.favorite_button     = this.mainActivity.findViewById(R.id.id_main_activity_favorite_button);
    }




}
