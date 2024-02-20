package fr.wcs.notepad__.View;

import android.content.Context;
import android.view.View;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import fr.wcs.notepad__.Controler.DrawerControler;
import fr.wcs.notepad__.R;

public class DrawerManager {

    private final DrawerLayout drawerLayout;
    private DrawerControler drawerControler;
    public DrawerManager(DrawerLayout drawer) {
        this.drawerLayout = drawer;
        this.drawerControler = new DrawerControler(this);
    }

    public void openDrawer() {
        this.drawerLayout.openDrawer(GravityCompat.START);

        // set les listener ici
        this.drawerLayout.findViewById(R.id.id_menu_back).setOnClickListener(this.drawerControler);
    }

    public void closeDrawer(){
        this.drawerLayout.closeDrawer(GravityCompat.START);
    }



}