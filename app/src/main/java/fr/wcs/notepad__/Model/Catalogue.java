package fr.wcs.notepad__.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "catalogue")
public class Catalogue {
    @PrimaryKey(autoGenerate = true)
    public long catalogueId;

    public Catalogue() {
    }

    public long getCatalogueId() {
        return catalogueId;
    }

    public Catalogue setCatalogueId(long catalogueId) {
        this.catalogueId = catalogueId;
        return this;
    }

}
