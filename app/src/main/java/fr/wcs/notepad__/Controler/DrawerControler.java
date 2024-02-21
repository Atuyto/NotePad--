package fr.wcs.notepad__.Controler;

import android.view.View;
import fr.wcs.notepad__.R;
import fr.wcs.notepad__.View.DrawerManager;

public class DrawerControler implements View.OnClickListener {

    private DrawerManager drawerManager;
    public DrawerControler(DrawerManager drawerManager) {
        this.drawerManager = drawerManager;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.id_menu_back){
            this.drawerManager.closeDrawer();
        }
        if (v.getId() == R.id.nav_item_trash){
            System.out.println("trash");
        }
    }
}
