package fr.wcs.notepad__.Controler;

import android.view.View;
import fr.wcs.notepad__.View.DrawerManager;

public class DrawerControler implements View.OnClickListener {

    private DrawerManager drawerManager;
    public DrawerControler(DrawerManager drawerManager) {
        this.drawerManager = drawerManager;
    }

    @Override
    public void onClick(View v) {
        this.drawerManager.closeDrawer();
    }
}
