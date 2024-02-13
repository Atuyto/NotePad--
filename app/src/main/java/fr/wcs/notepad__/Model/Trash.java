package fr.wcs.notepad__.Model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Trash {

    @PrimaryKey(autoGenerate = true)
    private long trashId;

    public Trash() {

    }

    public long getTrashId() {
        return trashId;
    }

    public void setTrashId(long trashId) {
        this.trashId = trashId;
    }
}
