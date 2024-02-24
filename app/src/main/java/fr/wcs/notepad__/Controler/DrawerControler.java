package fr.wcs.notepad__.Controler;

import android.content.Intent;
import android.view.View;
import fr.wcs.notepad__.R;
import fr.wcs.notepad__.View.DrawerManager;
import fr.wcs.notepad__.View.TrashActivity;

public class DrawerControler implements View.OnClickListener {

    private final DrawerManager drawerManager;
    public DrawerControler(DrawerManager drawerManager) {
        this.drawerManager = drawerManager;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.id_menu_back){
            this.drawerManager.closeDrawer();
        }
        if (v.getId() == R.id.nav_item_trash){
            v.getContext().startActivity(new Intent(v.getContext(), TrashActivity.class));
            System.out.println("trash");
        }
    }
}
